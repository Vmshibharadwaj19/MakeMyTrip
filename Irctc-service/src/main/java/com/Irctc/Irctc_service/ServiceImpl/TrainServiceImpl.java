package com.Irctc.Irctc_service.ServiceImpl;

import com.Irctc.Irctc_service.Dto.TrainRequestDto;
import com.Irctc.Irctc_service.Dto.TrainResponseDto;
import com.Irctc.Irctc_service.Exception.InsufficientSeatsException;
import com.Irctc.Irctc_service.Exception.ResourceNotFoundException;
import com.Irctc.Irctc_service.Models.Train;
import com.Irctc.Irctc_service.Repo.TrainRepository;
import com.Irctc.Irctc_service.Service.TrainService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TrainServiceImpl implements TrainService {
    @Autowired
    private final TrainRepository trainRepository;
    @Override
    public TrainResponseDto create(TrainRequestDto dto) {
        Train train = new Train();
        train.setSource(dto.getSource());
        train.setDestination(dto.getDestination());
        train.setTrainName(dto.getTrainName());
        train.setNoOfSeats(dto.getSeats());
        train.setPrice(dto.getPrice());
        train.setTrainNumber(dto.getTrainNumber());

        return mapper(trainRepository.save(train));
    }

    @Override
    @Transactional(readOnly = true)
    public TrainResponseDto getTrainById(Long id) {
       Train t=trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No Train Found With The Provided Id"));
            return mapper(t);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TrainResponseDto> getAllTrains(Pageable pageable) {
        Page<Train> trains=trainRepository.findAll(pageable);
        return trains.map(this::mapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrainResponseDto> searchTrains(String source, String Destination) {
       List<Train> train= trainRepository.findBySourceAndDestination(source,Destination);
        return train.stream().map(this::mapper).toList();
    }

    @Override
    public TrainResponseDto reserve(Long id, Integer seats) {
        Train train=trainRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Train Not Found with given Id :"+ id));
        if(train.getNoOfSeats()<seats)
        {
            throw new InsufficientSeatsException("Insufficient Seats");
        }
        train.setNoOfSeats(
                train.getNoOfSeats() - seats);

        return mapper(
                trainRepository.save(train));
    }

    @Override
    public TrainResponseDto release(Long id, Integer seats) {
        Train train = trainRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("No Train Found With The Provided Id"));

        train.setNoOfSeats(
                train.getNoOfSeats() + seats
        );

        Train updatedTrain = trainRepository.save(train);

        return mapper(updatedTrain);
    }

    TrainResponseDto mapper(Train train) {

           return new TrainResponseDto(
                train.getId(),
                train.getTrainNumber(),
                train.getTrainName(),
                train.getSource(),
                train.getDestination(),
                train.getNoOfSeats(),
                train.getPrice()
        );

    }
}

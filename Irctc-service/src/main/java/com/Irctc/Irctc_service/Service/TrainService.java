package com.Irctc.Irctc_service.Service;

import com.Irctc.Irctc_service.Dto.TrainRequestDto;
import com.Irctc.Irctc_service.Dto.TrainResponseDto;
import com.Irctc.Irctc_service.Models.Train;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TrainService {

    TrainResponseDto create(TrainRequestDto dto);
    TrainResponseDto getTrainById(Long id);
    Page<TrainResponseDto>  getAllTrains(Pageable pageable);
    List<TrainResponseDto> searchTrains(String source,String Destination);
    TrainResponseDto reserve(Long id,Integer seats);


    TrainResponseDto release(Long id, Integer seats);
}

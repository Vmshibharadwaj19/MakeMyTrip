package com.Irctc.MMR_Service.ServiceImpl;



import com.Irctc.MMR_Service.Client.IrctcClient;
import com.Irctc.MMR_Service.Client.PaymentClient;
import com.Irctc.MMR_Service.Dto.*;
import com.Irctc.MMR_Service.Entitits.Booking;
import com.Irctc.MMR_Service.Repository.BookingRepository;
import com.Irctc.MMR_Service.Service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
//Booking Service Imp
@Service
@RequiredArgsConstructor

public class BookingServiceImpl
        implements BookingService {
     Logger log= LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;

    private final IrctcClient irctcClient;
    private final PaymentClient paymentClient;

    @Override
    @Transactional
    public BookingResponseDto createBooking(
            BookingRequestDto request) {
     log.debug("createBooking with request {}", request.toString());

        log.info("Booking: {}", request.toString());
        // 1. Get train from IRCTC
        TrainResponse train =
                irctcClient.getTrain(
                        request.getTrainId());

        // 2. Reserve seats in IRCTC
        irctcClient.reserveSeats(
                request.getTrainId(),
                request.getSeats());

        // 3. Calculate total amount
        BigDecimal totalAmount =
                train.price().multiply(
                        BigDecimal.valueOf(
                                request.getSeats()
                        )
                );

        // 4. Create booking
        Booking booking = new Booking();

        booking.setPassengerName(
                request.getPassengerName());

        booking.setTrainId(
                train.id());

        booking.setTrainName(
                train.trainName());

        booking.setSource(
                train.source());

        booking.setDestination(
                train.destination());

        booking.setSeats(
                request.getSeats());

        booking.setAmount(
                totalAmount);

        booking.setStatus(
                "CONFIRMED");

        // 5. Save booking in MMT DB
        Booking savedBooking =
                bookingRepository.save(booking);

        // 6. Convert Entity → DTO
        PaymentRequest paymentRequest =
                new PaymentRequest(
                        savedBooking.getId(),
                        totalAmount,
                        request.getPaymentMethod()
                );

        PaymentResponse payment =
                paymentClient.createPayment(paymentRequest);

        // 6. Process Payment
        PaymentResponse processedPayment =
                paymentClient.processPayment(
                        payment.id());

        // 7. Check payment result
        if ("Seccess".equals(
                processedPayment.status())) {

            savedBooking.setStatus(
                    "CONFIRMED");

        } else {

            savedBooking.setStatus(
                    "FAILED");
            irctcClient.releaseSeats(request.getTrainId(), request.getSeats());
        }

        // 8. Save final booking status
        Booking finalBooking =
                bookingRepository.save(
                        savedBooking);
      log.info("Booking last line");
        return mapToResponse(finalBooking);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookingResponseDto> getAllBookings(
            Pageable pageable) {

        return bookingRepository
                .findAll(pageable)
                .map(this::mapToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public BookingResponseDto getBookingById(
            Long id) {

        Booking booking =
                bookingRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Booking not found: "
                                                + id));

        return mapToResponse(booking);
    }

    private BookingResponseDto mapToResponse(
            Booking booking) {

        return new BookingResponseDto(
                booking.getId(),
                booking.getPassengerName(),
                booking.getTrainId(),
                booking.getTrainName(),
                booking.getSource(),
                booking.getDestination(),
                booking.getSeats(),
                booking.getAmount(),
                booking.getStatus()
        );
    }
}

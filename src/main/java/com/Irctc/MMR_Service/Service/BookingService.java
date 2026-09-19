package com.Irctc.MMR_Service.Service;



import com.Irctc.MMR_Service.Dto.BookingRequestDto;
import com.Irctc.MMR_Service.Dto.BookingResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookingService {

    BookingResponseDto createBooking(
            BookingRequestDto request);

    Page<BookingResponseDto> getAllBookings(
            Pageable pageable);

    BookingResponseDto getBookingById(
            Long id);
}

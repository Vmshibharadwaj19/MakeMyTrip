package com.Irctc.MMR_Service.Controller;


import com.Irctc.MMR_Service.Dto.BookingRequestDto;
import com.Irctc.MMR_Service.Dto.BookingResponseDto;
import com.Irctc.MMR_Service.Service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(
            @Valid @RequestBody BookingRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        bookingService.createBooking(request)
                );
    }

    @GetMapping
    public ResponseEntity<Page<BookingResponseDto>> getAllBookings(
            Pageable pageable) {

        return ResponseEntity.ok(
                bookingService.getAllBookings(pageable)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBookingById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                bookingService.getBookingById(id)
        );
    }
}

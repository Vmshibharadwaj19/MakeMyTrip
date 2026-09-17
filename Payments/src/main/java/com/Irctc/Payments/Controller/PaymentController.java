package com.Irctc.Payments.Controller;

import com.Irctc.Payments.Dto.PaymentRequestDto;
import com.Irctc.Payments.Dto.PaymentResponseDto;
import com.Irctc.Payments.Service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @Operation(summary = "Create Payment")
    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(
            @Valid @RequestBody PaymentRequestDto request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        paymentService.createPayment(request)
                );
    }

    @Operation(summary = "Process Payment")
    @PostMapping("/{id}/process")
    public ResponseEntity<PaymentResponseDto> processPayment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentService.processPayment(id)
        );
    }

    @Operation(summary = "Get Payment")
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDto> getPayment(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(id)
        );
    }

    @Operation(summary = "Get Payment By Booking")
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PaymentResponseDto>
    getPaymentByBooking(
            @PathVariable Long bookingId) {

        return ResponseEntity.ok(
                paymentService
                        .getPaymentByBookingId(
                                bookingId
                        )
        );
    }

    @Operation(summary = "Get Payments")
    @GetMapping
    public ResponseEntity<Page<PaymentResponseDto>>
    getPayments(Pageable pageable) {

        return ResponseEntity.ok(
                paymentService
                        .getAllPayments(pageable)
        );
    }
}
package com.Irctc.Payments.Service;


import com.Irctc.Payments.Dto.PaymentRequestDto;
import com.Irctc.Payments.Dto.PaymentResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {

    PaymentResponseDto createPayment(
            PaymentRequestDto request);

    PaymentResponseDto processPayment(
            Long paymentId);

    PaymentResponseDto getPaymentById(
            Long paymentId);

    PaymentResponseDto getPaymentByBookingId(Long bookingId);

    Page<PaymentResponseDto> getAllPayments(
            Pageable pageable);
}

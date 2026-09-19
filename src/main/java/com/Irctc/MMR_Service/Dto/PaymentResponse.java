package com.Irctc.MMR_Service.Dto;


import java.math.BigDecimal;

public record PaymentResponse(
        Long id,
        Long bookingId,
        BigDecimal amount,
        String paymentMethod,
        String status,
        String transactionId
) {
}
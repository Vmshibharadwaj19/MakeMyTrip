package com.Irctc.MMR_Service.Dto;



import java.math.BigDecimal;

public record PaymentRequest(
        Long bookingId,
        BigDecimal amount,
        String paymentMethod
) {
}
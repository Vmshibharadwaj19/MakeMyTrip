package com.Irctc.Payments.Dto;

import com.Irctc.Payments.PaymentType;
import com.Irctc.Payments.Status;

import java.math.BigDecimal;
import java.sql.Timestamp;

public record PaymentResponseDto(

        Long id,

        Long bookingId,

        BigDecimal amount,

        PaymentType paymentMethod,

        Status status,

        String transactionId,

        Timestamp createdAt

) {
}
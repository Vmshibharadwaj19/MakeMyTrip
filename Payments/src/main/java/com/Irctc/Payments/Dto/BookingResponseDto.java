package com.Irctc.Payments.Dto;

import java.math.BigDecimal;

public record BookingResponseDto(
        Long id,
        String passengerName,
        Long trainId,
        String trainName,
        String source,
        String destination,
        Integer seats,
        BigDecimal amount,
        String status
) {
}
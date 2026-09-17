package com.Irctc.Irctc_service.Dto;

import java.math.BigDecimal;


public record TrainResponseDto(
        Long id,
        String trainNumber,
        String trainName,
        String source,
        String destination,
        Integer availableSeats,
        BigDecimal price
) {
}
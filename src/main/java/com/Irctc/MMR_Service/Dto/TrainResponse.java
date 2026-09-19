package com.Irctc.MMR_Service.Dto;

import java.math.BigDecimal;

public record TrainResponse(
        Long id,
        String trainNumber,
        String trainName,
        String source,
        String destination,
        Integer availableSeats,
        BigDecimal price
) {
}
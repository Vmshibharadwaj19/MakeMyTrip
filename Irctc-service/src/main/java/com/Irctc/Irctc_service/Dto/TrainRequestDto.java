package com.Irctc.Irctc_service.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class TrainRequestDto {

    @NotBlank(message = "Train Number is Required")
    private String trainNumber;

    @NotBlank(message = "Train Name is Rrequired")
    @Size(max = 100)
    private  String trainName;

    @NotNull(message = "Available Seats is Required")
    @PositiveOrZero
    private Integer seats;

    @NotBlank(message = "Source is required")
    private String source;

    @NotBlank(message = "destination is required")
    private String destination;


    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;

}

package com.Irctc.MMR_Service.Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequestDto {

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @NotNull(message = "Train ID is required")
    private Long trainId;

    @NotNull(message = "Seats are required")
    @Min(value = 1)
    @Max(value = 6)
    private Integer seats;


    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}

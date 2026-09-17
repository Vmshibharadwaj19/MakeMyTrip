package com.Irctc.Irctc_service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiError {
    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private String exception;

}

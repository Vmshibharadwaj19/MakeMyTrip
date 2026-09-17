package com.Irctc.Irctc_service.Exception;

public class InsufficientSeatsException extends RuntimeException{

   public InsufficientSeatsException(String msg)
    {
        super(msg);
    }
}

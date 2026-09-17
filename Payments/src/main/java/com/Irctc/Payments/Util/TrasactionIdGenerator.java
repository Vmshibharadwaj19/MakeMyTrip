package com.Irctc.Payments.Util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TrasactionIdGenerator {

    public String generateTrasactionId(){

        return "TXN2026-"+ UUID.randomUUID().toString().substring(0,15);
    }



}

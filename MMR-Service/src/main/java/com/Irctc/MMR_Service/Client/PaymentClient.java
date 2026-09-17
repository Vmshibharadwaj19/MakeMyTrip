package com.Irctc.MMR_Service.Client;

import com.Irctc.MMR_Service.Dto.PaymentRequest;
import com.Irctc.MMR_Service.Dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class PaymentClient {

    private final RestTemplate restTemplate;

    @Value("${payment.service.url}")
    private String  urlPayment;

    public PaymentResponse createPayment(PaymentRequest pr)
    {
        String url= urlPayment+"/payments";

        return restTemplate.postForObject(url, pr, PaymentResponse.class);
    }

    public PaymentResponse processPayment(Long paymentId)
    {
        String url= urlPayment+"/payments"+"/"+paymentId+"/process";

        return restTemplate.postForObject(url, paymentId, PaymentResponse.class);
    }


}

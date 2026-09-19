package com.Irctc.MMR_Service.Client;

import com.Irctc.MMR_Service.Dto.TrainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class IrctcClient {

    private final RestTemplate restTemplate;

    @Value("${irctc.service.url}")
    private String irctcServiceUrl;

    public TrainResponse getTrain(Long trainId) {

        String url = irctcServiceUrl
                + "/trains/"
                + trainId;

        return restTemplate.getForObject(
                url,
                TrainResponse.class
        );
    }

    public TrainResponse reserveSeats(
            Long trainId,
            Integer seats) {

        String url = irctcServiceUrl+"/"
                + trainId
                + "/reserve?seats="
                + seats;

        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                null,
                TrainResponse.class
        ).getBody();
    }
    public TrainResponse releaseSeats(Long id,Integer seats)
    {
        String url=irctcServiceUrl+"/"+id+"/release?seats="+seats;

        return restTemplate.exchange(url,HttpMethod.PUT,null,TrainResponse.class).getBody();

    }
}
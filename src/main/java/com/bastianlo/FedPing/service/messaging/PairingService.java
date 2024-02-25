package com.bastianlo.FedPing.service.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class PairingService {
    public void startPairingProcess(String hostname){
        if (!hostname.startsWith("http")){
            hostname = "http://" + hostname;
        }
        log.info("Pairing with host {} started...", hostname);
        log.info(String.format("%s/api/pairing/receive", hostname));
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(String.format("%s/api/observer/pairing/receive", hostname));
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.POST,
                entity,
                String.class);
        log.info("Pairing Response is {}", response);
    }
}

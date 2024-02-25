package com.bastianlo.FedPing.controller;

import com.bastianlo.FedPing.model.Observer.Observer;
import com.bastianlo.FedPing.model.Observer.ObserverRepository;
import com.bastianlo.FedPing.service.messaging.PairingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/observer")
@Slf4j
public class ObserverController {

    @Autowired
    private ObserverRepository observerRepository;
    @Autowired
    private PairingService pairingService;

    @PostMapping("/")
    public Observer createObserver(@RequestBody final Observer observer) {
        return observerRepository.save(observer);
    }
    @PostMapping("/pairing/start/{hostname}")
    public void startPairing(@PathVariable("hostname") final String hostname) {
        pairingService.startPairingProcess(hostname);
    }

    @PostMapping("/pairing/receive")
    public void receivePairing(HttpServletRequest request) {
        log.info("Received pairing request from {}!", request.getRemoteAddr());
    }

    @GetMapping("/")
    public Collection<Observer> getAllObservers() {
        return observerRepository.findAll();
    }
}

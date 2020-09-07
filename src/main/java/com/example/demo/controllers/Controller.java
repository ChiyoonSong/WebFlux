package com.example.demo.controllers;

import com.example.demo.service.HttpClientTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/demo")
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private final HttpClientTest httpClientTest;

    public Controller(HttpClientTest httpClientTest) {
        this.httpClientTest = httpClientTest;
    }

    @GetMapping("/async")
    public Mono<ResponseEntity> async() {
        try {
            return httpClientTest.httpWebClientGetTest()
                    .map(data -> ResponseEntity.status(HttpStatus.OK).body(data)).cast(ResponseEntity.class)
                    .defaultIfEmpty(ResponseEntity.status(HttpStatus.NO_CONTENT).body(HttpStatus.NO_CONTENT.getReasonPhrase()));
        } catch (Exception e) {
            return (Mono<ResponseEntity>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

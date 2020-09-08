package com.example.demo.controllers;

import com.example.demo.service.HttpWebClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = "/demo")
public class Controller {
    private static final Logger logger = LogManager.getLogger(Controller.class);

    private final AtomicInteger atomicInteger = new AtomicInteger();

    private final HttpWebClient httpWebClient;

    public Controller(HttpWebClient httpWebClient) {
        this.httpWebClient = httpWebClient;
    }

    @GetMapping("/async")
    public String async() throws InterruptedException {
        Thread.sleep(3000);
        httpWebClient.getEthGasPrice();
        return "success - " + this.atomicInteger.incrementAndGet();
    }
}

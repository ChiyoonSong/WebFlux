package com.example.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * etherscan API call
 * [GET] gasPrice Value
 */
@Service
public class HttpClientTest {
    private static final Logger logger = LogManager.getLogger(HttpClientTest.class);

    private final WebClient customWebClinet;

    private final String APIKEY = "";

    public HttpClientTest(WebClient customWebClinet) {
        this.customWebClinet = customWebClinet;
    }

    public Mono<String> httpWebClientGetTest() {
        return customWebClinet.mutate() /* 기존 설정값 상속하여 사용 */
                .baseUrl("https://api.etherscan.io/api").build()
                .get()
                .uri("?module=gastracker&action=gasoracle&apikey={APIKEY}", APIKEY)
                .accept(MediaType.APPLICATION_JSON)
                /*.headers(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)*/
                /* exchange 를 이용하게 되면 Response 컨텐츠에 대한 모든 처리를 직접 하면서 발생할 수 있는 memory leak 가능성 때문에 가급적 retrieve 를 사용하기를 권고 */
                .retrieve()
                /* 응답 코드에 따라 임의 처리 또는 Exeption 랩핑 */
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError()
                        , clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new RuntimeException(body)))
                .bodyToMono(String.class);
        /**
         *      .subscribe(response -> {
         *                     logger.info(response);
         *                 }, e -> logger.error(e.getMessage()));
         *
         *
         */
    }
}

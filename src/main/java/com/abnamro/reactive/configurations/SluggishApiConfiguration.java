package com.abnamro.reactive.configurations;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class SluggishApiConfiguration {

    @Value("${sluggish-api.url}")
    private String sluggishApiUrl;

    @Value("${sluggish-api.reactorClientTimeOut}")
    private int reactorClientTimeOut;

    @Value("${sluggish-api.writeTimeOut}")
    private int writeTimeOut;

    @Value("${sluggish-api.readTimeOut}")
    private int readTimeOut;

    @Bean
    public WebClient sluggishApiWebClient() {
        WebClient.Builder webClientBuilder = WebClient.builder();
        return webClientBuilder.clientConnector(
            new ReactorClientHttpConnector(HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, reactorClientTimeOut)
                .doOnConnected(it -> it
                    .addHandlerLast(new WriteTimeoutHandler(writeTimeOut, TimeUnit.MILLISECONDS))
                    .addHandlerLast(new ReadTimeoutHandler(readTimeOut, TimeUnit.MILLISECONDS))
                )
            )
        ).baseUrl(sluggishApiUrl).build();
    }

}
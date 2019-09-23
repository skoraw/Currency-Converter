package com.skorczewski.currencyConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skorczewski.Application;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Application.class)
public class TestConfiguration {

    @Bean
    JsonResponseFileReader jsonResponseFileReader(ObjectMapper objectMapper) {
        return new JsonResponseFileReader(objectMapper);
    }
}

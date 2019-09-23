package com.skorczewski.currencyConverter.configuration;

import com.skorczewski.currencyConverter.service.CurrencyService;
import com.skorczewski.currencyConverter.service.ExchangeRateConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    CurrencyService currencyService(
            @Value("${currencyService.url}") String currencyServiceUrl,
            RestTemplate restTemplate
    ) {
        return new CurrencyService(
                currencyServiceUrl,
                restTemplate,
                new ExchangeRateConverter()
        );
    }
}

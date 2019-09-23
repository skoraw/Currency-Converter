package com.skorczewski.currencyConverter.service;

import com.skorczewski.currencyConverter.model.ExchangeRate;
import com.skorczewski.currencyConverter.model.ExchangeRateRequest;
import com.skorczewski.currencyConverter.model.ExchangeRateResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
public class CurrencyService {

    private final String currencyServiceUrl;
    private final RestTemplate restTemplate;
    private final ExchangeRateConverter exchangeRateConverter;

    public List<ExchangeRate> getExchangeRate(ExchangeRateRequest request) {
        ExchangeRateResponse response = callExternalCurrencyService(request);
        return exchangeRateConverter.converter(response);
    }

    private ExchangeRateResponse callExternalCurrencyService(ExchangeRateRequest request) {
        return restTemplate.getForObject(
                currencyServiceUrl,
                ExchangeRateResponse.class,
                request.getTable(),
                request.getCode(),
                request.getStartDate(),
                request.getEndDate()
        );
    }
}

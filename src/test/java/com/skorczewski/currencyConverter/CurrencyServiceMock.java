package com.skorczewski.currencyConverter;

import com.skorczewski.currencyConverter.model.CurrencyCode;
import com.skorczewski.currencyConverter.model.ExchangeRateResponse;
import com.skorczewski.currencyConverter.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyServiceMock {

    @Autowired
    private JsonResponseFileReader jsonResponseFileReader;

    @GetMapping("/mockCurrencyService/{table}/{code}/{startDate}/{endDate}")
    ResponseEntity<ExchangeRateResponse> getExchangeRate(
            @PathVariable Table table,
            @PathVariable CurrencyCode code,
            @PathVariable String startDate,
            @PathVariable String endDate
    ) {
        if ("2019-04-01".equals(startDate) && "2019-04-02".equals(endDate)) {
            return ResponseEntity.ok(
                    jsonResponseFileReader.getObject("mockResponse/exchangeRateResponse_2019_04_01_to_2019_04_02.json", ExchangeRateResponse.class)
            );
        }
        return ResponseEntity.ok(
                jsonResponseFileReader.getObject("mockResponse/exchangeRateResponse.json", ExchangeRateResponse.class)
        );
    }
}

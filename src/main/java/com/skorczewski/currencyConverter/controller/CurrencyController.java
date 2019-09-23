package com.skorczewski.currencyConverter.controller;

import com.skorczewski.currencyConverter.model.CurrencyCode;
import com.skorczewski.currencyConverter.model.ExchangeRate;
import com.skorczewski.currencyConverter.model.ExchangeRateRequest;
import com.skorczewski.currencyConverter.model.Table;
import com.skorczewski.currencyConverter.service.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/getExchangeRate")
    public List<ExchangeRate> getExchangeRateFromNBP(
            @RequestParam(defaultValue = "C") Table table,
            @RequestParam(defaultValue = "USD") CurrencyCode code,
            @RequestParam(defaultValue = "2019-09-01") String fromDate,
            @RequestParam(defaultValue = "2019-09-05") String toDate
    ) {
        LocalDate fromDateConverted =
                fromDate != null ? LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(fromDate))
                        : LocalDate.MIN;
        LocalDate toDateConverted =
                toDate != null ? LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(toDate))
                        : LocalDate.MAX;

        return currencyService.getExchangeRate(ExchangeRateRequest.builder()
                .code(code)
                .table(table)
                .startDate(fromDateConverted)
                .endDate(toDateConverted)
                .build()
        );
    }
}

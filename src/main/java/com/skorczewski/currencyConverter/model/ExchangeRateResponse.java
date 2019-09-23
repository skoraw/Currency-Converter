package com.skorczewski.currencyConverter.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ExchangeRateResponse {
    private Table table;
    private String currency;
    private String code;
    private List<Rate> rates;

    @Data
    public static class Rate {
        private String no;
        private LocalDate effectiveDate;
        private BigDecimal bid;
        private BigDecimal ask;
    }
}
package com.skorczewski.currencyConverter.service;

import com.skorczewski.currencyConverter.model.ExchangeRate;
import com.skorczewski.currencyConverter.model.ExchangeRateResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ExchangeRateConverter {

    List<ExchangeRate> converter(ExchangeRateResponse exchangeRateResponse) {

        List<ExchangeRateResponse.Rate> rates = exchangeRateResponse.getRates();
        List<ExchangeRate> exchangeRates = new ArrayList<>();

        for (int i = 1; i <= rates.size(); i++) {
            BigDecimal askDifference;
            BigDecimal bidDifference;
            if (i == rates.size()) {
                askDifference = BigDecimal.ZERO;
                bidDifference = BigDecimal.ZERO;
            } else {
                askDifference = rates.get(i - 1).getAsk().subtract(rates.get(i).getAsk());
                bidDifference = rates.get(i - 1).getBid().subtract(rates.get(i).getBid());
            }
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setNo(rates.get(i - 1).getNo());
            exchangeRate.setEffectiveDate(rates.get(i - 1).getEffectiveDate());
            exchangeRate.setAsk(rates.get(i - 1).getAsk());
            exchangeRate.setBid(rates.get(i - 1).getBid());
            exchangeRate.setAskDifferenceWithNextDay(askDifference);
            exchangeRate.setBidDifferenceWithNextDay(bidDifference);
            exchangeRates.add(exchangeRate);
        }
        return exchangeRates;
    }
}

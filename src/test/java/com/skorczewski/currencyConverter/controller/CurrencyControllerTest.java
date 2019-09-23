package com.skorczewski.currencyConverter.controller;

import com.skorczewski.currencyConverter.IntegrationTest;
import com.skorczewski.currencyConverter.model.ExchangeRate;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyControllerTest extends IntegrationTest {

    @Test
    public void shouldGetExchangeRatesWhenDefaultRequestValuesGiven() {
        //when
        ResponseEntity<List<ExchangeRate>> response = restTemplate.exchange(
                basePath.concat("/getExchangeRate"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        //then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(24);
    }

    @Test
    public void shouldGetExchangeRatesWhenSpecificDatesRangeGiven() {
        //given
        ExchangeRate firstExchangeRate = new ExchangeRate();
        firstExchangeRate.setNo("064/C/NBP/2019");
        firstExchangeRate.setEffectiveDate(LocalDate.of(2019, 4, 1));
        firstExchangeRate.setBid(BigDecimal.valueOf(3.7939));
        firstExchangeRate.setAsk(BigDecimal.valueOf(3.8705));
        firstExchangeRate.setBidDifferenceWithNextDay(new BigDecimal("0.0070"));
        firstExchangeRate.setAskDifferenceWithNextDay(new BigDecimal("0.0070"));

        ExchangeRate secondExchangeRate = new ExchangeRate();
        secondExchangeRate.setNo("065/C/NBP/2019");
        secondExchangeRate.setEffectiveDate(LocalDate.of(2019, 4, 2));
        secondExchangeRate.setBid(BigDecimal.valueOf(3.7869));
        secondExchangeRate.setAsk(BigDecimal.valueOf(3.8635));
        secondExchangeRate.setBidDifferenceWithNextDay(BigDecimal.valueOf(0));
        secondExchangeRate.setAskDifferenceWithNextDay(BigDecimal.valueOf(0));


        //when
        ResponseEntity<List<ExchangeRate>> response = restTemplate.exchange(
                basePath.concat("/getExchangeRate?table=C&code=USD&fromDate=2019-04-01&toDate=2019-04-02"),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );

        //then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(2);
        assertThat(response.getBody())
                .usingDefaultElementComparator()
                .hasSameElementsAs(Arrays.asList(firstExchangeRate, secondExchangeRate));
    }
}
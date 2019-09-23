package com.skorczewski.currencyConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.URL;

@AllArgsConstructor
public class JsonResponseFileReader {
    private final ObjectMapper objectMapper;

    <T> T getObject(String filePath, Class<T> objectClass) {
        try {
            return objectMapper.readValue(resource(filePath), objectClass);
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem with reading from file: " + filePath, e);
        }
    }

    private URL resource(String filePath) {
        return getClass().getClassLoader().getResource(filePath);
    }
}

package ru.itis.info.semesterwork.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Component
public class JsonParser {

    @Autowired
    private ObjectMapper objectMapper;

    public Optional<String> getPropertyFromInputStream(InputStream inputStream, String propertyName) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            JsonNode jsonNode = objectMapper.readTree(reader);
            return Optional.of(jsonNode.get(propertyName).toString());
        } catch (IOException | ClassCastException | NullPointerException e) {
            return Optional.empty();
        }
    }
}

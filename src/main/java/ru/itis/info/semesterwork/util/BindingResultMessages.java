package ru.itis.info.semesterwork.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class BindingResultMessages {

    public Optional<String> getMessageFromError(BindingResult result, String errorCode) {
       return result.getAllErrors()
                .stream().map(objectError -> Arrays.stream(Objects.requireNonNull(objectError.getCodes()))
                        .filter(error -> error.equals(errorCode)).findAny()
                       .map(error -> objectError.getDefaultMessage()))
               .findAny().orElseGet(Optional::empty);
    }
}

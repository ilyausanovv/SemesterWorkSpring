package ru.itis.info.semesterwork.converter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.info.semesterwork.entity.User;

import java.util.UUID;

@Component
public class PayloadToUserConverter implements Converter<GoogleIdToken.Payload, User> {

    @Override
    public User convert(GoogleIdToken.Payload source) {
        return User.builder()
                .email(String.valueOf(source.get("email")))
                .login(String.valueOf(source.get("name")))
                .country(String.valueOf(source.get("locale")))
                .gender("-")
                .way(User.Way.OAUTH)
                .hashPassword(UUID.randomUUID().toString())
                .role(User.Role.USER)
                .build();
    }
}

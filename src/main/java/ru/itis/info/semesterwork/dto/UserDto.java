package ru.itis.info.semesterwork.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.info.semesterwork.entity.User;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String login;
    private String country;
    private String gender;

    public static UserDto to(User user) {
        return UserDto.builder()
                .country(user.getCountry())
                .id(user.getId())
                .login(user.getLogin())
                .gender(user.getGender()).build();
    }
}

package ru.itis.info.semesterwork.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.info.semesterwork.validation.Password;

import javax.validation.constraints.Email;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserForm {

    private String login;
    @Email(message = "{sign_up_page.wrong.email}")
    private String email;
    private String country;
    private String password;
    private String gender;
}

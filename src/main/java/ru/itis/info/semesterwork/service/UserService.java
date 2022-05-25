package ru.itis.info.semesterwork.service;

import ru.itis.info.semesterwork.dto.TeamDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.SignUpUserForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserDto> getByLogin(String login);
    Optional<UserDto> getByEmail(String email);
    Optional<UserDto> authenticate(String email, String password);
    void add(SignUpUserForm entity);
    Optional<UserDto> getById(Long id);
    Optional<UserDto> update(String email, SignUpUserForm now);
    Optional<UserDto> confirmRegistration(String code);
    void delete(String email);
    List<UserDto> getUsersByTeam(String teamName);
}

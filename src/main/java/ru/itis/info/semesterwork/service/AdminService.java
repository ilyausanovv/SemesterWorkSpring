package ru.itis.info.semesterwork.service;

import ru.itis.info.semesterwork.dto.AdDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.AdForm;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<UserDto> getAllUsers();
    boolean deleteTeam(String name);
    Optional<AdDto> updateAd(Long id, AdForm adForm);
}

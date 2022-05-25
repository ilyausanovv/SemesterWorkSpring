package ru.itis.info.semesterwork.service;


import ru.itis.info.semesterwork.dto.AdDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.AdForm;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdService {

    void add(AdForm adForm);
    List<AdDto> getAllWhereStatusIsActive();
    List<AdDto> getByEmail(String email);
    void setFinished(Long id);
    List<AdDto> getByUserEmail(String name);

    Optional<AdDto> getById(Long id);

    Optional<UserDto> getUserByUserEmail(String name);
}

package ru.itis.info.semesterwork.service;


import ru.itis.info.semesterwork.dto.ResumeDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.ResumeForm;

import java.util.List;
import java.util.Optional;

public interface ResumeService {

    List<ResumeDto> getAll();
    void add(ResumeForm resume);
    List<ResumeDto> getByUserEmail(String email);

    Optional<ResumeDto> getById(Long id);

    Optional<UserDto> getUserByUserEmail(String name);
}

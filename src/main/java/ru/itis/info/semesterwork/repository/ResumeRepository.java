package ru.itis.info.semesterwork.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.info.semesterwork.entity.Resume;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    List<Resume> getAllByOwnerEmail(String login);

}

package ru.itis.info.semesterwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.info.semesterwork.entity.Ad;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> getAllByOwnerEmail(String email);

}

package ru.itis.info.semesterwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.info.semesterwork.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getByLogin(String login);

    Optional<User> getByEmail(String email);

    Optional<User> getById(Long id);

    Optional<User> findByConfirmCode(String code);

    @Query("select team.participant from Team team where team.name = :name")
    List<User> getUsersByTeamName(@Param("name") String name);
}

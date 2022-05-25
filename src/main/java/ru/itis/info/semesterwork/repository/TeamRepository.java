package ru.itis.info.semesterwork.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.info.semesterwork.entity.Team;
import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> getTeamByCreatorEmail(String email);

    @Query("select user.team from User user where user.email = :email")
    List<Team> getTeamByParticipantEmail(@Param("email") String email);

    @Query("select user.team from User user where user.login = :login")
    List<Team> getTeamByParticipantLogin(@Param("login") String login);

    Optional<Team> findByName(String name);
}

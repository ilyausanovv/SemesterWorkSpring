package ru.itis.info.semesterwork.service;

import ru.itis.info.semesterwork.dto.TeamDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.TeamForm;
import java.util.List;
import java.util.Optional;

public interface TeamService {
    void createTeam(TeamForm teamForm);

    List<TeamDto> getTeamsByCreatorEmail(String email);

    List<TeamDto> getTeamsByParticipant(String name);

    Optional<UserDto> getCreator(String email);

    Optional<TeamDto> getTeamByName(String name);

    List<TeamDto> getAll();

    List<TeamDto> getTeamsByUser(String login);
}

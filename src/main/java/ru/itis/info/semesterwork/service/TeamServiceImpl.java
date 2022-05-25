package ru.itis.info.semesterwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.info.semesterwork.dto.TeamDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.entity.Team;
import ru.itis.info.semesterwork.entity.User;
import ru.itis.info.semesterwork.form.TeamForm;
import ru.itis.info.semesterwork.repository.TeamRepository;
import ru.itis.info.semesterwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    private final Class<Team> targetType = Team.class;

    @Override
    public void createTeam(TeamForm teamForm) {
        Team team = conversionService.convert(teamForm, targetType);
        User user = userRepository.getByLogin(teamForm.getCreatorLogin())
                .orElseThrow(() -> new UsernameNotFoundException("user with login " + teamForm.getCreatorLogin() + " isn't found"));
        assert team != null;
        team.setCreator(user);
        teamRepository.save(team);
    }

    @Override
    public List<TeamDto> getTeamsByCreatorEmail(String email) {
        return teamRepository.getTeamByCreatorEmail(email)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamsByParticipant(String email) {
        return teamRepository.getTeamByParticipantEmail(email)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getCreator(String email) {
        return userRepository.getByEmail(email).map(UserDto::to);
    }

    @Override
    public Optional<TeamDto> getTeamByName(String name) {
        return teamRepository.findByName(name).map(team -> {
            TeamDto teamDto = TeamDto.to(team);
            teamDto.setParticipant(team.getParticipant().stream().map(UserDto::to).collect(Collectors.toList()));
            return teamDto;
        });
    }

    @Override
    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream().map(TeamDto::to).collect(Collectors.toList());
    }

    @Override
    public List<TeamDto> getTeamsByUser(String login) {
        return teamRepository.getTeamByParticipantLogin(login)
                .stream().map(TeamDto::to).collect(Collectors.toList());
    }

}

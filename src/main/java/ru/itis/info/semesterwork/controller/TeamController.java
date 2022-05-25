package ru.itis.info.semesterwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.info.semesterwork.dto.TeamDto;
import ru.itis.info.semesterwork.form.TeamForm;
import ru.itis.info.semesterwork.service.TeamService;

import java.security.Principal;
import java.util.Optional;

@Controller
@PreAuthorize("isAuthenticated()")
public class TeamController {

    @Autowired
    private TeamService teamService;


    @PreAuthorize("permitAll()")
    @GetMapping("/teams")
    private String getTeams(Model model) {
        model.addAttribute("teams", teamService.getAll());
        return "list_team";
    }

    @GetMapping("/team/own")
    public String getTeamsAsCreator(Model model, Principal principal) {
        model.addAttribute("teams", teamService.getTeamsByCreatorEmail(principal.getName()));
        return "list_my_teams";
    }

    @GetMapping("/teams/participant")
    public String getTeamsAsParticipant(Model model, Principal principal) {
        model.addAttribute("user", teamService.getTeamsByParticipant(principal.getName()));
        return "list_part_teams";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/teams/{teamName}")
    public String getTeam(Model model, @PathVariable("teamName") String teamName) {
        Optional<TeamDto> teamDto = teamService.getTeamByName(teamName);
        if (teamDto.isPresent()) {
            model.addAttribute("team", teamDto.get());
            return "page_team";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "team isn't found");
    }

    @GetMapping("/team/create")
    public String getPageForCreatingTeam(Principal principal, Model model) {
        model.addAttribute("creator", teamService.getCreator(principal.getName()).get());
        return "create_team";
    }

    @PostMapping("/team/create")
    public String createTeam(TeamForm teamForm) {
        teamService.createTeam(teamForm);
        return "redirect:/teams/own";
    }

    @GetMapping("/users/{login}/teams/")
    public String getTeamsByUser(Model model, @PathVariable("login") String login) {
        model.addAttribute("teams", teamService.getTeamsByUser(login));
        return "list_team";
    }
}

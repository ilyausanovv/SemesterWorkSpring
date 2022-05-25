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
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.SignUpUserForm;
import ru.itis.info.semesterwork.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getMyPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getByEmail(principal.getName()).get());
        return "page_profile";
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/users/{name}")
    public String getUserPage(Model model, @PathVariable("name") String name) {
        Optional<UserDto> userDto = userService.getByLogin(name);
        if (userDto.isPresent()) {
            model.addAttribute("user", userDto.get());
            return "page_user";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user isn't found");
    }

    @PostMapping("/profile/delete")
    public String deleteProfile(Principal principal) {
        userService.delete(principal.getName());
        return "redirect:/logout";
    }

    @GetMapping("/profile/update")
    public String getUpdatePage(Model model) {
        model.addAttribute("userForm", new SignUpUserForm());
        return "page_profile_update";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Principal principal, SignUpUserForm userForm) {
        userService.update(principal.getName(), userForm);
        return "redirect:/logout";
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/teams/{teamName}/participant")
    public String getUsersByTeam(Model model, @PathVariable("teamName") String teamName) {
        model.addAttribute("participants", userService.getUsersByTeam(teamName));
        return "list_part_teams";
    }

}

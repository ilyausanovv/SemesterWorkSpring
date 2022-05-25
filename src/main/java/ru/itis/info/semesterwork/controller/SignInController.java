package ru.itis.info.semesterwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.service.UserService;

import java.util.Optional;

@Controller
@PreAuthorize("permitAll()")
@RequestMapping(value = "/signIn")
public class SignInController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String SignInPage() {
        return "sign_in_page";
    }

    @PostMapping
    public String getUser(String email, String password) {
        Optional<UserDto> userDto = userService.authenticate(email, password);
        if (userDto.isPresent()) {
            return "redirect:/profile";
        }
        return "sign_in_page";
    }
}

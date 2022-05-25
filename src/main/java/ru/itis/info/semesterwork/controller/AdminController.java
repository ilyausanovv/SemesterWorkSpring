package ru.itis.info.semesterwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.info.semesterwork.dto.AdDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.form.AdForm;
import ru.itis.info.semesterwork.service.AdminService;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }

    @DeleteMapping("/team/delete/{name}")
    public ResponseEntity<?> deleteTeam(@PathVariable("name") String name) {
        return  adminService.deleteTeam(name) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/ad/update/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Long id,@RequestBody AdForm adForm) {
        return adminService.updateAd(id, adForm).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

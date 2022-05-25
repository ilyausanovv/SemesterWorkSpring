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
import ru.itis.info.semesterwork.dto.ResumeDto;
import ru.itis.info.semesterwork.form.ResumeForm;
import ru.itis.info.semesterwork.service.ResumeService;

import java.security.Principal;
import java.util.Optional;

@PreAuthorize("isAuthenticated()")
@Controller
public class ResumeController {

	@Autowired
	private ResumeService resumeService;

	private final int MAX_ELEMENT = 12;

	@GetMapping("/resume/create")
	public String getPageForCreate(Model model, Principal principal) {
		model.addAttribute("user", resumeService.getUserByUserEmail(principal.getName()).get());
		return "create_resume";
	}

	@PostMapping("/resume/create")
	public String createResume(ResumeForm resumeForm) {
		resumeService.add(resumeForm);
		return "redirect:/resumes";
	}

	@GetMapping("/resume/own")
	public String getMyAd(Model model, Principal principal) {
		model.addAttribute("resumes", resumeService.getByUserEmail(principal.getName()));
		return "list_resume";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/resumes")
	public String getResumes(Model model, Principal principal) {
		model.addAttribute("user", resumeService.getUserByUserEmail(principal.getName()).get());
		model.addAttribute("resumes", resumeService.getAll());
		return "list_resume";
	}

	@PreAuthorize("permitAll()")
	@GetMapping("/resumes/{resumeId}")
	public String getAdPage(@PathVariable("resumeId") Long id, Model model) {
		Optional<ResumeDto> resumeDto = resumeService.getById(id);
		if (resumeDto.isPresent()) {
			model.addAttribute("resume", resumeDto.get());
			return "page_resume";
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ad isn't found");
	}
}

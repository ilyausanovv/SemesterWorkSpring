package ru.itis.info.semesterwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.info.semesterwork.dto.ResumeDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.entity.Resume;
import ru.itis.info.semesterwork.entity.User;
import ru.itis.info.semesterwork.form.ResumeForm;
import ru.itis.info.semesterwork.repository.ResumeRepository;
import ru.itis.info.semesterwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserRepository userRepository;

    private final Class<Resume> targetType = Resume.class;

    private final int MAX_PAGE_ELEMENT = 12;

    @Override
    public List<ResumeDto> getAll() {
        return resumeRepository.findAll().stream()
                .map(ResumeDto::to).collect(Collectors.toList());
    }

    @Override
    public void add(ResumeForm resumeForm) {
        Resume resume = conversionService.convert(resumeForm, targetType);
        User user = userRepository.getByLogin(resumeForm.getCreatorLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User isn't found with login" + resumeForm.getCreatorLogin()));
        assert resume != null;
        resume.setOwner(user);
        resumeRepository.save(resume);
    }

    @Override
    public List<ResumeDto> getByUserEmail(String login) {
        return resumeRepository.getAllByOwnerEmail(login).stream()
                .map(ResumeDto::to).collect(Collectors.toList());
    }

    @Override
    public Optional<ResumeDto> getById(Long id) {
        return resumeRepository.findById(id).map(ResumeDto::to);
    }

    @Override
    public Optional<UserDto> getUserByUserEmail(String name) {
        return userRepository.getByEmail(name).map(UserDto::to);
    }

}

package ru.itis.info.semesterwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.itis.info.semesterwork.dto.AdDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.entity.Ad;
import ru.itis.info.semesterwork.form.AdForm;
import ru.itis.info.semesterwork.repository.AdRepository;
import ru.itis.info.semesterwork.repository.TeamRepository;
import ru.itis.info.semesterwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AdRepository adRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDto::to).collect(Collectors.toList());
    }

    @Override
    public boolean deleteTeam(String name) {
        return teamRepository.findByName(name).map(team -> {
            teamRepository.delete(team);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<AdDto> updateAd(Long id, AdForm adForm) {
        return adRepository.findById(id).map(ad -> {
            Ad newAd = conversionService.convert(adForm, Ad.class);
            adRepository.delete(ad);
            assert newAd != null;
            newAd.setId(ad.getId());
            adRepository.save(newAd);
            return AdDto.to(ad);
        });
    }
}

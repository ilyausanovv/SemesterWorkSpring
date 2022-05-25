package ru.itis.info.semesterwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.info.semesterwork.dto.AdDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.entity.Ad;
import ru.itis.info.semesterwork.entity.User;
import ru.itis.info.semesterwork.form.AdForm;
import ru.itis.info.semesterwork.repository.AdRepository;
import ru.itis.info.semesterwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserRepository userRepository;

    private final Class<Ad> targetType = Ad.class;


    @Override
    public void add(AdForm adForm) {
        Ad ad = conversionService.convert(adForm, targetType);
        User user = userRepository.getByLogin(adForm.getCreatorLogin())
                .orElseThrow(() -> new UsernameNotFoundException("User isn't found"));
        assert ad != null;
        ad.setOwner(user);
        adRepository.save(ad);
    }

    @Override
    public List<AdDto> getAllWhereStatusIsActive() {
        return adRepository.findAll().stream()
                .map(AdDto::to).collect(Collectors.toList());
    }

    @Override
    public List<AdDto> getByEmail(String email) {
        return adRepository.getAllByOwnerEmail(email).stream()
                .map(AdDto::to).collect(Collectors.toList());
    }

    @Override
    public void setFinished(Long id) {
        adRepository.findById(id).ifPresent(ad -> {
            adRepository.delete(ad);
            ad.setStatus(Ad.Status.FINISHED);
            adRepository.save(ad);
        });
    }

    @Override
    public List<AdDto> getByUserEmail(String name) {
        return adRepository.getAllByOwnerEmail(name).stream().map(AdDto::to).collect(Collectors.toList());
    }

    @Override
    public Optional<AdDto> getById(Long id) {
        return adRepository.findById(id).map(AdDto::to);
    }

    @Override
    public Optional<UserDto> getUserByUserEmail(String name) {
        return userRepository.getByEmail(name).map(UserDto::to);
    }
}

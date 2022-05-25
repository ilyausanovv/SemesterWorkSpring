package ru.itis.info.semesterwork.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.info.semesterwork.dto.TeamDto;
import ru.itis.info.semesterwork.dto.UserDto;
import ru.itis.info.semesterwork.entity.User;
import ru.itis.info.semesterwork.form.SignUpUserForm;
import ru.itis.info.semesterwork.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value(value = "${spring.mail.username}")
    private String userName;

    @Value(value = "${server.url}")
    private String serverUrl;

    private final Class<User> targetType = User.class;

    @Autowired
    private ConversionService conversionService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDto> getByLogin(String login) {
        return userRepository.getByLogin(login).map(UserDto::to);
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        return userRepository.getByEmail(email).map(UserDto::to);
    }

    @Override
    public Optional<UserDto> authenticate(String email, String password) {
        Optional<User> user = userRepository.getByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getHashPassword())) {
            return user.map(UserDto::to);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> update(String email, SignUpUserForm now) {
        Optional<User> user = userRepository.getByEmail(email);
        if (user.isPresent()) {
            User nowUser = conversionService.convert(now, targetType);
            assert nowUser != null;
            nowUser.setHashPassword(passwordEncoder.encode(now.getPassword()));
            userRepository.delete(user.get());
            userRepository.save(nowUser);
            return Optional.of(UserDto.to(nowUser));
        }
        else throw new UsernameNotFoundException("User isn't found");
    }

    @Override
    public Optional<UserDto> confirmRegistration(String code) {
        Optional<User> opUser = userRepository.findByConfirmCode(code);
        if (opUser.isPresent()) {
            User user = opUser.get();
            userRepository.delete(user);
            opUser.get().setConfirmed(true);
            userRepository.save(user);
            return Optional.of(UserDto.to(opUser.get()));
        }
        return Optional.empty();
    }

    @Override
    public void delete(String email) {
        userRepository.getByEmail(email).ifPresent(userRepository::delete);
    }


    @Override
    public void add(SignUpUserForm entity) {
        User user = conversionService.convert(entity, targetType);
        assert user != null;
        user.setConfirmCode(UUID.randomUUID().toString());
        user.setRole(User.Role.USER);
        user.setWay(User.Way.REGISTRATION);
        user.setHashPassword(passwordEncoder.encode(entity.getPassword()));
        user.setConfirmed(true);
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> getById(Long id) {
        return userRepository.getById(id).map(UserDto::to);
    }

    @Override
    public List<UserDto> getUsersByTeam(String teamName) {
        return userRepository.getUsersByTeamName(teamName)
                .stream().map(UserDto::to).collect(Collectors.toList());
    }
}

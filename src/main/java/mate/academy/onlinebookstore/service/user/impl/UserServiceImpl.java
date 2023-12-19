package mate.academy.onlinebookstore.service.user.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.user.UserDto;
import mate.academy.onlinebookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore.mapper.UserMapper;
import mate.academy.onlinebookstore.model.User;
import mate.academy.onlinebookstore.repository.user.UserRepository;
import mate.academy.onlinebookstore.service.user.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    @Override
    public UserDto registerUser(UserRegistrationRequestDto requestDto) {
        User user = userMapper.toModel(requestDto);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}

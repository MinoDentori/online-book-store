package mate.academy.onlinebookstore.service.user;

import mate.academy.onlinebookstore.dto.user.UserDto;
import mate.academy.onlinebookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore.exception.RegistrationException;

public interface UserService {
    UserDto registerUser(UserRegistrationRequestDto requestDto) throws RegistrationException;
}

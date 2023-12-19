package mate.academy.onlinebookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.user.UserDto;
import mate.academy.onlinebookstore.dto.user.UserRegistrationRequestDto;
import mate.academy.onlinebookstore.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth/registration")
public class AuthenticationController {
private final UserService userService;
    @PostMapping
    @Operation(summary = "Register a new user",
            description = "Register a new user using request data")
    public UserDto registerUser(@RequestBody @Valid UserRegistrationRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }
}

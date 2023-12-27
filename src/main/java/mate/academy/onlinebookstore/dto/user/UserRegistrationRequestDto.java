package mate.academy.onlinebookstore.dto.user;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.NOT_BLANK_MESSAGE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import mate.academy.onlinebookstore.validator.FieldMatch;

@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRegistrationRequestDto {
    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Email
    private String email;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String password;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String repeatPassword;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String firstName;
    @NotBlank(message = NOT_BLANK_MESSAGE)
    private String lastName;
    private String shippingAddress;
}

package mate.academy.onlinebookstore.dto.user;

import static mate.academy.onlinebookstore.util.ConstraintsMessages.NOT_NULL_MESSAGE;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.onlinebookstore.validator.FieldMatch;

@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
@Data
public class UserRegistrationRequestDto {
    @NotNull(message = NOT_NULL_MESSAGE)
    @Email
    private String email;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String password;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String repeatPassword;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String firstName;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String lastName;
    private String shippingAddress;
}

package mate.academy.onlinebookstore.dto.book;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.MUST_BE_POSITIVE_MESSAGE;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.NOT_NULL_MESSAGE;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull(message = NOT_NULL_MESSAGE)
    private String title;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String author;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String isbn;
    @NotNull(message = NOT_NULL_MESSAGE)
    @Positive(message = MUST_BE_POSITIVE_MESSAGE)
    private BigDecimal price;
    private String description;
    private String coverImage;
}

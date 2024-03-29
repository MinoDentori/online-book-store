package mate.academy.onlinebookstore.dto.book;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.MUST_BE_POSITIVE_MESSAGE;
import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.NOT_NULL_MESSAGE;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.ISBN;

@Data
@Accessors(chain = true)
public class CreateBookRequestDto {
    @NotNull(message = NOT_NULL_MESSAGE)
    private String title;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String author;
    @ISBN
    @NotNull(message = NOT_NULL_MESSAGE)
    private String isbn;
    @NotNull(message = NOT_NULL_MESSAGE)
    @Positive(message = MUST_BE_POSITIVE_MESSAGE)
    private BigDecimal price;
    private String description;
    private String coverImage;
    private Set<Long> categoryIds;
}

package mate.academy.onlinebookstore.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    public static final String NOT_NULL_MESSAGE = "Can't be null";
    @NotNull(message = NOT_NULL_MESSAGE)
    private String title;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String author;
    @NotNull(message = NOT_NULL_MESSAGE)
    private String isbn;
    @NotNull(message = NOT_NULL_MESSAGE)
    @Positive(message = "Must be greater than 0")
    private BigDecimal price;
    private String description;
    private String coverImage;
}

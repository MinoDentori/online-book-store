package mate.academy.onlinebookstore.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequestDto(
        @NotBlank(message = "Enter name of category")
        String name,
        String description
){
}

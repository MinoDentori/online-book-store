package mate.academy.onlinebookstore.service.category;

import java.util.List;
import mate.academy.onlinebookstore.dto.category.CategoryDto;
import mate.academy.onlinebookstore.dto.category.CreateCategoryRequestDto;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto findById(Long id);

    CategoryDto save(CreateCategoryRequestDto categoryDto);

    CategoryDto updateCategoryById(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);
}

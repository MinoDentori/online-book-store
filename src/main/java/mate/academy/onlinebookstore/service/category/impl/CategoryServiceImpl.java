package mate.academy.onlinebookstore.service.category.impl;

import static mate.academy.onlinebookstore.util.ErrorMessagesConstants.CANT_GET_CATEGORY_FROM_DB_BY_ID;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.onlinebookstore.dto.category.CategoryDto;
import mate.academy.onlinebookstore.dto.category.CreateCategoryRequestDto;
import mate.academy.onlinebookstore.exception.EntityNotFoundException;
import mate.academy.onlinebookstore.mapper.CategoryMapper;
import mate.academy.onlinebookstore.model.Category;
import mate.academy.onlinebookstore.repository.category.CategoryRepository;
import mate.academy.onlinebookstore.service.category.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto findById(Long id) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(CANT_GET_CATEGORY_FROM_DB_BY_ID + id));
        return categoryMapper.toDto(foundCategory);
    }

    @Override
    public CategoryDto save(CreateCategoryRequestDto categoryDto) {
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategoryById(Long id, CreateCategoryRequestDto requestDto) {
        Category foundCategory = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(CANT_GET_CATEGORY_FROM_DB_BY_ID + id));
        Category updatedCategory = categoryMapper.toEntity(requestDto);
        updatedCategory.setId(id);
        categoryRepository.save(updatedCategory);
        return categoryMapper.toDto(updatedCategory);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}

package com.gabriel.apilibrarymanagement.service;

import com.gabriel.apilibrarymanagement.domain.category.Category;
import com.gabriel.apilibrarymanagement.domain.category.CategoryDTO;
import com.gabriel.apilibrarymanagement.domain.category.CategoryRegistration;
import com.gabriel.apilibrarymanagement.domain.category.CategoryUpdate;
import com.gabriel.apilibrarymanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO categoryRegister(CategoryRegistration categoryRegistration) {
        try {
            Category category = new Category(categoryRegistration);
            category = categoryRepository.save(category);
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CategoryDTO getCategoryById(Integer id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                return CategoryDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<CategoryDTO> getCategories() {
        try {
            List<Category> categories = categoryRepository.findAll();
            return categories.stream()
                    .map(category -> CategoryDTO.builder()
                            .id(category.getId())
                            .name(category.getName())
                            .build()).toList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void deleteCategory(Integer id) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                categoryRepository.delete(category);
            } else {
                throw new RuntimeException("Category not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public CategoryDTO updateCategory(Integer id, CategoryUpdate categoryUpdate) {
        try {
            Optional<Category> categoryOptional = categoryRepository.findById(id);
            if (categoryOptional.isPresent()) {
                Category category = categoryOptional.get();
                Optional.ofNullable(categoryUpdate.name()).ifPresent(category::setName);
                category = categoryRepository.save(category);
                return CategoryDTO.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .build();
            } else {
                throw new RuntimeException("Category not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

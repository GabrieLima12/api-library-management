package com.gabriel.apilibrarymanagement.controller;

import com.gabriel.apilibrarymanagement.domain.category.CategoryDTO;
import com.gabriel.apilibrarymanagement.domain.category.CategoryRegistration;
import com.gabriel.apilibrarymanagement.domain.category.CategoryUpdate;
import com.gabriel.apilibrarymanagement.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDTO>> getAuthors() {
        List<CategoryDTO> category = categoryService.getCategories();
        return ResponseEntity.ok(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getBookById(@PathVariable Integer id) {
        CategoryDTO category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(category);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<CategoryDTO> authorRegistration(@RequestBody CategoryRegistration categoryRegistration) {
        CategoryDTO category = categoryService.categoryRegister(categoryRegistration);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateAuthor(
            @PathVariable Integer id,
            @RequestBody CategoryUpdate categoryUpdate) {
        CategoryDTO category = categoryService.updateCategory(id, categoryUpdate);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

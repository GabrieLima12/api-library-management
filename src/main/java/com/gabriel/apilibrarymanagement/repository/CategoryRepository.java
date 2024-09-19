package com.gabriel.apilibrarymanagement.repository;

import com.gabriel.apilibrarymanagement.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}

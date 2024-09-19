package com.gabriel.apilibrarymanagement.repository;

import com.gabriel.apilibrarymanagement.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {}

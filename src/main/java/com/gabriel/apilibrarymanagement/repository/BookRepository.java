package com.gabriel.apilibrarymanagement.repository;

import com.gabriel.apilibrarymanagement.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false")
    List<Book> findAllByIsDeleteFalse();

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false AND b.id = :id")
    Optional<Book> findByIdAndIsDeletedFalse(@Param("id") Integer id);
}

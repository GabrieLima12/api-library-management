package com.gabriel.apilibrarymanagement.service;

import com.gabriel.apilibrarymanagement.domain.author.Author;
import com.gabriel.apilibrarymanagement.domain.book.Book;
import com.gabriel.apilibrarymanagement.domain.book.BookDTO;
import com.gabriel.apilibrarymanagement.domain.book.BookRegistration;
import com.gabriel.apilibrarymanagement.domain.book.BookUpdate;
import com.gabriel.apilibrarymanagement.domain.category.Category;
import com.gabriel.apilibrarymanagement.repository.AuthorRepository;
import com.gabriel.apilibrarymanagement.repository.BookRepository;
import com.gabriel.apilibrarymanagement.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public BookDTO bookRegister(BookRegistration bookRegistration) {
        try {
            List<Author> authors = authorRepository.findAllById(bookRegistration.authorIds());
            List<Category> categories = categoryRepository.findAllById(bookRegistration.categoryIds());
            Book book = new Book(bookRegistration, authors, categories);
            book = bookRepository.save(book);
            return BookDTO.builder()
                    .id(book.getId())
                    .title(book.getTitle())
                    .description(book.getDescription())
                    .publishDate(book.getPublishDate())
                    .availability(book.getAvailabilityEnum())
                    .authors(book.getAuthors().stream().map(Author::getName).toList())
                    .categories(book.getCategories().stream().map(Category::getName).toList())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public BookDTO getBookById(Integer id) {
        try {
            Optional<Book> bookOptional = bookRepository.findByIdAndIsDeletedFalse(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                return BookDTO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .publishDate(book.getPublishDate())
                        .availability(book.getAvailabilityEnum())
                        .build();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<BookDTO> getBooks() {
        try {
            List<Book> books = bookRepository.findAllByIsDeleteFalse();
            return books.stream().map(this::covertToDto).toList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private BookDTO covertToDto(Book book) {
        return BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .publishDate(book.getPublishDate())
                .availability(book.getAvailabilityEnum())
                .authors(book.getAuthors().stream().map(Author::getName).toList())
                .categories(book.getCategories().stream().map(Category::getName).toList())
                .build();
    }

    public void deleteBook(Integer id) {
        try {
            Optional<Book> bookOptional = bookRepository.findByIdAndIsDeletedFalse(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                book.setDeleted(true);
                bookRepository.save(book);
            } else {
                throw new RuntimeException("Book not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public BookDTO updateBook(Integer id, BookUpdate bookUpdate) {
        try {
            Optional<Book> bookOptional = bookRepository.findById(id);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                Optional.ofNullable(bookUpdate.title()).ifPresent(book::setTitle);
                Optional.ofNullable(bookUpdate.description()).ifPresent(book::setDescription);
                Optional.ofNullable(bookUpdate.publishDate()).ifPresent(book::setPublishDate);
                Optional.ofNullable(bookUpdate.availability()).ifPresent(book::setAvailabilityEnum);
                if (book.isDeleted()) {
                    book.setDeleted(false);
                }
                book = bookRepository.save(book);
                return BookDTO.builder()
                        .id(book.getId())
                        .title(book.getTitle())
                        .description(book.getDescription())
                        .publishDate(book.getPublishDate())
                        .availability(book.getAvailabilityEnum())
                        .build();
            } else {
                throw new RuntimeException("Book not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

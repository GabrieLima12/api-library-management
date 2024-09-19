package com.gabriel.apilibrarymanagement.controller;

import com.gabriel.apilibrarymanagement.domain.book.BookDTO;
import com.gabriel.apilibrarymanagement.domain.book.BookRegistration;
import com.gabriel.apilibrarymanagement.domain.book.BookUpdate;
import com.gabriel.apilibrarymanagement.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<List<BookDTO>> getBooks() {
        List<BookDTO> booksDTO = bookService.getBooks();
        return ResponseEntity.ok(booksDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Integer id) {
        BookDTO bookDTO = bookService.getBookById(id);
        if (bookDTO != null) {
            return ResponseEntity.ok(bookDTO);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<BookDTO> bookRegistration(@RequestBody BookRegistration bookRegistration) {
        BookDTO bookDTO = bookService.bookRegister(bookRegistration);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(
            @PathVariable Integer id,
            @RequestBody BookUpdate bookUpdate) {
        BookDTO book = bookService.updateBook(id, bookUpdate);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

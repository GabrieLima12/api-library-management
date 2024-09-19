package com.gabriel.apilibrarymanagement.controller;

import com.gabriel.apilibrarymanagement.domain.author.AuthorDTO;
import com.gabriel.apilibrarymanagement.domain.author.AuthorRegistration;
import com.gabriel.apilibrarymanagement.domain.author.AuthorUpdate;
import com.gabriel.apilibrarymanagement.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthController {

    private final AuthorService authorService;

    public AuthController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDTO>> getAuthors() {
        List<AuthorDTO> authorsDTO = authorService.getAuthors();
        return ResponseEntity.ok(authorsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getBookById(@PathVariable Integer id) {
        AuthorDTO authorDTO = authorService.getAuthorById(id);
        if (authorDTO != null) {
            return ResponseEntity.ok(authorDTO);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<AuthorDTO> authorRegistration(@RequestBody AuthorRegistration authorRegistration) {
        AuthorDTO authorDTO = authorService.authorRegister(authorRegistration);
        return new ResponseEntity<>(authorDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(
            @PathVariable Integer id,
            @RequestBody AuthorUpdate authorUpdate) {
        AuthorDTO authorDTO = authorService.updateAuthor(id, authorUpdate);
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

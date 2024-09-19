package com.gabriel.apilibrarymanagement.service;

import com.gabriel.apilibrarymanagement.domain.author.Author;
import com.gabriel.apilibrarymanagement.domain.author.AuthorDTO;
import com.gabriel.apilibrarymanagement.domain.author.AuthorRegistration;
import com.gabriel.apilibrarymanagement.domain.author.AuthorUpdate;
import com.gabriel.apilibrarymanagement.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO authorRegister(AuthorRegistration authorRegistration) {
        try {
            Author author = new Author(authorRegistration);
            author = authorRepository.save(author);
            return AuthorDTO.builder()
                    .id(author.getId())
                    .name(author.getName())
                    .birthDate(author.getBirthDate())
                    .nationality(author.getNationality())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public AuthorDTO getAuthorById(Integer id) {
        try {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                return AuthorDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .birthDate(author.getBirthDate())
                        .nationality(author.getNationality())
                        .build();
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public List<AuthorDTO> getAuthors() {
        try {
            List<Author> authors = authorRepository.findAll();
            return authors.stream()
                    .map(author -> AuthorDTO.builder()
                            .id(author.getId())
                            .name(author.getName())
                            .birthDate(author.getBirthDate())
                            .nationality(author.getNationality())
                            .build()).toList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void deleteAuthor(Integer id) {
        try {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                authorRepository.delete(author);
            } else {
                throw new RuntimeException("Author not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public AuthorDTO updateAuthor(Integer id, AuthorUpdate authorUpdate) {
        try {
            Optional<Author> authorOptional = authorRepository.findById(id);
            if (authorOptional.isPresent()) {
                Author author = authorOptional.get();
                Optional.ofNullable(authorUpdate.name()).ifPresent(author::setName);
                Optional.ofNullable(authorUpdate.nationality()).ifPresent(author::setNationality);
                author = authorRepository.save(author);
                return AuthorDTO.builder()
                        .id(author.getId())
                        .name(author.getName())
                        .birthDate(author.getBirthDate())
                        .nationality(author.getNationality())
                        .build();
            } else {
                throw new RuntimeException("Author not found!");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

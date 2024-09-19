package com.gabriel.apilibrarymanagement.domain.author;

import com.gabriel.apilibrarymanagement.domain.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "AUTHOR")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDate birthDate;
    private String nationality;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();

    public Author(AuthorRegistration authorRegistration) {
        this.name = authorRegistration.name();
        this.birthDate = authorRegistration.birthDate();
        this.nationality = authorRegistration.nationality();
    }
}

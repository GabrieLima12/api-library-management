package com.gabriel.apilibrarymanagement.domain.book;

import com.gabriel.apilibrarymanagement.domain.author.Author;
import com.gabriel.apilibrarymanagement.domain.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "BOOK")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private LocalDate publishDate;
    private boolean isDeleted;

    @Column(name = "AVAILABILITY")
    @Enumerated(EnumType.STRING)
    private AvailabilityEnum availabilityEnum;

    @ManyToMany
    @JoinTable(
            name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "BOOK_CATEGORIES",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private List<Category> categories = new ArrayList<>();

    public Book(BookRegistration bookRegistration, List<Author> authors, List<Category> categories) {
        this.title = bookRegistration.title();
        this.description = bookRegistration.description();
        this.publishDate = bookRegistration.publishDate();
        this.availabilityEnum = AvailabilityEnum.AVAILABLE;
        this.authors = authors;
        this.categories = categories;
        this.isDeleted = false;
    }
}

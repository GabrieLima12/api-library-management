package com.gabriel.apilibrarymanagement.domain.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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

    public Book(BookRegistration bookRegistration) {
        this.title = bookRegistration.title();
        this.description = bookRegistration.description();
        this.publishDate = bookRegistration.publishDate();
        this.availabilityEnum = AvailabilityEnum.AVAILABLE;
        this.isDeleted = false;
    }
}

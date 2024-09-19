package com.gabriel.apilibrarymanagement.domain.category;

import com.gabriel.apilibrarymanagement.domain.book.Book;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Table(name = "CATEGORY")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "categories")
    private List<Book> books = new ArrayList<>();

    public Category(CategoryRegistration categoryRegistration) {
        this.name = categoryRegistration.name();
    }
}

package com.gabriel.apilibrarymanagement.domain.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class BookDTO {

    private Integer id;
    private String title;
    private String description;
    private LocalDate publishDate;
    private AvailabilityEnum availability;
    private List<String> authors;
    private List<String> categories;
}

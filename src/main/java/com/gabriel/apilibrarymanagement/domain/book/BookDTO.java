package com.gabriel.apilibrarymanagement.domain.book;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class BookDTO {

    private Integer id;
    private String title;
    private String description;
    private LocalDate publishDate;
    private AvailabilityEnum availability;
}

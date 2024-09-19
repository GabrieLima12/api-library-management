package com.gabriel.apilibrarymanagement.domain.book;

import java.time.LocalDate;

public record BookRegistration(
        String title,
        String description,
        LocalDate publishDate
) {
}

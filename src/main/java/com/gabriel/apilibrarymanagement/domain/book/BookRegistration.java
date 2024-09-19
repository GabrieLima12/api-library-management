package com.gabriel.apilibrarymanagement.domain.book;

import java.time.LocalDate;
import java.util.List;

public record BookRegistration(
        String title,
        String description,
        LocalDate publishDate,
        List<Integer> authorIds,
        List<Integer> categoryIds
) {
}

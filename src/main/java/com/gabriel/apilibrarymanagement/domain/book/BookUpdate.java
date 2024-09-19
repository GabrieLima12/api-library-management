package com.gabriel.apilibrarymanagement.domain.book;

import java.time.LocalDate;

public record BookUpdate(String title, String description, LocalDate publishDate, AvailabilityEnum availability) {}

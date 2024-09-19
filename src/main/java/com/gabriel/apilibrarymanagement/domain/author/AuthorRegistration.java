package com.gabriel.apilibrarymanagement.domain.author;

import java.time.LocalDate;

public record AuthorRegistration(String name, LocalDate birthDate, String nationality) {}

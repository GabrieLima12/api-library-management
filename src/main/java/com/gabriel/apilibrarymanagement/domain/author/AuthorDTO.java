package com.gabriel.apilibrarymanagement.domain.author;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AuthorDTO {

    private Integer id;
    private String name;
    private LocalDate birthDate;
    private String nationality;
}

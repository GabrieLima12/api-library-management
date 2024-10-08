package com.gabriel.apilibrarymanagement.domain.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryDTO {

    private Integer id;
    private String name;
}

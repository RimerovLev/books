package org.bookservice.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AuthorDto {
    String name;
    LocalDate birthDate;
}

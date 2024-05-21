package pl.leverx.ms.user.crud.dto;

import jakarta.validation.constraints.NotBlank;

public record PersonDTO(
        @NotBlank
        String idNumber,
        @NotBlank
        String firstName,
        @NotBlank
        String lastName
) {
}

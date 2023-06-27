package com.alura.hotelAlura.model.guests;

import jakarta.validation.constraints.NotBlank;

public record GuestRecordData(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String birthday,
        @NotBlank
        String country,
        @NotBlank
        String phone
) {
}

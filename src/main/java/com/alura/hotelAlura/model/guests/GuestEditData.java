package com.alura.hotelAlura.model.guests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GuestEditData(
        @NotNull
        Long id,
        String name,
        String lastname,
        String birthday,
        String country,
        String phone
) {
}

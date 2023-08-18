package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.model.reservations.Reservation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record GuestEditData(
        @NotNull
        Long id,
        String name,
        String lastname,
        String birthday,
        String country,
        String phone,
        String document,
        Set<Reservation> reservation
) {
}

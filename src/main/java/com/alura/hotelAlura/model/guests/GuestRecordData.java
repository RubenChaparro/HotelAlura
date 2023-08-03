package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.model.reservations.Reservation;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        String phone,
        Set<Reservation> reservation

) {
}

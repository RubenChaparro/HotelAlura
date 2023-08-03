package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.model.reservations.Reservation;

import java.util.Set;

public record GuestResponseData(
        Long id,
        String name,
        String lastname,
        String date,
        String country,
        String phone,
        Set<Reservation> reservations
) {

}

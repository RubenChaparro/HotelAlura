package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.model.reservations.Reservation;

import java.util.Set;

public record GuestListData(
        Long id,
        String name,
        String lastname,
        String birthday,
        String country,
        String phone,
        String document,
        Set<Reservation> reservations

) {
    public GuestListData(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getLastname(), guest.getBirthday(), guest.getCountry(), guest.getPhone(), guest.getDocument(),guest.getReservations());
    }
}

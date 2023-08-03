package com.alura.hotelAlura.model.reservations;

import com.alura.hotelAlura.model.guests.Guest;

public record ReservationListData(
        Long id,
        String entrydate,
        String outdate,
        Float price,
        String payform,
        Long guest
) {

    public ReservationListData(Reservation reservation) {
        this(reservation.getId(), reservation.getEntrydate(), reservation.getOutdate(), reservation.getPrice(), reservation.getPayform(), reservation.getGuest().getId());
    }
}

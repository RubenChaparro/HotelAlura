package com.alura.hotelAlura.model.reservations;

public record ReservationListData(
        Long id,
        String entrydate,
        String outdate,
        Float price,
        String payform
) {

    public ReservationListData(Reservation reservation) {
        this(reservation.getId(), reservation.getEntrydate(), reservation.getOutdate(), reservation.getPrice(), reservation.getPayform());
    }
}

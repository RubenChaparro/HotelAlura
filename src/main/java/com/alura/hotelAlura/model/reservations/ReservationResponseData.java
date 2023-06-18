package com.alura.hotelAlura.model.reservations;

public record ReservationResponseData(
       Long id,
       String entrydate,
       String outdate,
       Float price,
       String payform
) {
}

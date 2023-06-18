package com.alura.hotelAlura.model.reservations;

public record ReservationResponseData(
       Long id,
       String entrydate,
       String outdate,
       float price,
       String payform

       //Long idGuest

) {
}

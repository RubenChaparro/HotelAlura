package com.alura.hotelAlura.model.reservations;

import com.alura.hotelAlura.model.guests.Guest;
import jakarta.validation.constraints.NotNull;

public record ReservationEditData(
        @NotNull
        Long id,
        String entrydate,
        String outdate,
        Float price,
        String payform,
        Guest guest


) {
}

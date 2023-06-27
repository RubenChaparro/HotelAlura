package com.alura.hotelAlura.model.reservations;

import jakarta.validation.constraints.NotNull;

public record ReservationEditData(
        @NotNull
        Long id,
        String entrydate,
        String outdate,
        String payform
) {
}

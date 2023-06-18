package com.alura.hotelAlura.model.reservations;

import jakarta.validation.constraints.NotBlank;

public record ReservationRecordData(
        @NotBlank
        String entrydate,
        @NotBlank
        String outdate,
        @NotBlank
        float price,
        @NotBlank
        String payform

        //Long idGuest
) {
}

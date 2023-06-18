package com.alura.hotelAlura.model.reservations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservationRecordData(
        @NotBlank
        String entrydate,
        @NotBlank
        String outdate,
        @NotNull
        Float price,
        @NotBlank
        String payform
) {
}

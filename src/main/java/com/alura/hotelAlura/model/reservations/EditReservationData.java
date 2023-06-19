package com.alura.hotelAlura.model.reservations;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditReservationData(
        @NotNull
        Long id,
        String entrydate,
        String outdate,
        String payform
) {
}

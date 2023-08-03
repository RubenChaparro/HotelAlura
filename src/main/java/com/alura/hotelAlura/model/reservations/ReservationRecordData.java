package com.alura.hotelAlura.model.reservations;

import com.alura.hotelAlura.model.guests.Guest;
import jakarta.validation.Valid;
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
        String payform,
        @Valid
        Guest guest
) {

}

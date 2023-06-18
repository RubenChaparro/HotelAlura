package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.model.reservations.Reservation;
import com.alura.hotelAlura.model.reservations.ReservationRepository;
import com.alura.hotelAlura.model.reservations.ReservationRecordData;
import com.alura.hotelAlura.model.reservations.ReservationResponseData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping
    public ResponseEntity<ReservationResponseData> RecordReservation(@RequestBody @Valid ReservationRecordData reservationRecordData, UriComponentsBuilder uriComponentsBuilder){
        Reservation reservation = reservationRepository.save(new Reservation(reservationRecordData));
        ReservationResponseData reservationResponseData = new ReservationResponseData(
                reservation.getId(),reservation.getEntryDate(),
                reservation.getOutDate(), reservation.getPrice(),
                reservation.getPayform());

        URI url = uriComponentsBuilder.path("/reservations/{id}").buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(url).body(reservationResponseData);
    }
}

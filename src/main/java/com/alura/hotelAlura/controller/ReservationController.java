package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.model.reservations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                reservation.getId(),reservation.getEntrydate(),
                reservation.getOutdate(), reservation.getPrice(),
                reservation.getPayform());

        URI url = uriComponentsBuilder.path("/reservations/{id}").buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(url).body(reservationResponseData);
    }

    @GetMapping
    public Page<ReservationListData> ReservationList(@PageableDefault(size = 5, sort = "entrydate") Pageable pageable) {
        return reservationRepository.findAll(pageable).map(ReservationListData :: new);
    }
}

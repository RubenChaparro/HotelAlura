package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.model.reservations.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

// Usando lo aplicado en la clase

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping
    public ResponseEntity<ReservationResponseData> record(@RequestBody @Valid ReservationRecordData reservationRecordData, UriComponentsBuilder uriComponentsBuilder) {
        Reservation reservation = reservationRepository.save(new Reservation(reservationRecordData));
        ReservationResponseData reservationResponseData = new ReservationResponseData(
                reservation.getId(), reservation.getEntrydate(),
                reservation.getOutdate(), reservation.getPrice(),
                reservation.getPayform());

        URI url = uriComponentsBuilder.path("/reservations/{id}").buildAndExpand(reservation.getId()).toUri();
        return ResponseEntity.created(url).body(reservationResponseData);
    }

   @GetMapping
    public Page<ReservationListData> list(@PageableDefault(size = 50, sort = "id") Pageable pageable) {
        return reservationRepository.findAll(pageable).map(ReservationListData::new);
    }


    @PutMapping
    @Transactional
    public ResponseEntity<ReservationResponseData> edit(@RequestBody @Valid ReservationEditData reservationEditData) {
        Reservation reservation = reservationRepository.getReferenceById(reservationEditData.id());
        reservation.editData(reservationEditData);
        return ResponseEntity.ok(new ReservationResponseData(reservation.getId(), reservation.getEntrydate(), reservation.getOutdate(), reservation.getPrice(), reservation.getPayform()));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        reservationRepository.delete(reservation);
        //return ResponseEntity.ok(reservationRepository.findAll().stream().map(ReservationListData::new));
    }
}



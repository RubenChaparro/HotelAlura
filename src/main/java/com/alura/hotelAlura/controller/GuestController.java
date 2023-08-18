package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.model.guests.*;
import com.alura.hotelAlura.model.reservations.Reservation;
import com.alura.hotelAlura.model.reservations.ReservationListData;
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

// Usando lo indicado por el cliente
@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    GuestRepository guestRepository;

    @PostMapping
    public ResponseEntity<GuestResponseData> record(@RequestBody @Valid GuestRecordData guestRecordData,
            UriComponentsBuilder uriComponentsBuilder) {
        Guest guest = guestRepository.save(new Guest(guestRecordData));
        GuestResponseData guestResponseData = new GuestResponseData(
                guest.getId(), guest.getName(),
                guest.getLastname(), guest.getBirthday(),
                guest.getCountry(), guest.getPhone(), guest.getDocument(), guest.getReservations());

        URI url = uriComponentsBuilder.path("/guests/{id}").buildAndExpand(guest.getId()).toUri();
        return ResponseEntity.created(url).body(guestResponseData);
    }

    @GetMapping
    public Page<GuestListData> list(@PageableDefault(size = 50) Pageable pageable) {
        return guestRepository.findAll(pageable).map(GuestListData::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestListData> listForId(@PathVariable Long id) {
        Guest guest = guestRepository.getReferenceById(id);
        return ResponseEntity.ok(new GuestListData(guest));
    }

    @PutMapping
    @Transactional
    public void edit(@RequestBody @Valid GuestEditData guestEditData) {
        Guest guest = guestRepository.getReferenceById(guestEditData.id());
        guest.editData(guestEditData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Guest guest = guestRepository.getReferenceById(id);
        guestRepository.delete(guest);
    }
}

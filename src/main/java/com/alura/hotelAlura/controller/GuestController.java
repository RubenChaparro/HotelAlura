package com.alura.hotelAlura.controller;

import com.alura.hotelAlura.model.guests.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

// Usando lo indicado por el cliente
@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    GuestRepository guestRepository;


    @PostMapping
    public void record(@RequestBody @Valid GuestRecordData guestRecordData) {
        guestRepository.save(new Guest(guestRecordData));
    }

    @GetMapping
    public Page<GuestListData> list(Pageable pageable) {
        return guestRepository.findAll(pageable).map(GuestListData :: new);
    }

    @PutMapping
    @Transactional
    public void edit(@RequestBody @Valid GuestEditData guestEditData) {
        Guest guest = guestRepository.getReferenceById(guestEditData.id());
        guest.editData(guestEditData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Guest guest = guestRepository.getReferenceById(id);
        guestRepository.delete(guest);
    }
}

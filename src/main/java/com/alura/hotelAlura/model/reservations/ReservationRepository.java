package com.alura.hotelAlura.model.reservations;

import com.alura.hotelAlura.model.guests.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    Page<Reservation> findById(Long id, Pageable pageable);
}

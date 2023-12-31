package com.alura.hotelAlura.model.guests;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest,Long> {
    Page<Guest> findById(Long id, Pageable pageable);
}

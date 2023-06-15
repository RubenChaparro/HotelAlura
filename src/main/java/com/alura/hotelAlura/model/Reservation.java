package com.alura.hotelAlura.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "reservations")
@Entity(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entryDate;
    private String outDate;
    private float price;
    private String payform;
}

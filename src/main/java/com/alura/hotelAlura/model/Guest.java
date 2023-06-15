package com.alura.hotelAlura.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "guests")
@Entity(name = "guest")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String document;
    private String country;
    private String phone;
    private Boolean activate;
    private Long idReservation;
}

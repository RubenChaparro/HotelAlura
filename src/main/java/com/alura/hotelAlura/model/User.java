package com.alura.hotelAlura.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "users")
@Entity(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String login;
    private String password;
}

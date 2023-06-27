package com.alura.hotelAlura.model.guests;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "guests")
@Entity
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
    private String lastname;
    private String birthday;
    private String country;
    private String phone;
    @Column(name = "id_reservation")
    private Long idreservartion;

    public Guest(GuestRecordData guestRecordData) {
        this.name = guestRecordData.name();
        this.lastname = guestRecordData.lastname();
        this.birthday = guestRecordData.birthday();
        this.country = guestRecordData.country();
        this.phone = guestRecordData.phone();
    }

    public void editData(GuestEditData guestEditData) {
        if(guestEditData.name()!= null) {
            this.name = guestEditData.name();
        }
        if(guestEditData.lastname()!= null) {
            this.lastname = guestEditData.lastname();
        }
        if(guestEditData.birthday()!= null) {
            this.birthday = guestEditData.birthday();
        }
        if(guestEditData.country()!= null) {
            this.country = guestEditData.country();
        }
        if(guestEditData.phone()!= null) {
            this.phone = guestEditData.phone();
        }
    }
}

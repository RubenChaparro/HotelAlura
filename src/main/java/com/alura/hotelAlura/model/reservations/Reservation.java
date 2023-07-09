package com.alura.hotelAlura.model.reservations;

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
    private String entrydate;
    private String outdate;
    private Float price;
    private String payform;

    public Reservation(ReservationRecordData reservationRecordData) {
        this.entrydate = reservationRecordData.entrydate();
        this.outdate = reservationRecordData.outdate();
        this.price = reservationRecordData.price();
        this.payform = reservationRecordData.payform();
    }

    public Reservation(String entrydate) {
    }

    public void editData(ReservationEditData reservationEditData) {

        if (reservationEditData.entrydate()!=null) {
            this.entrydate = reservationEditData.entrydate();
        }
        if (reservationEditData.outdate()!=null) {
            this.outdate= reservationEditData.entrydate();
        }
        if (reservationEditData.payform()!=null) {
            this.payform= reservationEditData.payform();
        }
    }


    public void delete(Reservation reservation) {
    }
}

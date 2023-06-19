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

    public void editData(EditReservationData editReservationData) {

        if (editReservationData.entrydate()!=null) {
            this.entrydate = editReservationData.entrydate();
        }
        if (editReservationData.outdate()!=null) {
            this.outdate=editReservationData.entrydate();
        }
        if (editReservationData.payform()!=null) {
            this.payform=editReservationData.payform();
        }
    }

}

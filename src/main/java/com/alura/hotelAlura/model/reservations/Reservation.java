package com.alura.hotelAlura.model.reservations;

import com.alura.hotelAlura.model.guests.Guest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

@Table(name = "reservations")
@Entity(name = "reservation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String entrydate;
    private String outdate;
    private Float price;
    private String payform;
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idguest")
    private Guest guest;

    public Reservation(ReservationRecordData reservationRecordData) {
        this.entrydate = reservationRecordData.entrydate();
        this.outdate = reservationRecordData.outdate();
        this.price = reservationRecordData.price();
        this.payform = reservationRecordData.payform();
        this.guest = reservationRecordData.guest();
    }

    public void editData(ReservationEditData reservationEditData) {

        if (reservationEditData.entrydate() != null) {
            this.entrydate = reservationEditData.entrydate();
        }
        if (reservationEditData.outdate() != null) {
            this.outdate = reservationEditData.entrydate();
        }
        if (reservationEditData.price() != null) {
            this.price = reservationEditData.price();
        }
        if (reservationEditData.payform() != null) {
            this.payform = reservationEditData.payform();
        }
        if (reservationEditData.guest() != null) {
            this.guest = reservationEditData.guest();
        }
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", entrydate='" + entrydate + '\'' +
                ", outdate='" + outdate + '\'' +
                ", price=" + price +
                ", payform='" + payform + '\'' +
                ", idguest=" + guest +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
                ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass)
            return false;
        Reservation that = (Reservation) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy
                ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}

package com.alura.hotelAlura.model.guests;

import com.alura.hotelAlura.model.reservations.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
@Entity
@Table(name = "guests", uniqueConstraints = {@UniqueConstraint(columnNames = {"document"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String birthday;
    private String country;
    private String phone;
    private String document;
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new HashSet<>();

    public Guest(GuestRecordData guestRecordData) {
        this.name = guestRecordData.name();
        this.lastname = guestRecordData.lastname();
        this.birthday = guestRecordData.birthday();
        this.country = guestRecordData.country();
        this.phone = guestRecordData.phone();
        this.document = guestRecordData.document();
        this.reservations = guestRecordData.reservation();
    }

    public void editData(GuestEditData guestEditData) {
        if (guestEditData.name() != null) {
            this.name = guestEditData.name();
        }
        if (guestEditData.lastname() != null) {
            this.lastname = guestEditData.lastname();
        }
        if (guestEditData.birthday() != null) {
            this.birthday = guestEditData.birthday();
        }
        if (guestEditData.country() != null) {
            this.country = guestEditData.country();
        }
        if (guestEditData.phone() != null) {
            this.phone = guestEditData.phone();
        }
        if (guestEditData.document() != null) {
            this.document = guestEditData.document();
        }
        if (guestEditData.reservation() != null) {
            this.reservations = guestEditData.reservation();
        }
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
        for (Reservation reservation :reservations) {
            reservation.setGuest(this);
        }
    }


    @Override
    public String toString() {
        return "Guest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", document='" + document + '\'' +
                ", reservations=" + reservations +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Guest guest = (Guest) o;
        return getId() != null && Objects.equals(getId(), guest.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
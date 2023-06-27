package com.alura.hotelAlura.model.guests;

public record GuestListData(
        Long id,
        String name,
        String lastname,
        String birthday,
        String country,
        String phone,
        Long idreservation
) {
    public GuestListData(Guest guest) {
        this(guest.getId(), guest.getName(), guest.getLastname(), guest.getBirthday(), guest.getCountry(), guest.getPhone(), guest.getIdreservartion());
    }
}

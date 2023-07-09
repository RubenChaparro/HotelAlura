package com.alura.hotelAlura.model.reservations;

public enum FormaDePago {


    CREDITCARD("Tarjeta de Credito"),
    CASH("Efectivo"),
    DEBITCARD("Tarjeta de Debito");

    private final String pay;

    private FormaDePago(String pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return pay;
    }
}

package com.hotel.modelo;

public class Reserva {
    private String fecha;
    private String servicio;
    private Huesped huesped;
    private Recepcionista recepcionista;

    public Reserva(String fecha, String servicio, Huesped huesped, Recepcionista recepcionista) {
        this.fecha = fecha;
        this.servicio = servicio;
        this.huesped = huesped;
        this.recepcionista = recepcionista;
    }

    public String getInfo() {
        return "Reserva: " + fecha + " | Servicio: " + servicio + 
               " | Recepcionista: " + recepcionista.getNombre() + " | Huésped: " + huesped.getNombre();
    }

    public String getFecha() {
        return fecha;
    }

    public String getServicio() {
        return servicio;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Recepcionista getRecepcionista() {
        return recepcionista;
    }
}
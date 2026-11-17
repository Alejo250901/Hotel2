package com.hotel.modelo;

import java.util.ArrayList;
import com.hotel.excepciones.ReservaInvalidaException;

public class Recepcionista extends Empleado {
    private ArrayList<Reserva> reservas;

    public Recepcionista(String nombre, String id, String cargo) {
        super(nombre, id, cargo);
        this.reservas = new ArrayList<>();
    }

// Sobrecarga - Versión 1: Reserva con servicio por defecto
    public void hacerReserva(String fecha, Huesped huesped) throws ReservaInvalidaException {
        Validador.validarFecha(fecha); // Validación estricta del formato y fecha real
        if (huesped == null) {
            throw new ReservaInvalidaException("Debe indicarse un huésped válido.");
        }
        Reserva nuevaReserva = new Reserva(fecha, "Alojamiento estándar", huesped, this);
        reservas.add(nuevaReserva);
        System.out.println("Reserva hecha para " + fecha + " a nombre de " + huesped.getNombre());
    }

    // Sobrecarga - Versión 2: Reserva con servicio específico
    public void hacerReserva(String fecha, String servicio, Huesped huesped) throws ReservaInvalidaException {
        Validador.validarFecha(fecha); // Validación estricta del formato y fecha real
        if (servicio == null || servicio.trim().isEmpty()) {
            throw new ReservaInvalidaException("El servicio no puede estar vacío.");
        }
        if (huesped == null) {
            throw new ReservaInvalidaException("Debe indicarse un huésped válido.");
        }
        Reserva nuevaReserva = new Reserva(fecha, servicio, huesped, this);
        reservas.add(nuevaReserva);
        System.out.println("Reserva hecha para " + fecha + " con servicio " + servicio + " a nombre de " + huesped.getNombre());
    }

    @Override
    public String generarReporte() {
        return "=== RECEPCIONISTA ===\n" +
               "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Cargo: " + getCargo() + "\n" +
               "Reservas gestionadas: " + reservas.size();
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
}
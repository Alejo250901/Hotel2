package com.hotel.modelo;

import java.util.ArrayList;
import com.hotel.excepciones.HabitacionLlenaException;
import com.hotel.excepciones.HuespedNoEncontradoException;

public class Habitacion {
    private String numero;
    private int capacidad;
    private ArrayList<Huesped> huespedes;

    public Habitacion(String numero, int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
        this.numero = numero;
        this.capacidad = capacidad;
        this.huespedes = new ArrayList<>();
    }

    public void alojarHuesped(Huesped h) throws HabitacionLlenaException {
        if (huespedes.size() >= capacidad) {
            throw new HabitacionLlenaException("Habitación " + numero + " sin cupo disponible.");
        }
        huespedes.add(h);
        System.out.println("Huésped " + h.getNombre() + " alojado en habitación " + numero);
    }

    public void liberarHuesped(String documento) throws HuespedNoEncontradoException {
        Huesped encontrado = buscarHuesped(documento);
        huespedes.remove(encontrado);
        System.out.println("Check-out realizado.");
    }

    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        for (Huesped h : huespedes) {
            if (h.getDocumento().equals(documento)) {
                return h;
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    public void listarHuespedes() {
        if (huespedes.isEmpty()) {
            System.out.println("No hay huéspedes en esta habitación.");
        } else {
            for (Huesped h : huespedes) {
                // Polimorfismo dinámico: invoca el obtenerInfo() específico de cada subclase
                System.out.println(" - " + h.obtenerInfo());
            }
        }
    }

    public String getNumero() {
        return numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public ArrayList<Huesped> getHuespedes() {
        return huespedes;
    }
}
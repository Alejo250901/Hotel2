package com.hotel.modelo;

public class Camarero extends Empleado {
    private String zona;

    public Camarero(String nombre, String id, String cargo, String zona) {
        super(nombre, id, cargo);
        this.zona = zona;
    }

    @Override
    public String generarReporte() {
        return "=== CAMARERO ===\n" +
               "Nombre: " + getNombre() + "\n" +
               "ID: " + getId() + "\n" +
               "Cargo: " + getCargo() + "\n" +
               "Zona: " + zona;
    }

    public String getZona() {
        return zona;
    }
}
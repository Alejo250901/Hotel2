package com.hotel.modelo;

public abstract class Empleado {
    private String nombre;
    private String id;
    private String cargo;

    public Empleado(String nombre, String id, String cargo) {
        Validador.validarNombre(nombre);
        this.nombre = nombre;
        this.id = id;
        this.cargo = cargo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getCargo() {
        return cargo;
    }

    public abstract String generarReporte();
}
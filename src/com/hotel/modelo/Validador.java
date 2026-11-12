package com.hotel.modelo;

public class Validador {
    // Constructor privado para evitar la instanciación de la clase de utilidad
    private Validador() {}

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        for (char c : nombre.toCharArray()) {
            if (Character.isDigit(c)) {
                throw new IllegalArgumentException("El nombre no puede contener números: " + nombre);
            }
        }
    }

    public static void validarDocumento(String documento) {
        if (documento == null || documento.length() < 5 || documento.length() > 12) {
            throw new IllegalArgumentException("El documento debe tener entre 5 y 12 dígitos.");
        }
        for (char c : documento.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El documento solo puede contener números.");
            }
        }
    }
}
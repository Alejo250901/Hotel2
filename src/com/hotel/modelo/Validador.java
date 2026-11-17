package com.hotel.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.hotel.excepciones.ReservaInvalidaException;

public class Validador {
    // Constructor privado para evitar la instanciación de la clase de utilidad
    private Validador() {
    }

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
    //NUEVO MÉTODO PARA VALIDAR ESTRICTAMENTE EL FORMATO Y LÓGICA DE LA FECHA
    public static void validarFecha(String fecha) throws ReservaInvalidaException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new ReservaInvalidaException("La fecha no puede estar vacía.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(java.time.format.ResolverStyle.STRICT);
            LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new ReservaInvalidaException(
                    "Fecha inválida o formato incorrecto. Use estrictamente YYYY-MM-DD (Ej: 2026-12-25).");
        }
    }
}
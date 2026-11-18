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
        // Solo acepta letras (incluyendo tildes, ñ y ü) y espacios en blanco
        if (!nombre.trim().matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$")) {
            throw new IllegalArgumentException("El nombre solo puede contener letras y espacios: " + nombre);
        }
    }

    public static void validarDocumento(String documento) {
        if (documento == null || documento.trim().length() < 5 || documento.trim().length() > 12) {
            throw new IllegalArgumentException("El documento debe tener entre 5 y 12 dígitos.");
        }
        for (char c : documento.trim().toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("El documento solo puede contener números.");
            }
        }
    }

    // MÉTODO PARA VALIDAR ESTRICTAMENTE EL FORMATO Y LÓGICA DE LA FECHA
    public static void validarFecha(String fecha) throws ReservaInvalidaException {
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new ReservaInvalidaException("La fecha no puede estar vacía.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
                    .withResolverStyle(java.time.format.ResolverStyle.STRICT);
            LocalDate.parse(fecha.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new ReservaInvalidaException(
                    "Fecha inválida o formato incorrecto. Use estrictamente YYYY-MM-DD (Ej: 2026-12-25).");
        }
    }

    // MÉTODO PARA VALIDAR HABITACIONES (3 pisos, habitaciones 01 a 20 por piso)
    public static void validarNumeroHabitacion(String numero) {
        if (numero == null || !numero.trim().matches("^[1-3](0[1-9]|1[0-9]|20)$")) {
            throw new IllegalArgumentException(
                    "Número de habitación inválido. Solo se permiten 3 pisos con 20 habitaciones c/u (101-120, 201-220, 301-320).");
        }
    }
}
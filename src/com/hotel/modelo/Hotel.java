package com.hotel.modelo;

import java.util.ArrayList;
import com.hotel.excepciones.EmpleadoNoEncontradoException;
import com.hotel.excepciones.HuespedNoEncontradoException;

public class Hotel {
    private String nombre;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Empleado> empleados;

    public Hotel(String nombre) {
        this.nombre = nombre;
        this.habitaciones = new ArrayList<>();
        this.empleados = new ArrayList<>();

        // Creación interna de las habitaciones iniciales (Composición)
        habitaciones.add(new Habitacion("101", 2));
        habitaciones.add(new Habitacion("102", 3));
        habitaciones.add(new Habitacion("201", 4));
    }

    public void agregarHabitacion(String numero, int capacidad) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de habitación no puede estar vacío.");
        }
        if (buscarHabitacion(numero) != null) {
            throw new IllegalArgumentException("Ya existe la habitación " + numero + ".");
        }
        habitaciones.add(new Habitacion(numero, capacidad));
        System.out.println("Habitación '" + numero + "' creada con capacidad " + capacidad);
    }

    public void agregarEmpleado(Empleado e) {
        empleados.add(e);
        System.out.println(e.getNombre() + " registrado en el hotel.");
    }

    public Habitacion buscarHabitacion(String numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero().equals(numero)) {
                return h;
            }
        }
        return null;
    }

    public Huesped buscarHuesped(String documento) throws HuespedNoEncontradoException {
        for (Habitacion habitacion : habitaciones) {
            try {
                return habitacion.buscarHuesped(documento);
            } catch (HuespedNoEncontradoException e) {
                // Continúa buscando en las demás habitaciones
            }
        }
        throw new HuespedNoEncontradoException(documento);
    }

    public Recepcionista buscarRecepcionista(String id) throws EmpleadoNoEncontradoException {
        for (Empleado e : empleados) {
            if (e.getId().equals(id) && e instanceof Recepcionista) {
                return (Recepcionista) e;
            }
        }
        throw new EmpleadoNoEncontradoException(id);
    }

    public void listarRecepcionistas() {
        System.out.println("--- Lista de Recepcionistas ---");
        boolean hayRecepcionistas = false;
        for (Empleado e : empleados) {
            if (e instanceof Recepcionista) {
                System.out.println(e.getId() + " - " + e.getNombre() + " (" + e.getCargo() + ")");
                hayRecepcionistas = true;
            }
        }
        if (!hayRecepcionistas) {
            System.out.println("No hay recepcionistas registrados.");
        }
    }

    public void generarReporteGeneral() {
        System.out.println("\n========== REPORTE GENERAL DEL HOTEL: " + nombre + " ==========");
        System.out.println("\n--- Habitaciones ---");
        for (Habitacion hab : habitaciones) {
            System.out.println("Habitación: " + hab.getNumero() + " | Ocupación: " + hab.getHuespedes().size() + "/"
                    + hab.getCapacidad());
        }

        System.out.println("\n--- Empleados (" + empleados.size() + ") ---");
        for (Empleado e : empleados) {
            // Polimorfismo dinámico: ejecuta el generarReporte() de Recepcionista o
            // Camarero
            System.out.println(e.generarReporte());
            System.out.println("----------------------------------------");
        }
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }
}
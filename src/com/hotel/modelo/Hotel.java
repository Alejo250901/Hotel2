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
        Validador.validarNumeroHabitacion(numero); // El hotel le esta pidiendo a la clase Validador que compruebe si el número de habitación recibido es válido
        if (buscarHabitacion(numero.trim()) != null) {
            throw new IllegalArgumentException("Ya existe la habitación " + numero + ".");
        }
        habitaciones.add(new Habitacion(numero.trim(), capacidad));
        System.out.println("Habitación '" + numero.trim() + "' creada con capacidad " + capacidad);
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

    //Este método sirve para podamos buscar un huésped utilizando su documento.//
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
            if (e.getId().equals(id) && e instanceof Recepcionista) { // instanceof es un operador de java que permite comprobar si un objeto pertenece a una clase determinada o a alguna de sus clases hijas
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

    public void listarHabitaciones() {
        System.out.println("--- Habitaciones Disponibles en el Hotel (Pisos 1-3: 101-120, 201-220, 301-320) ---");
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones creadas.");
            return;
        }
        for (Habitacion hab : habitaciones) {
            int ocupados = hab.getHuespedes().size();
            int cap = hab.getCapacidad();
            String estado = (ocupados < cap) ? "Disponible (" + (cap - ocupados) + " cupos)" : "LLENA";
            System.out.println(" - Habitación " + hab.getNumero() + " | Ocupación: " + ocupados + "/" + cap + " -> " + estado);
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
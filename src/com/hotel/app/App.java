package com.hotel.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import com.hotel.excepciones.*;
import com.hotel.modelo.*;

public class App {

    // Método auxiliar obligatorio para proteger lecturas numéricas
    private static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpiar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un valor numérico entero válido.");
                sc.nextLine(); // Limpiar buffer erróneo
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Huesped> huespedesRegistrados = new ArrayList<>();
        Hotel hotel = new Hotel("Hotel Sabana Real");

        // Registrar personal inicial
        Recepcionista r1 = new Recepcionista("Camila Prieto", "R001", "Recepción día");
        Recepcionista r2 = new Recepcionista("Andrés Mora", "R002", "Recepción noche");
        Camarero c1 = new Camarero("Luz Marina Díaz", "C001", "Housekeeping", "Piso 1");

        hotel.agregarEmpleado(r1);
        hotel.agregarEmpleado(r2);
        hotel.agregarEmpleado(c1);

        int opcion = -1;
        do {
            System.out.println("\n================ MENÚ HOTEL ================");
            System.out.println("1. Registrar huésped");
            System.out.println("2. Listar huéspedes registrados");
            System.out.println("3. Alojar huésped en una habitación");
            System.out.println("4. Hacer una reserva");
            System.out.println("5. Ver huéspedes de una habitación");
            System.out.println("6. Ver reservas de un recepcionista");
            System.out.println("7. Check-out de un huésped");
            System.out.println("8. Buscar huésped por documento");
            System.out.println("9. Reporte general del hotel");
            System.out.println("10. Crear nueva habitación");
            System.out.println("0. Salir");

            opcion = leerEntero(sc, "Seleccione una opción: ");

            try {
                switch (opcion) {
                    case 1:
                        System.out.println("\n--- Registrar Huésped ---");
                        int tipo = leerEntero(sc, "Tipo de huésped (1. Regular, 2. VIP): ");
                        if (tipo != 1 && tipo != 2) {
                            System.out.println("Tipo inválido. Volviendo al menú.");
                            break;
                        }
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("Documento: ");
                        String documento = sc.nextLine();
                        int edad = leerEntero(sc, "Edad: ");
                        int noches = leerEntero(sc, "Número de noches: ");

                        // CORRECCION: Validar que no exista un huésped con el mismo documento
                        for (Huesped existente : huespedesRegistrados) {
                            if (existente.getDocumento().equals(documento)) {
                                throw new IllegalArgumentException(
                                        "Ya existe un huésped registrado con el documento " + documento + ".");
                            }
                        }

                        if (tipo == 1) {
                            HuespedRegular hr = new HuespedRegular(nombre, documento, edad, noches);
                            huespedesRegistrados.add(hr);
                            System.out.println("¡Huésped regular registrado con éxito!");
                        } else {
                            int descuento = leerEntero(sc, "Descuento VIP (0-50%): ");
                            HuespedVIP hv = new HuespedVIP(nombre, documento, edad, noches, descuento);
                            huespedesRegistrados.add(hv);
                            System.out.println("¡Huésped VIP registrado con éxito!");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- Huéspedes Registrados ---");
                        if (huespedesRegistrados.isEmpty()) {
                            System.out.println("No hay huéspedes registrados en el sistema.");
                        } else {
                            for (Huesped h : huespedesRegistrados) {
                                System.out.println(" - " + h.obtenerInfo());
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n--- Alojar Huésped ---");
                        System.out.print("Número de habitación: ");
                        String numHab = sc.nextLine();
                        Habitacion habitacion = hotel.buscarHabitacion(numHab);
                        if (habitacion == null) {
                            System.out.println("Error: La habitación " + numHab + " no existe.");
                            break;
                        }
                        System.out.print("Documento del huésped a alojar: ");
                        String docAloj = sc.nextLine();

                        // Buscar en la lista general de registrados
                        Huesped huespedAEncontrar = null;
                        for (Huesped h : huespedesRegistrados) {
                            if (h.getDocumento().equals(docAloj)) {
                                huespedAEncontrar = h;
                                break;
                            }
                        }
                        if (huespedAEncontrar == null) {
                            throw new HuespedNoEncontradoException(docAloj);
                        }
                            //CORRECCION: Validar que el huésped no esté ya alojado en otra habitación
                        try {
                            hotel.buscarHuesped(docAloj);
                            throw new IllegalArgumentException("El huésped ya está alojado en una habitación.");
                        } catch (HuespedNoEncontradoException e) {
                        }
                        
                        habitacion.alojarHuesped(huespedAEncontrar);
                        break;

                    case 4:
                        System.out.println("\n--- Hacer Reserva ---");
                        hotel.listarRecepcionistas();
                        System.out.print("Ingrese ID del recepcionista: ");
                        String idRec = sc.nextLine();
                        Recepcionista recep = hotel.buscarRecepcionista(idRec);

                        System.out.print("Documento del huésped: ");
                        String docRes = sc.nextLine();
                        Huesped huespedRes = hotel.buscarHuesped(docRes);

                        System.out.print("Fecha de reserva (ej. 2026-12-25): ");
                        String fecha = sc.nextLine();

                        System.out.print("¿Desea agregar un servicio especial? (s/n): ");
                        String respServ = sc.nextLine();

                        if (respServ.equalsIgnoreCase("s")) {
                            System.out.print("Descripción del servicio: ");
                            String servicio = sc.nextLine();
                            recep.hacerReserva(fecha, servicio, huespedRes);
                        } else {
                            recep.hacerReserva(fecha, huespedRes);
                        }
                        break;

                    case 5:
                        System.out.println("\n--- Ver Huéspedes de Habitación ---");
                        System.out.print("Número de habitación: ");
                        String nHab = sc.nextLine();
                        Habitacion habVer = hotel.buscarHabitacion(nHab);
                        if (habVer == null) {
                            System.out.println("Error: La habitación no existe.");
                            break;
                        }
                        habVer.listarHuespedes();
                        break;

                    case 6:
                        System.out.println("\n--- Ver Reservas de Recepcionista ---");
                        hotel.listarRecepcionistas();
                        System.out.print("Ingrese ID del recepcionista: ");
                        String idRecV = sc.nextLine();
                        Recepcionista recVer = hotel.buscarRecepcionista(idRecV);

                        ArrayList<Reserva> listaRes = recVer.getReservas();
                        if (listaRes.isEmpty()) {
                            System.out.println("Este recepcionista no tiene reservas gestionadas.");
                        } else {
                            for (Reserva r : listaRes) {
                                System.out.println(" - " + r.getInfo());
                            }
                        }
                        break;

                    case 7:
                        System.out.println("\n--- Check-out de Huésped ---");
                        System.out.print("Número de habitación: ");
                        String nHabOut = sc.nextLine();
                        Habitacion habOut = hotel.buscarHabitacion(nHabOut);
                        if (habOut == null) {
                            System.out.println("Error: La habitación no existe.");
                            break;
                        }
                        System.out.print("Documento del huésped a retirar: ");
                        String docOut = sc.nextLine();
                        habOut.liberarHuesped(docOut);
                        break;

                    case 8:
                        System.out.println("\n--- Buscar Huésped ---");
                        System.out.print("Documento del huésped: ");
                        String docBusc = sc.nextLine();
                        Huesped hEncontrado = hotel.buscarHuesped(docBusc);
                        System.out.println("¡Encontrado!: " + hEncontrado.obtenerInfo());
                        break;

                    case 9:
                        hotel.generarReporteGeneral();
                        break;

                    case 10:
                        System.out.println("\n--- Crear Nueva Habitación ---");
                        System.out.print("Número de la nueva habitación: ");
                        String nuevoNum = sc.nextLine();
                        int nuevaCap = leerEntero(sc, "Capacidad: ");
                        hotel.agregarHabitacion(nuevoNum, nuevaCap);
                        break;

                    case 0:
                        System.out.println("Saliendo del sistema... ¡Hasta luego!");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (IllegalArgumentException | HabitacionLlenaException | HuespedNoEncontradoException
                    | EmpleadoNoEncontradoException | ReservaInvalidaException e) {
                System.out.println("Error de ejecución: " + e.getMessage());
            }

        } while (opcion != 0);

        sc.close();
    }
}
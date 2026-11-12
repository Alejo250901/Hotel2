## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
# Sistema de Gestión Hotelera - POO (Java)

Sistema de gestión desarrollado en Java para administrar habitaciones, registrar empleados (recepcionistas y camareros), organizar huéspedes (regulares y VIP) y gestionar reservas mediante un menú interactivo en consola, aplicando los principios de la Programación Orientada a Objetos (POO).

---

## 👥 Integrantes del Grupo
* **Sara Amortegui**, **Samuel Sanabria**, **Jaider Mendieta** y **Diego Sánchez**

---

## 📋 Generalidades del Proyecto
* **Objetivo:** Implementar un sistema robusto aplicando herencia, clases abstractas, modificadores (`final`), polimorfismo estático y dinámico, colecciones (`ArrayList`), y manejo de excepciones personalizadas.
* **Funcionalidades principales:**
  * Registro y control de huéspedes regulares y VIP.
  * Gestión de personal (recepcionistas con sobrecarga de métodos para reservas y camareros por zona).
  * Control de ocupación en habitaciones con manejo de capacidad y excepciones por cupo lleno.
  * Menú interactivo por consola protegido contra errores de lectura numérica (`InputMismatchException`).
 
  * <img width="1654" height="1081" alt="DIAGRAMA DE CLASES HOTEL" src="https://github.com/user-attachments/assets/1af794b8-bc65-4e0b-b2a1-637a27fce795" />


---

## 📁 Estructura del Proyecto
El código fuente se encuentra organizado en paquetes bajo la ruta `src/com/hotel/`:

```text
src/
└── com/
    └── hotel/
        ├── app/
        │   └── App.java                # Clase principal y menú interactivo
        ├── excepciones/
        │   ├── EmpleadoNoEncontradoException.java
        │   ├── HabitacionLlenaException.java
        │   ├── HuespedNoEncontradoException.java
        │   └── ReservaInvalidaException.java
        └── modelo/
            ├── Camarero.java
            ├── Empleado.java           # Clase abstracta
            ├── Habitacion.java         # Agregación con Huésped
            ├── Hotel.java              # Composición con Habitación
            ├── Huesped.java            # Clase abstracta
            ├── HuespedRegular.java
            ├── HuespedVIP.java         # Clase final
            ├── Recepcionista.java      # Sobrecarga de reservas
            ├── Reserva.java
            └── Validador.java          # Validación estática de datos


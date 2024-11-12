package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static HashMap<String, Map<String, Object>> empleados = new HashMap<>(); //Map original; contenedor padre
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú:");
            System.out.println("1. Añadir Empleado");
            System.out.println("2. Ver Empleados");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    añadirEmpleado();
                    break;
                case 2:
                    verEmpleados();
                    break;
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
                    break;
            }
        }
        scanner.close();
    }

    public static void añadirEmpleado() {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese los días de trabajo (separados por comas): ");
        List<String> dias = List.of(scanner.nextLine().split(","));

        System.out.print("Ingrese la disponibilidad para esos días (Sí/No separados por comas): ");
        List<String> disponibilidad = List.of(scanner.nextLine().split(","));

        List<List<String>> horarios = new ArrayList<>();
        for (String dia : dias) {
            System.out.print("Ingrese dos horarios para " + dia + " (separados por comas): ");
            List<String> horariosDia = List.of(scanner.nextLine().split(","));
            horarios.add(horariosDia);
        }

        System.out.print("Ingrese comentarios adicionales (separados por comas): ");
        List<String> comentarios = List.of(scanner.nextLine().split(","));

        Map<String, Object> empleadoData = new HashMap<>();
        empleadoData.put("Dias", dias);
        empleadoData.put("Disponibilidad", disponibilidad);
        empleadoData.put("Horarios", horarios);
        empleadoData.put("Comentarios", comentarios);

        empleados.put(nombre, empleadoData);
        System.out.println("Empleado añadido exitosamente.");
    }

    public static void verEmpleados() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
        } else {
            for (Map.Entry<String, Map<String, Object>> entry : empleados.entrySet()) {
                String nombre = entry.getKey();
                Map<String, Object> datos = entry.getValue();

                System.out.println("\nNombre: " + nombre);
                System.out.println("Días: " + datos.get("Dias"));
                System.out.println("Disponibilidad: " + datos.get("Disponibilidad"));
                System.out.println("Horarios: " + datos.get("Horarios"));
                System.out.println("Comentarios: " + datos.get("Comentarios"));
            }
        }
    }

}

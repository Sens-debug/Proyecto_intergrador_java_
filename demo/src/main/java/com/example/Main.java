package com.example;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Creamos todos los conjuntos de datos que requerimos ¿? El programa requiere
        // almacenar
        // información sobre los empleados, sus horarios, comentarios diarios,
        // disponibilidad diaria,.

        // Aquí se deben inicializar estos conjuntos

        // Conjunto contenedor de las credenciales<usuario,contraseña>
        ArrayList<String> listaUsuarios = new ArrayList<String>();
        ArrayList<String> listaContraseñas = new ArrayList<String>();

        // Set conjunto contenedor de DiasLaborables -> DISPONIBILIDAD
        HashMap<String, HashMap<String, Boolean>> DiasLaborables = new HashMap<>();
        // Lista seter del Boolean en DiasLaborables
        List<Boolean> diasLaborablesBoolean = List.of(true, true, true, true, true, true, true);
        // conjunto "valor" de DiasLaborables

        // Lista SETER de maps
        List<String> diasSemana = List.of("domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado");

        // Set conjunto contenedor de RegistrosHorarios
        // Se setea con <diasSemana> como clave del valor
        HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras = new HashMap<>();

        // Set conjunto contenedor de ComentariosDiarios
        HashMap<String, HashMap<String, List<String>>> comentariosDiarios = new HashMap<>();

        // ----------------------------------------------------------------
        System.out.println(" credenciales admin : usr=0 y pasw=0");
        String usuarioAdmin = "0";
        String claveAdmin = "0";

        System.out.println("<----Bienvenido a la interfaz de registro de horarios---->");
        while (true) {
            System.out.println("<--------------------------->");
            System.out.println("Elija una opción válida");
            Scanner scanner2 = new Scanner(System.in);
            System.out.println("1 - Iniciar Sesión");
            System.out.println("2 - Salir");
            int opcion = scanner2.nextInt();

            switch (opcion) {
                case 1:
                    Scanner teclado = new Scanner(System.in);
                    System.out.println("Ingrese su nombre de usuario:");
                    String usuario = teclado.nextLine();
                    System.out.println("Ingrese su contraseña:");
                    String contraseña = teclado.nextLine();
                    if (usuario.equals(usuarioAdmin) && contraseña.equals(claveAdmin)) {
                        sesionAdministrador(listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean,
                                DiasLaborables, registroDeHoras, comentariosDiarios);
                    } else {
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            if (listaUsuarios.get(i).equals(usuario) && listaContraseñas.get(i).equals(contraseña)) {
                                System.out.println("Bienvenido al Sistema,  " + usuario);
                                sesionEmpleado(registroDeHoras, usuario, DiasLaborables, comentariosDiarios,
                                        diasSemana);

                            }
                        }
                    }
                    break;

                case 2:
                    System.out.println("Gracias por usar nuestro sistema.");
                    System.exit(0);

                default:
                    System.out.println("Opción inválida");
                    break;
            }

        }

    }

    public static void sesionAdministrador(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas,
            List<String> diasSemana,
            List<Boolean> diasLaborablesBoolean,
            HashMap<String, HashMap<String, Boolean>> diasLaborables,
            HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios) {
        boolean centinelaMenuAdmin = true;
        while (centinelaMenuAdmin) {
            System.out.println("<------------------------------------------>");
            System.out.println("Bienvenido - administrador");
            System.out.println("<------------------------------------------>");
            System.out.println("Seleccione ");
            System.out.println("1.Opciones con Usuarios\n2.Opciones con los registros de Usuarios\n3.Salir");
            Scanner scanner3 = new Scanner(System.in);
            int opcion = scanner3.nextInt();
            switch (opcion) {
                case 1:
                System.out.println("<--------------------------------->");
                    System.out.println("1.Crear Usuario\n2.Almacenar Comentarios\n3.Cerrar Sesion");
                    int opcion2 = scanner3.nextInt();
                    switch (opcion2) {
                        case 1:
                            crearUsuario(listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean,
                                    diasLaborables,
                                    registroDeHoras, comentariosDiarios);

                            break;
                        case 2:
                        System.out.println("");
                            agregarComentarios(listaUsuarios, comentariosDiarios, diasSemana);
                            System.out.println("");
                            break;
                        case 3:
                            System.out.println("Cerrando Sesion");
                            centinelaMenuAdmin = false;
                            return;
                        default:
                            System.out.println("OPCIÓN INVALIDA");
                            break;
                    }// Switch anidado
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1.Imprimir Registros Horarios\n2.Cerrar Sesion");
                    int opcion3 = scanner.nextInt();
                    switch (opcion3) {
                        case 1:
                            impresionRegistrosHorarios(registroDeHoras, diasSemana, listaUsuarios);
                            break;

                        case 2:
                            System.out.println("Cerrando sesion");
                            return;

                        default:
                            System.out.println("Opción inválida");
                            break;
                    }

                default:
                    System.out.println("Opcion inválida");
                    break;
            }
        }
    }

    public static void crearUsuario(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas,
            List<String> diasSemana,
            List<Boolean> diasLaborablesBoolean,
            HashMap<String, HashMap<String, Boolean>> diasLaborables,
            HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese las credenciales del nuevo usuario");
        System.out.println("Usuario :");
        String usuario = scanner.nextLine();
        System.out.println("Contraseña :");
        String contraseña = scanner.nextLine();
        if (contraseña.isBlank() || usuario.isBlank()) {
            System.out.println("Credenciales Invalidas");
            System.out.println("<------------------------->");
            return;
        } else {
            for (int i = 0; i < listaUsuarios.size(); i++) {
                if (listaUsuarios.get(i).equals(usuario)) {
                    System.out.println("Este nombre de usuario ya existe");
                    return;
                }

            }
            listaUsuarios.add(usuario);
            listaContraseñas.add(contraseña);

            // INICIALIZAMOS TODOS LOS CONJUNTOS

            // INICIAMOS EL CONJUNTO DE DIAS LABORABLES
            HashMap<String, Boolean> setDiasLaborables = new HashMap<>();
            for (String dia : diasSemana) {
                setDiasLaborables.put(dia, true);
            }
            // Creamos un map anidado cuya clave es el nombre del empleado, y la key son
            // todos los datos a manejar
            diasLaborables.put(usuario, setDiasLaborables);

            // INICIAMOS EL CONJUNTO DE COMENTARIOS
            // Empezamos seteando el mapa anidado
            HashMap<String, List<String>> comentarios = new HashMap<>();
            // Seteamos los dias de la semana
            for (String dia : diasSemana) {
                comentarios.put(dia, null);
            }
            comentariosDiarios.put(usuario, comentarios);

            // INICIAMOS EL CONJUNTO DE LAS HORAS
            // empezamos con el anidado
            HashMap<String, LocalTime[]> registros = new HashMap<>();
            for (String dia : diasSemana) {
                registros.put(dia, null);
            }
            registroDeHoras.put(usuario, registros);
            System.out.println("");
            System.out.println("Se creó el empleado y se inicializaron todos sus conjuntos de datos");
            System.out.println("");

        }

    }

    public static void sesionEmpleado(HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras, String usuario,
            HashMap<String, HashMap<String, Boolean>> diasLaborables,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            List<String> diasSemana) {
        boolean centinelaMenuEmpleado = true;
        while (centinelaMenuEmpleado) {
            System.out.println("<--------Seleccione--------> ");
            System.out.println("1. Registrar horas: ");
            System.out.println("2. Leer comentarios");
            System.out.println("3. Cerrar sesión. ");
            Scanner teclado2 = new Scanner(System.in);
            int opcion = teclado2.nextInt();
            switch (opcion) {
                case 1:
                    String claveSeleccionada = selectorDias(diasLaborables, diasSemana, usuario);
                    if (diasLaborables.get(usuario).get(claveSeleccionada) != false) {
                        Scanner teclado3 = new Scanner(System.in);
                        LocalTime[] horasTemporal = new LocalTime[2];
                        System.out
                                .println(
                                        "Ingrese su hora de entrada(Formato 24HR'S). Después se le pedirán los minutos");
                        int horaIn = teclado3.nextInt();
                        System.out.println("Ingrese los minutos (Si entro en punto escriba (0)");
                        int minIn = teclado3.nextInt();
                        if (horaIn > 23 || horaIn < 0 || minIn > 59 || minIn < 0) {
                            System.out.println("RANGO HORARIO INVALIDO ");
                            break;
                        }
                        System.out.println(
                                "Ingrese su hora de salida(Formato 24HR'S). Después se le pedirán los minutos");
                        int horaOut = teclado3.nextInt();
                        System.out.println("Ingrese los minutos (Si entro en punto escriba (0)");
                        int minOut = teclado3.nextInt();
                        if (horaOut > 23 || horaOut < 0 || minOut > 59 || minOut < 0) {
                            System.out.println("RANGO HORARIO INVALIDO");
                            break;
                        }
                        LocalTime horaIngreso = LocalTime.of(horaIn, minIn);
                        LocalTime horaSalida = LocalTime.of(horaOut, minOut);
                        if (horaIngreso.isAfter(horaSalida)) {
                            System.out.println("Registro no efectuado-- Problemas con las horas ingresadas");
                            return;
                        }
                        horasTemporal[0] = horaIngreso;
                        horasTemporal[1] = horaSalida;

                        registroDeHoras.get(usuario).put(claveSeleccionada, horasTemporal);
                        diasLaborables.get(usuario).put(claveSeleccionada, false);
                    } else {
                        System.out.println("Día invalido");
                    }

                    break;

                case 2:
                    imprimirComentarios(usuario, comentariosDiarios, diasSemana);

                case 3:
                    System.out.println("Se ha cerrado la sesión");
                    centinelaMenuEmpleado = false;
                    return;

                default:
                    System.out.println("Selección inválida");
                    break;
            }
        }
    }

    public static String selectorDias(HashMap<String, HashMap<String, Boolean>> diasDisponibles,
            List<String> diasSemana, String usuario) {
        System.out.println("Seleccione a través del indice");
        for (int i = 0; i < diasSemana.size(); i++) {
            System.out.println((i) + ". " + diasSemana.get(i) + " Disponibilidad? "
                    + diasDisponibles.get(usuario).get(diasSemana.get(i)));
        }
        Scanner scanner4 = new Scanner(System.in);
        int seleccion = scanner4.nextInt();
        String claveSeleccion = diasSemana.get(seleccion);
        return claveSeleccion;
    }

    public static void impresionRegistrosHorarios(
            HashMap<String, HashMap<String, LocalTime[]>> registroHoras, List<String> diasSemana,
            ArrayList<String> listaEmpleados) {

        for (int z = 0; z < listaEmpleados.size(); z++) {
            String user = listaEmpleados.get(z);

            System.out.println("-------------------------------------\n" + user + "--> Registro de Empleado:");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            for (String key : diasSemana) {
                LocalTime[] horasOrigen = registroHoras.get(user).get(key);

                if (registroHoras.get(user).get(key) != null) {
                    String[] horas = new String[horasOrigen.length];
                    for (int i = 0; i < horas.length; i++) {
                        horas[i] = horasOrigen[i].format(formatter);
                    }

                    // LocalTime horaEntrada = LocalTime.parse(horas[0],
                    // DateTimeFormatter.ofPattern("hh:mm a"));
                    // LocalTime horaSalida = LocalTime.parse(horas[1],
                    // DateTimeFormatter.ofPattern("hh:mm a"));
                    Duration duracion = Duration.between(horasOrigen[0], horasOrigen[1]);
                    long horasTrabajadas = duracion.toHours();
                    long minutosTrabajados = duracion.toMinutes() % 60;
                    String hrs = Arrays.toString(horas);
                    System.out.println("DIA -> " + key + ", Horas: " + hrs + ", Duración: " + horasTrabajadas
                            + " Horas " + minutosTrabajados + " Minutos");
                    // System.out.println("EMPLEADO: " + user + ", HORAS: " + hrs );
                } else {
                    System.out.println("Día " + key + ", No hay registros");
                }
            }

            System.out.println("-------------------------------------------------");
        }
    }

    public static void imprimirComentarios(String usuario,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios, List<String> diasSemana) {
        System.out.println("Los comentarios que le han asignado son --->");
        for (String dia : diasSemana) {
            System.out.println(dia);
            if (comentariosDiarios.get(usuario).get(dia) == null) {
                System.out.println("No hay comentarios");
                continue;
            } else {
                for (String comentario : comentariosDiarios.get(usuario).get(dia)) {
                    System.out.println("- " + comentario);
                }
            }
        }
    }

    public static void agregarComentarios(ArrayList<String> listaUsuarios,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            List<String> diasSemana) {
        // Contiene la clave en texto del empleado a "trabajar"
        String empleadoSeleccionado = selectorEmpleado(listaUsuarios);
        if (empleadoSeleccionado == null) {
            System.out.println("No hay empleados a los que asignar comentarios");
            return;

        }
        String diaSeleccionado = selectorDias(diasSemana);
        // Este condicional controla como se prosigue sí no hay comentarios existentes
        if (comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado) == null) {

            System.out.println("Ingrese sus comentarios para el empleado: " + empleadoSeleccionado);
            ArrayList<String> comentariosTemporal = new ArrayList<>();
            boolean exec = true;
            while (exec) {
                System.out.println("El ingreso de comentarios se mantendrá valido hasta que escriba 'fin");
                Scanner scanner2 = new Scanner(System.in);
                String comentario = scanner2.nextLine();
                if (comentario.equalsIgnoreCase("fin")) {
                    return;
                } else {
                    comentariosTemporal.add(comentario);
                    comentariosDiarios.get(empleadoSeleccionado).put(diaSeleccionado, comentariosTemporal);
                    System.out.println("Se guardó correctamente el comentario");
                }
            }
            // Este condicional controla como se prosigue sí ya hay comentarios existentes
        } else {
            ArrayList<String> comentariosT = new ArrayList<>(
                    comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado));
            boolean exec2 = true;
            while (exec2) {
                System.out.println("El ingreso de comentarios se mantendrá valido hasta que escriba 'fin");
                Scanner scanner2 = new Scanner(System.in);
                String comentario = scanner2.nextLine();
                if (comentario.equalsIgnoreCase("fin")) {
                    return;
                } else {
                    comentariosT.add(comentario);
                    comentariosDiarios.get(empleadoSeleccionado).put(diaSeleccionado, comentariosT);
                    System.out.println("Se guardó correctamente el comentario");
                }
            }
        }
    }
    public static String selectorEmpleado(ArrayList<String> listaUsuarios) {
        if (listaUsuarios.size() > 0) {
            System.out.println("Elija el indice del empleado a comentar");
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < listaUsuarios.size(); i++) {
                System.out.println("Indice -> " + i + " Nombre -> " + listaUsuarios.get(i));
            }
            int seleccion = scanner.nextInt();
            String claveSeleccion = listaUsuarios.get(seleccion);
            return claveSeleccion;
        } else {
            System.out.println("No hay empleado ");
            return null;
        }
    }

    public static String selectorDias(List<String> diasSemana) {
        System.out.println("Elija el día que desea registrar");
        for (int i = 0; i < diasSemana.size(); i++) {
            System.out.println("Indice -> " + i + " Dia -> " + diasSemana.get(i));
        }
        Scanner scanner = new Scanner(System.in);
        int seleccion = scanner.nextInt();
        String claveSeleccion = diasSemana.get(seleccion);
        return claveSeleccion;
    }
}

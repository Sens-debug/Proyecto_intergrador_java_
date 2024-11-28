package com.example;

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
        // Lista setter del Boolean en DiasLaborables
        final List<Boolean> diasLaborablesBoolean = List.of(false, true, true, true, true, true, false);
        // conjunto "valor" de DiasLaborables

        // Lista SETTER de maps
        final List<String> diasSemana = List.of("domingo", "lunes", "martes", "miércoles", "jueves", "viernes",
                "sábado");

        // Set conjunto contenedor de RegistrosHorarios
        // Se settea con <diasSemana> como clave del valor
        HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras = new HashMap<>();

        // Set conjunto contenedor de ComentariosDiarios
        HashMap<String, HashMap<String, List<String>>> comentariosDiarios = new HashMap<>();

        // ----------------------------------------------------------------
        System.out.println(" credenciales admin : usr=0 y psw=0");
        final String usuarioAdmin = "0";
        final String claveAdmin = "0";

        // Setteo empleados para testing
        crearUsuarioTesting("David","1", listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean, DiasLaborables, registroDeHoras, comentariosDiarios);
        crearUsuarioTesting("Brahian","2", listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean, DiasLaborables, registroDeHoras, comentariosDiarios);
        crearUsuarioTesting("Juan","3", listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean, DiasLaborables, registroDeHoras, comentariosDiarios);

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
            System.out.println();
            System.out.println("Seleccione ");
            System.out.println(
                    "1.Opciones con Usuarios\n2.Opciones con los registros de Usuarios\n3.Mostrar Credenciales\n4.Cerrar Sesion");
            Scanner scanner3 = new Scanner(System.in);
            int opcion = scanner3.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("<--------------------------------->");
                    System.out.println(
                            "1.Crear Usuario\n2.Modificar Turnos\n3.Almacenar Comentarios\n4.Editar Comentarios\n5.Eliminar Comentarios\n6.Eliminar Credencial\n7.Cerrar Sesion");
                    int opcion2 = scanner3.nextInt();
                    switch (opcion2) {
                        case 1:
                            crearUsuario(listaUsuarios, listaContraseñas, diasSemana, diasLaborablesBoolean,
                                    diasLaborables,
                                    registroDeHoras, comentariosDiarios);

                            break;
                        case 2:
                            System.out.println();
                            modificarDiasLaborables(diasLaborables, diasSemana, listaUsuarios);
                            System.out.println();
                            break;
                        case 3:
                            System.out.println("");
                            agregarComentarios(listaUsuarios, comentariosDiarios, diasSemana);
                            System.out.println("");
                            break;
                        case 4:
                            editarComentarios(listaUsuarios, comentariosDiarios, diasSemana);
                            break;
                        case 5:
                            borrarComentarios(listaUsuarios, comentariosDiarios, diasSemana);
                            break;
                        case 6:
                            System.out.println();
                            eliminarEmpleado(listaUsuarios, listaContraseñas);
                            System.out.println();
                            break;
                        case 7:
                            System.out.println("Cerrando Sesion");
                            centinelaMenuAdmin = false;
                            return;
                        default:
                            System.out.println("OPCIÓN INVALIDA");
                            break;
                    }// Cierre del Switch anidado
                    break; // cierre del case 1
                case 2:
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("1.Imprimir Registros Horarios\n2.Modificar Registros\n3.Cerrar Sesion");
                    int opcion3 = scanner.nextInt();
                    switch (opcion3) {

                        case 1:
                            impresionRegistrosHorarios(comentariosDiarios, registroDeHoras, diasSemana, listaUsuarios);
                            break;

                        case 2:
                            modificarHorasRegistradas(comentariosDiarios, registroDeHoras, diasSemana,
                                    listaContraseñas);
                            break;

                        case 3:
                            System.out.println("Cerrando sesion");
                            return;

                        default:
                            System.out.println("Opción inválida");
                            break;
                    }
                    break;

                case 3:
                    System.out.println();
                    mostrarCredenciales(listaUsuarios, listaContraseñas);
                    System.out.println();
                    break;

                case 4:
                    System.out.println("Cerrando sesion");
                    return;
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
            System.out.println("Credenciales Invalidas-VACÍAS");
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
            // aurorellenador del map diasLaborables
            for (String dia : diasSemana) {
                // condiciona que si los dias son != de domingo y sabado (dias inmutables)
                // enotnces son validos
                if (dia != diasSemana.get(0) && dia != diasSemana.get(6)) {
                    setDiasLaborables.put(dia, true);
                } // condiciona que si los dias son == de domingo y sabado (dias inmutables)
                  // entonces son invalidos
                else {
                    setDiasLaborables.put(dia, false);
                }

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

    //Mismo funcionamiento que el propio del codigo, solo que no es dinamico y se introducen los valores desde la llamada
    public static void crearUsuarioTesting(String usuario, String contraseña,ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas,
            List<String> diasSemana,
            List<Boolean> diasLaborablesBoolean,
            HashMap<String, HashMap<String, Boolean>> diasLaborables,
            HashMap<String, HashMap<String, LocalTime[]>> registroDeHoras,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios) {
            Scanner scanner = new Scanner(System.in);
       
            listaUsuarios.add(usuario);
            listaContraseñas.add(contraseña);

            // INICIALIZAMOS TODOS LOS CONJUNTOS

            // INICIAMOS EL CONJUNTO DE DIAS LABORABLES
            HashMap<String, Boolean> setDiasLaborables = new HashMap<>();
            // aurorellenador del map diasLaborables
            for (String dia : diasSemana) {
                // condiciona que si los dias son != de domingo y sabado (dias inmutables)
                // enotnces son validos
                if (dia != diasSemana.get(0) && dia != diasSemana.get(6)) {
                    setDiasLaborables.put(dia, true);
                } // condiciona que si los dias son == de domingo y sabado (dias inmutables)
                  // entonces son invalidos
                else {
                    setDiasLaborables.put(dia, false);
                }

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
                    String claveSeleccionada = selectorDiasRegistro(diasLaborables, diasSemana, usuario);
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
                    break;

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

    public static String selectorDiasRegistro(HashMap<String, HashMap<String, Boolean>> diasDisponibles,
            List<String> diasSemana, String usuario) {
        System.out.println("Seleccione a través del indice");
        for (int i = 0; i < diasSemana.size(); i++) {
            if (diasDisponibles.get(usuario).get(diasSemana.get(i)) != true) {
                System.out
                        .println("Indice -> " + i + " || Dia -> " + diasSemana.get(i) + " Se encuentra Deshabilitado");
            } else {
                System.out.println("Indice -> " + i + " || Dia ->" + diasSemana.get(i) + " Se encuentra Habilitado");
            }

        }
        Scanner scanner4 = new Scanner(System.in);
        int seleccion = scanner4.nextInt();
        String claveSeleccion = diasSemana.get(seleccion);
        return claveSeleccion;
    }

    public static void impresionRegistrosHorarios(
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            HashMap<String, HashMap<String, LocalTime[]>> registroHoras, List<String> diasSemana,
            ArrayList<String> listaEmpleados) {
        if (listaEmpleados.size() < 1) {
            System.out.println("NO HAY EMPLEADOS REGISTRADOS");
            return;
        }

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

                    Duration duracion = Duration.between(horasOrigen[0], horasOrigen[1]);
                    // variable almacena la cantidad total de horas trabajadas
                    long horasTrabajadas = duracion.toHours();
                    // almacena la cantidad de minutos correspondientes al registro horario
                    long minutosTrabajados = duracion.toMinutes() % 60;
                    // almacena los registros de las horas, en un formato imprimible
                    String hrs = Arrays.toString(horas);
                    // Si las horas trabajadas son mayores a 8 horas, imprime el número de horas
                    // extra
                    if (horasTrabajadas > 8 && comentariosDiarios.get(user).get(key) != null) {
                        long horasExtra = horasTrabajadas - 8;
                        System.out.println("DIA -> " + key + ", Registro: " + hrs + ", Duración: " + horasTrabajadas
                                + " Horas " + minutosTrabajados + " Minutos" + "|| Horas Extra? = " + horasExtra + "\n"
                                + " -Comentarios = " + comentariosDiarios.get(user).get(key));
                    } else {
                        // comprueba si hay comentarios
                        if (comentariosDiarios.get(user).get(key) != null) {
                            // Mensaje predeterminado
                            System.out.println("DIA -> " + key + ", Registro: " + hrs + ", Duración: " + horasTrabajadas
                                    + " Horas " + minutosTrabajados + " Minutos" + "\n" + " -Comentarios = "
                                    + comentariosDiarios.get(user).get(key));
                        }
                        if (horasTrabajadas > 8) {
                            long horasExtra = horasTrabajadas - 8;
                            System.out.println("DIA -> " + key + ", Registro: " + hrs + ", Duración: " + horasTrabajadas
                                    + " Horas " + minutosTrabajados + " Minutos" + "|| Horas Extra? = " + horasExtra);
                        } else// si no hay comentarios entonces imprime esta plantilla ni horas extra
                        {
                            System.out.println("DIA -> " + key + ", Registro: " + hrs + ", Duración: " + horasTrabajadas
                                    + " Horas " + minutosTrabajados + " Minutos " + "No hay comentarios almacenados");
                        }
                    }

                } else {
                    if (comentariosDiarios.get(user).get(key) != null) {
                        System.out.println("Día " + key + ", No hay registros\n -Comentarios: "
                                + comentariosDiarios.get(user).get(key));
                    } else {
                        System.out.println("Día " + key + ", No hay registros");
                    }
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
                    System.out.println(" - " + comentario);
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
            System.out.println();
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
                }
                if (comentario.isBlank()) {
                    System.out.println("Comentario vació. No se guardó");
                    continue;
                }

                comentariosTemporal.add(comentario);
                comentariosDiarios.get(empleadoSeleccionado).put(diaSeleccionado, comentariosTemporal);
                System.out.println("Se guardó correctamente el comentario");
            }
        } // Este condicional controla como se prosigue sí ya hay comentarios existentes
        else {
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

    public static void editarComentarios(ArrayList<String> listaUsuarios,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            List<String> diasSemana) {
        // contenedor de la Key del usuario a modificar
        String empleadoSeleccionado = selectorEmpleado(listaUsuarios);
        if (empleadoSeleccionado != null) {
            System.out.println("Comentarios almacenados para -> " + empleadoSeleccionado);
            for (int x = 0; x < diasSemana.size(); x++) {
                System.out.println("Dia -> " + diasSemana.get(x) + " || Comentarios -> "
                        + comentariosDiarios.get(empleadoSeleccionado).get(diasSemana.get(x)));
            }
            System.out.println();
            String diaSeleccionado = selectorDias(diasSemana);
            if (comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado) != null) {
                System.out.println("Elija el índice del comentario que desea editar");
                for (int i = 0; i < comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).size(); i++) {
                    System.out.println("Indice -> " + i + " Comentario -> "
                            + comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).get(i));
                }
                Scanner scanner = new Scanner(System.in);
                int seleccion = scanner.nextInt();
                if (seleccion >= 0
                        && seleccion < comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).size()) {
                    System.out.println("Escriba el comentario nuevo : ");
                    Scanner scanner2 = new Scanner(System.in);
                    String nuevoComentario = scanner2.nextLine();
                    ArrayList<String> comentariosActualizados = new ArrayList<String>(
                            comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado));
                    comentariosActualizados.set(seleccion, nuevoComentario);
                    comentariosDiarios.get(empleadoSeleccionado).put(diaSeleccionado, comentariosActualizados);
                    System.out.println("Comentario actualzado con exito");
                }

            } else {
                System.out.println("No hay comentarios almacenados. Imposible editar");
                return;
            }

        }

    }

    public static void borrarComentarios(ArrayList<String> listaUsuarios,
            HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            List<String> diasSemana) {
        boolean centinelaEliminarComentarios = true;
        while (centinelaEliminarComentarios) {
            System.out.println("Desea continuar? Y/N (N finaliza la seccion)");
            Scanner scanner3 = new Scanner(System.in);
            String respuesta = scanner3.nextLine();
            if (respuesta.equalsIgnoreCase("N")) {
                System.out.println("Saliendo...");
                return;
            }
            // contenedor de la Key del usuario a modificar
            String empleadoSeleccionado = selectorEmpleado(listaUsuarios);
            if (empleadoSeleccionado != null) {
                System.out.println("Comentarios almacenados para -> " + empleadoSeleccionado);
                for (int x = 0; x < diasSemana.size(); x++) {
                    System.out.println("Dia -> " + diasSemana.get(x) + "|| Comentarios -> "
                            + comentariosDiarios.get(empleadoSeleccionado).get(diasSemana.get(x)));
                }
                System.out.println();
                String diaSeleccionado = selectorDias(diasSemana);
                if (comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado) != null) {
                    System.out.println("Elija el índice del comentario que desea borrar");
                    for (int i = 0; i < comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).size(); i++) {
                        System.out.println("Indice -> " + i + " Comentario -> "
                                + comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).get(i));
                    }
                    Scanner scanner77 = new Scanner(System.in);
                    int seleccionDia = scanner77.nextInt();
                    System.out.println("Se ha eliminado corretamente el comentario '"
                            + comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).get(seleccionDia)
                            + "'");
                    comentariosDiarios.get(empleadoSeleccionado).get(diaSeleccionado).remove(seleccionDia);
                } else {
                    System.out.println("No hay comentarios para este dia");
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
            System.out.println("No hay empleados ");
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

    public static void modificarHorasRegistradas(HashMap<String, HashMap<String, List<String>>> comentariosDiarios,
            HashMap<String, HashMap<String, LocalTime[]>> registroHoras,
            List<String> diasSemana,
            ArrayList<String> listaEmpleados) {
        System.out.println("Desea ver los registros? Y/N");
        Scanner scanner6 = new Scanner(System.in);
        String eleccion = scanner6.nextLine();
        if (eleccion.equalsIgnoreCase("Y")) {
            impresionRegistrosHorarios(comentariosDiarios, registroHoras, diasSemana, listaEmpleados);
        }

        String empleadoSeleccionado = selectorEmpleado(listaEmpleados);
        if (empleadoSeleccionado == null) {
            return;
        }
        System.out.println("Seleccine el dia que quiere modificar");
        String diaSeleccionado = selectorDias(diasSemana);
        if (registroHoras.get(empleadoSeleccionado).get(diaSeleccionado) != null) {
            System.out.println("Elija el índice de la hora que desea modificar");
            for (int i = 0; i < registroHoras.get(empleadoSeleccionado).get(diaSeleccionado).length; i++) {
                System.out.println("Indice -> " + i + "|| Hora registrada -> "
                        + registroHoras.get(empleadoSeleccionado).get(diaSeleccionado)[i]);
            }
            Scanner scanner7 = new Scanner(System.in);
            int indiceHoraAModificar = scanner7.nextInt();
            System.out.println("Ingrese la hora nueva (formato 24hr's) Los minutos se le solicitaran despues");
            int horaN = scanner7.nextInt();
            System.out.println("Ingrese los minutos nuevos si entró en punto, escriba '0'");
            int minN = scanner7.nextInt();
            LocalTime nuevaHora = LocalTime.of(horaN, minN);
            registroHoras.get(empleadoSeleccionado).get(diaSeleccionado)[indiceHoraAModificar] = nuevaHora;
        } else {
            System.out.println("NO HAY REGISTROS. IMPOSIBLE MODIFICAR");
        }

    }

    public static void modificarDiasLaborables(HashMap<String, HashMap<String, Boolean>> diasLaborables,
            List<String> diasSemana,
            ArrayList<String> listaEmpleados) {
        boolean centinelaModificarDiasLaborables = true;
        while (centinelaModificarDiasLaborables) {
            if (listaEmpleados.size() < 1) {
                System.out.println("NO HAY EMPLEADOS. IMPOSIBLE PROSEGUIR");
                return;
            }
            System.out.println("Desea continuar? Y/N");
            Scanner scanner6 = new Scanner(System.in);
            String eleccion8 = scanner6.nextLine();
            if (eleccion8.equalsIgnoreCase("N")) {
                centinelaModificarDiasLaborables = false;
                return;
            }

            System.out.println("--------------------------------");
            System.out.println(
                    "Desea imprimir los turnos de TODOS los empleado (A)? o desea seleccionar el empleado a mostrar (C)? A/C");
            Scanner scanner7 = new Scanner(System.in);
            String eleccion = scanner7.nextLine();
            // Sí el usuario desea imprimir todos los regs entonces se ejecuta el siguiente
            // bloque
            if (eleccion.equalsIgnoreCase("a")) {
                // for anidado debido a la necesidad de usar 2 variables dinamicas
                for (String emp : listaEmpleados) {
                    System.out.println("Empleado -> " + emp);
                    for (String dia : diasSemana) {
                        // Mensaje condicionado por el contenido de las sentencias Boolean
                        if (diasLaborables.get(emp).get(dia) != false) {
                            System.out.println(" -Dia: " + dia + " -> Se encuentra habilitado");
                        }
                        if (diasLaborables.get(emp).get(dia) == false) {
                            System.out.println(" -Dia: " + dia + " -> Se encuentra deshabilitado");
                        }
                    }
                }
                System.out.println();
            }
            if (eleccion.equalsIgnoreCase("c")) {
                String seleccionEmpleado = selectorEmpleado(listaEmpleados);
                System.out.println("Empleado -> " + seleccionEmpleado);
                // itera los dias con el objetivo de usar la variable each
                for (String dia : diasSemana) {
                    // imprime un mensaje según la condicion
                    if (diasLaborables.get(seleccionEmpleado).get(dia) != false) {
                        System.out.println(" -Dia: " + dia + " -> Se encuentra Habilitado");
                    }
                    // Imprimible según la condición
                    if (diasLaborables.get(seleccionEmpleado).get(dia) == false) {
                        System.out.println(" -Dia: " + dia + " -> Se encuentra Deshabilitado");
                    }

                }
                System.out.println();
            }
            System.out.println("Desea modificar los turnos? Y/N");
            Scanner scanner8 = new Scanner(System.in);
            String eleccionModificar = scanner8.nextLine();
            if (eleccionModificar.equalsIgnoreCase("Y")) {
                String empleadoSel = selectorEmpleado(listaEmpleados);
                System.out.println("Desea habilitar o deshabilitar algún turno de " + empleadoSel
                        + "?\nHabilitar(E) -- Deshabilitar (A)");
                String eleccion2 = scanner8.nextLine();
                if (eleccion2.equalsIgnoreCase("E")) {
                    String diaSeleccionado = selectorDias(diasSemana);
                    if (diasLaborables.get(empleadoSel).get(diaSeleccionado) == true) {
                        System.out.println("Dia ya Habilitado");
                        System.out.println();
                        continue;
                    } else {
                        diasLaborables.get(empleadoSel).put(diaSeleccionado, true);
                        System.out.println("Dia habilitado Exitosamente...");
                        System.out.println();
                    }
                }
                if (eleccion2.equalsIgnoreCase("A")) {
                    String diaSeleccionado = selectorDias(diasSemana);
                    if (diasLaborables.get(empleadoSel).get(diaSeleccionado) == false) {
                        System.out.println("Dia ya Deshabilitado");
                        System.out.println();
                        continue;
                    } else {
                        diasLaborables.get(empleadoSel).put(diaSeleccionado, false);
                        System.out.println("Dia deshabilitado Exitosamente...");
                        System.out.println();
                    }

                }

            }
        } // Fin de while

    }

    public static void mostrarCredenciales(ArrayList<String> listaEmpleados,
            ArrayList<String> listaContraseñas) {
        if (listaEmpleados.size() < 1) {
            System.out.println("NO HAY EMPLEADOS");
            return;
        }
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println("-Empleado: " + listaEmpleados.get(i));
            System.out.println(" -Contraseña: " + listaContraseñas.get(i));
            System.out.println();
        }
    }
    public static void eliminarEmpleado(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas){
        if(listaUsuarios.size() < 1){
            System.out.println("NO HAY EMPLEADOS");
            return;
        }
        String empleadoSeleccionado = selectorEmpleado(listaUsuarios);
        int indiceEmpleado = listaUsuarios.indexOf(empleadoSeleccionado);
        listaUsuarios.remove(indiceEmpleado);
        listaContraseñas.remove(indiceEmpleado);

        System.out.println("Empleado eliminado correctamente");

    }
}

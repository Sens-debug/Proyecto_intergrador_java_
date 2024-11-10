package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;

public class Main {
    
    public static void registrarUsuario(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("INGRESE EL NOMBRE DE USUARIO A REGISTRAR");
        String tryUsuario = teclado.nextLine();
        System.out.println("INGRESE LA CONTRASEÑA DEL USUARIO A REGISTRAR");
        String tryContraseña = teclado.nextLine();
        for (int i =0; i<listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).equals(tryUsuario)) { 
                System.out.println("REGISTRO INVALIDO: USUARIO YA EXISTENTE");
                return;
            }else if (tryUsuario.isEmpty() || tryContraseña.isEmpty()){
                System.out.println("REGISTRO INVALIDO : ALGUNA CREDENCIAL VACÍA");
                return;
            }
        }//FIN DE BUCLE FOR ( SE ITERA EL CONDICIONAL EN BUSCA DE LA EXCEPCION "CREDENCIALES REPETIDAS O VACIAS"), una vez recorrido se sabrá si es pertienente el registro
            listaUsuarios.add(tryUsuario);
            listaContraseñas.add(tryContraseña);
            // HashMap<String, Boolean> diasValidosSemana = new HashMap<>();
    }

    public static void sesionEmpleado (HashMap<String, LocalTime[]> registroHoras, String usuario){
        System.out.println("SELECCIONE: " );
        System.out.println("1. REGISTRAR HORAS: ");
        System.out.println("2. CERRAR SESION: ");
        Scanner teclado2 = new Scanner(System.in);
        int opcion = teclado2.nextInt();
        switch (opcion) {
            case 1:
                Scanner teclado3 = new Scanner(System.in);
                LocalTime[] horasTemporal = new LocalTime[2]; //CREACION DEL ARRAY CONTENEDOR DEL REGISTRO HOARIO ( LIBRERIA LOCALTIME)
                System.out.println("INGRESE SU HORA DE ENTRADA (FORMATO 24HR'S). DESPUES SE LE PEDIRÁN LOS MINUTOS");
                int horaIn= teclado3.nextInt();
                System.out.println("INGRESE LOS MINUTOS (SÍ ENTRÓ EN PUNTO ESCRIBA '0')");
                int minIn=teclado3.nextInt();
                if (horaIn>23 || horaIn<0 || minIn>59 || minIn<0) {
                    System.out.println("RANGO HORARIO INVALIDO ");
                    break;
                }
                System.out.println("INGRESE SU HORA DE SALIDA (FORMATO 24HR'S). DESPUES SE LE PEDIRÁN LOS MINUTOS");
                int horaOut= teclado3.nextInt();
                System.out.println("INGRESE LOS MINUTOS (SÍ ENTRÓ EN PUNTO ESCRIBA '0')");
                int minOut=teclado3.nextInt();
                if (horaOut>23 || horaOut<0 || minOut>59 || minOut<0) {
                    System.out.println("RANGO HORARIO INVALIDO");
                    break;
                }
                LocalTime horaIngreso = LocalTime.of(horaIn, minIn);
                LocalTime horaSalida = LocalTime.of(horaOut, minOut);

                horasTemporal[0]= horaIngreso;
                horasTemporal[1]= horaSalida;
                registroHoras.put(usuario, horasTemporal);

                break;
            
            case 2:
            System.out.println("SE HA CERRADO LA SESIÓN");
            return;
            
            default:
            System.out.println("SELECCION INVALIDA");
            break;
        }

    }

    public static void impresionRegistrosHorarios(String user, HashMap<String, LocalTime[]> registroHoras){
         System.out.println("-------------------------------------\n"+user + "--> Registro de Empleado:");
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
         for (String key : registroHoras.keySet()) {
            LocalTime[] horasOrigen = registroHoras.get(key);
            String[] horas = new String[horasOrigen.length];
            for(int i =0 ; i<horas.length;i++){
                horas[i] = horasOrigen[i].format(formatter);
            }
            if (registroHoras.get(key)!=null) {
                LocalTime horaEntrada = LocalTime.parse(horas[0], DateTimeFormatter.ofPattern("hh:mm a")); 
                LocalTime horaSalida = LocalTime.parse(horas[1], DateTimeFormatter.ofPattern("hh:mm a")); 
                Duration duracion = Duration.between(horaEntrada, horaSalida); long horasTrabajadas = duracion.toHours(); 
                long minutosTrabajados = duracion.toMinutes() % 60; 
                System.out.println("EMPLEADO: " + key + ", HORAS: " + Arrays.toString(horas) + ", DURACIÓN: " + horasTrabajadas + " horas y " + minutosTrabajados + " minutos"); 
            }else{System.out.println("EMPLEADO: "+key+ ", NO HAY REGISTROS");}
        } 
            
        System.out.println("-------------------------------------------------");
    }

    public static void sesionAdministrador(ArrayList<String>listaContraseñas, ArrayList<String>listaUsuarios,String usuario, HashMap<String,LocalTime[]>registroHoras) {
        while (true) {
            System.out.println("SELECCIONE");
            System.out.println("1. REGISTRAR USUARIO"); 
            System.out.println("2. MOSTRAR CREDENCIALES DE USUARIOS"); 
            System.out.println("3. MOSTRAR REGISTROS DE HORARIOS"); 
            System.out.println("4. CERRAR SESION"); 
            Scanner teclado4 = new Scanner(System.in);
            int opcion = teclado4.nextInt();

            switch (opcion) {
                case 1:
                    registrarUsuario(listaUsuarios, listaContraseñas);
                    break;

                case 2:
                System.out.println("SELECIONO -> MOSTRAR CREDENCIALES DE USUARIO");
                for (int i =0; i<listaContraseñas.size();i++) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("USUARIO: " + listaUsuarios.get(i) + " CONTRASEÑA: " + listaContraseñas.get(i));
                    System.out.println("---------------------------------------------------------");
                }
                break;

                case 3:
                System.out.println("SELECCIONO -> MOSTRAR REGISTROS HORARIOS");
                // impresionRegistrosHorarios(usuario, registroHoras);
                System.out.println("\nFUNCIONALIDAD PENDIENTE ; SIN POO ES UN DOLOR DE GVAS\n");

                break;

                case 4: 
                System.out.println("SE HA CERRADO LA SESIÓN");
                return;
            
                default:
                System.out.println("OPCION IVALIDA");
                break;
            }
        }  
    }
    
    public static void inicioSesion(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas, String usuarioAdministrador, String contraseñaAdministrador, HashMap<String, LocalTime[]>registroHoras){
        Scanner teclado = new Scanner(System.in);
        System.out.println("INGRESE EL NOMBRE DE USUARIO");
        String tryUser = teclado.nextLine();
        System.out.println("INGRESE LA CONTRASEÑA");
        String tryPassword = teclado.nextLine();
        if(tryUser.equals(usuarioAdministrador) && tryPassword.equals(contraseñaAdministrador)){
            System.out.println("SE HA INICIADO SESION COMO ADMINISTRADOR");
            sesionAdministrador(listaContraseñas, listaUsuarios, tryUser, registroHoras);
            return;
        }
        for(int i =0; i < listaUsuarios.size();i++){
            if (listaUsuarios.get(i).equals(tryUser) && listaContraseñas.get(i).equals(tryPassword)) {
                System.out.println("SE HA INICIADO SESIÓN COMO EL USUARIO -> " + tryUser);
                sesionEmpleado(registroHoras, tryUser);
                return;
            }} if (tryUser.isEmpty() || tryPassword.isEmpty()){
                System.out.println("SESION INVALIDA: ALGUNA CREDENCIAL VACÍA");
                return;
            }else {
                System.out.println("SESION INVALIDA: NOMBRE DE USUARIO Y/O CONTRASEÑA INCORRECTOS");
            }
        
    

    }
    
    
    public static void main(String[] args) {
        String tryUser, tryPassword, userAdmin, passwordAdmin;
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> users = new ArrayList<>(); // contenedor iterable de la credencial "usuario"
        ArrayList<String> passwords = new ArrayList<>(); // contenedor iterable de la credencial "contraseña"

        HashMap<String, LocalTime[]> registros = new HashMap<>(); // Diccionario de datos (almacenaje horas, nombres)
        userAdmin= "ADMIN";
        passwordAdmin = "ADMIN";
        boolean centinelaW = true;
        while (centinelaW) {
            System.out.println("Ingrese una opcion valida dentro del formulario \n");
            System.out.println("1. INICIAR SESIÓN\n");
            System.out.println("2. SALIR\n");
            int centinela = teclado.nextInt();
            switch (centinela) {

                case 1:
                System.out.println("SELECCIONÓ --> INICIAR SESIÓN");
                inicioSesion(users, passwords, userAdmin, passwordAdmin, registros);
                break;

                case 2:
                System.out.println("SE HA CERRADO LA APLICACIÓN");
                centinelaW = false;
                break;
                   

           }
        }
    }
}
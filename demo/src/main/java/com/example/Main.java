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
        System.out.println("Ingrese el nombre de usuario a registrar");
        String tryUsuario = teclado.nextLine();
        System.out.println("Ingrese la contraseña del usuario a registrar");
        String tryContraseña = teclado.nextLine();
        for (int i =0; i<listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).equals(tryUsuario)) { 
                System.out.println("Registro inválido: Ingreso ya existente.");
                return;
            }else if (tryUsuario.isEmpty() || tryContraseña.isEmpty()){
                System.out.println("Registro inválido: Alguna credencial vacía.");
                return;
            }
        }//Fin De Bucle For ( Se itera el condicional en busca de la excepción "Credenciales vacias o repetidas"), una vez recorrido se sabrá si es pertienente el registro
            listaUsuarios.add(tryUsuario);
            listaContraseñas.add(tryContraseña);
            // HashMap<String, Boolean> diasValidosSemana = new HashMap<>();
    }

    public static void sesionEmpleado (HashMap<String, LocalTime[]> registroHoras, String usuario){
        System.out.println("<----Seleccione----> " );
        System.out.println("1. Registrar horas: ");
        System.out.println("2. Cerrar sesión: ");
        Scanner teclado2 = new Scanner(System.in);
        int opcion = teclado2.nextInt();
        switch (opcion) {
            case 1:
                Scanner teclado3 = new Scanner(System.in);
                LocalTime[] horasTemporal = new LocalTime[2]; //CREACION DEL ARRAY CONTENEDOR DEL REGISTRO HOARIO ( LIBRERIA LOCALTIME)
                System.out.println("Ingrese su hora de entrada (Formato 24HR'S). Después se le pediran los minutos");
                int horaIn= teclado3.nextInt();
                System.out.println("Ingrese los minutos (Sí entro en punto ingrese '0')");
                int minIn=teclado3.nextInt();
                if (horaIn>23 || horaIn<0 || minIn>59 || minIn<0) {
                    System.out.println("Rango horario inválido ");
                    break;
                }
                System.out.println("Ingrese su horario de salida (Formato 24HR'S). Después se le pediran los minutos");
                int horaOut= teclado3.nextInt();
                System.out.println("Ingrese los minutos (Sí entro en punto ingrese '0')");
                int minOut=teclado3.nextInt();
                if (horaOut>23 || horaOut<0 || minOut>59 || minOut<0) {
                    System.out.println("Rango horario inválido ");
                    break;
                }
                LocalTime horaIngreso = LocalTime.of(horaIn, minIn);
                LocalTime horaSalida = LocalTime.of(horaOut, minOut);

                horasTemporal[0]= horaIngreso;
                horasTemporal[1]= horaSalida;
                registroHoras.put(usuario, horasTemporal);

                break;
            
            case 2:
            System.out.println("Se ha cerrado la sesión");
            return;
            
            default:
            System.out.println("Selección inválida");
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
                // LocalTime horaEntrada = LocalTime.parse(horas[0], DateTimeFormatter.ofPattern("hh:mm a")); 
                // LocalTime horaSalida = LocalTime.parse(horas[1], DateTimeFormatter.ofPattern("hh:mm a")); 
                // Duration duracion = Duration.between(horaEntrada, horaSalida); long horasTrabajadas = duracion.toHours(); 
                // long minutosTrabajados = duracion.toMinutes() % 60; 
                System.out.println("Empleado: " + key + ", Horas: " + Arrays.toString(horas) ); 
            }else{System.out.println("Empleado: "+key+ ", No hay registros");}
        } 
            
        System.out.println("-------------------------------------------------");
    }

    public static void sesionAdministrador(ArrayList<String>listaContraseñas, ArrayList<String>listaUsuarios,String usuario, HashMap<String,LocalTime[]>registroHoras) {
        while (true) {
            System.out.println("<--------------Seleccione----------->");
            System.out.println("1. Registrar usuario"); 
            System.out.println("2. Mostrar credenciales de usuario"); 
            System.out.println("3. Mostrar registros de horarios"); 
            System.out.println("4. Cerrar sesión"); 
            Scanner teclado4 = new Scanner(System.in);
            int opcion = teclado4.nextInt();

            System.out.println("--------------------------------------");

            switch (opcion) {
                case 1:
                    registrarUsuario(listaUsuarios, listaContraseñas);
                    break;

                case 2:
                System.out.println("Seleccionó -> Mostrar credenciales de usuarioo");
                for (int i =0; i<listaContraseñas.size();i++) {
                    System.out.println("---------------------------------------------------------");
                    System.out.println("Usuario: " + listaUsuarios.get(i) + " Contraseña: " + listaContraseñas.get(i));
                    System.out.println("---------------------------------------------------------");
                }
                break;

                case 3:
                System.out.println("Seleccionó -> Mostrar registros de horarios");
                impresionRegistrosHorarios(usuario, registroHoras);
                System.out.println("");

                break;

                case 4: 
                System.out.println("Se ha cerrado la sesión");
                return;
            
                default:
                System.out.println("Opción inválida");
                break;
            }
        }  
    }
    
    public static void inicioSesion(ArrayList<String> listaUsuarios, ArrayList<String> listaContraseñas, String usuarioAdministrador, String contraseñaAdministrador, HashMap<String, LocalTime[]>registroHoras){
        Scanner teclado = new Scanner(System.in);
        System.out.println("<------------------------------------>");
        System.out.println("Credenciales: user=0  password=0  ");
        System.out.println("Ingrese el nombre de usuario");
        String tryUser = teclado.nextLine();
        System.out.println("Ingrese la contraseña");
        String tryPassword = teclado.nextLine();
        if(tryUser.equals(usuarioAdministrador) && tryPassword.equals(contraseñaAdministrador)){
            System.out.println("Se ha iniciado sesión como administrador");
            sesionAdministrador(listaContraseñas, listaUsuarios, tryUser, registroHoras);
            return;
        }
        for(int i =0; i < listaUsuarios.size();i++){
            if (listaUsuarios.get(i).equals(tryUser) && listaContraseñas.get(i).equals(tryPassword)) {
                System.out.println("Se ha iniciado sesión como el usuario-> " + tryUser);
                sesionEmpleado(registroHoras, tryUser);
                return;
            }} if (tryUser.isEmpty() || tryPassword.isEmpty()){
                System.out.println("Sesión inválida: Alguna credencial vacía");
                return;
            }else {
                System.out.println("Sesión inválida: Nombre de usuario y/o contraseñas incorrectos");
            }
        
    

    }
    
    
    public static void main(String[] args) {
        String tryUser, tryPassword, userAdmin, passwordAdmin;
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> users = new ArrayList<>(); // contenedor iterable de la credencial "usuario"
        ArrayList<String> passwords = new ArrayList<>(); // contenedor iterable de la credencial "contraseña"

        HashMap<String, LocalTime[]> registros = new HashMap<>(); // Diccionario de datos (almacenaje horas, nombres)
        userAdmin= "0";
        passwordAdmin = "0";
        

        boolean centinelaW = true;
        while (centinelaW) {
            System.out.println("Ingrese una opcion válida dentro del formulario \n");
            System.out.println("1. Iniciar Sesión\n");
            System.out.println("2. Salir\n");
            int centinela = teclado.nextInt();
            System.out.println("<--------------------------------------------->");
            switch (centinela) {

                case 1:
                System.out.println("Seleccionó --> Iniciar Sesión");
                inicioSesion(users, passwords, userAdmin, passwordAdmin, registros);
                break;

                case 2:
                System.out.println("Se ha cerrado la aplicación");
                centinelaW = false;
                break;
                   

           }
        }
    }
}
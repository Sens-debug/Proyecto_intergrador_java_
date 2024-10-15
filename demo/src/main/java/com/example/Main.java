package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String tryUser, tryPassword, user, password;
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> users = new ArrayList<>(); // contenedor iterable de la credencial "usuario"
        ArrayList<String> passwords = new ArrayList<>(); // contenedor iterable de la credencial "contraseña"

        HashMap<String, ArrayList<Integer>> map = new HashMap<>(); // Diccionario de datos (almacenaje horas, nombres)
        users.add("ADMINISTRAD0R");
        passwords.add("ADMINISTRAD0R");
        boolean centinelaW = true;
        while (centinelaW) {
            System.out.println("Ingrese una opcion valida dentro del formulario \n");
            System.out.println("1. REGISTRAR USUARIO\n");
            System.out.println("2. INICIAR SESIÓN\n");
            System.out.println("3. SALIR\n");
            int centinela = teclado.nextInt();
            switch (centinela) {
                case 1:
                    System.out.println("SELECCIONÓ --> REGISTRAR USUARIO");

                    Scanner input = new Scanner(System.in);
                    try {
                        System.out.println("Ingrese el usuario a registrar");
                        user = input.nextLine();
                        users.add(user);
                        System.out.println("Usuario valido");

                        System.out.println("Ingrese la contraseña a registrar");
                        password = input.nextLine();
                        passwords.add(password);

                    } catch (Exception e) {
                        System.out.println("ERROR EN EL REGISTRO; CRENDECIALES INVALIDAS.\n vuelva a intentar");
                        break;
                    }
                    System.out.println("Registro exitoso, puede registrar otro usuario repitiendo el formulario\n\n");

                    break;// break "case 1"

                case 2:
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("SELECCIONÓ --> INICIO DE SESION\n");
                    System.out.println("INGRESE USUARIO");
                    tryUser = input2.nextLine();
                    System.out.println("INGRESE CONTRASEÑA");
                    tryPassword = input2.nextLine();
                    boolean centinelaWhileUsers = true;
                    for (int i = 1; i <= passwords.size(); i++) {

                        try {
                            if (users.get(i).equals(tryUser) && passwords.get(i).equals(tryPassword)) {
                                System.out.println("INICIO DE SESION EXITOSO\n");
                                System.out.println("Bienvenido " + users.get(i) + "\n");
                                while (centinelaWhileUsers) {
                                    ArrayList<Integer> temporal = new ArrayList<>();
                                    System.out.println(
                                            "El sistema desplegará una interfaz que le solicitará sus horarios");
                                    System.out.println("1.REGISTRO DE HORAS");
                                    System.out.println("2.SALIR DE INTERFAZ");
                                    Scanner teclado2 = new Scanner(System.in);
                                    int centinelaUsers = teclado2.nextInt();
                                    switch (centinelaUsers) {
                                        case 1:
                                            System.out.println("1 ---> INGRESAR HORA INGRESO");
                                            System.out.println(
                                                    "ESCRIBA LA HORA DE INGRESO (FORMATO 24HR's) (horas enteras)");
                                            Integer horaIn = teclado2.nextInt();
                                            if (horaIn > 24) {
                                                System.out.println("Rango horario invalido");
                                                break;

                                            } else {
                                                temporal.add(horaIn);
                                            }

                                            System.out.println("2 ---> INGRESAR HORA SALIDA");
                                            System.out.println("ESCRIBA LA HORA DE SALIDA (FORMATO 24HR's)");
                                            Integer horaOut = teclado2.nextInt();
                                            if (horaOut > 24) {
                                                System.out.println("Rango horario invalido");
                                                break;

                                            } else {
                                                temporal.add(horaOut);
                                            }
                                            map.put(users.get(i), temporal);
                                            break;

                                        case 2:
                                            System.out.println("GRACIAS\nSALIENDO\n");
                                            centinelaWhileUsers = false;
                                            break;

                                        default:
                                            System.out.println("OPCION INVALIDA\n");
                                            break;
                                    }

                                }
                                break;
                            } else if (users.get(0).equals(tryUser) && passwords.get(0).equals(tryPassword)) {
                                System.out.println("SESION DE ADMINISTRADOR");
                                boolean centinelaWhileAdmin = true;
                                while (centinelaWhileAdmin) {
                                    Scanner teclado2 = new Scanner(System.in);
                                    System.out.println("QUE OPCION QUIERE REALIZAR?");
                                    System.out.println("1.MOSTRAR REGISTROS DE EMPLEADOS");
                                    System.out.println("2.Salir de la interfaz\n");
                                    int centinelaAdmin = teclado2.nextInt();
                                    switch (centinelaAdmin) {
                                        case 1:
                                            for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
                                                String clave = entry.getKey();
                                                ArrayList<Integer> listaHoras = entry.getValue();
                                                    Collections.sort(listaHoras, new Comparator<Integer>() {
                                                    @Override
                                                    public int compare(Integer a, Integer b) {
                                                    return b.compareTo(a); // Cambia el orden para mayor a menor
                                                    }
                                                    });
                                                    int horasTotales=listaHoras.get(0)-listaHoras.get(1);
                                                    
                                                
                                                System.out.println("USUARIO-->" + clave + " , HORAS (INGRESO, SALIDA)-->" + listaHoras+" TOTAL DIARIO -->"+horasTotales);
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Gracias por usar la interfaz\nSALIENDO");
                                            centinelaWhileAdmin=false;
                                            break;

                                        default:
                                        System.out.println("OPCION INVALIDA");
                                            break;
                                    }

                                }

                            } else {
                                continue;
                            }

                        } catch (Exception e) {
                            System.out.println("CREDENCIALES INVALIDAS\n");

                        }

                    }
                    break;// break "case 2"

                case 3:
                    System.out.println("SALIENDO\nGRACIAS POR USAR LA INTERFAZ");
                    centinelaW = false;
                    break; // break "case 3"

                default:
                    System.out.println("ingrese una opcion valida dentro del menu\n");

            }

        }
        teclado.close();

    }
}
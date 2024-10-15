package com.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String tryUser, tryPassword, user, password;
        Scanner teclado = new Scanner(System.in);
        ArrayList<String> users = new ArrayList<>(); // contenedor iterable de la credencial "usuario"
        ArrayList<String> passwords = new ArrayList<>(); // contenedor iterable de la credencial "contraseña"
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

                    }catch (Exception e) {
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
                    for (int i = 0; i <= passwords.size(); i++) {

                        try {
                            if (users.get(i).equals(tryUser) && passwords.get(i).equals(tryPassword)) {
                                System.out.println("INICIO DE SESION EXITOSO\n");
                                System.out.println("Bienvenido " + users.get(i) + "\n");
                                break;
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

                default : 
                System.out.println("ingrese una opcion valida dentro del menu\n");

            }

        }
        teclado.close();

    }
}
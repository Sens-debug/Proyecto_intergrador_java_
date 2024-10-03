package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {//Credenciales asignadas desde codigo; una vez se aplique BD se podrán reasignar libremente
        String tryUser, tryPassword;
        String user = "admin";
        String password = "admon";
        Scanner teclado = new Scanner(System.in);
        System.out.println("A continuacion se verá dentro de la pestaña 'LOG-IN'");
        while (true) {
            System.out.println("Ingrese su nombre de usuario:");
            tryUser = teclado.nextLine();
            System.out.println("Ingrese su contraseña:");
            tryPassword = teclado.nextLine();
            if (!tryUser.equals(user) || !tryPassword.equals(password)) {
                System.out.println("Credenciales incorrectas: \nVuelva a intentar");
            }
            if (tryUser.equals(user) && tryPassword.equals(password)) {
                System.out.println("Credenciales correctas \nINICIANDO SESIÓN");
                break;
            }
        }
        teclado.close();
    }
}
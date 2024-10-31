package com.example;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

//------------------CLASE -> EMPLEADOS ---------------------------
class Empleados {
    
    String usuario;
    String contraseña;
    HashMap<String, Boolean> diasSemana ;
    LocalTime[]horasRegistradas;
    HashMap<String, String[]> registro ;
    

    
    // ---------------------CONSTRUCTOR -> EMPLEADOS ----------------------------
    public Empleados(String usuario, String contraseña){

        this.usuario = usuario;//Usuario corresponde a nombre del empleado
        this.contraseña = contraseña;//Contraseña corresponde a la contraseña del empleado
        horasRegistradas = new LocalTime[2]; //Creacion del contenedor iterable que almacena las horas diarias (2 registros LocalTime)
        //Contenedor iterable que almacena las fechas registradas (LocalTime no es imprimible)
        registro = new HashMap<>();//Registro va a combinar lo almacenado en "horas"(value) con (key ->) correspondiente al dia
        diasSemana  = new HashMap<>();//diasSemana contiene longitud de semana como clave y un valor que asignara disponibilidad
        
        
        //---------DECLARACION DE REGISTROS DENTRO DEL MAP "diasSemana" ------------------
        {
        diasSemana.put("Lunes", true);
        diasSemana.put("Martes", true);
        diasSemana.put("Miercoles", true);
        diasSemana.put("Jueves", true);
        diasSemana.put("Viernes", true);
        diasSemana.put("Sabado", false);
        diasSemana.put("Domingo", false);
        }
        //-----------FIN DECLARACION DE REGISTROS DEL MAP "diasSemana" ----------------------

     //---------DECLARACION DE REGISTROS DENTRO DEL MAP "Registro" ------------------
        {
         registro.put("Lunes", null);
         registro.put("Martes", null);
         registro.put("Miercoles", null);
         registro.put("Jueves", null);
         registro.put("Viernes", null);
         registro.put("Sabado", null);
         registro.put("Domingo", null);
         }
        //-----------FIN DECLARACION DE REGISTROS DEL MAP "Registro" ----------------------
    
    }
    //-------------------------------------FIN CONSTRUCTOR -> EMPLEADOS ---------------------------------------------

    //----------------------METODOS DE CLASE -> EMPLEADOS-----------------------------------------------

    // ---------------------METODO PARA REGISTRAR ENTRADA Y SALIDA DE EMPLEADOS----------------------------
    public void registroHoras(LocalTime[]horasRegistradas) {
       String diaSeleccionado = menuSelectorDias(diasSemana);
        if (diasSemana.get(diaSeleccionado)) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("\n1 ---> INGRESAR HORA ENTRADA");
        System.out.println("ESCRIBA LA HORA DE ENTRADA (FORMATO 24HR's) MAS ADELANTE SE LE SOLICITARAN LOS MINUTOS DE LA HORA");
        Integer horaIn = teclado.nextInt();
        System.out.println("ESCRIBA EL MINUTO CORREPONDIENTE A SU HORA DE ENTRADA (SI ENTRO EN PUNTO ESCRIBA '0')");
        Integer minutoIn = teclado.nextInt();
        if (horaIn > 23 || horaIn <0) {
            System.out.println("RANGO HORARIO INVALIDO");
            return;
        }else if(minutoIn<0 || minutoIn >59){
            System.out.println("RANGO HORARIO INVALIDO");
            return;
        }
         LocalTime horaIngreso = LocalTime.of(horaIn, minutoIn);

         System.out.println("\n2 ---> INGRESAR HORA SALIDA");
         System.out.println("ESCRIBA LA HORA DE SALIDA (FORMATO 24HR's) MAS ADELANTE SE LE SOLICITARAN LOS MINUTOS DE LA HORA");
         Integer horaOut = teclado.nextInt();
         System.out.println("ESCRIBA EL MINUTO CORREPONDIENTE A SU HORA DE SALIDA (SI ENTRO EN PUNTO ESCRIBA '0')");
         Integer minutoOut = teclado.nextInt();
         if (horaOut > 23 || horaOut <0) {
             System.out.println("RANGO HORARIO INVALIDO");
             return;
         }else if(minutoOut<0 || minutoOut >59){
             System.out.println("RANGO HORARIO INVALIDO");
             return;
            }
         LocalTime horaSalida = LocalTime.of(horaOut, minutoOut);

         horasRegistradas[0]= horaIngreso;
         horasRegistradas[1]= horaSalida;


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); //("a" Atributo que permite impesion de AM o PM )se crea un formateador para poder imprimir los registros
        String[] registroH = new String[2];
        registroH[0]=horaIngreso.format(formatter);
        registroH[1]=horaSalida.format(formatter);
        registro.put(diaSeleccionado,registroH);

         diasSemana.put(diaSeleccionado, false);
         return;

        }else{
            System.out.println("DIA NO DISPONIBLE");
            return;
        }

        } //-------------------------------------FIN METODO -> REGISTRAR ENTRADA Y SALIDA DE EMPLEADOS ---------------------------------------------
    
    //---------------------- METODO MENU DEL SELECTOR DE DIAS -------------------------------
    public String menuSelectorDias(HashMap<String, Boolean> diasSemana) {
        System.out.println("A CONTINUACION SERA DESPLEGADO UN MENU PARA QUE SELECCIONE EL DIA QUE DESEA REGISTAR");
        System.out.println("TENGA EN CUENTA QUE SOLO SE LE PERMITIRA UN REGISTRO POR DIA");
        
        String[] claves = diasSemana.keySet().toArray(new String[0]);
        for(int i =0; i<claves.length;i++){
        System.out.println("indice->"+i+ " "+ claves[i]);
     }
     System.out.println("Selecciona el dia que vas a registar");
     Scanner teclado3 = new Scanner(System.in);
     int indiceClaveSeleccionado = teclado3.nextInt();
     String claveIndiceSeleccionado = claves[indiceClaveSeleccionado];
     return claveIndiceSeleccionado ;


    }

    //-------------------------------------FIN METODO MENU DEL SELECTOR DE DIAS ---------------------------------------------

}//-------------------------------------FIN METODOS DE CLASE -> EMPLEADOS ---------------------------------------------
//-------------------------------FIN CLASE -> EMPLEADOS----------------------------------------------

//------------------CLASE ADMINISTRADOR---------------------------
 class Administrador {
     String usuario = "ADMINISTRADOR"; //credenciales de acceso al perfil administrador
     String password = "ADMINISTRADOR"; //credenciales de acceso al perfil administrador


     //----------------------METODOS DE CLASE -> ADMINISTRADOR-----------------------------------------------

     //------------------------METODO "CREAR_EMPLEADO"------------------------------
    public void crearEmpleado(ArrayList<Empleados> listaEmpleados) {
         Scanner teclado = new Scanner(System.in);
         System.out.println("Ingrese el nombre del nuevo empleado");
         String nombreEmpleado = teclado.nextLine();
         System.out.println("Ingrese la contraseña del nuevo empleado");
         String contraseñaEmpleado = teclado.nextLine();
         
         for(int i=0; i<listaEmpleados.size(); i++){
         if (nombreEmpleado.equals("") && contraseñaEmpleado.equals("")) {
            System.out.println("CREDENCIALES INVALIDAS -> VACIAS");
            return;
         }
         else if (listaEmpleados.get(i).usuario.equals(nombreEmpleado)) {
             System.out.println("CREDENCIALES INVALIDAS->NOMBRE DE USUARIO YA EXISTENTE");
             return;
            
         }}
         listaEmpleados.add(new Empleados(nombreEmpleado, contraseñaEmpleado));
         System.out.println("Empleado creado exitosamente");
   
        } //---------------------FIN METODO "CREAR-EMPELADO"------------------------------------------

      //--------------------METODO "IMPRIMIR_REGISTROS_EMPLEADOS"---------------------------------------
       public void impirmirRegistrosEmpleados(HashMap<String, String[]> registro,String user, ArrayList<Empleados>listaEmpleados) { 
         System.out.println("------------------\n"+user + "--> Registro de Empleado:");
         for (String key : registro.keySet()) {
            if (registro.get(key)!=null) {
                System.out.println("DIA: " + key + ", HORAS: " + Arrays.toString(registro.get(key))); 
            }else{System.out.println("DIA: "+key+ ", NO HAY REGISTROS");}
        } 
            
        System.out.println("-------------------------------------------------");
        } 
    

    //-------------------------------FIN DE METODO "IMPRIMIR_REGISTROS_EMPLEADOS"-----------------------
         
        
    } //----------------------FIN METODOS DE CLASE -> ADMINISTRADOR------------------------------
//---------------------------------FIN CLASE -> ADMINISTRADOR-------------------------------------------------




public class Main {

    //---------METODO "LOGIN" ------------
 public static void LogIn(String tryUser, String tryPassword, ArrayList<Empleados> listaEmpleados, Administrador admin){

    if(tryUser.equals(admin.usuario) && tryPassword.equals(admin.password)){ //VERIFICA SI LAS CREDENCIALES COINCIDEN CON LAS DEL ADMIN 
         System.out.println("Bienvenido Administrador\n");  
         boolean centinelaWhileAdmin= true;
         while (centinelaWhileAdmin) {
         Scanner teclado = new Scanner(System.in);
         System.out.println("Seleccione la accion que desea realizar");
         System.out.println("1. -> REGISTRAR EMPLEADOS");
         System.out.println("2. -> MOSTRAR REGISTROS DE EMPLEADOS");
         System.out.println("3. -> CERRAR SESION");
         int opcion = teclado.nextInt();
         switch (opcion) {
            case 1:
                admin.crearEmpleado(listaEmpleados);
                break;
            case 2:
                for (int i=0; i<listaEmpleados.size(); i++){
                admin.impirmirRegistrosEmpleados(listaEmpleados.get(i).registro, listaEmpleados.get(i).usuario, listaEmpleados);}
            break;
            
            case 3:
            System.out.println("SESION CERRADA CON EXITO\n");
            centinelaWhileAdmin = false;
            break;
         
            default:
            System.out.println("OPCION INVALIDA");
                break;
         } // llave correspondiente al switch case  
        }// llave correspondiente al bucle while 

     }// llave correspondiente al IF statement
     for(int i =0; i<listaEmpleados.size();i++){
     if(tryUser.equals(listaEmpleados.get(i).usuario) && tryPassword.equals(listaEmpleados.get(i).contraseña)){
        System.out.println("BIENVENIDO "+ tryUser);
        boolean centinelaWhileEmpleados= true;
        while (centinelaWhileEmpleados) { 
            Scanner teclado4 = new Scanner(System.in);
            System.out.println("A CONTINUACION SERA DESPLEGADO SU MENU DE ACCION");
            System.out.println("1. -> REGISTRAR HORAS");
            System.out.println("2. -> SALIR DE LA INTERFAZ");
            int opcion = teclado4.nextInt();
        switch (opcion) {
            case 1:
            listaEmpleados.get(i).registroHoras(listaEmpleados.get(i).horasRegistradas);

                break;
            case 2:
            System.out.println("SALIENDO DE LA INTERFAZ\nGRACIAS POR USAR");
            centinelaWhileEmpleados= false;
                break;
        
            default:
            System.out.println("OPCION INVALIDA");
                break;
        }

        }//llave bucle while
    } //llave if statement
    } // llave bucle for
    

 }
 //-----------FIN METODO "LOGIN" -----------------------


 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Administrador admin = new Administrador();
        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        Scanner tecladoMain = new Scanner(System.in);
        listaEmpleados.add(new Empleados("test", "test"));
        System.out.println("BIENVENIDO");
        while (true) {
        System.out.println("QUE ACCION DESEA REALIZAR?");
        System.out.println("1. -> INICIAR SESION");
        System.out.println("2. -> SALIR");
        int opcion= tecladoMain.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("INGRESE USUARIO");
                String tryUser = sc.nextLine();
                System.out.println("INGRESE CONTRASEÑA");
                String tryPassword = sc.nextLine();
        
                LogIn(tryUser, tryPassword, listaEmpleados, admin);
                break;

            case 2: 
            System.out.println("GRACIAS POR USAR");
            System.exit(0);
            
            break;
        
            default:
            System.out.println("OPCION INVALIDA");
                break;
        }
    }
    } //-------------------------------------FIN DEL MAIN ---------------------------------------------

}//-------------------------------------FIN CLASE -> MAIN ---------------------------------------------

//-------------------------------FIN CLASE -> MAIN----------------------------------------------
        
       



        

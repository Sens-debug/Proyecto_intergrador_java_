package com.example;


import java.time.Duration;
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
    HashMap<String, Boolean> diasSemana;
    LocalTime[] horasRegistradas;
    HashMap<String, String[]> registro;
    HashMap<String, ArrayList<String>> comentariosSemana;

    // ---------------------CONSTRUCTOR -> EMPLEADOS ----------------------------
    public Empleados(String usuario, String contraseña) {

        this.usuario = usuario;// Usuario corresponde a nombre del empleado
        this.contraseña = contraseña;// Contraseña corresponde a la contraseña del empleado
        horasRegistradas = new LocalTime[2]; // Creación del contenedor iterable que almacena las horas diarias (2
                                             // registros LocalTime)
        registro = new HashMap<>();// Registro va a combinar lo almacenado en "horas"(value) con (key ->)
                                   // correspondiente al dia
        comentariosSemana = new HashMap<>(); // Contenedor iterable - comentariosSemanales <clave=Dia - Key=comentarios>
        diasSemana = new HashMap<>();// diasSemana contiene longitud de semana como clave y un valor que asignara
                                     // disponibilidad

        // ---------DECLARACIÓN DE REGISTROS DENTRO DEL MAP "díasSemana"
        // ------------------
        {
            diasSemana.put("Lunes", true);
            diasSemana.put("Martes", true);
            diasSemana.put("Miercoles", true);
            diasSemana.put("Jueves", true);
            diasSemana.put("Viernes", true);
            diasSemana.put("Sabado", false);
            diasSemana.put("Domingo", false);
        }
        // -----------FIN DECLARACIÓN DE REGISTROS DEL MAP "diasSemana"
        // ----------------------

        // ---------DECLARACIÓN DE REGISTROS DENTRO DEL MAP "Registro"
        // ------------------

        // {
        // registro.put("Lunes", null);EJEMPLO MOCHO DEL COMO SERÍA SIN BUCLE == VALIDO
        // registro.put("Martes", null);
        // }

        for (String dia : diasSemana.keySet()) { // BUCLE AUTORELLENADOR DE HASHMAP
            registro.put(dia, null);// ¿Razón? clonamos "keys" asignamos un mismo valor a todos los registros; el
                                    // bucle lo permite
        }

        // -----------FIN DECLARACIÓN DE REGISTROS DEL MAP "Registro"
        // ----------------------

        // ------------------DECLARACIÓN REGISTROS
        // <comentariosSemana>-----------------------
        for (String key : diasSemana.keySet()) {
            comentariosSemana.put(key, null); // CLONAMOS LAS CLAVES DEL MAP <diasSemana>
        }
        // --------------------FIN DECLARACIÓN DEL MAP
        // <comentariosSemana>--------------------
    }
    // -------------------------------------FIN CONSTRUCTOR -> EMPLEADOS
    // ---------------------------------------------

    // ----------------------MÉTODOS DE CLASE ->
    // EMPLEADOS-----------------------------------------------

    // ---------------------MÉTODO PARA REGISTRAR ENTRADA Y SALIDA DE
    // EMPLEADOS----------------------------
    public void registroHoras(LocalTime[] horasRegistradas, HashMap<String,ArrayList<String>> comentariosSemana) {
       
        String diaSELECCIONADO = menuSelectorDias(diasSemana);
        if (diasSemana.get(diaSELECCIONADO)) {
            Scanner teclado = new Scanner(System.in);
            System.out.println("\n1 ---> INGRESAR HORA ENTRADA");
            System.out.println(
                    "ESCRIBA LA HORA DE ENTRADA (FORMATO 24HR's) MAS ADELANTE SE LE SOLICITARAN LOS MINUTOS DE LA HORA");
            Integer horaIn = teclado.nextInt();
            System.out
                    .println("ESCRIBA EL MINUTO CORRESPONDIENTE A SU HORA DE ENTRADA (SI ENTRO EN PUNTO ESCRIBA '0')");
            Integer minutoIn = teclado.nextInt();
            if (horaIn > 23 || horaIn < 0 || minutoIn < 0 || minutoIn > 59) {
                System.out.println("RANGO HORARIO INVALIDO");
                return;
            }
            LocalTime horaIngreso = LocalTime.of(horaIn, minutoIn);

            System.out.println("\n2 ---> INGRESAR HORA SALIDA");
            System.out.println(
                    "ESCRIBA LA HORA DE SALIDA (FORMATO 24HR's) MAS ADELANTE SE LE SOLICITARAN LOS MINUTOS DE LA HORA");
            Integer horaOut = teclado.nextInt();
            System.out.println("ESCRIBA EL MINUTO CORRESPONDIENTE A SU HORA DE SALIDA (SI ENTRO EN PUNTO ESCRIBA '0')");
            Integer minutoOut = teclado.nextInt();
            if (horaOut > 23 || horaOut < 0 || minutoOut < 0 || minutoOut > 59) {
                System.out.println("RANGO HORARIO INVALIDO");
                return;
            }
            LocalTime horaSalida = LocalTime.of(horaOut, minutoOut);

            horasRegistradas[0] = horaIngreso;
            horasRegistradas[1] = horaSalida;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a"); // ("a" Atributo que permite impresión
                                                                                  // de AM o PM )se crea un formatter
                                                                                  // para poder imprimir los registros
            String[] registroH = new String[2]; // Crea un conjunto de datos temporal; almacena temporalmente las horas,
                                                // luego inserta en el Map de cada OBJ
            registroH[0] = horaIngreso.format(formatter);
            registroH[1] = horaSalida.format(formatter);
            registro.put(diaSELECCIONADO, registroH);

            diasSemana.put(diaSELECCIONADO, false);

           
            return;

        } else {
            System.out.println("DIA NO DISPONIBLE");
            return;
        }

    } // -------------------------------------FIN MÉTODO -> REGISTRAR ENTRADA Y SALIDA
      // DE EMPLEADOS ---------------------------------------------

      //---------------------------------MÉTODO IMPRESIÓN DE COMENTARIOS --------------------------------
    public void impresiónComentarios(HashMap<String, ArrayList<String>>comentariosSemana){
        System.out.println();
        for(int i =0;i<comentariosSemana.values().size();i++){
            String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
            // String [] dias = comentariosSemana.keySet().toArray(new String[0]);
            String diasBucle = dias[i];
            if (comentariosSemana.get(diasBucle)!=null) {
                for(int varComentarios=0;varComentarios<comentariosSemana.get(diasBucle).size();varComentarios++) {
                    System.out.println();
                    System.out.println(diasBucle+(varComentarios+1)+" " +comentariosSemana.get(diasBucle).get(varComentarios));
                    System.out.println();
                }
            }else {
                System.out.println();
                System.out.println(diasBucle+" ---> NO HAY COMENTARIOS ALMACENADOS");}
        }
        System.out.println();
    }
      //--------FIN MÉTODO IMPRESIÓN COMENTARIOS --------------------------------


    // ---------------------- MÉTODO MENU DEL SELECTOR DE DIAS
    // -------------------------------
    public String menuSelectorDias(HashMap<String, Boolean> diasSemana) {
        System.out.println("A CONTINUACIÓN SERA DESPLEGADO UN MENU PARA QUE SELECCIONE EL DIA QUE DESEA REGISTRAR");
        System.out.println("TENGA EN CUENTA QUE SOLO SE LE PERMITIRÁ UN REGISTRO POR DIA");

        String[] claves = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};

        // String[] claves = diasSemana.keySet().toArray(new String[0]);
        for (int i = 0; i < claves.length; i++) {
            System.out.println("indice->" + i + " " + claves[i]);
        }
        System.out.println("SELECCIONA el dia que vas a registrar");
        Scanner teclado3 = new Scanner(System.in);
        int indiceClaveSELECCIONADO = teclado3.nextInt();
        String claveIndiceSELECCIONADO = claves[indiceClaveSELECCIONADO];
        return claveIndiceSELECCIONADO;

    }

    // -------------------------------------FIN MÉTODO MENU DEL SELECTOR DE DIAS
    // ---------------------------------------------

}// -------------------------------------FIN MÉTODOS DE CLASE -> EMPLEADOS
 // ---------------------------------------------
 // -------------------------------FIN CLASE ->
 // EMPLEADOS----------------------------------------------

// ------------------CLASE ADMINISTRADOR---------------------------
class Administrador {
    String usuario = "ADMI"; // credenciales de acceso al perfil administrador
    String password = "ADMI"; // credenciales de acceso al perfil administrador

    // ----------------------MÉTODOS DE CLASE ->
    // ADMINISTRADOR-----------------------------------------------

    // ------------------------MÉTODO "CREAR_EMPLEADO"------------------------------
    public void crearEmpleado(ArrayList<Empleados> listaEmpleados) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el nombre del nuevo empleado");
        String nombreEmpleado = teclado.nextLine();
        System.out.println("Ingrese la contraseña del nuevo empleado");
        String contraseñaEmpleado = teclado.nextLine();

        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (nombreEmpleado.isEmpty() || contraseñaEmpleado.isEmpty()) {
                System.out.println("CREDENCIALES INVALIDAS -> VACÍAS");
                return;
            } else if (listaEmpleados.get(i).usuario.equals(nombreEmpleado)) {
                System.out.println("CREDENCIALES INVALIDAS->NOMBRE DE USUARIO YA EXISTENTE");
                return;

            }
        }
        listaEmpleados.add(new Empleados(nombreEmpleado, contraseñaEmpleado));
        System.out.println("Empleado creado exitosamente");

    } // ---------------------FIN MÉTODO
      // "CREAR-EMPELADO"------------------------------------------


    //-------------MÉTODO IMPRIMIR COMENTARIOS (RETORNA STR)--------------------------
    public String imprimirComentariosGetKey(ArrayList<Empleados> listaEmpleados, int indiceEmpleado) {
        System.out.println("-------------------\nCOMENTARIOS PARA : "+ listaEmpleados.get(indiceEmpleado).usuario);
           
        String[] claves = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        // String[] claves = listaEmpleados.get(indiceEmpleado).comentariosSemana.keySet().toArray(new String[0]); //contenedor iterable de las claves del MAP

        System.out.println("SELECCIONE EL DIA QUE QUIERE COMENTAR ( A TRAVÉS DEL INDICE)");
        System.out.println();
            for(int i =0; i < claves.length; i++){  
                if (listaEmpleados.get(indiceEmpleado).comentariosSemana.get(claves[i])!= null){
                    System.out.println("INDICE -> " + i +"| DIA : "+ claves[i]+ " COMENTARIO : "+ listaEmpleados.get(indiceEmpleado).comentariosSemana.get(claves[i]));
                    System.out.println();
                    
                }else{
                    System.out.println("INDICE ->" + i +"| DIA : "+ claves[i] + " NO HAY COMENTARIO REGISTRADO");
                    System.out.println();
                
                    }
            }
        Scanner teclado56 = new Scanner(System.in);
        int indiceDiaSeleccionado = teclado56.nextInt();
        String claveSeleccionada = claves[indiceDiaSeleccionado];
        return claveSeleccionada;
            
    }

    //-------------FIN MÉTODO IMPRIMIR COMENTARIOS (RETORNA STR)-------------------------

     //-------------MÉTODO IMPRIMIR COMENTARIOS(RETORNA INT)------------------------
     public int imprimirComentariosGetIndex(ArrayList<Empleados> listaEmpleados, int indiceEmpleado) {
        System.out.println("-------------------\n COMENTARIOS PARA : "+ listaEmpleados.get(indiceEmpleado).usuario);
        
        String[] claves = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        // String[] claves = listaEmpleados.get(indiceEmpleado).comentariosSemana.keySet().toArray(new String[0]); //contenedor iterable de las claves del MAP

        System.out.println("SELECCIONE EL DIA QUE QUIERE COMENTAR ( A TRAVÉS DEL INDICE)");
            for(int i =0; i < claves.length; i++){
                if (listaEmpleados.get(indiceEmpleado).comentariosSemana.get(claves[i])!= null){
                    System.out.println("INDICE : " + i +" DIA : "+ claves[i]+ " COMENTARIO : "+ listaEmpleados.get(indiceEmpleado).comentariosSemana.get(claves[i]));
                    System.out.println();
                    
                }else{
                    System.out.println("INDICE : " + i +" DIA : "+ claves[i] + "NO HAY COMENTARIO REGISTRADO");
                    System.out.println();
                
                    }
            }
        Scanner teclado56 = new Scanner(System.in);
        int indiceDiaSeleccionado = teclado56.nextInt();
        String claveSeleccionada = claves[indiceDiaSeleccionado];
        return indiceDiaSeleccionado;
            
    }

    //-------------FIN MÉTODO IMPRIMIR COMENTARIOS (RETORNA INT)-----------------------


    // -----------MÉTODO "IMPRIMIR_REGISTROS_EMPLEADOS"-----------------------
    public void imprimirRegistrosEmpleados(HashMap<String, String[]> registro, String user,
            ArrayList<Empleados> listaEmpleados, HashMap<String, ArrayList<String>>comentariosSemana) {
                String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        System.out.println("-------------------------------------\n" + "--> Registro de" + user + " :");
        for (String key : dias) {
            String[] horas = registro.get(key);
            if (registro.get(key) != null) {
                LocalTime horaEntrada = LocalTime.parse(horas[0], DateTimeFormatter.ofPattern("hh:mm a"));
                LocalTime horaSalida = LocalTime.parse(horas[1], DateTimeFormatter.ofPattern("hh:mm a"));
                Duration duracion = Duration.between(horaEntrada, horaSalida);
                long horasTrabajadas = duracion.toHours();
                long minutosTrabajados = duracion.toMinutes() % 60;
                System.out.println("DIA: " + key + ", HORAS: " + Arrays.toString(horas) + ", DURACIÓN: "
                        + horasTrabajadas + " horas y " + minutosTrabajados + " minutos"+"\n"+ "comentarios: " + comentariosSemana.get(key)) ;
            } else {
                System.out.println("DIA: " + key + ", NO HAY REGISTROS");
            }
        }

        System.out.println("-------------------------------------------------");
    }

    // -------------------------------FIN DE MÉTODO
    // "IMPRIMIR_REGISTROS_EMPLEADOS"-----------------------

    // -------------------------------MÉTODO VISUALIZAR CREDENCIALES EMPLEADOS
    // ------------------------------------------
    public void visualizarCredencialesEmpleados(ArrayList<Empleados> listaEmpleados) {
        System.out.println("-------------------------------------\nCredenciales Empleados:");
        for (Empleados empleado : listaEmpleados) {
            System.out.println("USUARIO: " + empleado.usuario + ", CONTRASEÑA: " + empleado.contraseña);
        }
        System.out.println("-------------------------------------------------");
    }// ----------------------FIN MÉTODO VISUALIZAR CREDENCIALES EMPLEADOS
     // --------------------------

    // ---------------------- MÉTODO ACTUALIZAR DIAS LABORABLES
    // --------------------------
    public void actualizarDiasLaborables(ArrayList<Empleados> listaEmpleados) {
        boolean centinelaWhileActuDiasLaborables = true;
        while (centinelaWhileActuDiasLaborables) { // ESTA LINEA MANTIENE EN EJECUCIÓN EL MÉTODO DESDE ANTES DE LA
                                                   // SELECCIÓN DEL EMPLEADO
            int indiceEmpleado = selectorEmpleados(listaEmpleados); // MÉTODO selector empleado(return indice ArrayList)
            boolean centinelaWhileActuDiasLaborables2 = true;
            while (centinelaWhileActuDiasLaborables2) {// ESTA LINEA MANTIENE EN EJECUCIÓN EL MÉTODO DESPUÉS DE LA
                                                       // SELECCIÓN DEL EMPLEADO
                String[] claves = listaEmpleados.get(indiceEmpleado).diasSemana.keySet().toArray(new String[0]);// Crea
                                                                                                                // un
                                                                                                                // elemento
                                                                                                                // iterable
                                                                                                                // contenedor
                                                                                                                // de
                                                                                                                // claves(propio
                                                                                                                // de
                                                                                                                // cada
                                                                                                                // empleado)
                System.out.println("SELECCIONA EL DIA QUE VAS A MODIFICAR");
                for (int i = 0; i < claves.length; i++) // Recorre los dias, imprimiendo un indice dinámico; para que el
                                                        // usuario elija el dia
                {
                    System.out.println("indice->" + i + " " + claves[i]);
                } // Impresión-> Uso del for complejo por la necesidad de var control
                Scanner teclado8 = new Scanner(System.in);
                int indiceClaveSELECCIONADO = teclado8.nextInt();// SELECCIÓNa un indice impreso en el bucle for
                String claveIndiceSELECCIONADO = claves[indiceClaveSELECCIONADO];// almacena la clave SELECCIÓNada a
                                                                                 // través del indice en la linea
                                                                                 // anterior
                if (listaEmpleados.get(indiceEmpleado).diasSemana.get(claveIndiceSELECCIONADO) == true) {
                    System.out.println("EL DIA " + claveIndiceSELECCIONADO
                            + " ACTUALMENTE ESTÁ ACTIVADO ( VALIDO PARA EL REGISTRO)");
                } else if (listaEmpleados.get(indiceEmpleado).diasSemana.get(claveIndiceSELECCIONADO) == false) {
                    System.out.println("EL DIA " + claveIndiceSELECCIONADO
                            + " ACTUALMENTE ESTÁ DESACTIVADO ( INVALIDO PARA EL REGISTRO)");
                }
                System.out.println("¿ QUE DESEA CAMBIAR DEL DIA LABORABLE ->" + claveIndiceSELECCIONADO + "?");
                System.out.println("1. ACTIVAR");
                System.out.println("2. DESACTIVAR");
                System.out.println("3. SELECCIONAR OTRO EMPLEADO");
                System.out.println("4. FINALIZAR SECCIÓN");
                int respuesta = teclado8.nextInt();
                switch (respuesta) {
                    case 1:
                        if (listaEmpleados.get(indiceEmpleado).diasSemana.get(claveIndiceSELECCIONADO) == true) {
                            System.out.println("EL DIA SELECCIONADO YA ESTÁ ACTIVADO");
                            break;
                        } else {
                            listaEmpleados.get(indiceEmpleado).diasSemana.put(claveIndiceSELECCIONADO, true);
                            System.out
                                    .println("EL DIA LABORABLE " + claveIndiceSELECCIONADO + " SE ACTIVÓ EXITOSAMENTE");
                            break;
                        }

                    case 2:
                        if (listaEmpleados.get(indiceEmpleado).diasSemana.get(claveIndiceSELECCIONADO) == false) {
                            System.out.println("EL DIA SELECCIONADO YA ESTÁ DESACTIVADO");
                            break;
                        } else {
                            listaEmpleados.get(indiceEmpleado).diasSemana.put(claveIndiceSELECCIONADO, false);
                            System.out.println(
                                    "EL DIA LABORABLE " + claveIndiceSELECCIONADO + " SE DESACTIVO EXITOSAMENTE");
                            break;
                        }

                    case 3:
                        System.out.println(
                                "FIN DE MODIFICACIONES EN EL USUARIO " + listaEmpleados.get(indiceEmpleado).usuario);
                        centinelaWhileActuDiasLaborables2 = false;
                        break;

                    case 4:
                        System.out.println("SALIENDO DE LA INTERFAZ");
                        centinelaWhileActuDiasLaborables = false;
                        return;

                    default:
                        System.out.println("OPCIÓN INVALIDA");
                        break;
                }

            } // ----------- FIN WHILE ACTUALIZAR DIAS LABORABLES-EMPLEADO SELECCIONADO
              // --------------------------
        } // ----------- FIN WHILE ACTUALIZAR DIAS LABORABLES-SIN SELECCIÓN EMPLEADOS
          // --------------------------
    }// ------------------------------- FIN MÉTODO ACTUALIZAR DIAS LABORABLES
     // --------------------------

     // -------------------MÉTODO INGRESO DE COMENTARIOS AL REGISTRO-----------

    public void ingresoComentarios(String claveDiaSeleccionado, HashMap<String,ArrayList<String>>comentarioSemana) {
        System.out.println();
        if (comentarioSemana.get(claveDiaSeleccionado)== null || comentarioSemana.get(claveDiaSeleccionado).isEmpty()) {
            ArrayList<String> comentarioTemporal= new ArrayList<>();
            while (true) {
                System.out.println("CUANDO HAYA FINALIZADO EL INGRESO DE COMENTARIOS -> ESCRIBA 'FIN'");
            System.out.println("INGRESE EL COMENTARIO");
            Scanner teclado = new Scanner(System.in);
            String comentario = teclado.nextLine();
            if (comentario.equalsIgnoreCase("fin")) {
                System.out.println();
                return;
            }

            comentarioTemporal.add(comentario);
            comentarioSemana.put(claveDiaSeleccionado, comentarioTemporal);
            System.out.println("COMENTARIO REGISTRADO CORRECTAMENTE");
            System.out.println("COMENTARIO: "+comentario+"\n");
            }
        
        }else{
            ArrayList<String> comentariosT = new ArrayList<String>(comentarioSemana.get(claveDiaSeleccionado));
            while (true) {
                System.out.println("CUANDO HAYA FINALIZADO EL INGRESO DE COMENTARIOS -> ESCRIBA 'FIN'");
            System.out.println("INGRESE EL COMENTARIO");
            Scanner teclado = new Scanner(System.in);
            String comentario = teclado.nextLine();
            if (comentario.equalsIgnoreCase("fin")) {
                System.out.println();
                return;
            }

            comentariosT.add(comentario);
            comentarioSemana.put(claveDiaSeleccionado, comentariosT);
            System.out.println("COMENTARIO REGISTRADO CORRECTAMENTE");
            System.out.println("COMENTARIO: "+comentario+"\n");
            }

        } 

    }

    // ----------------FIN MÉTODO INGRESO DE COMENTARIOS AL REGISTRO-----------

    //---------------MÉTODO ELIMINAR COMENTARIOS DEL REGISTRO-----------
        public void eliminarComentarios(int indiceDiaAEliminar, HashMap<String, ArrayList<String>> comentarios){
            System.out.println();
            String[] claves = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
            // String [] claves = comentarios.keySet().toArray(new String[0]);
            String diaSeleccionado = claves[indiceDiaAEliminar];
            if (comentarios.get(diaSeleccionado)!=null) {
                System.out.println("SELECCIONE EL INDICE DEL COMENTARIO QUE QUIERA ELIMINAR");
                for(int i=0; i<comentarios.get(claves[indiceDiaAEliminar]).size(); i++){ //IMPRIME LOS MENSAJES GUARDADOS CON SUS INDICES
                    System.out.println("INDICE : " +i+ " COMENTARIO ->"+comentarios.get(claves[indiceDiaAEliminar]).get(i));
                }
                System.out.println("SELECCIONE \n 1. ELIMINAR TODOS LOS COMENTARIOS:\n2. ELIMINAR REGISTRO ESPECIFICO\n");
                Scanner teclado69 = new Scanner(System.in);
                int opcion = teclado69.nextInt();
                switch (opcion) {
                    case 1:
                    comentarios.put(claves[indiceDiaAEliminar],null );
                    System.out.println("COMENTARIOS RESTABLECIDOS");
                        break;
                    case 2:
                    Scanner teclado70 = new Scanner(System.in);
                    System.out.println("SELECCIONE EL INDICE DEL COMENTARIO QUE QUIERE ELIMINAR");
                    int indiceComentario = teclado70.nextInt();


                    ArrayList<String>comentariosTemp = new ArrayList<>(comentarios.get(diaSeleccionado));
                    comentariosTemp.remove(indiceComentario);
                    comentarios.put(diaSeleccionado, comentariosTemp );
                    System.out.println("COMENTARIO CON ID -> "+ indiceComentario+" ELIMINADO");
                    break;

                    default:
                    System.out.println("OPCIÓN INVALIDA");
                        break;
                }
            }else{System.out.println("NO HAY COMENTARIOS A ELIMINAR");}
            System.out.println();
            
        }

    //-------------- FIN MÉTODO ELIMINAR COMENTARIOS DEL REGISTRO-----------

    // ------------------MÉTODO MODIFICAR COMENTARIOS-------------------------
    public void modificarComentarios(int indiceDiaAModificar,HashMap<String, ArrayList<String>> comentarios) {
        Scanner teclado95 = new Scanner(System.in);
        System.out.println();
        String[] claves = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        // String [] claves = comentarios.keySet().toArray(new String [0]);
        String diaSeleccionado = claves[indiceDiaAModificar];
        if (comentarios.get(diaSeleccionado)!=null) {
            ArrayList<String>comentariosTemp = new ArrayList<>(comentarios.get(diaSeleccionado));
            System.out.println("SELECCIONE EL INDICE DEL COMENTARIO QUE QUIERE MODIFICAR");
            for (int i = 0; i < comentarios.get(claves[indiceDiaAModificar]).size();i++){
                System.out.println("INDICE : "+i+" COMENTARIO : "+comentarios.get(diaSeleccionado).get(i));
            }
            System.out.println("SELECCIONE EL INDICE DEL COMENTARIO A MODIFICAR");
            int indiceComentarioAMod= teclado95.nextInt();
            teclado95.nextLine();
            System.out.println("INGRESE EL COMENTARIO CON EL QUE VA A REEMPLAZARLO");
            String comentarioReplace = teclado95.nextLine();
            comentariosTemp.set(indiceComentarioAMod, comentarioReplace);

            comentarios.put(claves[indiceDiaAModificar], comentariosTemp);
            System.out.println("COMENTARIO CON ID ->"+ indiceComentarioAMod+" MODIFICADO");
        }else{System.out.println("NO HAY COMENTARIOS A MODIFICAR");
        System.out.println();
            return;
        }

    }
    //-------------- FIN MÉTODO MODIFICAR COMENTARIOS ----------------------------------------------------------------

    // -------------------MÉTODO SELECTOR EMPLEADOS-------------------------
    public int selectorEmpleados(ArrayList<Empleados> listaEmpleados) {
        System.out.println();
        Scanner teclado7 = new Scanner(System.in);
        System.out.println("SELECCIONE EL INDICE DEL EMPLEADO ");
        int SELECCIÓN;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            System.out.println(
                    "----------------------\n " + "INDICE -> " + i + "| NOMBRE ->" + listaEmpleados.get(i).usuario);
        }
        System.out.println("---------------------");
        SELECCIÓN = teclado7.nextInt();
        System.out.println();
        return SELECCIÓN;

    }// ---------------FIN MÉTODO SELECTOR EMPLEADOS---------------------------

       
    //-------------------MÉTODO MODIFICAR HORAS REGISTRADAS----------
    public void modificarHorasRegistradas(ArrayList<Empleados>listaEmpleados,int indiceEmpleado){
        // System.out.println("SELECCIONE EL EMPLEADO AL QUE QUIERE MODIFICAR LAS HORAS REGISTRADAS");
        // for (int i = 0; i < listaEmpleados.size(); i++) {
        //     System.out.println("INDICE -> "+i+"| USUARIO : "+listaEmpleados.get(i).usuario);
        // }
        // Scanner selectorEmpleado = new Scanner(System.in);
        // int indiceEmpleado = selectorEmpleado.nextInt();

        System.out.println("SELECCIONE EL DÍA QUE QUIERE MODIFICAR LAS HORAS REGISTRADAS");
        String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
        for (int index = 0; index < dias.length; index++) {
            String horast = Arrays.toString(listaEmpleados.get(indiceEmpleado).registro.get(dias[index]));
            System.out.println("INDICE -> " + index+ "| DIA-> "+ dias[index]+ " HORAS ->"+ horast);   
        }
        Scanner selectorDia = new Scanner(System.in);
        int indiceDia = selectorDia.nextInt();
        String claveDiaSeleccionado = dias[indiceDia];
        if (listaEmpleados.get(indiceEmpleado).registro.get(claveDiaSeleccionado)!=null) {
            boolean centinela=true;
            while (centinela) {
                System.out.println("EDITARÁ HORAS HASTA QUE DIGITE COMO 'HORA' EL #27");
                System.out.println("SELECCIONE EL INDICE DE LA HORA QUE DESEA MODIFICAR");
                for (int i = 0; i < listaEmpleados.get(indiceEmpleado).registro.get(claveDiaSeleccionado).length; i++){
                    System.out.println("INDICES -> "+ i+" HORA -> "+listaEmpleados.get(indiceEmpleado).registro.get(claveDiaSeleccionado)[i]);
                }
                Scanner teclado77=new Scanner(System.in);
                int indiceHoraAModificar = teclado77.nextInt();
                System.out.println("EDITARÁ HORAS HASTA QUE DIGITE COMO 'HORA' EL #27");
                System.out.println("INGRESE LA NUEVA HORA A ALMACENAR");
                Scanner scanner = new Scanner(System.in);
                System.out.println("ESCRIBA LA HORA DE ENTRADA (FORMATO 24HR's) MAS ADELANTE SE LE SOLICITARAN LOS MINUTOS DE LA HORA");
                Integer horaIn = scanner.nextInt();
                if (horaIn==27) {
                    centinela=false;
                    return;
                }
                System.out.println("ESCRIBA EL MINUTO CORRESPONDIENTE A SU HORA DE ENTRADA (SI ENTRO EN PUNTO ESCRIBA '0')");
                Integer minutoIn = scanner.nextInt();
                if (horaIn > 23 || horaIn < 0 || minutoIn < 0 || minutoIn > 59) {
                    System.out.println("RANGO HORARIO INVALIDO");
                    return;
                }
                LocalTime horanew = LocalTime.of(horaIn, minutoIn);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
                String [] horasTemp=listaEmpleados.get(indiceEmpleado).registro.get(claveDiaSeleccionado).clone();
                horasTemp[indiceHoraAModificar]=horanew.format(formatter);
                listaEmpleados.get(indiceEmpleado).registro.put(claveDiaSeleccionado, horasTemp);
            } 
        }else{System.out.println("NO HAY REGISTROS QUE MODIFICAR");}
        return;
    } 

    //----------------FIN METODO MODIFICAR HORAS REGISTRADAS---------


    // ------------------MÉTODO MENÚ ADMINISTRATIVO  REGISTROS--------------------
    public void menuRegistros(ArrayList<Empleados> listaEmpleados, Administrador admin) {
            System.out.println();
            System.out.println("SELECCIONE");
            System.out.println("1. MOSTRAR REGISTROS DE EMPLEADOS");
            System.out.println("2. EDITAR REGISTROS DE EMPLEADOS");
            System.out.println("3. ALMACENAR COMENTARIOS PARA SUS EMPLEADOS");
            System.out.println("4. ELIMINAR COMENTARIOS PARA SUS EMPLEADOS");
            System.out.println("5. MODIFICAR COMENTARIOS PARA SUS EMPLEADOS");
            System.out.println("6. REGRESAR");
            System.out.println();
            Scanner teclado9 = new Scanner(System.in);
            int opcion = teclado9.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println();
                    for (int i = 0; i < listaEmpleados.size(); i++) {
                        admin.imprimirRegistrosEmpleados(listaEmpleados.get(i).registro, listaEmpleados.get(i).usuario,
                                listaEmpleados,listaEmpleados.get(i).comentariosSemana);
                    }
                    System.out.println();
                    break;

                case 2:
                System.out.println("TENDRÁ QUE ELEGIR EL INDICE DEL EMPLEADO QUE QUIERA USAR");
                int indiceEmpleadoModReg= selectorEmpleados(listaEmpleados);//Guarda el indice del empleado a modificar
                modificarHorasRegistradas(listaEmpleados,indiceEmpleadoModReg);
                break;

                case 3:
                    System.out.println("TENDRÁ QUE ELEGIR EL INDICE DEL EMPLEADO QUE QUIERA USAR");
                    int indiceEmpleado= selectorEmpleados(listaEmpleados);//Guarda el indice del empleado a modificar
                    String claveDiaSeleccionado = imprimirComentariosGetKey(listaEmpleados, indiceEmpleado); //Linea 196, retorna la clave del dia a comentar

                    ingresoComentarios(claveDiaSeleccionado, listaEmpleados.get(indiceEmpleado).comentariosSemana);//Linea 381       
                    System.out.println();
                break;

                case 4:
                System.out.println("TENDRÁ QUE ELEGIR EL INDICE DEL EMPLEADO QUE QUIERA USAR");
                int indiceEmpleadoDel= selectorEmpleados(listaEmpleados);//Guarda el indice del empleado a modificar
                int indexDiaAEliminar = imprimirComentariosGetIndex(listaEmpleados, indiceEmpleadoDel); //Linea 224, retorna la clave del dia a eliminar
                admin.eliminarComentarios(indexDiaAEliminar, listaEmpleados.get(indiceEmpleadoDel).comentariosSemana); //Linea 403
                System.out.println();

                break;

                case 5:
                System.out.println("TENDRÁ QUE ELEGIR EL INDICE DEL EMPLEADO QUE QUIERA USAR");
                int indiceEmpleadoMod= selectorEmpleados(listaEmpleados);//Guarda el indice del empleado a modificar
                int indexDiaAModificar = imprimirComentariosGetIndex(listaEmpleados, indiceEmpleadoMod); //Linea 224, retorna la clave del dia a eliminar
                admin.modificarComentarios(indexDiaAModificar, listaEmpleados.get(indiceEmpleadoMod).comentariosSemana); //Linea 403
                System.out.println();


                break;
                case 6:
                System.out.println();
                    return;
    
                default:
                System.out.println("OPCIÓN INVALIDA");
                    break;
            }
    
        }
        // ---------------FIN MÉTODO MENÚ REGISTROS--------------------
    
        //--------- MÉTODO MENÚ EMPLEADOS--------------------
    
    
        //------------FIN MÉTODO MENÚ EMPLEADOS--------------------
    
        // -------------- MÉTODO MENÚ OPCIONES CON EMPLEADOS----
        public void menuEmpleados(Administrador admin,ArrayList<Empleados>listaEmpleados){
            System.out.println();
            System.out.println("SELECCIONE");
            System.out.println("1. REGISTRAR EMPLEADO");
            System.out.println("2. IMPRIMIR CREDENCIALES DE EMPLEADOS");
            System.out.println("3. MODIFICAR DIAS LABORABLES");
            System.out.println("4. ATRÁS");
            System.out.println();
    
            Scanner teclado10 = new Scanner(System.in);
            int opcion = teclado10.nextInt();
    
            switch (opcion) {
                case 1:  
                admin.crearEmpleado(listaEmpleados);
                System.out.println();
                    break;
                case 2: 
                admin.visualizarCredencialesEmpleados(listaEmpleados);
                System.out.println();
                break;
                case 3:
                admin.actualizarDiasLaborables(listaEmpleados);
                System.out.println();
                break;
                case 4 :
                return;
                default:
                System.out.println();
                System.out.println("OPCIÓN INVALIDA");
                System.out.println();
                    break;
            }
        }
    
        // -------------- FIN MÉTODO MENÚ OPCIONES CON EMPLEADOS---
    
        // -----------FIN MÉTODOS DE CLASE ADMINISTRADOR-----------------
    } // -----------------------FIN CLASE
      // ADMINISTRADOR---------------------------------
    
    public class Main {

        //-----MÉTODO SESIÓN ADMINISTRADOR--------------
        public static void sesionAdministrador(Administrador admin, ArrayList<Empleados>listaEmpleados){
                    System.out.println("\nBienvenido Administrador\n");
                        boolean centinelaWhileAdmin = true;
                        while (centinelaWhileAdmin) { 
                            Scanner teclado = new Scanner(System.in);
                           System.out.println("SELECCIÓNe la acción que desea realizar");
                            System.out.println("1. -> OPCIONES CON EMPLEADOS");
                            System.out.println("2. -> OPCIONES CON LOS REGISTROS DE EMPLEADOS");
                            System.out.println("3. -> CERRAR SESIÓN");
                            int opcion = teclado.nextInt();
                            switch (opcion) {
                                case 1:
                                    System.out.println();
                                    admin.menuEmpleados(admin, listaEmpleados);
                                    System.out.println();
                                    break;
                                case 2:
                                System.out.println();
                                  admin.menuRegistros(listaEmpleados, admin);
                                  System.out.println();
                                  break;
                            case 3:
                                System.out.println("SESIÓN CERRADA CON ÉXITO\n");
                                centinelaWhileAdmin = false;
                                break;
        
                            default:
                                System.out.println("OPCIÓN INVALIDA");
                                break;
                        } // llave correspondiente al switch case
                    } // llave correspondiente al bucle while
        
                }
        
                //-----FIN MÉTODO SESIÓN ADMINISTRADOR------------
            
                // ---------MÉTODO "LOGIN" ------------
                public static  void LogIn(String tryUser, String tryPassword, ArrayList<Empleados> listaEmpleados,
                                Administrador admin) {
                    
                            if (tryUser.equals(admin.usuario) && tryPassword.equals(admin.password)) { // VERIFICA SI LAS CREDENCIALES  COINCIDEN CON LAS DEL ADMIN
                            sesionAdministrador(admin, listaEmpleados);
                } // llave correspondiente al IF statement
        
                for (int i = 0; i < listaEmpleados.size(); i++) {
                    if (tryUser.equals(listaEmpleados.get(i).usuario) && tryPassword.equals(listaEmpleados.get(i).contraseña)) {
                        System.out.println("BIENVENIDO " + tryUser);
                        boolean centinelaWhileEmpleados = true;
                        while (centinelaWhileEmpleados) {
                            Scanner teclado4 = new Scanner(System.in);
                            System.out.println("A CONTINUACIÓN SERA DESPLEGADO SU MENU DE ACCIÓN");
                            System.out.println("1. -> REGISTRAR HORAS");
                            System.out.println("2. -> MOSTRAR COMENTARIOS");
                            System.out.println("3. -> SALIR DE LA INTERFAZ");
                            int opcion = teclado4.nextInt();
                            switch (opcion) {
                                case 1:
                                    listaEmpleados.get(i).registroHoras(listaEmpleados.get(i).horasRegistradas,
                                            listaEmpleados.get(i).comentariosSemana);
        
                                    break;
                                case 2:
                                listaEmpleados.get(i).impresiónComentarios(listaEmpleados.get(i).comentariosSemana);;
                                break;
                                case 3:
                                    System.out.println("SALIENDO DE LA INTERFAZ\nGRACIAS POR USAR");
                                    centinelaWhileEmpleados = false;
                                    break;
        
                                default:
                                    System.out.println("OPCIÓN INVALIDA");
                                    break;
                            }
        
                        } // llave bucle while
                    } // llave if statement
                } // llave bucle for
        
            }
            // -----------FIN MÉTODO "LOGIN" -----------------------
        
            public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                Administrador admin = new Administrador();
                ArrayList<Empleados> listaEmpleados = new ArrayList<>();
                Scanner tecladoMain = new Scanner(System.in);
                listaEmpleados.add(new Empleados("test", "test"));
                System.out.println("BIENVENIDO");
                while (true) {
                    System.out.println("QUE ACCIÓN DESEA REALIZAR?");
                    System.out.println("1. -> INICIAR SESIÓN");
                    System.out.println("2. -> SALIR");
                    int opcion = tecladoMain.nextInt();
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
                    System.out.println("OPCIÓN INVALIDA");
                    break;
            }
        }
    } // -------------------------------------FIN DEL MAIN
      // ---------------------------------------------

}// -------------------------------------FIN CLASE -> MAIN
 // ---------------------------------------------

// -------------------------------FIN CLASE ->
// MAIN----------------------------------------------

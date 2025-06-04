package com.prueba.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Conversion {

    private String result;
    private String time_last_update_utc;
    private Object conversion_rates; // esto viene en formato objeto de la API
    private String[] tazasConversion;
    private int longitudArreglo;
    private Map<String, Double> tazasAsociativo;



    public Conversion(){
        this.setResult("");
        this.setTime_last_update_utc("");
        this.setConversion_rates(null);
        this.setTazasConversion(null);
        this.setLongitudArreglo(0);
        this.setTazasAsociativo(null);

    }

    public void setResult(String result){
        this.result = result;
    }
    public String getResult(){
        return this.result;
    }

    public void setTime_last_update_utc(String time_last_update_utc){ this.time_last_update_utc = time_last_update_utc;}
    public String getTime_last_update_utc(){return this.time_last_update_utc;}

    public void setConversion_rates(Object conversion_rates){ this.conversion_rates = conversion_rates;}
    public Object getConversion_rates(){return this.conversion_rates;}

    public void setTazasConversion(String[] tazasConversion){ this.tazasConversion = tazasConversion;}
    public String[] getTazasConversion(){return this.tazasConversion;}

    public void setLongitudArreglo(int longitudArreglo){ this.longitudArreglo = longitudArreglo;}
    public int getLongitudArreglo(){return this.longitudArreglo;}

    public void setTazasAsociativo(Map<String, Double> tazasAsociativo){ this.tazasAsociativo = tazasAsociativo;}
    public Map<String, Double> getTazasAsociativo(){return this.tazasAsociativo;}

    public Map<String, Double> obtenerTazasConversion(Object objetoTaza){
        String nuevoJson ="";
        String stringTaza = objetoTaza.toString();
        // remplazamos llaves en el string
        nuevoJson = stringTaza.replace("{", "").replace("}", "");
        // luego separamos cada uno de los elementos del json a través de la coma, y guardamos cada resultado en un arreglo
        String[] keyValuePairs = nuevoJson.split(",");
        // almacenamos en el objeto el arreglo obtenido
        this.setTazasConversion(keyValuePairs);
        // almacenamos la logitud del arreglo
        this.longitudArreglo = keyValuePairs.length;
        System.out.println("Mensaje Conversor: Longitud del arreglo: "+this.longitudArreglo);
        // Definimos arreglos para las claves y los valores con dimensión igual al arreglo que tiene todos los clave-valor
        String[] arregloClaves = new String[this.longitudArreglo];
        Double[] arregloValores = new Double[this.longitudArreglo];

        // creamos el map que se comportara como arreglo asociativo
        Map<String, Double> arreglo_asociativo = new HashMap<String, Double>();

        // finalmente recorremos el arreglo de claves par llave-valor, para crear un arreglo para llaves y otro para valores
        for (int i = 0; i < this.longitudArreglo; i++) {
            System.out.println("Mensaje Conversor: Key-Value: "+keyValuePairs[i]);
            // separamos cada uno de los elementos del resultado para extraer la clave y el valor por serparado a traves del signo igual =
            String[] arrElementos = keyValuePairs[i].split("=");
            for (int j = 0; j < arrElementos.length; j++) {
                    System.out.println("Mensaje Conversor: arrElementos j= "+j+" es: "+arrElementos[j]);
                    // separamos las claves y los valores en cada iteracion
                    if(j==0){
                        //limpiamos espacios en blanco en las claves, porque traen espacios segun se ve en la salida oroginal
                        //se utiliza una expresion regular para remplazar con comillas vacias lo que no sea numero, mayuscula o minuscula
                        String claveLimpia = arrElementos[j].replaceAll("[^\\dA-Za-z]", "");
                        // asignamos la clave limpia al arreglo de claves
                        arregloClaves[i] = claveLimpia;
                    }
                    else{
                        // asignamos los valores convertidos a Double al arreglo de valores
                        arregloValores[i] = Double.parseDouble(arrElementos[j]);
                    }

            }// fin for interno
            // despues de cada iteracion interna(clave-valor) asignamos al arreglo asociativo las claves y los valores identificados
            arreglo_asociativo.put(arregloClaves[i], arregloValores[i]);
        }// fin for Externo

        // establecemos en el map del objeto el arreglo asociativo obtenio
        this.setTazasAsociativo(arreglo_asociativo);

        // retornamos el arreglo asociativo obtenido
        return this.getTazasAsociativo();
    }//fin obtenerTazasConversion

    public int mostrarMenu(){
        int opcion = 0;
        Scanner capturarOpcion = new Scanner(System.in);
        try{
            while(opcion != 7 ){
                System.out.println("*******************************************");
                System.out.println("Sea bienvenido/a al conversor de Moneda =] \n");
                System.out.println("1) Dolar => Peso argentino");
                System.out.println("2) Peso argentino => Dolar \n");
                System.out.println("3) Dolar => Real Brasileno");
                System.out.println("4) Real Brasileno => Dolar \n");
                System.out.println("5) Dolar => Peso Colombiano");
                System.out.println("6) Peso Colombiano => Dolar \n");
                System.out.println("7) Salir");
                System.out.println("Elija una Opción válida");
                opcion = capturarOpcion.nextInt();

                String mensajeUsuario = "";
                String mensajeSalida = "";
                Double valorAtrapado=0.0;
                Double valorCalculado =0.0;
                DecimalFormat df = new DecimalFormat("0.00");
                String numeroFormateado = "";
                switch(opcion){
                    case 1:
                        mensajeUsuario = "Ingrese el valor en dólares que desea convertir a pesos argentinos";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = dolarAPesoArgentino(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[USD] convertido a pesos argentinos es de "+numeroFormateado+"[ARS]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }
                        break;
                    case 2:
                        mensajeUsuario = "Ingrese el valor en Pesos argentinos que desea convertir a dólares";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = pesoArgentinoADolar(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[ARS] convertido a dólares es de "+numeroFormateado+"[USD]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }
                        break;
                    case 3:
                        mensajeUsuario = "Ingrese el valor en dólares que desea convertir a real brasileño";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = dolarARealBrasileno(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[USD] convertido a real brasileño es de "+numeroFormateado+"[BRL]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }
                        break;
                    case 4:
                        mensajeUsuario = "Ingrese el valor en Reales Brasileños que desea convertir a dólares";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = realBrasilenoADolar(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[BRL] convertido a dólares es de "+numeroFormateado+"[USD]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }
                        break;
                    case 5:
                        mensajeUsuario = "Ingrese el valor en dólares que desea convertir a peso colombiano";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = dolarAPesoColombiano(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[USD] convertido a peso colombiano es de "+numeroFormateado+"[COP]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }
                        break;
                    case 6:
                        mensajeUsuario = "Ingrese el valor en Pesos Colombianos que desea convertir a dólares";
                        valorAtrapado = atraparDatos(mensajeUsuario);
                        if(valorAtrapado != 99.0){
                            valorCalculado = pesoColombianoADolar(valorAtrapado);
                            numeroFormateado = df.format(valorCalculado);//formato con dos cifras decimales
                            mensajeSalida = "El valor "+valorAtrapado+"[COP] convertido a dólares es de "+numeroFormateado+"[USD]";
                        }
                        else{
                            mensajeSalida= "Error ingresando datos";
                        }

                        break;
                    case 7:
                        mensajeSalida= "Eligió Opción Salir";
                        break;
                    default:
                        break;
                }
                System.out.println("==================================");
                System.out.println("El resultado se mostrará por 10 segundos.");
                System.out.println(mensajeSalida);
                System.out.println("==================================");
                Thread.sleep(10000); // Pause for 2 seconds
                System.out.println("*******************************************");
            }

            return 0;
        }
        catch(Exception e){
            System.out.println("Excepción en la captura de datos. Digite solo números. "+e.getMessage());
            return 99;
        }

    }// fin mostrar menu

    public Double atraparDatos(String mensaje){
        Double valorIngresado=0.0;
        Scanner capturar = new Scanner(System.in);
        try{
            System.out.println(mensaje);
            valorIngresado = capturar.nextDouble();
            return valorIngresado;
        }
        catch(Exception e){
            System.out.println("Excepcion en la captura de datos. Digite solo numeros. "+e.getMessage());
            return 99.0;
        }

    }// fin atraparDatos

    public Double dolarAPesoArgentino(Double dolar){

        Double factorConversion = this.getTazasAsociativo().get("ARS"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double pesoArgentino = dolar * factorConversion;
        return pesoArgentino;

    }


    public Double pesoArgentinoADolar(Double pesoArgentino){

        Double factorConversion = this.getTazasAsociativo().get("ARS"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double dolar= pesoArgentino / factorConversion;
        return dolar;

    }

    public Double dolarARealBrasileno(Double dolar){

        Double factorConversion = this.getTazasAsociativo().get("BRL"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double realBrasileno = dolar * factorConversion;
        return realBrasileno;

    }

    public Double realBrasilenoADolar(Double realBrasileno){

        Double factorConversion = this.getTazasAsociativo().get("BRL"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double dolar= realBrasileno / factorConversion;
        return dolar;

    }

    public Double dolarAPesoColombiano(Double dolar){

        Double factorConversion = this.getTazasAsociativo().get("COP"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double pesoColombiano = dolar * factorConversion;
        return pesoColombiano;

    }

    public Double pesoColombianoADolar(Double pesoColombiano){

        Double factorConversion = this.getTazasAsociativo().get("COP"); // OBTENEMOS DEL ARREGLO ASOCIATIVO EL FACTOR DE CONVERSION, DE ACUERDO CON LA SIGLA DEL PAIS
        Double dolar= pesoColombiano / factorConversion;
        return dolar;

    }


    // metodo toString para mostrar informacion del objeto imprimiendo directamente el objeto sin invocar algun metodo
    // el metodo ya existe en el objeto asi que se tiene que sobre escribir
    @Override
    public String toString(){
        return  "\n$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n"+
                "\n Salida metodo toString() clase Conversion \n"+
                "\n Resultado: "+this.getResult()+
                "\n Ultima actualizacion del cambio: "+this.getTime_last_update_utc()+
                "\n Tazas de Conversion: "+this.getConversion_rates()+
                "\n $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$\n ";
    }// fin toString


}// fin clase



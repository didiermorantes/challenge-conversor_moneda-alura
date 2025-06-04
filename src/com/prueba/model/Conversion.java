package com.prueba.model;

import java.util.HashMap;
import java.util.Map;

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
        return 0;
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



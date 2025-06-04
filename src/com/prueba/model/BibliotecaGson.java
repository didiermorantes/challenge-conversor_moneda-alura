package com.prueba.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.List;

public class BibliotecaGson {

    // incluimos el .jar de la biblioteca Gson para su uso sin utilizar maven o gradle
    // se descarga de maven repository https://mvnrepository.com/artifact/com.google.code.gson/gson/2.13.1
    // se incluye en intelliJ con file->Project Structure ->modules ->Dependencies -> + (Add) ->JARs or Directories



    public Conversion procesarJsonConversion(String elJson){
        Gson gson = new Gson();
        Conversion respuestaConversion = gson.fromJson(elJson, Conversion.class );
        return respuestaConversion;
    }



    public String convertirAJsonObjeto(Conversion elObjetoConversion){
        // se utiliza el patron Builder para implementar la politica en el objeto gson que permite que las propiedades que vengan de la API se almacenen sin distinguir las mayusculas
        // se utiliza la politica para que los nombre comiencen en minuscula y sean entendidos por la biblioteca
        // se utiliza la mejora prettyPrinting para que l json generado se vea organizado y bonito
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        String elJson = gson.toJson(elObjetoConversion);
        return elJson;
    }


}

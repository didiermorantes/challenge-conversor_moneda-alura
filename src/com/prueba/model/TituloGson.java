package com.prueba.model;

import com.google.gson.annotations.SerializedName;

public class TituloGson {
    // creamos una clase serializando las propiedades que deseamos almacenar. Lo ideal es que el nombre de la propiedad de la API y el nombre de la variable de clase sean iguales
    @SerializedName("Title") // anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String nombre;
    @SerializedName("Released") // anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String lanzamiento ="";
    @SerializedName("Runtime") // anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String duracion = "";
    @SerializedName("Genre")// anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String genero = "";
    @SerializedName("Director")// anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String director ="";
    @SerializedName("Plot")// anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String trama ="";
    @SerializedName("Country")// anotacion de Gson para que cuando encuentre el campo, proceda a asignar el valor en la variable despues de la anotacion
    private String pais = "";


    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void setLanzamiento(String lanzamiento){
        this.lanzamiento = lanzamiento;
    }
    public String getLanzamiento(){
        return this.lanzamiento;
    }

    public void setDuracion(String duracion){ this.duracion = duracion;}
    public String getDuracion(){return this.duracion;}

    public void setGenero(String genero){ this.genero = genero;}
    public String getGenero(){return this.genero;}

    public void setDirector(String director){ this.director = director;}
    public String getDirector(){return this.director;}

    public void setTrama(String trama){ this.trama = trama;}
    public String getTrama(){return this.trama;}

    public void setPais(String pais){ this.pais = pais;}
    public String getPais(){return this.pais;}


    public void listarInformacionAlmacenada(){
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("INFORMACION DE LA PELICULA EN OBJETO GSON");
        System.out.println("Titulo de la pelicula: "+this.getNombre());
        System.out.println("Fecha de lanzamiento de la pelicula: "+this.getLanzamiento());
        System.out.println("Duracion de la pelicula: "+this.getDuracion());
        System.out.println("Genero de la pelicula: "+this.getGenero());
        System.out.println("Director de lanzamiento de la pelicula: "+this.getDirector());
        System.out.println("Trama de la pelicula: "+this.getTrama());
        System.out.println("Pais de la pelicula: "+this.getPais());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }// fin listarInformacion



}// fin clase



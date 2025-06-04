package com.prueba.persistencia;
import com.prueba.model.Conversion;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

import java.util.Scanner;

public class Archivo {

    /*
    private String dato;

    private String json;

    public String getDato(){
        return this.dato;
    }

    public String getJson(){
        return this.json;
    }


    public String guardarDato(Pelicula unTitulo){
        boolean resultado = false;
        String mensaje = "";
        this.dato = unTitulo.toString();
        System.out.println("Guardando en archivo de texto la siguiente informacion: \n"+this.dato);
        try{
            FileWriter objetoWriter = new FileWriter("persistenciaPelicula.txt");
            objetoWriter.write(this.dato);
            objetoWriter.close();
            System.out.println("Persistencia aplicada existosamente");
            resultado = true;
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion guardando en archivo de texto: "+e.getMessage());
        }
        finally {
            System.out.println("Proceso de persistencia finalizado");
        }

        if(resultado== true){
           mensaje= "Información Almacenada exitosamente en el archivo de texto";
        }
        else{
            mensaje="No se almacenó información en el archivo de texto";
        }
        return mensaje;

    }


    public String guardarJson(String unJson){
        boolean resultado = false;
        String mensaje = "";
        this.json = unJson;
        System.out.println("Guardando en archivo json la siguiente informacion: \n"+this.json);
        try{
            FileWriter objetoWriter = new FileWriter("persistenciaPeliculaJson.json");
            objetoWriter.write(this.json);
            objetoWriter.close();
            System.out.println("Persistencia json aplicada existosamente");
            resultado = true;
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion guardando en archivo json: "+e.getMessage());
        }
        finally {
            System.out.println("Proceso de persistencia json finalizado");
        }

        if(resultado== true){
            mensaje= "Información Almacenada exitosamente en el archivo json";
        }
        else{
            mensaje="No se almacenó información en el archivo json";
        }
        return mensaje;

    }

    public String guardarJsonObjeto(Pelicula elObjetoPelicula){
        boolean resultado = false;
        String mensaje = "";
        this.json = elObjetoPelicula.toString();
        System.out.println("Guardando en archivo json la siguiente informacion: \n"+this.json);
        try{
            FileWriter objetoWriter = new FileWriter(elObjetoPelicula.getTitle()+".json");
            objetoWriter.write(this.json);
            objetoWriter.close();
            System.out.println("Persistencia json aplicada existosamente");
            resultado = true;
        }
        catch(Exception e){
            System.out.println("Ocurrio una excepcion guardando en archivo json: "+e.getMessage());
        }
        finally {
            System.out.println("Proceso de persistencia json finalizado");
        }

        if(resultado== true){
            mensaje= "Información Almacenada exitosamente en el archivo json";
        }
        else{
            mensaje="No se almacenó información en el archivo json";
        }
        return mensaje;

    }

    public String leerDatoScanner(){
        String datoLeido = "";
        String path= "persistenciaPelicula.txt";
        System.out.println("Leyendo archivo de texto con la clase SCANNER de la siguiente direccion: "+path);
        try{
            File file = new File(path);
            Scanner objetoScanner = new Scanner(file);
            String linea = "";

            while (objetoScanner.hasNextLine()) {
                linea = objetoScanner.nextLine() + "\n"; // agregamos salto de linea despues de haber leido la linea para que se asemeje al elemento guardado
                datoLeido = datoLeido + linea;
                System.out.print(linea);
            }
            objetoScanner.close();

        }
        catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado: "+e.getMessage());
        }
        catch(Exception e){
            System.out.println("Error leyendo archivo de texto: "+e.getMessage());
        }
        finally{
            System.out.println("\nProceso de Lectura de archivo de texto finalizado");
        }
        return datoLeido;
    }


    public String leerDato(){
        String datoLeido = "";
        String path= "persistenciaPelicula.txt";
        System.out.println("Leyendo archivo de texto  de la siguiente direccion: "+path);
        try{
            File file = new File(path);
            FileReader objetoReader = new FileReader(file);

            int data = objetoReader.read();
            while (data != -1) {
                datoLeido = datoLeido + (char) data;
                System.out.print((char) data);
                data = objetoReader.read();
            }
            objetoReader.close();

        }
        catch(Exception e){
            System.out.println("Error leyendo archivo de texto: "+e.getMessage());
        }
        finally{
            System.out.println("\nProceso de Lectura de archivo de texto finalizado");
        }
        return datoLeido;
    }

     */
}

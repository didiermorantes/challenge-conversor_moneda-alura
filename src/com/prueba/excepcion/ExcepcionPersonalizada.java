package com.prueba.excepcion;
// si extiende a RuntimeException es una excepcion no verificada
//significa que la clase que captura//arroja la excepcion no tiene que implementar throwable
// estudiar diferencias entre excepciones verificadas y no verificadas
public class ExcepcionPersonalizada extends RuntimeException{
    private String mensaje;


    public ExcepcionPersonalizada(String mensaje){
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage(){
        return this.mensaje;
    }
}

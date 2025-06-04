
import com.prueba.model.*;
import com.prueba.solicitud.SolicitudExchange;


import java.io.IOException;
import java.util.Map;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // creamos objeto solicitud
        SolicitudExchange objetoSolicitudExchange = new SolicitudExchange();
        // creamos objeto para recibir Respuesta
        Conversion objetoConversion= new Conversion();
        System.out.println("Mensaje main: El endpoint es: "+objetoSolicitudExchange.mostrarEndpoint());
        // consumimos el endpoint configurado
        objetoSolicitudExchange.consumirEndpointResponse();
        System.out.println("Mensaje main: La respuesta tipo response obtenida es:"+objetoSolicitudExchange.getRespuestaAPIResponse());

        // construimos un objeto Gson para procesar el JSON y obtener arreglos con las claves y valores
        BibliotecaGson objetoGson = new BibliotecaGson();
        // procesamos el json obtenido del request y obtenemos un objeto
        objetoConversion =  objetoGson.procesarJsonConversion(objetoSolicitudExchange.getRespuestaAPIResponse());
        System.out.println("Mensaje main:ObjetoConversion obtenido con biblioteca GSON: "+ objetoConversion);
        // guardamos las tazas de conversion obtenidas de la api en un objeto para luego transformarlo en arreglo
        Object objetoTazas = objetoConversion.getConversion_rates();
        // transformamos el objeto obtenido de la api en un arreglo asociativo para obtener las tazas por pais
        Map<String, Double> mapaTazasConversion = objetoConversion.obtenerTazasConversion(objetoTazas);
        System.out.println("Mensaje main:Taza de conversion : "+ mapaTazasConversion);
        boolean existeArgentina = mapaTazasConversion.containsKey("ARS");
        System.out.println("Mensaje main:¿Argentina está en el Map? " + existeArgentina);
        boolean existeTaza1184 = mapaTazasConversion.containsValue(1184.0);
        System.out.println("Mensaje main:¿Existe alguna taza igual a 1184.0 ? " + existeTaza1184);

        boolean existeBrasil = mapaTazasConversion.containsKey("BRL");
        System.out.println("Mensaje main:¿Brasil está en el Map? " + existeBrasil);
        boolean existeTaza56702 = mapaTazasConversion.containsValue(5.6702);
        System.out.println("Mensaje main:¿Existe alguna taza igual a 5.6702 ? " + existeTaza56702);

        System.out.println("Mensaje main:Taza de conversion de ARGENTINA: "+ mapaTazasConversion.get("ARS"));  // EXPLORAMOS LA RESPUESTA DEVUELTA POR EL METODO
        System.out.println("Mensaje main:Taza de conversion de BRASIL: "+ objetoConversion.getTazasAsociativo().get("BRL")); // EXPLORAMOS LA RESPUESTA DENTRO DEL OBJETO
        System.out.println("Mensaje main:Taza de conversion de COLOMBIA: "+ objetoConversion.getTazasAsociativo().get("COP")); // EXPLORAMOS LA RESPUESTA DENTRO DEL OBJETO
        /*
        // convertimos el objetoConversion a json
        String jsonConversion = objetoGson.convertirAJsonObjeto(objetoPelicula);
        // construimos un objeto de la clase Archivo para guardar el json en archivo .json
        Archivo objetoArchivo = new Archivo();
        String resultadoGuardar =  objetoArchivo.guardarJson(jsonPelicula);
        System.out.println(resultadoGuardar);

*/




    }// fin main
}// fin clase
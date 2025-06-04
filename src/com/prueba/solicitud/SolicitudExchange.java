package com.prueba.solicitud;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



public class SolicitudExchange {
    public String endpoint ;
    public String apiKey;
    private String respuestaAPIResponse = "";

    public String getRespuestaAPIResponse(){
        return this.respuestaAPIResponse;
    }
    public void setRespuestaAPIResponse(String respuestaAPIResponse){
        this.respuestaAPIResponse = respuestaAPIResponse;
    }



    public SolicitudExchange(){
        this.apiKey = "2be8742c50214ae031565bac"; // apiKey de Exchange
        this.endpoint = "https://v6.exchangerate-api.com/v6/"+this.apiKey+"/latest/USD"; // conformamos el endpoint
    }



    public String mostrarEndpoint(){
        return this.endpoint;
    }


    public void consumirEndpointResponse() throws IOException, InterruptedException{
        try{
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(this.endpoint))
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            this.setRespuestaAPIResponse(response.body());



        }
        catch(IOException | InterruptedException e){
            throw new RuntimeException("Excepcion IO/Interrupted consumiendo endpoint: "+e);
        }
        catch(Exception e ){
            System.out.println("Error consumiendo Endpoint tipo Response. Tipo Error: "+ e.getMessage());
            System.out.println("Error: "+ e);
        }
    } // fin consumirEndpointResponse
}

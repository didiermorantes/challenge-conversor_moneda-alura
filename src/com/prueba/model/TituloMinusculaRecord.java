package com.prueba.model;

// los argumentos del record NO COMIENZAN CON MAYUSCULA como en la API, porque se uso la politica FieldNamingPolicy.UPPER_CAMEL_CASE de la BibliotecaGson
// esta clase intermediara se denomina DTO Data Transfer Object. Objeto de transferencia de datos
public record TituloMinusculaRecord(String title, String released, String runtime, String genre, String director, String plot, String country ) {
}


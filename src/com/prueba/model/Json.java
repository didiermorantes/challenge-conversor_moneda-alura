package com.prueba.model;
// PROBLEMAS IDENTIFICADOS 19 MAYO 2025.
//LOS VALORES PUEDEN INCLUIR COMAS O DOS PUNTOS LO QUE PUEDE DAÑAR LOS SPLITS
// POSIBLE SOLUCIÓN: INTENTAR UNA LÓGICA QUE IDENTIFIQUE SI ES CLAVE O VALOR, Y NO TENER EN CUENTA SPLIT EN LOS VALORES
// EVITAR SPLIT ADICIONALES O SUCESIVOS
public class Json {

    private String[] Claves;
    private String[] Valores;
    private int longitudArreglo;
    private int longitudClaves;


    public void setLongitudArreglo(int longitudArreglo){
        this.longitudArreglo = longitudArreglo;
    }
    public int getLongitudArreglo(){
        return this.longitudArreglo;
    }

    public void setLongitudClaves(int longitudClaves){
        this.longitudClaves = longitudClaves;
    }
    public int getLongitudClaves(){
        return this.longitudClaves;
    }

    public void setClaves(String[] Claves){ this.Claves = Claves; }
    public String[] getClaves(){
        return this.Claves;
    }

    public void setValores(String[] Valores){ this.Valores = Valores; }
    public String[] getValores(){
        return this.Valores;
    }


    public Boolean procesarJson(String eljson){

        try{

            // System.out.println("Debugging=> Clase Json -Json recibido: "+eljson);
            // System.out.println("Debugging=> Clase Json -Tipo de dato Json recibido: "+eljson.getClass());
            String nuevoJson ="";
            // Remove curly braces and split by commas
            // son dos operaciones, primero quitamos las llaves del json y los remplazamos por comiilas
            nuevoJson = eljson.replace("{", "").replace("}", "");
            // luego separamos cada uno de los elementos del json a través de la coma, y guardamos cada resultado en un arreglo
            String[] keyValuePairs = eljson.split(",");
            // almacenamos la logitud del arreglo
            this.longitudArreglo = keyValuePairs.length;
            // Definimos arreglos para las claves y los valores con dimensión igual al arreglo que tiene todos los clave-valor
            String[] arregloClaves = new String[22]; // la dimension es 2 porque es la cantidad de respuestas de la API http://www.omdbapi.com/?t=dancing%20with%20the%20stars&apikey=c184ab6c
            String[] arregloValores = new String[22];

            // variables para determinar si hay datos adicionales en el JSON que fueron cortados al hacer split con la coma (,)
            String elementosAdicionales = "";
            Boolean flagElementosAdicionales = false;
            Boolean flagEsValor = false;
            Boolean flagEsKeyValueCorrecto = false;
            Boolean claveLlena = false;
            int indiceCorrecto = 0;
            String claveCorrecta = "";
            String valorCorrecto = "";
            int sincroniaIndices = 0;

            // Sabiendo la longitud del arreglo sabemos que los valores internamente pueden traer arreglos u objetos, pues no coincide el valor obtenido con el valor de las propiedades consumidas en la API
            //System.out.println("Debugging=> Clase Json -Longitud del arreglo par clave-valor: "+this.longitudArreglo);

            // System.out.println("Debugging=> Clase Json - =========================");
            // finalmente recorremos el arreglo de claves par llave-valor, para crear un arreglo para llaves y otro para valores
            for (int i = 0; i < this.longitudArreglo; i++) {
                // System.out.println("Debugging=> Clase Json - Valor de i =>"+i);
                System.out.println("Debugging=> Clase Json - Key:Value =>"+keyValuePairs[i]);
                flagEsKeyValueCorrecto = this.procesarKeyValue(keyValuePairs[i]);
                // separamos cada uno de los elementos del resultado para extraer la clave y el valor por serparado
                String[] arrElementos = keyValuePairs[i].split(":");

                for (int j = 0; j < arrElementos.length; j++) {
                    // System.out.println("Debugging=> Clase Json - Elementos internos =>"+arrElementos[j]);
                    // si el primer caracter es una comilla o una llave
                    String primerCaracter = arrElementos[j].substring(0, 1);
                    String ultimoCaracter = arrElementos[j].substring(arrElementos[j].length() - 1);
                    String propiedadLimpia = "";


                    // System.out.println("Debugging=> Clase Json - Primer Caracter =>"+primerCaracter);
                    // System.out.println("Debugging=> Clase Json - Ultimo Caracter =>"+ultimoCaracter);

                    // validamos que la propiedad no tenga extensiones sino simplemente es una palabra completa encerrada en llaves o comillas
                    if((primerCaracter.equals("\"") || primerCaracter.equals("{")) && ( ultimoCaracter.equals("}") || ultimoCaracter.equals("\""))){
                        propiedadLimpia = arrElementos[j].replace("{", "").replace("}", "").replace("\"", "");
                        System.out.println("Debugging=> Clase Json - Propiedad Limpia =>"+propiedadLimpia);
                    }
                    else{ // la propiedad no es limpia
                        flagElementosAdicionales = true;
                        // validamos si sincroniaIndices esta recien establecido para guardar el valor de i
                        if( sincroniaIndices == 0){

                            sincroniaIndices = i;
                            // System.out.println("Debugging=> Clase Json - /////////// sincronia de indices: /////////// :"+sincroniaIndices);
                        }

                        elementosAdicionales = elementosAdicionales + arrElementos[j] + " ,";
                        // actualizamos el valor de la propiedad limpia
                        propiedadLimpia = elementosAdicionales;
                        // System.out.println("Debugging=> Clase Json - ---------------------------------ADICIONALES---------------------------------- ");
                        // System.out.println("Debugging=> Clase Json - Hay Datos adicionales: "+ elementosAdicionales);
                        // pueden haber comillas dentro del mismo texto , lo cual daña esta logica
                        if(ultimoCaracter.equals("\"")){
                            // establecemos la bandera de datos adicionales a false, porque se terminaron de concatenar los datos adicionales
                            flagElementosAdicionales = false;
                            // normalmente los elementos adicionales son valores, porque stan separados por comas
                            // activamos este flag porque es posible que j=0 y se quiera almacenar un valor en ese momento, pues no siempre j=1 sera un valor
                            // de esta manera garantizamos que se almacene el valor en el momento adecuado
                            flagEsValor = true;
                            propiedadLimpia = elementosAdicionales.replace("{", "").replace("}", "").replace("\"", "");
                            System.out.println("Debugging=> Clase Json - Propiedad Limpia elementos adicionales =>"+propiedadLimpia);

                            //si no hay mas datos adicionales limpiamos elementosAdicionales para nueva iteracion
                            elementosAdicionales = "";
                            flagElementosAdicionales = false;

                            // System.out.println("Debugging=> Clase Json - ---------------------------------FIN ADICIONALES---------------------------------- ");
                        }// fin  if interno
                    }// fin else

                    // System.out.println("Debugging=> Clase Json - ++++++++++++++");
                    // System.out.println("Debugging=> Clase Json - Valor j= : "+j);
                    // System.out.println("Debugging=> Clase Json - Valor propiedad limpia= : "+propiedadLimpia);
                    // System.out.println("Debugging=> Clase Json - ++++++++++++++");

                    // si claveLlena esta en false, significa que no se ha diligenciado la clave todavia
                    if(claveLlena==false){

                        // DE ACUERDO CON EL DEBUGGING HAY PROPIEDADES QUE VAN VACIAS POR EL PROCESAMIENTO, ASI QUE ES MEJOR VALIDAR ANTES DE INSERTAR
                        if( (propiedadLimpia != "" || propiedadLimpia != null) && flagElementosAdicionales == false ){
                            // es una clave limpia sin elementos adicionales
                            // si no es valor -significa que es clave- y el flagEsKeyValueCorrecto viene en true, procedemos a almacenar la clave en el arreglo porque
                            // flagEsKeyValueCorrecto se activa cuando no viene json puro. Quizas los valores traen mas comas y dos puntos y se evalua asi
                            if(flagEsValor == false && flagEsKeyValueCorrecto== true){
                                // no es un valor lo que se quiere almacenar
                                // System.out.println("Debugging=> Clase Json - Entro por clavellena==false y propiedadLimpia diferente de null, y no hay elementos adicionales y flagEsKeyValueCorrecto en true. claveLlena= "+claveLlena+" , propiedadLimpia: "+propiedadLimpia+ ", flagElementosAdicionales: "+flagElementosAdicionales+ " , flagEsKeyValueCorrecto: "+flagEsKeyValueCorrecto);
                                // System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                               // System.out.println("Debugging=> Clase Json - ALMACENAMIENTO CLAVES =>flagEsValor en falso. Se almacenan CLAVES . propiedad "+propiedadLimpia);
                                //System.out.println("Debugging=> Clase Json - Indice Correcto: "+indiceCorrecto);
                                //System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                                arregloClaves[indiceCorrecto] = propiedadLimpia;
                                //actualizamos el valor de claveLlena a true para que no ingrese por esta rama del if
                                claveLlena = true;

                            }

                            else{
                                // es un valor lo que se quiere almacenar y no tiene  elementos adicionales, es decir camino con datos adicionales
                                // cuando flagEsValor es indeterminado pues puede ser verdadero o falso... el que permite entrar a esta rama es flagEsKeyValueCorrecto, que para el procesamiento del valor vendra siempre en falso
                                // flagEsKeyValueCorrecto cuando los valores traen muchos dos puntos y comas
                                // System.out.println("Debugging=> Clase Json - ============CAMINO flagEsValor indeterminado y flagEsKeyValueCorrecto en falso =======================");
                                // System.out.println("Debugging=> Clase Json - Entro por clavellena==false y propiedadLimpia diferente de null, y no hay elementos adicionales y flagEsKeyValueCorrecto en false. claveLlena= "+claveLlena+" , propiedadLimpia: "+propiedadLimpia+ ", flagElementosAdicionales: "+flagElementosAdicionales+ " , flagEsKeyValueCorrecto: "+flagEsKeyValueCorrecto);
                                //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
                                //System.out.println("Debugging=> Clase Json - Debugging=> Clase Json - ALMACENAMIENTO VALORES =>flagEsValor en indeterminado. Se almacenan VALORES. propiedad: "+propiedadLimpia);
                                //System.out.println("Debugging=> Clase Json - Indice Correcto: "+indiceCorrecto);
                                //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
                                arregloValores[indiceCorrecto] = propiedadLimpia;

                                //limpiamos la sincronia de indices para un nuevo valor
                                sincroniaIndices = 0;

                                if( flagEsValor == true ){ // solo si viene un valor se incrementa el indice correcto, pues puede venir una clave, y no sirve incrementar el indice correcto
                                    // limpiamos el flag para una nueva verificacion
                                    flagEsValor = false;
                                    //actualizamos el valor de claveLlena a false para que ingrese al proceso de diligenciar la clave, pues se guardo un valor
                                    claveLlena = false;
                                    //se almaceno el valor, podemos incrementar el indice correcto en uno
                                    indiceCorrecto = indiceCorrecto+1;
                                }
                                else{ // viene claves, no incrementamos el indice correcto
                                    // System.out.println("Debugging=> Clase Json -  ============CAMINO flagEsValor false y flagEsKeyValueCorrecto en falso =======================");
                                    // System.out.println("Debugging=> Clase Json - Entro por clavellena==false y propiedadLimpia diferente de null, y no hay elementos adicionales y flagEsKeyValueCorrecto en false. claveLlena= "+claveLlena+" , propiedadLimpia: "+propiedadLimpia+ ", flagElementosAdicionales: "+flagElementosAdicionales+ " , flagEsKeyValueCorrecto: "+flagEsKeyValueCorrecto);
                                    //System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                                    //System.out.println("Debugging=> Clase Json - ALMACENAMIENTO CLAVES =>flagEsValor en falso. Se almacenan CLAVES. propiedad: "+propiedadLimpia);
                                    //System.out.println("Debugging=> Clase Json - Indice Correcto: "+indiceCorrecto);
                                    //System.out.println("CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
                                    arregloClaves[indiceCorrecto] = propiedadLimpia;
                                }




                            }
                            // System.out.println("Debugging=> Clase Json - !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");


                        }


                    }
                    else{ // si claveLlena no esta en false, estamos observando los valores y almacenamos en el arregloValores, ademas que procesamos los elementos adicionales
                        // viene un valor
                        // System.out.println("Debugging=> Clase Json - !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        flagEsValor = true;
                        // si la bandera de datos adicionales esta en false , significa que ya se terminaron de concatenar los datos adicionales
                        if(flagElementosAdicionales == false){
                            // DE ACUERDO CON EL DEBUGGING HAY PROPIEDADES QUE VAN VACIAS POR EL PROCESAMIENTO, ASI QUE ES MEJOR VALIDAR ANTES DE INSERTAR
                            if(propiedadLimpia != "" || propiedadLimpia != null ){
                                // System.out.println("Debugging=> Clase Json - Entro por claveLlena == true y propiedadLimpia diferente de null, y flagElementosAdicionales en false. claveLLena= "+claveLlena+" , propiedadLimpia: "+propiedadLimpia+" , flagElementosAdicionales: "+flagElementosAdicionales+ " , flagEsKeyValueCorrecto: "+flagEsKeyValueCorrecto);
                                //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
                                //System.out.println("Debugging=> Clase Json - ALMACENAMIENTO VALORES=>flagEsValor en verdadero. Se almacenan VALORES. propiedad: "+propiedadLimpia);
                                //System.out.println("Debugging=> Clase Json - Indice Correcto: "+indiceCorrecto);
                                //System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");

                                arregloValores[indiceCorrecto] = propiedadLimpia;
                                // se almaceno el valor, podemos incrementar el indice correcto en uno
                                indiceCorrecto = indiceCorrecto+1;
                                // limpiamos el flag para una nueva verificacion
                                flagEsValor = false;
                                //limpiamos la sincronia de indices para un nuevo valor
                                sincroniaIndices = 0;
                                //actualizamos el valor de claveLlena a false para que ingrese al proceso de diligenciar la clave
                                claveLlena = false;

                            }

                        }
                        // de lo contrario no hacemos nada hasta que los datos adicionales se hayan concatenado
                        // System.out.println("Debugging=> Clase Json - !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    }// fin else
                }//fin for interno


                // System.out.println("Debugging=> Clase Json - =========================");

            }// fin for externo



            // ALMACENAMOS EN EL OBJETO LOS ARREGLOS DE CLAVES Y VALORES OBTENIDOS
            this.setClaves(arregloClaves);;
            this.setValores(arregloValores);
            this.setLongitudClaves(arregloClaves.length);

            return true; // retornamos true indicando que el proceso fluyo

        }
        catch( Exception e){
            System.out.println("Debugging=> Clase Json - Error procesando el json: "+e.getMessage());
            return false;
        }




    }// fin procesar JSON

    public void verParClaveValores(){
        System.out.println("*************************");
        System.out.println("Clase Json - PROCESAMIENTO DE LOS ARREGLOS DE CLAVES Y VALORES");
        System.out.println("Clase Json - Longitud Claves: "+this.getLongitudClaves());
        for (int i = 0; i < this.getLongitudClaves(); i++) {
            System.out.println("Clase Json - Valor i =>"+i);
            System.out.println("Clase Json - Clave =>"+this.Claves[i]);
            System.out.println("Clase Json - Valor =>"+this.Valores[i]);
            System.out.println("*************************");
        }// fin for arreglos
    }


    public Boolean procesarKeyValue(String keyValue) {
        Boolean keyValuePerfecto = false;
        int longitudString = keyValue.length();
        char caracter = 'a';
        int contadorComillas = 0;
        int contadorDosPuntos = 0;
        // creamos un ciclo para recorrer el string
        for (int i = 0; i < longitudString; i++) {
            caracter = keyValue.charAt(i); // extraemos cada caracter
            if (caracter == '"') {
                contadorComillas = contadorComillas + 1;
            } else if (caracter == ':') {
                contadorDosPuntos = contadorDosPuntos + 1;
            }// fin if
        }// fin for

        // System.out.println("Debugging=> Clase Json - ???????????????????????????");
        // System.out.println("Debugging=> Clase Json - Cantidad de comillas dobles en el keyValue: "+ contadorComillas);
        // System.out.println("Debugging=> Clase Json - Cantidad de dos puntos en el keyValue: "+ contadorDosPuntos);
        // validamos cantidad de comillas y dos puntos en un json perfecto
        // si hay cuatro comillas y un solo dos puntos, es un json sin caracteres adicionales como comas o dos puntos en los valores, por ejemplo una url o un texto largo con comas
        if(contadorComillas == 4 && contadorDosPuntos==1){
            keyValuePerfecto = true;
        }
        //System.out.println("Debugging=> Clase Json - Valor Booleano del keyValuePerfecto: "+ keyValuePerfecto );
        //System.out.println("Debugging=> Clase Json - ???????????????????????????");

        return keyValuePerfecto;
    }// fin procesarKeyValue
} // fin clase

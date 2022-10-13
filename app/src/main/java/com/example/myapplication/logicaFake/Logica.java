package com.example.myapplication.logicaFake;


import android.util.Log;

public class Logica {


    private Object Medicion;

    public void guardarMedicion(Medicion medicion) {

        Log.d("test", "entra a guardar medicion");
        // ojo: creo que hay que crear uno nuevo cada vez
        PeticionarioREST elPeticionario = new PeticionarioREST();

		/*

		   enviarPeticion( "hola", function (res) {
		   		res
		   })

        elPeticionario.hacerPeticionREST("GET",  "http://158.42.144.126:8080/prueba", null,
			(int codigo, String cuerpo) => { } );

		   */
        //la contrabarra es pa clavar la cometa dins del string sense tancar el stringç
        //http://localhost/phpmyadmin/sql.php?db=android_mysql&table=datosmedidos&pos=0
        String textoJSON = "{\"Medicion\":\"" + medicion.getMedicion() + "\", \"Latitud\":\"" + medicion.getLatitud() + "\", \"Longitud\":\"" + medicion.getLongitud() + "\"}";
        Log.d("JSON", textoJSON);
        elPeticionario.hacerPeticionREST("POST", "http://172.20.10.5:8888/backend_app_sprint0/src/logica/guardarMediciones.php", textoJSON,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("test" ,"Se ha insertado correctamente");
                    }
                }
        );

    }

    public void obtenerUltimasMediciones(int cuantas) {
        Log.d("clienterestandroid", "boton_enviar_pulsado");


        // ojo: creo que hay que crear uno nuevo cada vez
        PeticionarioREST elPeticionario = new PeticionarioREST();

		/*

		   enviarPeticion( "hola", function (res) {
		   		res
		   })

        elPeticionario.hacerPeticionREST("GET",  "http://158.42.144.126:8080/prueba", null,
			(int codigo, String cuerpo) => { } );

		   */
        //la contrabarra es pa clavar la cometa dins del string sense tancar el stringç
        //http://localhost/phpmyadmin/sql.php?db=android_mysql&table=datosmedidos&pos=0
        //String textoJSON = "{\"Medicion\":\"" + medicion.getMedicion() + "\", \"Latitud\":\"" + medicion.getLatitud() + " \", \"Longitud\":\"" + medicion.getLongitud() + "\"}";
        /*elPeticionario.hacerPeticionREST("POST", "http://192.168.64.2/backend_app_sprint0/guardarMediciones.php", textoJSON,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("" ,"Se ha insertado correctamente");
                    }
                }
        );

         */

    }

    public Medicion obtenerTodasLasMediciones() {
        Log.d("clienterestandroid", "boton_enviar_pulsado");


        // ojo: creo que hay que crear uno nuevo cada vez
        PeticionarioREST elPeticionario = new PeticionarioREST();

		/*

		   enviarPeticion( "hola", function (res) {
		   		res
		   })

        elPeticionario.hacerPeticionREST("GET",  "http://158.42.144.126:8080/prueba", null,
			(int codigo, String cuerpo) => { } );

		   */
        //la contrabarra es pa clavar la cometa dins del string sense tancar el stringç
        //http://localhost/phpmyadmin/sql.php?db=android_mysql&table=datosmedidos&pos=0
        //String textoJSON = "{\"Medicion\":\"" + medicion.getMedicion() + "\", \"Latitud\":\"" + medicion.getLatitud() + " \", \"Longitud\":\"" + medicion.getLongitud() + "\"}";
        /*elPeticionario.hacerPeticionREST("POST", "http://192.168.64.2/backend_app_sprint0/guardarMediciones.php", textoJSON,
                new PeticionarioREST.RespuestaREST() {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("" ,"Se ha insertado correctamente");
                    }
                }
        );

         */

        return (com.example.myapplication.logicaFake.Medicion) Medicion;
    }

}

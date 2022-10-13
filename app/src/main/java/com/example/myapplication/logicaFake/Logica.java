package com.example.myapplication.logicaFake;


import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONObject;

public class Logica {
    public static int testMedicion;
    private Button elBotonEnviar;
    private JSONObject datos_muestra = new JSONObject();
    private String string_json;
    private Object Medicion;

    public void guardarMedicion(Medicion medicion) {

        Log.d("test", "entra a guardar medicion");
        // ojo: creo que hay que crear uno nuevo cada vez

        testMedicion = medicion.getMedicion();
        Log.d("PRUEBADEFUEGO", String.valueOf(testMedicion));


        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datos_muestra.put("id", 852 );
                datos_muestra.put("value", medicion.getMedicion());
                datos_muestra.put("sensorId","e55d93a0-ed7f-4287-8263-3398fdaf4415");
                Log.d("debug", String.valueOf(datos_muestra));
                Log.d("numeroIncognita", String.valueOf(datos_muestra));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }


            Log.d("clienterestandroid", "boton_enviar_pulsado");
            try{
                Log.d("clienterestandroid", String.valueOf(datos_muestra));
                string_json = String.valueOf(datos_muestra);
            }
            catch (Error e)
            {

            }

            //Prueba POST /alta
            PeticionarioREST elPeticionario = new PeticionarioREST();
            elPeticionario.hacerPeticionREST("POST",  "http://192.168.0.15:3000/measures", string_json,
                    new PeticionarioREST.RespuestaREST () {
                        @Override
                        public void callback(int codigo, String cuerpo) {
                            Log.d("clienterestandroid", "POST /alta completado");
                        }
                    }
            );

        }



       public void obtenerUltimasMediciones(int cuantas) {
        Log.d("clienterestandroid", "boton_enviar_pulsado");


        // ojo: creo que hay que crear uno nuevo cada vez
        PeticionarioREST elPeticionario = new PeticionarioREST();


    }

    public Medicion obtenerTodasLasMediciones() {
        Log.d("clienterestandroid", "boton_enviar_pulsado");


        // ojo: creo que hay que crear uno nuevo cada vez
        PeticionarioREST elPeticionario = new PeticionarioREST();



        return (com.example.myapplication.logicaFake.Medicion) Medicion;
    }

}

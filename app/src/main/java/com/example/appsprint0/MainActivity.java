package com.example.appsprint0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {

    private TextView elTexto;
    private Button elBotonEnviar;
    private JSONObject datos_muestra = new JSONObject();
    private String string_json;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datos_muestra.put("id", 852 );
                datos_muestra.put("value",7777777);
                datos_muestra.put("sensorId","e55d93a0-ed7f-4287-8263-3398fdaf4415");
                Log.d("debug", String.valueOf(datos_muestra));

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        this.elTexto = (TextView) findViewById(R.id.muestra_txt);
        this.elBotonEnviar = (Button) findViewById(R.id.boton_recoger_muestra);
        elBotonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
                boton_enviar_muestra(v);
            }
        });
    }

    public void boton_enviar_muestra(View quien){

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
        /*
        //Prueba GET /muestra
        PeticionarioREST elPeticionario = new PeticionarioREST();
        elPeticionario.hacerPeticionREST("GET",  "http://192.168.1.14:8080/muestra", null,
                new PeticionarioREST.RespuestaREST () {
                    @Override
                    public void callback(int codigo, String cuerpo) {
                        Log.d("clienterestandroid", "GET /dividir completado");
                    }
                }
        );*/
    }
}
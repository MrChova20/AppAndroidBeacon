package com.example.myapplication.logicaFake;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.ServicioEscucharBeacons;
import com.example.myapplication.TramalBeacon;
import com.example.myapplication.Utilidades;

import org.json.JSONObject;

import java.util.List;

// ------------------------------------------------------------------
// ------------------------------------------------------------------
public class MainActivity extends AppCompatActivity {

    EditText txtMediciones;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO


    private Intent elIntentDelServicio = null;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO

    Logica laLogica = new Logica();//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO



    TextView txtLatitud;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    TextView txtLongitud;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    private LocationManager locManager;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    public Location loc;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO

    double longitud;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    double latitud;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO

    double major;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
    double minor;//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO

    private TextView elTexto;
    private Button elBotonEnviar;
    private JSONObject datos_muestra = new JSONObject();
    private String string_json;
    private  Medicion dato1;
    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private static final String ETIQUETA_LOG = ">>>>";

    private static final int CODIGO_PETICION_PERMISOS = 11223344;

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private BluetoothLeScanner elEscanner;

    private ScanCallback callbackDelEscaneo = null;
    // --------------------------------------------------------------
    /*
     * Método para buscar todos los dispositivos Bluetooth
     *
     * @param  le pasamos nada
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buscarTodosLosDispositivosBTLE() {
        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empieza ");

        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): instalamos scan callback ");

        this.callbackDelEscaneo = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult resultado) {
                super.onScanResult(callbackType, resultado);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanResult() ");

                mostrarInformacionDispositivoBTLE(resultado);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onBatchScanResults() ");

            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanFailed() ");

            }
        };

        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empezamos a escanear ");
        this.elEscanner.startScan(this.callbackDelEscaneo);

    } // ()

    // --------------------------------------------------------------
    /*
     * Método para buscar la información del dispositivo Bluetooth
     *
     * @param ScanResult resultado
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void mostrarInformacionDispositivoBTLE(ScanResult resultado) {

        BluetoothDevice bluetoothDevice = resultado.getDevice();
        byte[] bytes = resultado.getScanRecord().getBytes();
        int rssi = resultado.getRssi();
        String name = bluetoothDevice.getName() + "";//DISTINTOOOOOOOOOOOOOOOOOOOOOOOOOOOO
        if(name.equals("GTI-3A-Alva")){
            Log.d(ETIQUETA_LOG, " ******************");
            Log.d(ETIQUETA_LOG, " ** DISPOSITIVO DETECTADO BTLE ****** ");
            Log.d(ETIQUETA_LOG, " ******************");
            Log.d(ETIQUETA_LOG, " nombre = " + bluetoothDevice.getName());
            Log.d(ETIQUETA_LOG, " toString = " + bluetoothDevice.toString());

        /*
        ParcelUuid[] puuids = bluetoothDevice.getUuids();
        if ( puuids.length >= 1 ) {
            //Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].getUuid());
           // Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].toString());
        }*/

            Log.d(ETIQUETA_LOG, " dirección = " + bluetoothDevice.getAddress());
            Log.d(ETIQUETA_LOG, " rssi = " + rssi);

            Log.d(ETIQUETA_LOG, " bytes = " + new String(bytes));
            Log.d(ETIQUETA_LOG, " bytes (" + bytes.length + ") = " + Utilidades.bytesToHexString(bytes));



            TramalBeacon tib = new TramalBeacon(bytes);

            Log.d(ETIQUETA_LOG, " ----------------------------------------------------");

            Log.d(ETIQUETA_LOG, " prefijo  = " + Utilidades.bytesToHexString(tib.getPrefijo()));
            Log.d(ETIQUETA_LOG, "          advFlags = " + Utilidades.bytesToHexString(tib.getAdvFlags()));
            Log.d(ETIQUETA_LOG, "          advHeader = " + Utilidades.bytesToHexString(tib.getAdvHeader()));
            Log.d(ETIQUETA_LOG, "          companyID = " + Utilidades.bytesToHexString(tib.getCompanyID()));
            Log.d(ETIQUETA_LOG, "          iBeacon type = " + Integer.toHexString(tib.getiBeaconType()));
            Log.d(ETIQUETA_LOG, "          iBeacon length 0x = " + Integer.toHexString(tib.getiBeaconLength()) + " ( "
                    + tib.getiBeaconLength() + " ) ");
            Log.d(ETIQUETA_LOG, " uuid  = " + Utilidades.bytesToHexString(tib.getUUID()));
            Log.d(ETIQUETA_LOG, " uuid  = " + Utilidades.bytesToString(tib.getUUID()));
            Log.d(ETIQUETA_LOG, " major  = " + Utilidades.bytesToHexString(tib.getMajor()) + "( "
                    + Utilidades.bytesToInt(tib.getMajor()) + " ) ");
            Log.d(ETIQUETA_LOG, " minor  = " + Utilidades.bytesToHexString(tib.getMinor()) + "( "
                    + Utilidades.bytesToInt(tib.getMinor()) + " ) ");
            Log.d(ETIQUETA_LOG, " txPower  = " + Integer.toHexString(tib.getTxPower()) + " ( " + tib.getTxPower() + " )");
            Log.d(ETIQUETA_LOG, " ******************");

            major = Utilidades.bytesToInt(tib.getMajor());
            minor = Utilidades.bytesToInt(tib.getMinor());

            Logica logica = new Logica();


            obtenerCoordenadas();
            Medicion medicion = new Medicion((int) minor, latitud,longitud);

            logica.guardarMedicion(medicion);

            Log.d("test", String.valueOf(major));
            Log.d("test", String.valueOf(medicion.getMedicion()));
        }


    } // ()

    // --------------------------------------------------------------
    /*
     * Método para buscar este dispositivo Bluetooth
     *
     * @param {char} dispositivoBuscado
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buscarEsteDispositivoBTLE(final String dispositivoBuscado) {
        Log.d(ETIQUETA_LOG, " buscarEsteDispositivoBTLE(): empieza ");

        Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): instalamos scan callback ");


        // super.onScanResult(ScanSettings.SCAN_MODE_LOW_LATENCY, result); para ahorro de energía

        this.callbackDelEscaneo = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult resultado) {
                super.onScanResult(callbackType, resultado);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanResult() ");

                mostrarInformacionDispositivoBTLE(resultado);
            }

            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onBatchScanResults() ");

            }

            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanFailed() ");

            }
        };

        ScanFilter sf = new ScanFilter.Builder().setDeviceName(dispositivoBuscado).build();

        Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado);
        //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado
        //      + " -> " + Utilidades.stringToUUID( dispositivoBuscado ) );

        this.elEscanner.startScan(this.callbackDelEscaneo);
    } // ()

    // --------------------------------------------------------------
    /*
     * Método para detener la búsqueda de dispositivos Bluetooth
     *
     * @param No le pasamos nad
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void detenerBusquedaDispositivosBTLE() {

        if (this.callbackDelEscaneo == null) {
            return;
        }

        this.elEscanner.stopScan(this.callbackDelEscaneo);
        this.callbackDelEscaneo = null;

    } // ()

    // --------------------------------------------------------------
    /*
     * Método para buscar dispositivos Bluetooth
     *
     * @param {View} v
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonBuscarDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton buscar dispositivos BTLE Pulsado");
        this.buscarTodosLosDispositivosBTLE();
    } // ()

    // --------------------------------------------------------------
    /*
     * Método para buscar nuestro dispositivo Bluetooth
     *
     * @param {View} v
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonBuscarNuestroDispositivoBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton nuestro dispositivo BTLE Pulsado");
        //this.buscarEsteDispositivoBTLE( Utilidades.stringToUUID( "GTI-3A-Alva" ) );

        //this.buscarEsteDispositivoBTLE( "GTI-3A-Alva" );
        //this.buscarEsteDispositivoBTLE("alvaro");

    } // ()

    // --------------------------------------------------------------
    /*
     * Método para subir datos fake para así comprobar que funciona correctamente la subida de datos a la base de datos
     *
     * @param {String} URL - Le pasamos la URL de la base de datos
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void botonDetenerBusquedaDispositivosBTLEPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton detener busqueda dispositivos BTLE Pulsado");
        this.detenerBusquedaDispositivosBTLE();
    } // ()


    // --------------------------------------------------------------
    /*
     * Método para inicializar el Bluetooth
     *
     * @param No le pasamos nada
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void inicializarBlueTooth() {
        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): obtenemos adaptador BT ");

        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): habilitamos adaptador BT ");

        bta.enable();

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): habilitado =  " + bta.isEnabled());

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): estado =  " + bta.getState());

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): obtenemos escaner btle ");

        this.elEscanner = bta.getBluetoothLeScanner();

        if (this.elEscanner == null) {
            Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): Socorro: NO hemos obtenido escaner btle  !!!!");

        }

        Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): voy a perdir permisos (si no los tuviera) !!!!");

        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_FINE_LOCATION},
                    CODIGO_PETICION_PERMISOS);
        } else {
            Log.d(ETIQUETA_LOG, " inicializarBlueTooth(): parece que YA tengo los permisos necesarios !!!!");

        }
    } //

    // --------------------------------------------------------------
    /*
     * Método para arrancar el servicio
     *
     * @param v: View
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void botonArrancarServicioPulsado(View v) {
        Log.d(ETIQUETA_LOG, " boton arrancar servicio Pulsado");

        if (this.elIntentDelServicio != null) {
            // ya estaba arrancado
            return;
        }

        Log.d(ETIQUETA_LOG, " MainActivity.constructor : voy a arrancar el servicio");

        this.elIntentDelServicio = new Intent(this, ServicioEscucharBeacons.class);

        this.elIntentDelServicio.putExtra("tiempoDeEspera", (long) 5000);
        startService(this.elIntentDelServicio);

    } // ()

    // --------------------------------------------------------------
    /*
     * Método para detener el servicio
     *
     * @param v: View
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void botonDetenerServicioPulsado(View v) {

        if (this.elIntentDelServicio == null) {
            // no estaba arrancado
            return;
        }

        stopService(this.elIntentDelServicio);

        this.elIntentDelServicio = null;

        Log.d(ETIQUETA_LOG, " boton detener servicio Pulsado");


    } // ()

    // --------------------------------------------------------------
    /*
     * Método para obtener la latitud y la longitud
     *
     * @param No le pasamos nada
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void obtenerCoordenadas() {

        locManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (loc==null){
                //Location wasnt gathered
            }else{
                latitud = loc.getLatitude();
                //txtLatitud.setText(String.valueOf(latitud));
                // txtLongitud.setText(String.valueOf(longitud));
                longitud = loc.getLongitude();
            }
        }
    }

    // --------------------------------------------------------------
    /*
     * Método para insertar datos para así comprobar que funciona correctamente la subida de datos a la base de datos
     *
     * @param {View} v
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void botonGuardarMedicion(View v) {



    }

    // --------------------------------------------------------------
    /*
     * Método para insertar cuantas mediciones y que devuelva las últimas mediciones indicadas
     *
     * @param {View} v
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void botonMostrarUltimasMediciones(View v) {

        /*Medicion medicion = new Medicion(Integer.parseInt(txtMediciones.getText().toString()), latitud, longitud);

        laLogica.guardarMedicion(medicion);

        Log.d("", String.valueOf(medicion));

         */

    }

    // --------------------------------------------------------------
    /*
     * Método para insertar cuantas mediciones y que devuelva las últimas mediciones indicadas
     *
     * @param {View} v
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void botonMostrarTodasLasMediciones(View v) {

        /*Medicion medicion = new Medicion(Integer.parseInt(txtMediciones.getText().toString()), latitud, longitud);

        laLogica.guardarMedicion(medicion);

        Log.d("", String.valueOf(medicion));

         */

    }


    // --------------------------------------------------------------
    /*
     * Método On create
     *
     * @param Bundle savedInstanceState
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        obtenerCoordenadas();

        Log.d(ETIQUETA_LOG, " onCreate(): empieza ");

        inicializarBlueTooth();



        Log.d(ETIQUETA_LOG, " onCreate(): termina ");
        try{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                datos_muestra.put("id", 852 );
                datos_muestra.put("value", minor);
                datos_muestra.put("sensorId","e55d93a0-ed7f-4287-8263-3398fdaf4415");
                Log.d("debug", String.valueOf(datos_muestra));
                Log.d("numeroIncognita", String.valueOf(datos_muestra));
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
    } // onCreate()
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

    }
    // --------------------------------------------------------------
    /*
     * Método para pedir permisos
     *
     * @param Z requestCode
     * @param [char] permissions
     * @param [Z] grantResults
     *
     * @return No devuelve nada
     */
    // --------------------------------------------------------------
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults);

        switch (requestCode) {
            case CODIGO_PETICION_PERMISOS:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d(ETIQUETA_LOG, " onRequestPermissionResult(): permisos concedidos  !!!!");
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                }  else {

                    Log.d(ETIQUETA_LOG, " onRequestPermissionResult(): Socorro: permisos NO concedidos  !!!!");

                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    } // ()


}
package com.example.myapplication;

// -----------------------------------------------------------------------------------
// @author: Pablo Chova Aparisi

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.UUID;

// -----------------------------------------------------------------------------------
public class Utilidades { // Declaramos la clase pública Utilidades

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los strings a bytes
     *
     * @param {String} texto - Le pasamos un string
     *
     * @return Byte[] - Devuelve el string que se le ha pasado transformado a bytes.
     */
    // -------------------------------------------------------------------------------
    public static byte[] stringToBytes ( String texto ) {
        return texto.getBytes(); // Te devuelve los bytes que componen el string
        // byte[] b = string.getBytes(StandardCharsets.UTF_8); // Ja
    } // ()

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los strings a UUID
     *
     * @param {String} texto - Le pasamos un string
     *
     * @return UUID - Devuelve el string que se le ha pasado transformado a UUID.
     */
    // -------------------------------------------------------------------------------
    public static UUID stringToUUID(String uuid ) {
        if ( uuid.length() != 16 ) { // Si el tamaño del uuid es diferente a 16
            throw new Error( "stringUUID: string no tiene 16 caracteres "); // Mostrará como error que no tiene 16 carácteres
        }
        byte[] comoBytes = uuid.getBytes(); // Obtenemos los bytes y los almacenamos en una variable

        String masSignificativo = uuid.substring(0, 8); // Almacenamos el mas significativo y el menos en diferentes variables
        String menosSignificativo = uuid.substring(8, 16);
        UUID res = new UUID( Utilidades.bytesToLong( masSignificativo.getBytes() ), Utilidades.bytesToLong( menosSignificativo.getBytes() ) ); // Almacenamos en una nueva variable el nuevo UUID pasándole los parámetros anteriormente obtenidos

        // Log.d( MainActivity.ETIQUETA_LOG, " \n\n***** stringToUUID *** " + uuid  + "=?=" + Utilidades.uuidToString( res ) );

        // UUID res = UUID.nameUUIDFromBytes( comoBytes ); no va como quiero

        return res; // Devuelve la variable res que almacenaba el nuevo UUID
    } // ()

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los UUID a string
     *
     * @param {UUID} uuid - Le pasamos un UUID
     *
     * @return String - Devuelve el UUID que se le ha pasado transformado a string.
     */
    // -------------------------------------------------------------------------------
    public static String uuidToString ( UUID uuid ) { // Método que transforma uuid a strings pasándole un uuid
        return bytesToString( dosLongToBytes( uuid.getMostSignificantBits(), uuid.getLeastSignificantBits() ) ); // Devuelve el string pasándole los datos del uuid
    } // ()

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los UUID a hexString
     *
     * @param {UUID} uuid - Le pasamos un UUID
     *
     * @return String - Devuelve el UUID que se le ha pasado transformado a hexString.
     */
    // -------------------------------------------------------------------------------
    public static String uuidToHexString ( UUID uuid ) { // Método que transforma uuid a hexStrings pasándole un uuid
        return bytesToHexString( dosLongToBytes( uuid.getMostSignificantBits(), uuid.getLeastSignificantBits() ) ); // Devuelve el hexString pasándole los datos del uuid
    } // ()

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los bytes a string
     *
     * @param {byte[]} bytes - Le pasamos el array de bytes
     *
     * @return String - Devuelve los bytes que se le ha pasado transformados a string
     */
    // -------------------------------------------------------------------------------
    public static String bytesToString( byte[] bytes ) { // Método que transforma los bytes a string pásandole una lista de bytes
        if (bytes == null ) { // Si el array es null
            return ""; // Devuelve comillas
        }

        StringBuilder sb = new StringBuilder(); // Si no declaramos un StringBuilder
        for (byte b : bytes) { // Y para cada byte en el array de bytes
            sb.append( (char) b ); // Agregamos la representación de cadena del argumento en cuestión
        }
        return sb.toString(); // Devuelve el array completo a string
    }

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma dos long a bytes
     *
     * @param {long} masSignificativos - Le pasamos un long
     * @param {long} menosSignificativos - Le pasamos otro long
     *
     * @return Byte[] - Devuelve los longs que se le han pasado transformados a bytes
     */
    // -------------------------------------------------------------------------------
    public static byte[] dosLongToBytes( long masSignificativos, long menosSignificativos ) { // Método que transforma dos longs de los uuid a bytes
        ByteBuffer buffer = ByteBuffer.allocate( 2 * Long.BYTES ); // Declaramos un buffer
        buffer.putLong( masSignificativos ); // Le añadimos los parámetros que le hemos pasados al método
        buffer.putLong( menosSignificativos );
        return buffer.array(); // Devolvemos el array del buffer
    }

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los bytes a int
     *
     * @param {byte[]} bytes - Le pasamos el array de bytes
     *
     * @return Int - Devuelve los bytes que se le han pasado transformados a int
     */
    // -------------------------------------------------------------------------------
    public static int bytesToInt( byte[] bytes ) { // Método que transforma un array de bytes a ints
        return new BigInteger(bytes).intValue();
    }

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los bytes a long
     *
     * @param {byte[]} bytes - Le pasamos el array de bytes
     *
     * @return Long - Devuelve los bytes que se le han pasado transformados a long
     */
    // -------------------------------------------------------------------------------
    public static long bytesToLong( byte[] bytes ) { // Método que transforma un array de bytes a longs
        return new BigInteger(bytes).longValue();
    }

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los bytes a int
     *
     * @param {byte[]} bytes - Le pasamos el array de bytes
     *
     * @return Int - Devuelve los bytes que se le han pasado transformados a int
     */
    // -------------------------------------------------------------------------------
    public static int bytesToIntOK( byte[] bytes ) {
        if (bytes == null ) { // Si el array es nulo
            return 0; // Devuelve 0
        }

        if ( bytes.length > 4 ) { // Si el tamaño es mayor a 4
            throw new Error( "demasiados bytes para pasar a int "); // Error porque hay demasiados bytes para pasarlos a int
        }
        int res = 0; // Res igualado a 0



        for( byte b : bytes ) { // Para cada bytes del array de bytes
           /*
           Log.d( MainActivity.ETIQUETA_LOG, "bytesToInt(): byte: hex=" + Integer.toHexString( b )
                   + " dec=" + b + " bin=" + Integer.toBinaryString( b ) +
                   " hex=" + Byte.toString( b )
           );
           */
            res =  (res << 8) // * 16
                    + (b & 0xFF); // para quedarse con 1 byte (2 cuartetos) de lo que haya en b
        } // for

        if ( (bytes[ 0 ] & 0x8) != 0 ) {
            // si tiene signo negativo (un 1 a la izquierda del primer byte
            res = -(~(byte)res)-1; // complemento a 2 (~) de res pero como byte, -1
        }
       /*
        Log.d( MainActivity.ETIQUETA_LOG, "bytesToInt(): res = " + res + " ~res=" + (res ^ 0xffff)
                + "~res=" + ~((byte) res)
        );
        */

        return res;
    } // ()

    // -------------------------------------------------------------------------------
    /*
     * Método que transforma los strings a hexString
     *
     * @param {byte[]} bytes - Le pasamos el array de bytes
     *
     * @return String - Devuelve los bytes que se le han pasado transformados a hexString
     */
    // -------------------------------------------------------------------------------
    public static String bytesToHexString( byte[] bytes ) { // Método que transforma un array de bytes a hexString

        if (bytes == null ) { // Si el array es nulo
            return ""; // No devuelve nada
        }

        StringBuilder sb = new StringBuilder(); // Si no es nulo, declaramos StringBuilder
        for (byte b : bytes) { // para cada byte del array de bytes
            sb.append(String.format("%02x", b)); //  Agregamos la representación de cadena del argumento en cuestión
            sb.append(':');
        }
        return sb.toString(); // Devuelve el string
    } // ()
} // class
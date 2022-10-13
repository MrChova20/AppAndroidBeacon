package com.example.myapplication;


import java.util.Arrays;

// -----------------------------------------------------------------------------------
// @author: Jordi Bataller i Mascarell
// -----------------------------------------------------------------------------------
public class TramalBeacon {
    private byte[] prefijo = null; // 9 bytes
    private byte[] uuid = null; // 16 bytes
    private byte[] major = null; // 2 bytes
    private byte[] minor = null; // 2 bytes
    private byte txPower = 0; // 1 byte

    private byte[] losBytes;

    private byte[] advFlags = null; // 3 bytes
    private byte[] advHeader = null; // 2 bytes
    private byte[] companyID = new byte[2]; // 2 bytes
    private byte iBeaconType = 0 ; // 1 byte
    private byte iBeaconLength = 0 ; // 1 byte


    // Métodos GETTERS para obtener los atributos anteriormente declarados

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener prefijo
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte prefijo.
     */
    // -------------------------------------------------------------------------------
    public byte[] getPrefijo() {
        return prefijo;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener uuid
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte uuid.
     */
    // -------------------------------------------------------------------------------
    public byte[] getUUID() {
        return uuid;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener major
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte major.
     */
    // -------------------------------------------------------------------------------
    public byte[] getMajor() {
        return major;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener minor
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte minor.
     */
    // -------------------------------------------------------------------------------
    public byte[] getMinor() {
        return minor;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener txPower
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte txPower.
     */
    // -------------------------------------------------------------------------------
    public byte getTxPower() {
        return txPower;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener losBytes
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte losBytes.
     */
    // -------------------------------------------------------------------------------
    public byte[] getLosBytes() {
        return losBytes;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener advFlags
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte advFlags.
     */
    // -------------------------------------------------------------------------------
    public byte[] getAdvFlags() {
        return advFlags;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener advHeader
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte advHeader.
     */
    // -------------------------------------------------------------------------------
    public byte[] getAdvHeader() {
        return advHeader;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener companyID
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte companyID.
     */
    // -------------------------------------------------------------------------------
    public byte[] getCompanyID() {
        return companyID;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener iBeaconType
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte iBeaconType.
     */
    // -------------------------------------------------------------------------------
    public byte getiBeaconType() {
        return iBeaconType;
    }

    // -------------------------------------------------------------------------------
    /*
     * Método get para obtener iBeaconLength
     *
     * @param No le pasamos nada
     *
     * @return Byte[] - Devuelve el byte iBeaconLength.
     */
    // -------------------------------------------------------------------------------
    public byte getiBeaconLength() {
        return iBeaconLength;
    }

    // -------------------------------------------------------------------------------
    /*
     * Constructor de la clase al que le pasamos el array de bytes
     *
     * @param {Byte[]} bytes - Le pasamos el array de bytes
     *
     * @return No devuelve nada ya que se trata del constructor
     */
    // -------------------------------------------------------------------------------
    public TramalBeacon(byte[] bytes ) {
        this.losBytes = bytes;

        prefijo = Arrays.copyOfRange(losBytes, 0, 8+1 ); // 9 bytes
        uuid = Arrays.copyOfRange(losBytes, 9, 24+1 ); // 16 bytes
        major = Arrays.copyOfRange(losBytes, 25, 26+1 ); // 2 bytes
        minor = Arrays.copyOfRange(losBytes, 27, 28+1 ); // 2 bytes
        txPower = losBytes[ 29 ]; // 1 byte

        advFlags = Arrays.copyOfRange( prefijo, 0, 2+1 ); // 3 bytes
        advHeader = Arrays.copyOfRange( prefijo, 3, 4+1 ); // 2 bytes
        companyID = Arrays.copyOfRange( prefijo, 5, 6+1 ); // 2 bytes
        iBeaconType = prefijo[ 7 ]; // 1 byte
        iBeaconLength = prefijo[ 8 ]; // 1 byte

    } // ()
} // class
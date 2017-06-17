package correos.acropolis.software.Datos;

import java.sql.Connection;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:44 PM
 */
public class Conexion {

    private Connection connection;
    private String host;
    private String user;
    private String password;
    public Conexion m_Conexion;

    public Conexion() {

    }

    public void finalize() throws Throwable {

    }

    public static Conexion getInstancia() {
        return null;
    }

    public Connection getConexion() {
        return null;
    }

    public void abrirConexion() {

    }

    public void cerrarConexion() {

    }

}

package correos.acropolis.software;

import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class Asistencia {

    private int id;
    private String estado;
    private Date fecha;
    private int id_kardex;
    public Conexion m_Conexion;

    public Asistencia() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public void setAsistencia(String estado, Date fecha, int id_kardex) {

    }

    /**
     *
     * @param id
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public void setAsistencia(int id, String estado, Date fecha, int id_kardex) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getAsistencia(int id) {
        return null;
    }

    /**
     *
     * @param id_kardex
     */
    public DefaultTableModel getAsistencias(int id_kardex) {
        return null;
    }

    public int registrarAsistencia() {
        return 0;
    }

    public void modificarAsistencia() {

    }

}

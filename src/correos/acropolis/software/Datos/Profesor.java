package correos.acropolis.software.Datos;

import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:50 PM
 */
public class Profesor {

    private int id;
    private String nombres;
    private String apellidos;
    private int telefono;
    private Date fecha_postulacion;
    private boolean estado;
    public Conexion m_Conexion;

    public Profesor() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     */
    public void setProfesor(String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {

    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     */
    public void setProfesor(int id, String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getProfesor(int id) {
        return null;
    }

    public DefaultTableModel getProfesores() {
        return null;
    }

    public int registrarProfesor() {
        return 0;
    }

    public void modificarProfesor() {

    }

}

package correos.acropolis.software;

import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:41 PM
 */
public class ProfesorNegocio {

    public Profesor m_Profesor;

    public ProfesorNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerProfesor(int id) {
        return null;
    }

    public DefaultTableModel obtenerProfesores() {
        return null;
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     */
    public int registrarProfesor(String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {
        return 0;
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
    public void modificarProfesor(int id, String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {

    }

}

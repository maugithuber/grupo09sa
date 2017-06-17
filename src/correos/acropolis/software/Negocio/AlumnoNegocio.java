package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Alumno;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:29 PM
 */
public class AlumnoNegocio {

    public Alumno m_Alumno;

    public AlumnoNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerAlumno(int id) {
        return null;
    }

    public DefaultTableModel obtenerAlumnos() {
        return null;
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param fecha_ingreso
     * @param estado
     */
    public int registrarAlumno(String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {
        return 0;
    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param fecha_ingreso
     * @param estado
     */
    public void modificarAlumno(int id, String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {

    }

}

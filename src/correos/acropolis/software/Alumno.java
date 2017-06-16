package correos.acropolis.software;

import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:43 PM
 */
public class Alumno {

    private int id;
    private String nombres;
    private String apellidos;
    private int telefono;
    private Date fecha_nacimiento;
    private Date fecha_ingreso;
    private boolean estado;
    public Conexion m_Conexion;

    public Alumno() {

    }

    public void finalize() throws Throwable {

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
    public void setAlumno(String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {

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
    public void setAlumno(int id, String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getAlumno(int id) {
        return null;
    }

    public DefaultTableModel getAlumnos() {
        return null;
    }

    public int registrarAlumno() {
        return 0;
    }

    public void modificarAlumno() {

    }

}

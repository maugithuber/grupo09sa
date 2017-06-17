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
        this.m_Alumno = new Alumno();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerAlumno(int id) {
        return this.m_Alumno.getAlumno(id);
    }

    public DefaultTableModel obtenerAlumnos() {
        return this.m_Alumno.getAlumnos();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param fecha_ingreso
     * @param estado
     * @return 
     */
    public int registrarAlumno(String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {
        // No olvidar primero settear los datos
        this.m_Alumno.setAlumno(nombres, apellidos, telefono, fecha_nacimiento, fecha_ingreso, estado);
        return this.m_Alumno.registrarAlumno();
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
        // No olvidar primero settear los datos
        this.m_Alumno.setAlumno(id, nombres, apellidos, telefono, fecha_nacimiento, fecha_ingreso, estado);
        this.m_Alumno.modificarAlumno();
    }

}

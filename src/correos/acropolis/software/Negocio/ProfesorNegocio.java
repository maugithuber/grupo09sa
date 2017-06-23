package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Profesor;
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
        this.m_Profesor = new Profesor();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerProfesor(int id) {
        return this.m_Profesor.getProfesor(id);
    }

    public DefaultTableModel obtenerProfesores() {
        return this.m_Profesor.getProfesores();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     * @return
     */
    public int registrarProfesor(String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {
        this.m_Profesor.setProfesor(nombres, apellidos, telefono, fecha_postulacion, estado);
        return this.m_Profesor.registrarProfesor();
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
        this.m_Profesor.setProfesor(id, nombres, apellidos, telefono, fecha_postulacion, estado);
        this.m_Profesor.modificarProfesor();
    }

}

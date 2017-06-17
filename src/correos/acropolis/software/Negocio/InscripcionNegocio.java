package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Inscripcion;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:40 PM
 */
public class InscripcionNegocio {

    public Inscripcion m_Inscripcion;

    public InscripcionNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerInscripcion(int id) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel obtenerInscripciones(int id_alumno) {
        return null;
    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerDetalleInscripcion(int id) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel obtenerCursosHabilitados(int id_alumno) {
        return null;
    }

    /**
     *
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public int registrarInscripcion(Date fecha_inscripcion, int id_alumno) {
        return 0;
    }

    /**
     *
     * @param id
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void modificarInscripcion(int id, Date fecha_inscripcion, int id_alumno) {

    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void adicionarGrupos(int id, int id_grupo) {

    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void retirarGrupos(int id, int id_grupo) {

    }

}

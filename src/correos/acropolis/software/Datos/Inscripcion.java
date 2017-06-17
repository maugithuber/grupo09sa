package correos.acropolis.software.Datos;

import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:48 PM
 */
public class Inscripcion {

    private int id;
    private Date fecha_inscripcion;
    private int id_alumno;
    public Conexion m_Conexion;

    public Inscripcion() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void setInscripcion(Date fecha_inscripcion, int id_alumno) {

    }

    /**
     *
     * @param id
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void setInscripcion(int id, Date fecha_inscripcion, int id_alumno) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getInscripcion(int id) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel getInscripciones(int id_alumno) {
        return null;
    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getDetalleInscripcion(int id) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel getCursosHabilitados(int id_alumno) {
        return null;
    }

    public int registrarInscripcion() {
        return 0;
    }

    public void modificarInscripcion() {

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

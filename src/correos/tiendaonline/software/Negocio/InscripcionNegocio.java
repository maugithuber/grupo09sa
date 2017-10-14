package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Inscripcion;
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
        this.m_Inscripcion = new Inscripcion();
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerInscripcion(int id) {
        return this.m_Inscripcion.getInscripcion(id);
    }

    /**
     *
     * @param id_alumno
     * @return 
     */
    public DefaultTableModel obtenerInscripciones(int id_alumno) {
        return this.m_Inscripcion.getInscripciones(id_alumno);
    }

    /**
     *
     * @param id
     * @return 
     */
    public DefaultTableModel obtenerDetalleInscripcion(int id) {
        return this.m_Inscripcion.getDetalleInscripcion(id);
    }

    /**
     *
     * @param id_alumno
     * @return 
     */
    public DefaultTableModel obtenerCursosHabilitados(int id_alumno) {
        return this.m_Inscripcion.getCursosHabilitados(id_alumno);
    }

    /**
     *
     * @param fecha_inscripcion
     * @param id_alumno
     * @return 
     */
    public int registrarInscripcion(Date fecha_inscripcion, int id_alumno) {
        this.m_Inscripcion.setInscripcion(fecha_inscripcion, id_alumno);
        return this.m_Inscripcion.registrarInscripcion();
    }

    /**
     *
     * @param id
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void modificarInscripcion(int id, Date fecha_inscripcion, int id_alumno) {
        this.m_Inscripcion.setInscripcion(id, fecha_inscripcion, id_alumno);
        this.m_Inscripcion.modificarInscripcion();
    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void adicionarGrupos(int id, int id_grupo) {
        this.m_Inscripcion.adicionarGrupos(id, id_grupo);
    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void retirarGrupos(int id, int id_grupo) {
        this.m_Inscripcion.retirarGrupos(id, id_grupo);
    }

}

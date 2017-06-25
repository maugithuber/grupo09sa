package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Asistencia;
import correos.acropolis.software.Datos.Kardex;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:40 PM
 */
public class KardexNegocio {

    public Asistencia m_Asistencia;
    public Kardex m_Kardex;

    public KardexNegocio() {
        this.m_Asistencia = new Asistencia();
        this.m_Kardex = new Kardex();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerKardex(int id) {
        return this.m_Kardex.getKardex(id);
    }

    /**
     *
     * @param id_alumno
     * @return
     */
    public DefaultTableModel obtenerKardexs(int id_alumno) {
        return this.m_Kardex.getKardexs(id_alumno);
    }

    /**
     *
     * @param id_kardex
     * @return
     */
    public DefaultTableModel obtenerAsistencias(int id_kardex) {
        return this.m_Asistencia.getAsistencias(id_kardex);
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerAsistencia(int id) {
        return this.m_Asistencia.getAsistencia(id);
    }

    /**
     *
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     * @return
     */
    public int registrarKardex(int mes, int gestion, int id_alumno, int id_grupo) {
        this.m_Kardex.setKardex(mes, gestion, id_alumno, id_grupo);
        return this.m_Kardex.registrarKardex();
    }

    /**
     *
     * @param id
     */
    public void eliminarKardex(int id) {
        this.m_Kardex.eliminarKardex(id);
    }

    /**
     *
     * @param id
     * @param nota
     */
    public void registrarNota(int id, String nota) {
        this.m_Kardex.registrarNota(id, nota);
    }

    /**
     *
     * @param estado
     * @param fecha
     * @param id_kardex
     * @return
     */
    public int registrarAsistencia(String estado, Date fecha, int id_kardex) {
        this.m_Asistencia.setAsistencia(estado, fecha, id_kardex);
        return this.m_Asistencia.registrarAsistencia();
    }

    /**
     *
     * @param id
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public void modificarAsistencia(int id, String estado, Date fecha, int id_kardex) {
        this.m_Asistencia.setAsistencia(id, estado, fecha, id_kardex);
        this.m_Asistencia.modificarAsistencia();
    }

}

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

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerKardex(int id) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel obtenerKardexs(int id_alumno) {
        return null;
    }

    /**
     *
     * @param id_kardex
     */
    public DefaultTableModel obtenerAsistencias(int id_kardex) {
        return null;
    }

    /**
     *
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     */
    public int registrarKardex(int mes, int gestion, int id_alumno, int id_grupo) {
        return 0;
    }

    /**
     *
     * @param id
     */
    public void eliminarKardex(int id) {

    }

    /**
     *
     * @param id
     * @param nota
     */
    public void registrarNota(int id, String nota) {

    }

    /**
     *
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public int registrarAsistencia(String estado, Date fecha, int id_kardex) {
        return 0;
    }

    /**
     *
     * @param id
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public int modificarAsistencia(int id, String estado, Date fecha, int id_kardex) {
        return 0;
    }

}

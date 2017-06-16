package correos.acropolis.software;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class ReporteNegocio {

    public Kardex m_Kardex;
    public Grupo m_Grupo;
    public Curso m_Curso;
    public Alumno m_Alumno;

    public ReporteNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel reporteHistorico(int id_alumno) {
        return null;
    }

    /**
     *
     * @param id_grupo
     */
    public DefaultTableModel reporteAlumnosInscritos(int id_grupo) {
        return null;
    }

    public DefaultTableModel reporteOfertaCursos() {
        return null;
    }

}

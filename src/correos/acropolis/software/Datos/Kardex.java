package correos.acropolis.software.Datos;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:49 PM
 */
public class Kardex {

    private int id;
    private String nota;
    private int mes;
    private int gestion;
    private int id_alumno;
    private int id_grupo;
    public Conexion m_Conexion;

    public Kardex() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     */
    public void setKardex(int mes, int gestion, int id_alumno, int id_grupo) {

    }

    /**
     *
     * @param id
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     */
    public void setKardex(int id, int mes, int gestion, int id_alumno, int id_grupo) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getKardex(int id) {
        return null;
    }

    /**
     *
     * @param id_grupo
     * @param mes
     * @param gestion
     */
    public DefaultTableModel getKardex(int id_grupo, int mes, int gestion) {
        return null;
    }

    /**
     *
     * @param id_alumno
     */
    public DefaultTableModel getKardexs(int id_alumno) {
        return null;
    }

    public int registrarKardex() {
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

}

package correos.acropolis.software;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:47 PM
 */
public class Horario {

    private int id_grupo;
    private int id;
    private String dia;
    private String hora_inicio;
    private String hora_fin;
    private int id_aula;
    public Conexion m_Conexion;

    public Horario() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id_grupo
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     */
    public void setHorario(int id_grupo, String dia, String hora_inicio, String hora_fin, int id_aula) {

    }

    /**
     *
     * @param id_grupo
     * @param id
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     */
    public void setHorario(int id_grupo, int id, String dia, String hora_inicio, String hora_fin, int id_aula) {

    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public DefaultTableModel getHorario(int id, int id_grupo) {
        return null;
    }

    /**
     *
     * @param id_grupo
     */
    public DefaultTableModel getHorarios(int id_grupo) {
        return null;
    }

    public int registrarHorario() {
        return 0;
    }

    public void modificarHorario() {

    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void eliminarHorario(int id, int id_grupo) {

    }

}

package correos.acropolis.software;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:39 PM
 */
public class GrupoNegocio {

    public Grupo m_Grupo;
    public Horario m_Horario;

    public GrupoNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerGrupo(int id) {
        return null;
    }

    public DefaultTableModel obtenerGrupos() {
        return null;
    }

    /**
     *
     * @param id_grupo
     */
    public DefaultTableModel obtenerHorarios(int id_grupo) {
        return null;
    }

    /**
     *
     * @param nombre
     * @param id_curso
     */
    public int registrarGrupo(String nombre, int id_curso) {
        return 0;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param id_curso
     */
    public void modificarGrupo(int id, String nombre, int id_curso) {

    }

    /**
     *
     * @param id_grupo
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     */
    public int registrarHorario(int id_grupo, String dia, String hora_inicio, String hora_fin, int id_aula) {
        return 0;
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
    public void modificarHorario(int id_grupo, int id, String dia, String hora_inicio, String hora_fin, int id_aula) {

    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void eliminarHorario(int id, int id_grupo) {

    }

    /**
     *
     * @param id_grupo
     * @param id_profesor
     */
    public void asignarGrupo(int id_grupo, int id_profesor) {

    }

}

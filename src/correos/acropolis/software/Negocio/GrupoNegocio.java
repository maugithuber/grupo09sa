package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Horario;
import correos.acropolis.software.Datos.Grupo;
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
        this.m_Grupo = new Grupo();
        this.m_Horario = new Horario();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerGrupo(int id) {
        return this.m_Grupo.getGrupo(id);
    }

    public DefaultTableModel obtenerGrupos() {
        return this.m_Grupo.getGrupos();
    }

    /**
     *
     * @param id_grupo
     * @return
     */
    public DefaultTableModel obtenerHorarios(int id_grupo) {
        return this.m_Horario.getHorarios(id_grupo);
    }

    /**
     *
     * @param nombre
     * @param id_curso
     * @return
     */
    public int registrarGrupo(String nombre, int id_curso) {
        this.m_Grupo.setGrupo(nombre, id_curso);
        return this.m_Grupo.registrarGrupo();
    }

    /**
     *
     * @param id
     * @param nombre
     * @param id_curso
     */
    public void modificarGrupo(int id, String nombre, int id_curso) {
        this.m_Grupo.setGrupo(id, nombre, id_curso);
        this.m_Grupo.modificarGrupo();
    }

    /**
     *
     * @param id_grupo
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     * @return
     */
    public int registrarHorario(int id_grupo, String dia, String hora_inicio, String hora_fin, int id_aula) {
        this.m_Horario.setHorario(id_grupo, dia, hora_inicio, hora_fin, id_aula);
        return this.m_Horario.registrarHorario();
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
        this.m_Horario.setHorario(id_grupo, id, dia, hora_inicio, hora_fin, id_aula);
        this.m_Horario.modificarHorario();
    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void eliminarHorario(int id, int id_grupo) {
        this.m_Horario.eliminarHorario(id, id_grupo);
    }

    /**
     *
     * @param id_grupo
     * @param id_profesor
     */
    public void asignarGrupo(int id_grupo, int id_profesor) {
        this.m_Grupo.asignarGrupo(id_grupo, id_profesor);
    }

}

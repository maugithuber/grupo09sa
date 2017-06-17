package correos.acropolis.software.Datos;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:46 PM
 */
public class Grupo {

    private int id;
    private String nombre;
    private int id_curso;
    public Conexion m_Conexion;

    public Grupo() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param nombre
     * @param id_curso
     */
    public void setGrupo(String nombre, int id_curso) {

    }

    /**
     *
     * @param id
     * @param nombre
     * @param id_curso
     */
    public void setGrupo(int id, String nombre, int id_curso) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getGrupo(int id) {
        return null;
    }

    /**
     *
     * @param id_curso
     */
    public DefaultTableModel getGrupoCurso(int id_curso) {
        return null;
    }

    public DefaultTableModel getGrupos() {
        return null;
    }

    public int registrarGrupo() {
        return 0;
    }

    public void modificarGrupo() {

    }

    /**
     *
     * @param id_grupo
     * @param id_profesor
     */
    public void asignarGrupo(int id_grupo, int id_profesor) {

    }

}

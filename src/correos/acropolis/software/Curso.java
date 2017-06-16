package correos.acropolis.software;

import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:45 PM
 */
public class Curso {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean estado;
    public Conexion m_Conexion;

    public Curso() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void setCurso(String nombre, String descripcion, boolean estado) {

    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void setCurso(int id, String nombre, String descripcion, boolean estado) {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getCurso(int id) {
        return null;
    }

    public DefaultTableModel getCursos() {
        return null;
    }

    /**
     *
     * @param idCurso
     */
    public DefaultTableModel getPrerequisitos(int idCurso) {
        return null;
    }

    public int registrarCurso() {
        return 0;
    }

    public void modificarCurso() {

    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void agregarPrerequisito(int id_curso, int id_prerequisito) {

    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void quitarPrerequisito(int id_curso, int id_prerequisito) {

    }

}

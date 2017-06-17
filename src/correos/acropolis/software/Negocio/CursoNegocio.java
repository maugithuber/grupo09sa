package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Curso;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:38 PM
 */
public class CursoNegocio {

    public Curso m_Curso;

    public CursoNegocio() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param id
     */
    public DefaultTableModel obtenerCurso(int id) {
        return null;
    }

    public DefaultTableModel obtenerCursos() {
        return null;
    }

    /**
     *
     * @param idCurso
     */
    public DefaultTableModel obtenerPrerequisitos(int idCurso) {
        return null;
    }

    /**
     *
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public int registrarCurso(String nombre, String descripcion, boolean estado) {
        return 0;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void modificarCurso(int id, String nombre, String descripcion, boolean estado) {

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

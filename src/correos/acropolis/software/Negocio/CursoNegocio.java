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
        this.m_Curso = new Curso();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerCurso(int id) {
        return this.m_Curso.getCurso(id);
    }

    public DefaultTableModel obtenerCursos() {
        return this.m_Curso.getCursos();
    }

    /**
     *
     * @param idCurso
     * @return
     */
    public DefaultTableModel obtenerPrerequisitos(int idCurso) {
        return this.m_Curso.getPrerequisitos(idCurso);
    }

    /**
     *
     * @param nombre
     * @param descripcion
     * @param estado
     * @return
     */
    public int registrarCurso(String nombre, String descripcion, boolean estado) {
        this.m_Curso.setCurso(nombre, descripcion, estado);
        return this.m_Curso.registrarCurso();
    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void modificarCurso(int id, String nombre, String descripcion, boolean estado) {
        this.m_Curso.setCurso(id, nombre, descripcion, estado);
        this.m_Curso.modificarCurso();
    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void agregarPrerequisito(int id_curso, int id_prerequisito) {
        this.m_Curso.agregarPrerequisito(id_curso, id_prerequisito);
    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void quitarPrerequisito(int id_curso, int id_prerequisito) {
        this.m_Curso.quitarPrerequisito(id_curso, id_prerequisito);
    }

}

package correos.acropolis.software.Negocio;

import correos.acropolis.software.Datos.Aula;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:36 PM
 */
public class AulaNegocio {

    public Aula m_Aula;

    public AulaNegocio() {
        this.m_Aula = new Aula();
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel obtenerAula(int id) {
        return this.m_Aula.getAula(id);
    }

    public DefaultTableModel obtenerAulas() {
        return this.m_Aula.getAulas();
    }

    /**
     *
     * @param nombre
     * @param capacidad
     * @return
     */
    public int registrarAula(String nombre, int capacidad) {
        this.m_Aula.setAula(nombre, capacidad);
        return this.m_Aula.registrarAula();
    }

    /**
     *
     * @param id
     * @param nombre
     * @param capacidad
     */
    public void modificarAula(int id, String nombre, int capacidad) {
        this.m_Aula.setAula(id, nombre, capacidad);
        this.m_Aula.modificarAula();
    }

}


package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Categoria;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class CategoriaNegocio {
     private Categoria m_Categoria;
     
       public CategoriaNegocio() {
        this.m_Categoria = new Categoria();
    }

    public DefaultTableModel obtenerCategoria(int id) {
        return this.m_Categoria.getCategoria(id);
    }

    public DefaultTableModel obtenerCategorias() {
        return this.m_Categoria.getCategorias();
    }

    public int registrarCategoria(String nombre, String descripcion) {
        this.m_Categoria.setCategoria(nombre,descripcion);
        return this.m_Categoria.registrarCategoria();
    }
    
    public void modificarCategoria(int id, String nombre, String descripcion) {
      this.m_Categoria.setCategoria(id,nombre,descripcion);
        this.m_Categoria.modificarCategoria();
    }
        
     
     
}


package correos.tiendaonline.software.Negocio;
import correos.tiendaonline.software.Datos.Cliente;
import correos.tiendaonline.software.Datos.Persona;
import correos.tiendaonline.software.Datos.Producto;
import correos.tiendaonline.software.Datos.User;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Mauricio
 */
public class ProductoNegocio {
        private Producto m_Producto;
        
  public ProductoNegocio() {
        this.m_Producto = new Producto();
    }

    public DefaultTableModel obtenerProducto(int id) {
        return this.m_Producto.getProducto(id);
    }

    public DefaultTableModel obtenerProductos() {
        return this.m_Producto.getProductos();
    }

    public int registrarProducto(int id_categoria,String nombre, String descripcion,float precio) {
        this.m_Producto.setProducto(id_categoria, nombre,descripcion,precio);
        return this.m_Producto.registrarProducto();
    }
    public void modificarProducto(int id, int id_categoria, String nombre, String descripcion,float precio) {
        this.m_Producto.setProducto(id_categoria, nombre,descripcion,precio);
        this.m_Producto.modificarProducto();
    }
        
}

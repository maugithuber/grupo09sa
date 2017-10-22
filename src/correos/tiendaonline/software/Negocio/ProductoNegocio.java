
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

    public int registrarProducto(int id_categoria,String nombre, String descripcion,String foto1,float precio) {
        this.m_Producto.setProducto(id_categoria, nombre,descripcion,foto1,precio);
        return this.m_Producto.registrarProducto();
    }
    public void modificarProducto(int id, int id_categoria, String nombre, String descripcion,String foto1,float precio) {
        this.m_Producto.setProducto(id,id_categoria, nombre,descripcion,foto1,precio);
        this.m_Producto.modificarProducto();
    }
    
    public DefaultTableModel obtenerProductomas() {
        return this.m_Producto.getProductomas();
    }
    
     public DefaultTableModel obtenerProductomasq() {
        return this.m_Producto.getProductomasq();
    }
    
       public DefaultTableModel obtenerventasporzonas() {
        return this.m_Producto.getventasporzonas();
    }
       
     public void eliminarProducto(int id) {
     DefaultTableModel t= this.m_Producto.getProducto(id);
       this.m_Producto.setProducto(id,(int)t.getValueAt(0, 1)
               ,(String)t.getValueAt(0, 2),
               (String)t.getValueAt(0, 3),
              (String) t.getValueAt(0, 4),
              (float) t.getValueAt(0, 5));
        this.m_Producto.eliminarProducto();
    }  
       
}

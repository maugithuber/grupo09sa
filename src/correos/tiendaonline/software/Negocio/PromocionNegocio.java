/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;
import correos.tiendaonline.software.Datos.Promocion_detalle;
import correos.tiendaonline.software.Datos.Producto;
import java.sql.Date;
import correos.tiendaonline.software.Datos.Promocion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class PromocionNegocio {
       private Promocion m_Promocion;
       private Promocion_detalle m_productopromo;
     private Producto m_producto;
     
       public PromocionNegocio() {
        this.m_Promocion = new Promocion();
        this.m_productopromo = new Promocion_detalle();
        this.m_producto=new Producto();
    }

    public DefaultTableModel obtenerPromocion(int id) {
        return this.m_Promocion.getPromocion(id);
    }

    public DefaultTableModel obtenerPromociones() {
        return this.m_Promocion.getPromociones();
    }

    public int registrarPromocion(String nombre, String descripcion, Date fecha_inicio, Date fecha_fin) {
        this.m_Promocion.setPromocion(nombre,descripcion,fecha_inicio,fecha_fin);
        return this.m_Promocion.registrarPromocion();
    }
    
    public void modificarPromocion(int id, String nombre, String descripcion, Date fecha_inicio, Date fecha_fin) {
      this.m_Promocion.setPromocion(id,nombre,descripcion,fecha_inicio,fecha_fin);
        this.m_Promocion.modificarPromocion();
    }
        
         public void eliminarPromocion(int id) {
     DefaultTableModel t= this.m_Promocion.getPromocion(id);
       this.m_Promocion.setPromocion(id,(String)t.getValueAt(0, 1)
               ,(String)t.getValueAt(0, 2),
               (Date)t.getValueAt(0, 3),
              (Date) t.getValueAt(0, 4));
        this.m_Promocion.eliminarPromocion();
    }   
         
        public int registrarDetalle(int id_producto) {
         int lastpromo= (int) this.m_Promocion.getLastPromo().getValueAt(0,0);
         System.out.println("producto"+id_producto);  
         System.out.println("promo"+lastpromo);
         this.m_productopromo.setPromocion_detalle(id_producto,lastpromo);
         return this.m_productopromo.registrarDetalle();
    }
       
        
        public DefaultTableModel obtenerProductos() {
            int lastpromo= (int) this.m_Promocion.getLastPromo().getValueAt(0,0);
            return this.m_producto.getProductospromo(lastpromo);
    }
        
       public DefaultTableModel obtenerProductospromo(int id_promo) {
            return this.m_producto.getProductospromo(id_promo);
    }
     
}

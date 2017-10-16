/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;
import java.sql.Date;
import correos.tiendaonline.software.Datos.Promocion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class PromocionNegocio {
       private Promocion m_Promocion;
     
       public PromocionNegocio() {
        this.m_Promocion = new Promocion();
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
        
     
     
}

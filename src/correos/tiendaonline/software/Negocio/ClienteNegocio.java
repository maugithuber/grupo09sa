package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Cliente;
import correos.tiendaonline.software.Datos.Persona;
import correos.tiendaonline.software.Datos.User;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class ClienteNegocio {

    private Cliente m_Cliente;
    private User m_user;
    private Persona m_persona;

    public ClienteNegocio() {
        this.m_Cliente = new Cliente();
        this.m_persona = new Persona();
        this.m_user = new User();
    }

    public DefaultTableModel obtenerCliente(int id) {
        return this.m_Cliente.getCliente(id);
    }

    public DefaultTableModel obtenerClientes() {
        return this.m_Cliente.getClientes();
    }

    public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(id_persona, nombre, apellido);
        this.m_Cliente.setCliente(id_persona,telefono, direccion, id_persona);
        this.m_user.setUser(id_persona,tipo, id_persona, email, pass);
        
        this.m_persona.registrarPersona();
        this.m_user.registrarUser();
        return this.m_Cliente.registrarCliente();
    }
    public void modificarCliente(int id, String telefono , String direccion) {
         this.m_Cliente.setCliente(id,telefono ,direccion );
          this.m_Cliente.modificarCliente();
    }
    
      public void eliminarCliente(int id) {
       DefaultTableModel t= this.m_Cliente.getCliente2(id);
       this.m_Cliente.setCliente(id,
               (String)t.getValueAt(0, 1)
               ,(String)t.getValueAt(0, 2),
              (int) t.getValueAt(0, 3));
        this.m_Cliente.eliminarCliente();
    }  
  
}

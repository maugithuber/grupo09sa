/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Encargado;
import correos.tiendaonline.software.Datos.Persona;
import correos.tiendaonline.software.Datos.User;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class EncargadoNegocio {
    private Encargado m_Encargado;
    private User m_user;
    private Persona m_persona;

    public EncargadoNegocio() {
        this.m_Encargado = new Encargado();
        this.m_persona = new Persona();
        this.m_user = new User();
    }

    public DefaultTableModel obtenerEncargado(int id) {
        return this.m_Encargado.getEncargado(id);
    }

    public DefaultTableModel obtenerEncargados() {
        return this.m_Encargado.getEncargados();
    }

    public int registrarEncargado(String nombre, String apellido, int id_user, String email, String pass, 
        int tipo,int id_persona) {
        this.m_persona.setPersona(id_persona, nombre, apellido);
        this.m_Encargado.setEncargado(id_persona,id_persona);
        this.m_user.setUser(id_persona,tipo, id_persona, email, pass);
        
        this.m_persona.registrarPersona();
        this.m_user.registrarUser();
        return this.m_Encargado.registrarEncargado();
    }
      public void eliminarEncargado(int id) {
       DefaultTableModel t= this.m_Encargado.getEncargado(id);
       this.m_Encargado.setEncargado(id,(int)t.getValueAt(0, 1));
        this.m_Encargado.eliminarEncargado();
    }  
  
      
     public int registrarEncargado2(String nombre, String apellido, String email, String pass) {
        // No olvidar primero settear los datos
       int id_persona = (int) this.m_persona.getultimapersona().getValueAt(0, 0);
       id_persona++;
        System.out.println(id_persona);
        this.m_persona.setPersona(id_persona, nombre, apellido);
        this.m_Encargado.setEncargado(id_persona,id_persona);
        this.m_user.setUser(id_persona,2, id_persona, email, pass);
        
        this.m_persona.registrarPersona();
        this.m_user.registrarUser();
         return this.m_Encargado.registrarEncargado();
    }
}

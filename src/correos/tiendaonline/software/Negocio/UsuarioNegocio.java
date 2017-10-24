/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.User;
import correos.tiendaonline.utils.Utils;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grupo09sa
 */
public class UsuarioNegocio {

    private User m_user;

    public UsuarioNegocio() {
        this.m_user = new User();
    }

    public int getTipoUser(String email) {
        DefaultTableModel usuario = this.m_user.getTipoUsuario(email);
        return (usuario.getRowCount() >0) ? (int) usuario.getValueAt(0, 0) : -1;
        //System.out.println(Utils.dibujarTabla(usuario));
        //return 1;
    }
}

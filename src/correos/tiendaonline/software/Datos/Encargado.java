
package correos.tiendaonline.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class Encargado {
    int id,id_persona;
    Conexion m_Conexion;

    public Encargado() {
        this.m_Conexion = m_Conexion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setEncargado(int id, int id_persona) {
        this.id = id;
        this.id_persona = id_persona;
    }
    
      public DefaultTableModel getEncargado(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel encargado = new DefaultTableModel();
        encargado.setColumnIdentifiers(new Object[]{
            "id","id_persona"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "encargado.id,\n"
                + "encargado.id_persona\n"
                + "FROM encargado\n"
                + "WHERE encargado.id=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                encargado.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_persona")

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return encargado;
    }

    public DefaultTableModel getEncargados() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel encargados = new DefaultTableModel();
        encargados.setColumnIdentifiers(new Object[]{
            "id","id_persona"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
         + "encargado.id,\n"
         + "encargado.id_persona\n"
         + "FROM clientes";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                encargados.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_persona")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return encargados;
    }
    
}


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
public class Gerente {
    int id,id_persona;
    String cod_acceso;
    Conexion m_Conexion;

    public Gerente() {
    this.m_Conexion = Conexion.getInstancia();
    }
    
    public void setGerente(int id, int id_persona, String cod_acceso) {
        this.id = id;
        this.id_persona = id_persona;
        this.cod_acceso = cod_acceso;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setCod_acceso(String cod_acceso) {
        this.cod_acceso = cod_acceso;
    }
    
       public DefaultTableModel getGerente(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel gerente = new DefaultTableModel();
        gerente.setColumnIdentifiers(new Object[]{
            "id", "id_persona", "cod_acceso"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "gerente.id,\n"
                + "gerente.id_persona,\n"
                + "gerente.cod_acceso\n"
                + "FROM gerente\n"
                + "WHERE gerente.id=?";
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
                gerente.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_persona"),
                    rs.getString("cod_acceso"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gerente;
    }

    public DefaultTableModel getGerentes() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel gerentes = new DefaultTableModel();
        gerentes.setColumnIdentifiers(new Object[]{
              "id", "id_persona", "cod_acceso"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "gerente.id,\n"
                + "gerente.id_persona,\n"
                + "gerente.cod_acceso\n"
                + "FROM gerente\n";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                gerentes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_persona"),
                    rs.getString("cod_acceso"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return gerentes;
    }
    
    
    
    
}

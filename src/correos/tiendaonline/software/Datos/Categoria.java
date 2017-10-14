
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
public class Categoria {
    int id;
    String nombre,descripcion;
    Conexion m_Conexion;

    public Categoria() {
        this.m_Conexion = m_Conexion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
   public void setCategoria(int id,String nombre,String descripcion){
       this.id = id;
       this.nombre = nombre;
       this.descripcion = descripcion;         
   }
   
    public DefaultTableModel getCategoria(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel categoria = new DefaultTableModel();
        categoria.setColumnIdentifiers(new Object[]{
            "id", "nombre", "descripcion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "categoria.id,\n"
                + "categoria.nombre,\n"
                + "categoria.descripcion\n"
                + "FROM categoria\n"
                + "WHERE categoria.id=?";
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
                categoria.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categoria;
    }

    public DefaultTableModel getPersonas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel categorias = new DefaultTableModel();
        categorias.setColumnIdentifiers(new Object[]{
            "id", "nombre", "descrpcion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "categoria.id,\n"
                + "categoria.nombre,\n"
                + "categoria.descripcion\n"
                + "FROM categoria";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                categorias.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return categorias;
    }
}

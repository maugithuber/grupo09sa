
package correos.tiendaonline.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        this.m_Conexion = Conexion.getInstancia();
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
   
      public void setCategoria(String nombre,String descripcion){
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

    public DefaultTableModel getCategorias() {
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
    
    
    public int registrarCategoria() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO categoria(\n"
                + "nombre,descripcion)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

            // Obtengo el id generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public void modificarCategoria() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE categoria SET \n"
                + "nombre = ?,\n"
                + "descripcion = ?\n"
                + "WHERE categoria.id = ?";
        System.out.println(sql);
        System.out.println(toString());

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



}

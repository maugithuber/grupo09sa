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
public class Producto {

    int id, id_categoria;
    String nombre, descripcion;
    String foto1, foto2, video;
    float precio;
    Conexion m_Conexion;

    public Producto() {
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setProducto(int id, int id_categoria, String nombre, String descripcion,String foto1, float precio) {
        this.id = id;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto1=foto1;
        this.precio = precio;
    }

    public void setProducto(int id_categoria, String nombre, String descripcion,String foto1, float precio) {
        this.id_categoria = id_categoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto1=foto1;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFoto1(String foto1) {
        this.foto1 = foto1;
    }

    public void setFoto2(String foto2) {
        this.foto2 = foto2;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public DefaultTableModel getProducto(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel producto = new DefaultTableModel();
        producto.setColumnIdentifiers(new Object[]{
            "id", "id_categoria", "nombre", "descripcion", "precio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "productos.id,\n"
                + "productos.id_categoria,\n"
                + "productos.nombre,\n"
                + "productos.descripcion,\n"
                + "productos.precio\n"
                + "FROM productos\n"
                + "WHERE productos.id=?";
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
                producto.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_categoria"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getFloat("precio")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }

    public DefaultTableModel getProductos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productos = new DefaultTableModel();
        productos.setColumnIdentifiers(new Object[]{
            "id", "id_categoria", "nombre", "descripcion", "precio"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "productos.id,\n"
                + "productos.id_categoria,\n"
                + "productos.nombre,\n"
                + "productos.descripcion,\n"
                + "productos.precio\n"
                + "FROM productos";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                productos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_categoria"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getFloat("precio")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productos;
    }

    public int registrarProducto() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO productos(\n"
                + "nombre,descripcion,id_categoria,foto1,precio)\n"
                + "VALUES(?,?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            // ps.setBytes(3, this.foto1);
            // ps.setBytes(4, this.foto2);
            // ps.setBytes(5, this.video);
            ps.setInt(3, this.id_categoria);
            ps.setString(4, this.foto1);
            ps.setFloat(5, this.precio);

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

    public void modificarProducto() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE productos SET \n"
                + "nombre = ?,\n"
                + "descripcion = ?,\n"
                + "id_categoria = ?,\n"
                + "foto1 = ?,\n"
                + "precio = ? \n"
                + "WHERE productos.id = ?";
        System.out.println(sql);
        System.out.println(toString());

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setInt(3, this.id_categoria);
            ps.setString(4, this.foto1);
            ps.setFloat(5, this.precio);
            ps.setInt(6, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Producto:\n"
                + "{id=" + this.id + "\n"
                + "nombre=" + this.nombre + "\n"
                + "descripcion=" + this.descripcion + "\n"
                + "id_categoria=" + this.id_categoria + "\n"
                + "precio=" + this.precio + "\n"
                + "}";
    }
    
    
        public DefaultTableModel getProductomas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel producto = new DefaultTableModel();
        producto.setColumnIdentifiers(new Object[]{
            "nombre", "total"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "productos.nombre,\n"
                + "sum(cantidad*productos.precio)total\n"
                + "FROM detalle,productos\n"
                + "WHERE productos.id=detalle.id_producto \n"
                 + "GROUP BY productos.nombre \n"
                 + "ORDER BY sum(cantidad*productos.precio) desc \n"
                + "limit 1";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                producto.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getFloat("total")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }
        
     public DefaultTableModel getventasporzonas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ventas = new DefaultTableModel();
        ventas.setColumnIdentifiers(new Object[]{
            "nombre", "total"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "zona.nombre,\n"
                + "sum(total)total\n"
                + "FROM zona,orden\n"
                + "WHERE zona.id=orden.id_zona \n"
                 + "GROUP BY zona.nombre \n";
 
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                ventas.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getFloat("total")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ventas;
    }

     
     
     
    public DefaultTableModel getProductomasq() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel producto = new DefaultTableModel();
        producto.setColumnIdentifiers(new Object[]{
            "nombre", "cantidad"
        });
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "productos.nombre,\n"
                + "sum(cantidad)cantidad\n"
                + "FROM detalle,productos\n"
                + "WHERE productos.id=detalle.id_producto \n"
                + "GROUP BY productos.nombre \n"
                + "ORDER BY sum(cantidad*productos.precio) desc \n"
                + "limit 1";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                producto.addRow(new Object[]{
                    rs.getString("nombre"),
                    rs.getInt("cantidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return producto;
    }
}

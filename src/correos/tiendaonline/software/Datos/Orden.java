/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class Orden {

    int id, id_cliente, id_zona;
    float total;
    Date fecha;
    Conexion m_Conexion;

    public Orden() {
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setOrden(int id, int id_cliente, int id_zona, float total) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_zona = id_zona;
        this.total = total;
        // para el calendario
        java.util.Date utilDate = new java.util.Date();
        this.fecha = new java.sql.Date(utilDate.getTime());
    }

    public void setOrden(int id_cliente, int id_zona) {
        this.id_cliente = id_cliente;
        this.id_zona = id_zona;
        this.total = 0;
        // para el calendario
        java.util.Date utilDate = new java.util.Date();
        this.fecha = new java.sql.Date(utilDate.getTime());
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setId_zona(int id_zona) {
        this.id_zona = id_zona;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public DefaultTableModel getLastOrden() {
        DefaultTableModel orden = new DefaultTableModel();
        orden.setColumnIdentifiers(new Object[]{
            "id"
        });
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "id\n"
                + "FROM orden\n"
                + "ORDER BY id desc \n"
                + "limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.m_Conexion.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                orden.addRow(new Object[]{
                    rs.getInt("id")});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orden;
    }

    public DefaultTableModel getLastOrdenTotal() {
        DefaultTableModel orden = new DefaultTableModel();
        orden.setColumnIdentifiers(new Object[]{
            "idOrden", "Cliente", "Zona", "total", "fecha"
        });
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "orden.id,\n"
                + "persona.nombre as nombre,\n"
                + "zona.nombre as zona,\n"
                + "orden.total, orden.fecha \n"
                + "FROM orden,zona,persona,clientes\n"
                + "WHERE orden.id_zona=zona.id and clientes.id=orden.id_cliente and persona.id= clientes.id_persona \n"
                + "ORDER BY orden.id desc \n"
                + "limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.m_Conexion.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                orden.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("zona"),
                    rs.getFloat("total"),
                    rs.getString("fecha")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orden;
    }

    public DefaultTableModel getProductosLastOrdenTotal() {
        DefaultTableModel orden = new DefaultTableModel();
        orden.setColumnIdentifiers(new Object[]{
            "id_producto", "cantidad", "PrecioPagado", "subtotal", "nombre", "descripcion", "foto"
        });
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "productos.id,\n"
                + "detalle.cantidad, \n"
                + "productos.precio, \n"
                + "detalle.subtotal,\n"
                + "productos.nombre,\n"
                + "productos.descripcion,\n"
                + "productos.foto1\n"
                + "FROM orden,productos,detalle\n"
                + "WHERE orden.id=detalle.id_orden and productos.id=detalle.id_producto and orden.id=?"
                + "ORDER BY id desc \n";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, (int) getLastOrden().getValueAt(0, 0));
            ResultSet rs = ps.executeQuery();
            this.m_Conexion.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                orden.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("cantidad"),
                    rs.getFloat("precio"),
                    rs.getFloat("subtotal"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getString("foto1")

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orden;
    }

    public DefaultTableModel getOrden(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel orden = new DefaultTableModel();
        orden.setColumnIdentifiers(new Object[]{
            "id", "id_cliente", "id_zona", "total", "fecha"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "id,\n"
                + "id_cliente,\n"
                + "id_zona,\n"
                + "total,\n"
                + "fecha\n"
                + "FROM orden\n"
                + "WHERE orden.id=?";
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
                orden.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_zona"),
                    rs.getFloat("total"),
                    rs.getDate("fecha"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orden;
    }

    public DefaultTableModel getOrdenes() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ordenes = new DefaultTableModel();
        ordenes.setColumnIdentifiers(new Object[]{
            "id", "id_cliente", "id_zona", "total", "fecha"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "orden.id,\n"
                + "orden.id_cliente,\n"
                + "orden.id_zona\n"
                + "orden.total\n"
                + "orden.fecha\n"
                + "FROM orden\n";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                ordenes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_cliente"),
                    rs.getInt("id_zona"),
                    rs.getFloat("total"),
                    rs.getDate("fecha"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ordenes;
    }

    public int registrarOrden() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO orden(\n"
                + "id_zona,id_cliente,total,fecha)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_zona);
            ps.setInt(2, this.id_cliente);
            ps.setFloat(3, this.total);
            ps.setDate(4, this.fecha);

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

    public void endCarrito() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE orden SET\n"
                + "total= ?,\n"
                + "WHERE orden.id = ? \n"
                + "and orden.id_cliente =?";
        try {
            System.out.println("Modificando orden" + toString());
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, this.total);
            ps.setInt(2, this.id);

            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modificarOrden() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE orden SET\n"
                + "total= ?,\n"
                + "fecha = ?\n"
                + "WHERE orden.id = ? \n"
                + "and orden.id_cliente =?";
        try {
            System.out.println("Modificando orden" + toString());
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setFloat(1, this.total);
            ps.setDate(2, this.fecha);
            ps.setInt(3, this.id);
            ps.setInt(4, this.id_cliente);

            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void EliminarOrden() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from orden\n"
                + "WHERE orden.id = ? \n"
                + "and orden.id_cliente =?";
        try {
            System.out.println("Eliminar orden" + toString());
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.setInt(2, this.id_cliente);

            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", id_cliente=" + id_cliente + ", id_zona=" + id_zona + ", total=" + total + ", fecha=" + fecha.toString() + '}';
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Detalle {

    int id, id_orden, id_producto, cantidad;
    float subtotal;
    Conexion m_Conexion;

    public Detalle() {
        m_Conexion = Conexion.getInstancia();
    }

    public void setDetalle(int id, int id_orden, int id_producto, int cantidad, float subtotal) {
        this.id = id;
        this.id_orden = id_orden;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public void setDetalle(int id_orden, int id_producto, int cantidad, float subtotal) {

        this.id_orden = id_orden;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public DefaultTableModel getDetalle(int id_orden) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle = new DefaultTableModel();
        detalle.setColumnIdentifiers(new Object[]{
            "id", "id_orden", "id_producto", "cantidad", "subtotal"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle.id,\n"
                + "detalle.id_orden,\n"
                + "detalle.id_producto,\n"
                + "detalle.cantidad,\n"
                + "detalle.subtotal\n"
                + "FROM detalle\n"
                + "WHERE detalle.id_orden=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id_orden);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                detalle.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_orden"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("subtotal"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle;
    }

    public void modificarDetalle() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        /// Obtengo el id del detalle a partir de la orden
        DefaultTableModel detalle = getDetalle(id_orden);
        this.id = (int) detalle.getValueAt(0, 0);
        System.out.println("Modificando detalle: " + toString());
        // Preparo la consulta
        String sql = "UPDATE detalle SET\n"
                + "id_orden= ?,\n"
                + "id_producto = ?,\n"
                + "cantidad = ?,\n"
                + "subtotal = ?\n"
                + "WHERE detalle.id = ? \n";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id_orden);
            ps.setInt(2, this.id_producto);
            ps.setInt(3, this.cantidad);
            ps.setFloat(4, this.subtotal);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int registrarDetalle() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO detalle(\n"
                + "id_orden,id_producto,cantidad,subtotal)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setInt(1, this.id_orden);
            ps.setInt(2, this.id_producto);
            ps.setInt(3, this.cantidad);
            ps.setFloat(4, this.subtotal);

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

    public DefaultTableModel getDetalles() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalles = new DefaultTableModel();
        detalles.setColumnIdentifiers(new Object[]{
            "id", "id_orden", "id_producto", "cantidad", "subtotal"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle.id,\n"
                + "detalle.id_orden,\n"
                + "detalle.id_producto,\n"
                + "detalle.cantidad,\n"
                + "detalle.subtotal\n"
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
                detalles.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_orden"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("subtotal"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalles;
    }

    @Override
    public String toString() {
        return "Detalle{" + "id=" + id + ", id_orden=" + id_orden + ", id_producto=" + id_producto + ", cantidad=" + cantidad + ", subtotal=" + subtotal + '}';
    }

}

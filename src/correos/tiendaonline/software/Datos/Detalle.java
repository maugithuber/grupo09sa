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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class Detalle {
   int id,id_orden,id_producto,cantidad;
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
    
    public DefaultTableModel getDetalle(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle = new DefaultTableModel();
        detalle.setColumnIdentifiers(new Object[]{
              "id", "id_orden", "id_producto","cantidad","subtotal"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle.id,\n"
                + "detalle.id_orden,\n"
                + "detalle.id_producto\n"
                + "detalle.cantidad\n"
                + "detalle.subtotal\n"
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
                detalle.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_orden"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getFloat("subtotal"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle;
    }
     


    public DefaultTableModel getDetalles() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalles = new DefaultTableModel();
        detalles.setColumnIdentifiers(new Object[]{
        "id", "id_orden", "id_producto","cantidad","subtotal"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle.id,\n"
                + "detalle.id_orden,\n"
                + "detalle.id_producto\n"
                + "detalle.cantidad\n"
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
                    rs.getFloat("subtotal"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalles;
    }
    
   
   
   
}

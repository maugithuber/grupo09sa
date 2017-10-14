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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio
 */
public class Orden {
    int id,id_cliente,id_zona;
    float total;
    Date fecha;
    Conexion m_Conexion;

    public Orden() {
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setOrden(int id, int id_cliente, int id_zona, float total, Date fecha) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_zona = id_zona;
        this.total = total;
        this.fecha = fecha;
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
    
     public DefaultTableModel getOrden(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel orden = new DefaultTableModel();
        orden.setColumnIdentifiers(new Object[]{
            "id", "id_cliente", "id_zona","total","fecha"
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
                    rs.getDate("fecha"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orden;
    }
     


    public DefaultTableModel getProductos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel ordenes = new DefaultTableModel();
        ordenes.setColumnIdentifiers(new Object[]{
        "id", "id_cliente", "id_zona","total","fecha"
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
                    rs.getDate("fecha"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ordenes;
    }
    
    
}

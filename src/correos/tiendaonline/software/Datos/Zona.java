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
public class Zona {
    int id,id_encargado;
    String nombre,ubicacion;
    Conexion m_Conexion;
    
    
    public Zona() {
        this.m_Conexion = m_Conexion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_encargado(int id_encargado) {
        this.id_encargado = id_encargado;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setZona(int id, int id_encargado, String nombre, String ubicacion) {
        this.id = id;
        this.id_encargado = id_encargado;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }
    
     public DefaultTableModel getZona(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel zona = new DefaultTableModel();
        zona.setColumnIdentifiers(new Object[]{
            "id", "id_encargado", "nombre", "ubicacion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "zona.id,\n"
                + "zona.id_encargado,\n"
                + "zona.nombre\n"
                + "zona.ubicacion\n"
                + "FROM zona\n"
                + "WHERE zona.id=?";
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
                zona.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_encargado"),
                    rs.getString("nombre"),
                    rs.getString("ubicacion")

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return zona;
    }

    public DefaultTableModel getZonas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel zonas = new DefaultTableModel();
        zonas.setColumnIdentifiers(new Object[]{
              "id", "id_encargado", "nombre", "ubicacion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "zona.id,\n"
                + "zona.id_encargado,\n"
                + "zona.nombre\n"
                + "zona.ubicacion\n"
         + "FROM zona";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                zonas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_encargado"),
                    rs.getString("nombre"),
                    rs.getString("ubicacion")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return zonas;
    }
    
    
    
}

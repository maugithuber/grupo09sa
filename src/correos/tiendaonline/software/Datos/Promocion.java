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
public class Promocion {
    int id;
    String nombre,descripcion;
    Date fecha_inicio,fecha_fin;
    Conexion m_Conexion;

    public Promocion() {
      this.m_Conexion = Conexion.getInstancia();
    }

    public void setPromocion(int id, String nombre, String descripcion, Date fecha_inicio, Date fecha_fin) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
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

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
      public DefaultTableModel getPromocion(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel promocion = new DefaultTableModel();
        promocion.setColumnIdentifiers(new Object[]{
            "id", "nombre", "descripcion", "fecha_inicio","fecha_fin"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "promocion.id,\n"
                + "promocion.nombre,\n"
                + "promocion.descripcion\n"
                + "promocion.fecha_inicio\n"
                + "promocion.fecha_fin\n"
                + "FROM promocion\n"
                + "WHERE promocion.id=?";
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
                promocion.addRow(new Object[]{
                rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return promocion;
    }

    public DefaultTableModel getPromociones() {
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
                + "promocion.id,\n"
                + "promocion.nombre,\n"
                + "promocion.descripcion\n"
                + "promocion.fecha_inicio\n"
                + "promocion.fecha_fin\n"
                + "FROM promocion\n";

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
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_fin")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return zonas;
    }
    

}

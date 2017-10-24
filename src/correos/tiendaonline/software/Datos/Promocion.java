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
public class Promocion {

    int id;
    String nombre, descripcion;
    Date fecha_inicio, fecha_fin;
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

    public void setPromocion(String nombre, String descripcion, Date fecha_inicio, Date fecha_fin) {
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
            "id", "nombre", "descripcion", "fecha_inicio", "fecha_fin"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "promocion.id,\n"
                + "promocion.nombre,\n"
                + "promocion.descripcion,\n"
                + "promocion.fecha_inicio,\n"
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
            "id", "nombre", "descripcion", "fechaInicio", "fechaFin", "duracion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "promocion.id,\n"
                + "promocion.nombre,\n"
                + "promocion.descripcion,\n"
                + "promocion.fecha_inicio,\n"
                + "promocion.fecha_fin,\n"
                + "(promocion.fecha_fin- promocion.fecha_inicio ) as duracion\n"
                + "FROM promocion\n"
                + "WHERE promocion.estado='true'";

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
                    rs.getDate("fecha_fin"),
                    rs.getString("duracion")

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return zonas;
    }

    public int registrarPromocion() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO promocion(\n"
                + "nombre,descripcion,fecha_inicio,fecha_fin)\n"
                + "VALUES(?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setDate(3, this.fecha_inicio);
            ps.setDate(4, this.fecha_fin);
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

    public void modificarPromocion() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE promocion SET \n"
                + "nombre = ?,\n"
                + "descripcion = ?,\n"
                + "fecha_inicio = ?,\n"
                + "fecha_fin = ?\n"
                + "WHERE promocion.id = ?";
        System.out.println(sql);
        System.out.println(toString());

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setDate(3, this.fecha_inicio);
            ps.setDate(4, this.fecha_fin);
            ps.setInt(5, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void eliminarPromocion() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE promocion SET\n"
                + "estado = ?\n"
                + "WHERE promocion.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public DefaultTableModel getLastPromo() {
        DefaultTableModel promo = new DefaultTableModel();
        promo.setColumnIdentifiers(new Object[]{
            "id"
        });
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "id\n"
                + "FROM promocion\n"
                + "ORDER BY id desc \n"
                + "limit 1";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.m_Conexion.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                promo.addRow(new Object[]{
                    rs.getInt("id")});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return promo;
    }
}

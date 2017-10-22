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
public class Zona {

    int id, id_encargado;
    String nombre, ubicacion;
    Conexion m_Conexion;

    public Zona() {
        this.m_Conexion = Conexion.getInstancia();
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

    public void setZona(int id_encargado, String nombre, String ubicacion) {
        this.id_encargado = id_encargado;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

    public int registrarzona() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO zona(\n"
                + "id_encargado,nombre,ubicacion)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_encargado);
            ps.setString(2, this.nombre);
            ps.setString(3, this.ubicacion);
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

    public void modificarZona() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE zona SET\n"
                + "id_encargado= ?,\n"
                + "nombre = ?,\n"
                + "ubicacion = ?\n"
                + "WHERE zona.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id_encargado);
            ps.setString(2, this.nombre);
            ps.setString(3, this.ubicacion);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
                + "zona.nombre, \n"
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

    public void eliminarZona() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE from zona \n"
                + "WHERE zona.id = ? \n";

        try {
            System.out.println("Eliminar zona" + toString());
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id);

            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public boolean validarCreador(String email, String pass) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT * FROM usuarios \n"
                + "WHERE usuarios.email = ? \n"
                + "and usuarios.password= ? \n"
                + "and usuarios.tipo = 3";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            if (rs.next()) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean isUsuario(String email) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT * FROM usuarios \n"
                + "WHERE usuarios.email = ? \n";
               
                

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            if (rs.getInt(3)==3) {
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
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
                + "zona.nombre,\n"
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

    @Override
    public String toString() {
        return "Zona{" + "id=" + id + ", id_encargado=" + id_encargado + ", nombre=" + nombre + ", ubicacion=" + ubicacion + '}';
    }

}

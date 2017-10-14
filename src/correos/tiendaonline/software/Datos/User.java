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
 * @author grupo09sa
 */
public class User {

    int id, tipo, id_persona;
    String email, password;
    public Conexion m_Conexion;

    public User() {
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setUser(int id, int tipo, int id_persona, String email, String password) {
        this.id = id;
        this.tipo = tipo;
        this.id_persona = id_persona;
        this.email = email;
        this.password = password;
    }
        public void setUser(int tipo, int id_persona, String email, String password) {
        this.tipo = tipo;
        this.id_persona = id_persona;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DefaultTableModel getUser(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel user = new DefaultTableModel();
        user.setColumnIdentifiers(new Object[]{
            "id", "nombre", "apellido"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "persona.id,\n"
                + "persona.nombre,\n"
                + "persona.apellido\n"
                + "FROM persona\n"
                + "WHERE persona.id=?";
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
                user.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }

    public DefaultTableModel getUsers() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel users = new DefaultTableModel();
        users.setColumnIdentifiers(new Object[]{
            "id", "tipo", "id_persona","email","password"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "users.id,\n"
                + "users.tipo,\n"
                + "users.id_persona\n"
                + "users.email\n"
                + "users.password\n"
                + "FROM users";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                users.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("id_persona"),
                    rs.getString("email"),
                    rs.getString("password")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public int registrarUser() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO usuarios(\n"
                + "id,email,id_persona,tipo,password)\n"
                + "VALUES(?,?,?,?,?)";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id);
            ps.setString(2, this.email);
            ps.setInt(3, this.id_persona);
            ps.setInt(4, this.tipo);
            ps.setString(5, this.password);
           
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

}

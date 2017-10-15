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
public class Cliente {

    int id;
    String telefono;
    String direccion;
    int id_persona;
    public Conexion m_Conexion;

    public Cliente() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public void setCliente(int id, String telefono, String direccion) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void setCliente(int id, String telefono, String direccion, int id_persona) {
        this.id = id;
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_persona = id_persona;

    }

    public void setCliente(String telefono, String direccion, int id_persona) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.id_persona = id_persona;
    }
    public DefaultTableModel getCliente(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cliente = new DefaultTableModel();
        cliente.setColumnIdentifiers(new Object[]{
            "id","nombre","apellido", "telefono", "direccion", "id_persona"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "clientes.id,\n"
                + "persona.nombre,\n"
                + "persona.apellido,\n"
                + "clientes.telefono,\n"
                + "clientes.direccion,\n"
                + "clientes.id_persona\n"
                + "FROM clientes,persona\n"
                + "WHERE clientes.id_persona=persona.id\n"
                + "AND clientes.id=?";
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
                cliente.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getInt("id_persona")

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cliente;
    }

    public DefaultTableModel getClientes() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel clientes = new DefaultTableModel();
        clientes.setColumnIdentifiers(new Object[]{
            "id","nombre","apellido", "telefono", "direccion","id_persona"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
         + "clientes.id,\n"
         + "persona.nombre,\n"
         + "persona.apellido,\n"
         + "clientes.telefono,\n"
         + "clientes.direccion,\n"
         + "clientes.id_persona\n"
         + "FROM clientes,persona\n"
      + "WHERE clientes.id_persona=persona.id";
        
        
        
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                clientes.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("direccion"),
                    rs.getInt("id_persona")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
    
    
     public int registrarCliente() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO clientes(\n"
                + "telefono,direccion,id_persona)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.telefono);
            ps.setString(2, this.direccion);
            ps.setInt(3, this.id_persona);
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
    
     public void modificarCliente() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE clientes SET\n"
                + "telefono = ?,\n"
                + "direccion = ?\n"
                + "WHERE clientes.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.telefono);
            ps.setString(2, this.direccion);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

     
     
}

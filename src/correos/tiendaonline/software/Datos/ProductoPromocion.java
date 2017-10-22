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
public class ProductoPromocion {
    int id,id_producto,id_promocion;
    Conexion m_Conexion;

    public ProductoPromocion() {
        this.m_Conexion= Conexion.getInstancia();
    }

    public void setProductoPromocion(int id, int id_producto, int id_promocion) {
        this.id = id;
        this.id_producto = id_producto;
        this.id_promocion = id_promocion;
    }
    
    public void setProductoPromocion(int id_producto,int id_promocion ) {
        this.id_producto = id_producto;
        this.id_promocion = id_promocion;

    }
    
      public DefaultTableModel getProductoPromocion(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productopromocion = new DefaultTableModel();
        productopromocion.setColumnIdentifiers(new Object[]{
            "id", "id_producto", "id_promocion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "productopromocion.id,\n"
                + "productopromocion.id_producto,\n"
                + "productopromocion.id\n"
                + "FROM productopromocion\n"
                + "WHERE productopromocion.id=?";
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
                productopromocion.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_producto"),
                    rs.getInt("id_promocion"),

                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productopromocion;
    }

    public DefaultTableModel getUsers() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel productopromociones = new DefaultTableModel();
        productopromociones.setColumnIdentifiers(new Object[]{
            "id", "id_peroducto", "id_promocion"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "productopromocion.id,\n"
                + "productopromocion.id_producto,\n"
                + "productopromocion.id\n"
                + "FROM productopromocion\n";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                productopromociones.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("id_producto"),
                    rs.getInt("id_promocion"),
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return productopromociones;
    }
    
    public int registrarDetalle() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO promocion_detalle(\n"
                + "id_promocion,id_producto)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setInt(1, this.id_promocion);
            ps.setInt(2, this.id_producto);
          
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

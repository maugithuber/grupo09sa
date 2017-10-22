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

/**
 *
 * @author Mauricio
 */
public class Promocion_detalle {
    int id,id_producto,id_promocion;
    Conexion m_Conexion;

    public Promocion_detalle() {
        this.m_Conexion= Conexion.getInstancia();
    }
    
    public void setPromocion_detalle(int id_producto,int id_promocion ) {
        this.id_producto = id_producto;
        this.id_promocion = id_promocion;

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

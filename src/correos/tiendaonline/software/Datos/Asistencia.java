package correos.tiendaonline.software.Datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class Asistencia {

    private int id;
    private String estado;
    private Date fecha;
    private int id_kardex;
    public Conexion m_Conexion;

    public Asistencia() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public void setAsistencia(String estado, Date fecha, int id_kardex) {
        this.estado = estado;
        this.fecha = fecha;
        this.id_kardex = id_kardex;
    }

    /**
     *
     * @param id
     * @param estado
     * @param fecha
     * @param id_kardex
     */
    public void setAsistencia(int id, String estado, Date fecha, int id_kardex) {
        this.id = id;
        this.estado = estado;
        this.fecha = fecha;
        this.id_kardex = id_kardex;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getAsistencia(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel asistencia = new DefaultTableModel();
        asistencia.setColumnIdentifiers(new Object[]{
            "id", "estado", "fecha", "id_kardex"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "asistencia.id,\n"
                + "asistencia.estado,\n"
                + "asistencia.fecha,\n"
                + "asistencia.id_kardex\n"
                + "FROM asistencia\n"
                + "WHERE asistencia.id=?";
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
                asistencia.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("estado"),
                    rs.getDate("fecha"),
                    rs.getInt("id_kardex")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return asistencia;
    }

    /**
     *
     * @param id_kardex
     * @return
     */
    public DefaultTableModel getAsistencias(int id_kardex) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel asistencia = new DefaultTableModel();
        asistencia.setColumnIdentifiers(new Object[]{
            "id", "estado", "fecha", "id_kardex"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "asistencia.id,\n"
                + "asistencia.estado,\n"
                + "asistencia.fecha,\n"
                + "asistencia.id_kardex\n"
                + "FROM asistencia\n"
                + "WHERE asistencia.id_kardex=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_kardex);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                asistencia.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("estado"),
                    rs.getDate("fecha"),
                    rs.getInt("id_kardex")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return asistencia;
    }

    public int registrarAsistencia() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO asistencia(\n"
                + "estado,fecha,id_kardex)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.estado);
            ps.setDate(2, this.fecha);
            ps.setInt(3, this.id_kardex);
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

    public void modificarAsistencia() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE asistencia SET\n"
                + "estado = ?,\n"
                + "fecha = ?,\n"
                + "id_kardex = ?\n"
                + "WHERE asistencia.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.estado);
            ps.setDate(2, this.fecha);
            ps.setInt(3, this.id_kardex);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

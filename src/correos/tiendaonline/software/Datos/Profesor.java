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
 * @created 15-Jun-2017 9:04:50 PM
 */
public class Profesor {

    private int id;
    private String nombres;
    private String apellidos;
    private int telefono;
    private Date fecha_postulacion;
    private boolean estado;
    public Conexion m_Conexion;

    public Profesor() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     */
    public void setProfesor(String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_postulacion = fecha_postulacion;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_postulacion
     * @param estado
     */
    public void setProfesor(int id, String nombres, String apellidos, int telefono, Date fecha_postulacion, boolean estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_postulacion = fecha_postulacion;
        this.estado = estado;
    }

    /**
     *
     * @param id
     */
    public DefaultTableModel getProfesor(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel profesor = new DefaultTableModel();
        profesor.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_postulacion", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "profesor.id,\n"
                + "profesor.nombres,\n"
                + "profesor.apellidos,\n"
                + "profesor.telefono,\n"
                + "profesor.fecha_postulacion,\n"
                + "profesor.estado\n"
                + "FROM profesor\n"
                + "WHERE profesor.id=?";
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
                profesor.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_postulacion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return profesor;
    }

    public DefaultTableModel getProfesores() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel profesores = new DefaultTableModel();
        profesores.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_postulacion", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "profesor.id,\n"
                + "profesor.nombres,\n"
                + "profesor.apellidos,\n"
                + "profesor.telefono,\n"
                + "profesor.fecha_postulacion,\n"
                + "profesor.estado\n"
                + "FROM profesor";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                profesores.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_postulacion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return profesores;
    }

    public int registrarProfesor() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO profesor(\n"
                + "nombres,apellidos,telefono,fecha_postulacion,estado)\n"
                + "VALUES(?,?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_postulacion);
            ps.setBoolean(5, this.estado);
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

    public void modificarProfesor() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE profesor SET\n"
                + "nombres = ?,\n"
                + "apellidos = ?,\n"
                + "telefono = ?,\n"
                + "fecha_postulacion = ?,\n"
                + "estado = ?\n"
                + "WHERE profesor.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_postulacion);
            ps.setBoolean(5, this.estado);
            ps.setInt(6, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

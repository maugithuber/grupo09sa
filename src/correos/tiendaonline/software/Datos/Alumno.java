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
 * @created 15-Jun-2017 9:04:43 PM
 */
public class Alumno {

    private int id;
    private String nombres;
    private String apellidos;
    private int telefono;
    private Date fecha_nacimiento;
    private Date fecha_ingreso;
    private boolean estado;
    public Conexion m_Conexion;

    public Alumno() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param fecha_ingreso
     * @param estado
     */
    public void setAlumno(String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_ingreso = fecha_ingreso;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @param nombres
     * @param apellidos
     * @param telefono
     * @param fecha_nacimiento
     * @param fecha_ingreso
     * @param estado
     */
    public void setAlumno(int id, String nombres, String apellidos, int telefono, Date fecha_nacimiento, Date fecha_ingreso, boolean estado) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_ingreso = fecha_ingreso;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getAlumno(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel alumno = new DefaultTableModel();
        alumno.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_nacimiento", "fecha_ingreso", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "alumno.id,\n"
                + "alumno.nombres,\n"
                + "alumno.apellidos,\n"
                + "alumno.telefono,\n"
                + "alumno.fecha_nacimiento,\n"
                + "alumno.fecha_ingreso,\n"
                + "alumno.estado\n"
                + "FROM alumno\n"
                + "WHERE alumno.id=?";
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
                alumno.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getDate("fecha_ingreso"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return alumno;
    }

    public DefaultTableModel getAlumnos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel alumnos = new DefaultTableModel();
        alumnos.setColumnIdentifiers(new Object[]{
            "id", "nombres", "apellidos", "telefono", "fecha_nacimiento", "fecha_ingreso", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "alumno.id,\n"
                + "alumno.nombres,\n"
                + "alumno.apellidos,\n"
                + "alumno.telefono,\n"
                + "alumno.fecha_nacimiento,\n"
                + "alumno.fecha_ingreso,\n"
                + "alumno.estado\n"
                + "FROM alumno";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                alumnos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getInt("telefono"),
                    rs.getDate("fecha_nacimiento"),
                    rs.getDate("fecha_ingreso"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return alumnos;
    }

    public int registrarAlumno() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO alumno(\n"
                + "nombres,apellidos,telefono,fecha_nacimiento,fecha_ingreso,estado)\n"
                + "VALUES(?,?,?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_nacimiento);
            ps.setDate(5, this.fecha_ingreso);
            ps.setBoolean(6, this.estado);
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

    public void modificarAlumno() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE alumno SET\n"
                + "nombres = ?,\n"
                + "apellidos = ?,\n"
                + "telefono = ?,\n"
                + "fecha_nacimiento = ?,\n"
                + "fecha_ingreso = ?,\n"
                + "estado = ?\n"
                + "WHERE alumno.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombres);
            ps.setString(2, this.apellidos);
            ps.setInt(3, this.telefono);
            ps.setDate(4, this.fecha_nacimiento);
            ps.setDate(5, this.fecha_ingreso);
            ps.setBoolean(6, this.estado);
            ps.setInt(7, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

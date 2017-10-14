package correos.tiendaonline.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:44 PM
 */
public class Aula {

    private int id;
    private String nombre;
    private int capacidad;
    public Conexion m_Conexion;

    public Aula() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombre
     * @param capacidad
     */
    public void setAula(String nombre, int capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param capacidad
     */
    public void setAula(int id, String nombre, int capacidad) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getAula(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel aula = new DefaultTableModel();
        aula.setColumnIdentifiers(new Object[]{
            "id", "nombre", "capacidad"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "aula.id,\n"
                + "aula.nombre,\n"
                + "aula.capacidad\n"
                + "FROM aula\n"
                + "WHERE aula.id=?";
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
                aula.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return aula;
    }

    public DefaultTableModel getAulas() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel aulas = new DefaultTableModel();
        aulas.setColumnIdentifiers(new Object[]{
            "id", "nombre", "capacidad"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "aula.id,\n"
                + "aula.nombre,\n"
                + "aula.capacidad\n"
                + "FROM aula";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                aulas.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("capacidad")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return aulas;
    }

    public int registrarAula() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO aula(\n"
                + "nombre,capacidad)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setInt(2, this.capacidad);
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

    public void modificarAula() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE aula SET\n"
                + "nombre = ?,\n"
                + "capacidad = ?\n"
                + "WHERE aula.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.capacidad);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

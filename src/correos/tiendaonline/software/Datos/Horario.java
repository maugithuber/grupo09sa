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
 * @created 15-Jun-2017 9:04:47 PM
 */
public class Horario {

    private int id_grupo;
    private int id;
    private String dia;
    private String hora_inicio;
    private String hora_fin;
    private int id_aula;
    public Conexion m_Conexion;

    public Horario() {
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param id_grupo
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     */
    public void setHorario(int id_grupo, String dia, String hora_inicio, String hora_fin, int id_aula) {
        this.id_grupo = id_grupo;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_aula = id_aula;
    }

    /**
     *
     * @param id_grupo
     * @param id
     * @param dia
     * @param hora_inicio
     * @param hora_fin
     * @param id_aula
     */
    public void setHorario(int id_grupo, int id, String dia, String hora_inicio, String hora_fin, int id_aula) {
        this.id_grupo = id_grupo;
        this.id = id;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.id_aula = id_aula;
    }

    /**
     *
     * @param id
     * @param id_grupo
     * @return
     */
    public DefaultTableModel getHorario(int id, int id_grupo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel horario = new DefaultTableModel();
        horario.setColumnIdentifiers(new Object[]{
            "id_grupo", "id", "dia", "hora_inicio", "hora_fin", "id_aula"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "horario.id_grupo,\n"
                + "horario.id,\n"
                + "horario.dia,\n"
                + "horario.hora_inicio,\n"
                + "horario.hora_fin,\n"
                + "horario.id_aula\n"
                + "FROM horario\n"
                + "WHERE horario.id=? and horario.id_grupo=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id_grupo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                horario.addRow(new Object[]{
                    rs.getInt("id_grupo"),
                    rs.getInt("id"),
                    rs.getString("dia"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getInt("id_aula"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return horario;
    }

    /**
     *
     * @param id_grupo
     * @return
     */
    public DefaultTableModel getHorarios(int id_grupo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel horarios = new DefaultTableModel();
        horarios.setColumnIdentifiers(new Object[]{
            "id_grupo", "id", "dia", "hora_inicio", "hora_fin", "id_aula"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "horario.id_grupo,\n"
                + "horario.id,\n"
                + "horario.dia,\n"
                + "horario.hora_inicio,\n"
                + "horario.hora_fin,\n"
                + "horario.id_aula\n"
                + "FROM horario\n"
                + "WHERE horario.id_grupo=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_grupo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                horarios.addRow(new Object[]{
                    rs.getInt("id_grupo"),
                    rs.getInt("id"),
                    rs.getString("dia"),
                    rs.getString("hora_inicio"),
                    rs.getString("hora_fin"),
                    rs.getInt("id_aula"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return horarios;
    }

    public int registrarHorario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO horario(id_grupo,dia,hora_inicio,hora_fin,id_aula,id)\n"
                + "VALUES(?,?,?,?,?,\n"
                + "(SELECT (SELECT COUNT(*) FROM horario WHERE horario.id_grupo=?) + "
                + "(SELECT 1)))";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.id_grupo);
            ps.setString(2, this.dia);
            ps.setString(3, this.hora_inicio);
            ps.setString(4, this.hora_fin);
            ps.setInt(5, this.id_aula);
            ps.setInt(6, this.id_grupo);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }

    public void modificarHorario() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE horario SET\n"
                + "id_grupo = ?,\n"
                + "id = ?,\n"
                + "dia = ?,\n"
                + "hora_inicio = ?,\n"
                + "hora_fin = ?,\n"
                + "id_aula = ?\n"
                + "WHERE horario.id = ? AND horario.id_grupo = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.id_grupo);
            ps.setInt(2, this.id);
            ps.setString(3, this.dia);
            ps.setString(4, this.hora_inicio);
            ps.setString(5, this.hora_fin);
            ps.setInt(6, this.id_aula);
            ps.setInt(7, this.id);
            ps.setInt(8, this.id_grupo);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void eliminarHorario(int id, int id_grupo) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM horario\n"
                + "WHERE horario.id = ?\n"
                + "AND horario.id_grupo = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, id);
            ps.setInt(2, id_grupo);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

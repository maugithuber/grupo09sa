package correos.acropolis.software.Datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:49 PM
 */
public class Kardex {

    private int id;
    private String nota;
    private int mes;
    private int gestion;
    private int id_alumno;
    private int id_grupo;
    public Conexion m_Conexion;

    public Kardex() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     */
    public void setKardex(int mes, int gestion, int id_alumno, int id_grupo) {
        this.mes = mes;
        this.gestion = gestion;
        this.id_alumno = id_alumno;
        this.id_grupo = id_grupo;
    }

    /**
     *
     * @param id
     * @param mes
     * @param gestion
     * @param id_alumno
     * @param id_grupo
     */
    public void setKardex(int id, int mes, int gestion, int id_alumno, int id_grupo) {
        this.id = id;
        this.mes = mes;
        this.gestion = gestion;
        this.id_alumno = id_alumno;
        this.id_grupo = id_grupo;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getKardex(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel kardex = new DefaultTableModel();
        kardex.setColumnIdentifiers(new Object[]{
            "id", "nota", "mes", "gestion", "id_alumno", "id_grupo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "kardex.id,\n"
                + "kardex.nota,\n"
                + "kardex.mes,\n"
                + "kardex.gestion,\n"
                + "kardex.id_alumno,\n"
                + "kardex.id_grupo\n"
                + "FROM kardex\n"
                + "WHERE kardex.id=?";
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
                kardex.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nota"),
                    rs.getString("mes"),
                    rs.getString("gestion"),
                    rs.getString("id_alumno"),
                    rs.getInt("id_grupo"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kardex;
    }

    /**
     *
     * @param id_grupo
     * @param mes
     * @param gestion
     * @return
     */
    public DefaultTableModel getKardex(int id_grupo, int mes, int gestion) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel kardex = new DefaultTableModel();
        kardex.setColumnIdentifiers(new Object[]{
            "id", "nota", "mes", "gestion", "id_alumno", "id_grupo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "kardex.id,\n"
                + "kardex.nota,\n"
                + "kardex.mes,\n"
                + "kardex.gestion,\n"
                + "kardex.id_alumno,\n"
                + "kardex.id_grupo\n"
                + "FROM kardex\n"
                + "WHERE kardex.id_grupo=? "
                + "and kardex.mes=? "
                + "and kardex.gestion=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_grupo);
            ps.setInt(2, mes);
            ps.setInt(3, gestion);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                kardex.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("nota"),
                    rs.getString("mes"),
                    rs.getString("gestion"),
                    rs.getString("id_alumno"),
                    rs.getInt("id_grupo"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kardex;
    }

    /**
     *
     * @param id_alumno
     * @return
     */
    public DefaultTableModel getKardexs(int id_alumno) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel kardexs = new DefaultTableModel();
        kardexs.setColumnIdentifiers(new Object[]{
            "id", "nota", "mes", "gestion", "id_alumno", "id_grupo"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "kardex.id,\n"
                + "kardex.nota,\n"
                + "kardex.mes,\n"
                + "kardex.gestion,\n"
                + "kardex.id_alumno,\n"
                + "kardex.id_grupo\n"
                + "FROM kardex\n"
                + "WHERE kardex.id_alumno=?";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                kardexs.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("nota"),
                    rs.getString("mes"),
                    rs.getString("gestion"),
                    rs.getString("id_alumno"),
                    rs.getInt("id_grupo"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return kardexs;
    }

    public int registrarKardex() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO kardex(\n"
                + "nota,mes,gestion,id_alumno,id_grupo)\n"
                + "VALUES(?,?,?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nota);
            ps.setInt(2, this.mes);
            ps.setInt(3, this.gestion);
            ps.setInt(4, this.id_alumno);
            ps.setInt(5, this.id_grupo);
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

    /**
     *
     * @param id
     */
    public void eliminarKardex(int id) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM kardex\n"
                + "WHERE kardex.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
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
     * @param nota
     */
    public void registrarNota(int id, String nota) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE kardex SET\n"
                + "nota = ?\n"
                + "WHERE kardex.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nota);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

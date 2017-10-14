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
 * @created 15-Jun-2017 9:04:46 PM
 */
public class Grupo {
    
    private int id;
    private String nombre;
    private int id_curso;
    public Conexion m_Conexion;
    
    public Grupo() {
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombre
     * @param id_curso
     */
    public void setGrupo(String nombre, int id_curso) {
        this.nombre = nombre;
        this.id_curso = id_curso;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param id_curso
     */
    public void setGrupo(int id, String nombre, int id_curso) {
        this.id = id;
        this.nombre = nombre;
        this.id_curso = id_curso;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getGrupo(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel grupo = new DefaultTableModel();
        grupo.setColumnIdentifiers(new Object[]{
            "id", "nombre", "id_curso"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "grupo.id,\n"
                + "grupo.nombre,\n"
                + "grupo.id_curso\n"
                + "FROM grupo\n"
                + "WHERE grupo.id=?";
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
                grupo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("id_curso")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }

    /**
     *
     * @param id_curso
     * @return
     */
    public DefaultTableModel getGrupoCurso(int id_curso) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel grupo = new DefaultTableModel();
        grupo.setColumnIdentifiers(new Object[]{
            "id", "nombre", "id_curso"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "grupo.id,\n"
                + "grupo.nombre,\n"
                + "grupo.id_curso\n"
                + "FROM grupo\n"
                + "WHERE grupo.id_curso=?";
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
                grupo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("id_curso")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return grupo;
    }
    
    public DefaultTableModel getGrupos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel grupos = new DefaultTableModel();
        grupos.setColumnIdentifiers(new Object[]{
            "id", "nombre", "id_curso"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "grupo.id,\n"
                + "grupo.nombre,\n"
                + "grupo.id_curso\n"
                + "FROM grupo";
        
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                grupos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("id_curso")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return grupos;
    }
    
    public int registrarGrupo() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO grupo(\n"
                + "nombre,id_curso)\n"
                + "VALUES(?,?)";
        
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setInt(2, this.id_curso);
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
    
    public void modificarGrupo() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE grupo SET\n"
                + "nombre = ?,\n"
                + "id_curso = ?\n"
                + "WHERE grupo.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setInt(2, this.id_curso);
            ps.setInt(3, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id_grupo
     * @param id_profesor
     */
    public void asignarGrupo(int id_grupo, int id_profesor) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO imparte(\n"
                + "id_profesor,id_grupo,fecha_asignacion)\n"
                + "VALUES(?,?,?)";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_profesor);
            ps.setInt(2, id_grupo);
            ps.setDate(3, new Date(new java.util.Date().getTime()));
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

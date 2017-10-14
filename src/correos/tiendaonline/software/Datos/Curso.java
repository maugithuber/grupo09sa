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
 * @created 15-Jun-2017 9:04:45 PM
 */
public class Curso {

    private int id;
    private String nombre;
    private String descripcion;
    private boolean estado;
    public Conexion m_Conexion;

    public Curso() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void setCurso(String nombre, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @param nombre
     * @param descripcion
     * @param estado
     */
    public void setCurso(int id, String nombre, String descripcion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getCurso(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel curso = new DefaultTableModel();
        curso.setColumnIdentifiers(new Object[]{
            "id", "nombre", "descripcion", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "curso.id,\n"
                + "curso.nombre,\n"
                + "curso.descripcion,\n"
                + "curso.estado\n"
                + "FROM curso\n"
                + "WHERE curso.id=?";
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
                curso.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return curso;
    }

    public DefaultTableModel getCursos() {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cursos = new DefaultTableModel();
        cursos.setColumnIdentifiers(new Object[]{
            "id", "nombre", "descripcion", "estado"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "curso.id,\n"
                + "curso.nombre,\n"
                + "curso.descripcion,\n"
                + "curso.estado\n"
                + "FROM curso";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                cursos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getBoolean("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cursos;
    }

    /**
     *
     * @param idCurso
     * @return
     */
    public DefaultTableModel getPrerequisitos(int idCurso) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel prerequisitos = new DefaultTableModel();
        prerequisitos.setColumnIdentifiers(new Object[]{
            "id_curso", "id_prerequisito"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "prerequisitos.id_curso,\n"
                + "prerequisitos.id_prerequisito\n"
                + "FROM prerequisitos\n"
                + "WHERE prerequisitos.id_curso=?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idCurso);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                prerequisitos.addRow(new Object[]{
                    rs.getInt("id_curso"),
                    rs.getInt("id_prerequisito")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return prerequisitos;
    }

    public int registrarCurso() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO curso(\n"
                + "nombre,descripcion,estado)\n"
                + "VALUES(?,?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setBoolean(3, this.estado);
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

    public void modificarCurso() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE curso SET\n"
                + "nombre = ?,\n"
                + "descripcion = ?,\n"
                + "estado = ?\n"
                + "WHERE curso.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.descripcion);
            ps.setBoolean(3, this.estado);
            ps.setInt(4, this.id);
            int rows = ps.executeUpdate();

            // Cierro la conexion
            this.m_Conexion.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void agregarPrerequisito(int id_curso, int id_prerequisito) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO prerequisitos(\n"
                + "id_curso,id_prerequisito)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, id_curso);
            ps.setInt(2, id_prerequisito);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param id_curso
     * @param id_prerequisito
     */
    public void quitarPrerequisito(int id_curso, int id_prerequisito) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM prerequisitos\n"
                + "WHERE id_curso = ?\n"
                + "AND id_prerequisito = ?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, id_curso);
            ps.setInt(2, id_prerequisito);
            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.m_Conexion.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

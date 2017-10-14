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
 * @created 15-Jun-2017 9:04:48 PM
 */
public class Inscripcion {

    private int id;
    private Date fecha_inscripcion;
    private int id_alumno;
    public Conexion m_Conexion;

    public Inscripcion() {
        // Obteniendo la instancia de la Conexion
        this.m_Conexion = Conexion.getInstancia();
    }

    /**
     *
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void setInscripcion(Date fecha_inscripcion, int id_alumno) {
        this.fecha_inscripcion = fecha_inscripcion;
        this.id_alumno = id_alumno;
    }

    /**
     *
     * @param id
     * @param fecha_inscripcion
     * @param id_alumno
     */
    public void setInscripcion(int id, Date fecha_inscripcion, int id_alumno) {
        this.id = id;
        this.fecha_inscripcion = fecha_inscripcion;
        this.id_alumno = id_alumno;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getInscripcion(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel inscripcion = new DefaultTableModel();
        inscripcion.setColumnIdentifiers(new Object[]{
            "id", "fecha_inscripcion", "id_alumno"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "boleta_inscripcion.id,\n"
                + "boleta_inscripcion.fecha_inscripcion,\n"
                + "boleta_inscripcion.id_alumno\n"
                + "FROM boleta_inscripcion\n"
                + "WHERE boleta_inscripcion.id=?";
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
                inscripcion.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha_inscripcion"),
                    rs.getInt("id_alumno")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return inscripcion;
    }

    /**
     *
     * @param id_alumno
     * @return
     */
    public DefaultTableModel getInscripciones(int id_alumno) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel inscripciones = new DefaultTableModel();
        inscripciones.setColumnIdentifiers(new Object[]{
            "id", "fecha_inscripcion", "id_alumno"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "boleta_inscripcion.id,\n"
                + "boleta_inscripcion.fecha_inscripcion,\n"
                + "boleta_inscripcion.id_alumno\n"
                + "FROM boleta_inscripcion\n"
                + "WHERE boleta_inscripcion.id_alumno=?";
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
                inscripciones.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getDate("fecha_inscripcion"),
                    rs.getInt("id_alumno")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return inscripciones;
    }

    /**
     *
     * @param id
     * @return
     */
    public DefaultTableModel getDetalleInscripcion(int id) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle = new DefaultTableModel();
        detalle.setColumnIdentifiers(new Object[]{
            "id", "nombre"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "SELECT grupo.id AS id,\n"
                + "grupo.nombre AS nombre\n"
                + "FROM boleta_grupo,grupo\n"
                + "WHERE grupo.id = boleta_grupo.id_grupo\n"
                + "AND boleta_grupo.id_boleta = ?";
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
                detalle.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle;
    }

    /**
     *
     * @param id_alumno
     * @return
     */
    public DefaultTableModel getCursosHabilitados(int id_alumno) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel cursos = new DefaultTableModel();
        cursos.setColumnIdentifiers(new Object[]{
            "id", "nombre"
        });

        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "select curso.id, curso.nombre\n"
                + "from curso,prerequisitos\n"
                + "where (curso.id = prerequisitos.id_curso\n"
                + "and prerequisitos.id_prerequisito\n"
                + "in (select curso.id\n"
                + "from curso,grupo,kardex\n"
                + "where kardex.id_alumno = ?\n"
                + "and kardex.nota != 'R'\n"
                + "and kardex.id_grupo = grupo.id\n"
                + "and curso.id = grupo.id_curso))\n"
                + "or curso.id not in (select prerequisitos.id_curso from prerequisitos)\n"
                + "group by curso.id, curso.nombre";
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
                cursos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cursos;
    }

    public int registrarInscripcion() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO boleta_inscripcion(\n"
                + "fecha_inscripcion,id_alumno)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setDate(1, this.fecha_inscripcion);
            ps.setInt(2, this.id_alumno);
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

    public void modificarInscripcion() {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "UPDATE boleta_inscripcion SET\n"
                + "fecha_inscripcion = ?,\n"
                + "id_alumno = ?\n"
                + "WHERE boleta_inscripcion.id = ?";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, this.fecha_inscripcion);
            ps.setInt(2, this.id_alumno);
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
     * @param id
     * @param id_grupo
     */
    public void adicionarGrupos(int id, int id_grupo) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO boleta_grupo(\n"
                + "id_boleta,id_grupo)\n"
                + "VALUES(?,?)";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

    /**
     *
     * @param id
     * @param id_grupo
     */
    public void retirarGrupos(int id, int id_grupo) {
        // Abro y obtengo la conexion
        this.m_Conexion.abrirConexion();
        Connection con = this.m_Conexion.getConexion();

        // Preparo la consulta
        String sql = "DELETE FROM boleta_grupo\n"
                + "WHERE boleta_grupo.id_boleta = ?\n"
                + "AND boleta_grupo.id_grupo = ?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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

package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Curso;
import correos.tiendaonline.software.Datos.Grupo;
import correos.tiendaonline.software.Datos.Alumno;
import correos.tiendaonline.software.Datos.Conexion;
import correos.tiendaonline.software.Datos.Kardex;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * @author mauriballes
 * @version 1.0
 * @created 15-Jun-2017 9:04:42 PM
 */
public class ReporteNegocio {

    public Kardex m_Kardex;
    public Grupo m_Grupo;
    public Curso m_Curso;
    public Alumno m_Alumno;

    public ReporteNegocio() {
        this.m_Alumno = new Alumno();
        this.m_Curso = new Curso();
        this.m_Grupo = new Grupo();
        this.m_Kardex = new Kardex();
    }

    /**
     *
     * @param id_alumno
     * @return
     */
    public DefaultTableModel reporteHistorico(int id_alumno) {
        DefaultTableModel historico = new DefaultTableModel();
        historico.setColumnIdentifiers(new Object[]{
            "curso", "grupo", "nota"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT curso.nombre AS curso ,grupo.nombre AS grupo, nota \n"
                + "FROM kardex, grupo, curso \n"
                + "WHERE kardex.id_alumno = ? \n"
                + "and kardex.id_grupo = grupo.id\n"
                + "AND grupo.id_curso = curso.id";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                historico.addRow(new Object[]{
                    rs.getString("curso"),
                    rs.getString("grupo"),
                    rs.getString("nota")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return historico;
    }

    /**
     *
     * @param id_grupo
     * @param mes
     * @param gestion
     * @return
     */
    public DefaultTableModel reporteAlumnosInscritos(int id_grupo, int mes, int gestion) {
        DefaultTableModel listaAlumnos = new DefaultTableModel();
        listaAlumnos.setColumnIdentifiers(new Object[]{
            "id", "apellidos", "nombres"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT alumno.id, alumno.apellidos, alumno.nombres\n"
                + "FROM alumno, kardex, grupo\n"
                + "WHERE kardex.id_grupo = ?\n"
                + "AND alumno.id = kardex.id_alumno\n"
                + "AND kardex.mes = ?\n"
                + "AND kardex.gestion = ?\n"
                + "GROUP BY alumno.id";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_grupo);
            ps.setInt(2, mes);
            ps.setInt(3, gestion);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                listaAlumnos.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("apellidos"),
                    rs.getString("nombres")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listaAlumnos;
    }

    public DefaultTableModel reporteOfertaCursos() {
        DefaultTableModel oferta = new DefaultTableModel();
        oferta.setColumnIdentifiers(new Object[]{
            "curso", "grupo"
        });

        // Abrir Conexion
        Conexion.getInstancia().abrirConexion();
        Connection con = Conexion.getInstancia().getConexion();

        String sql = "SELECT curso.nombre AS curso, grupo.nombre AS grupo\n"
                + "FROM curso, grupo\n"
                + "WHERE grupo.id_curso = curso.id\n"
                + "AND curso.estado = TRUE";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            // Cerrar Conexion
            Conexion.getInstancia().cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                oferta.addRow(new Object[]{
                    rs.getString("curso"),
                    rs.getString("grupo"),});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return oferta;
    }

}

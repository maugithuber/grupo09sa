/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.acropolis.software;

import correos.acropolis.correo.ClienteSMTP;
import correos.acropolis.procesador.Analex;
import correos.acropolis.procesador.Cinta;
import correos.acropolis.procesador.Parser;
import correos.acropolis.procesador.Token;
import correos.acropolis.software.Negocio.AlumnoNegocio;
import correos.acropolis.software.Negocio.AulaNegocio;
import correos.acropolis.software.Negocio.CursoNegocio;
import correos.acropolis.software.Negocio.GrupoNegocio;
import correos.acropolis.software.Negocio.InscripcionNegocio;
import correos.acropolis.software.Negocio.KardexNegocio;
import correos.acropolis.software.Negocio.ProfesorNegocio;
import correos.acropolis.software.Negocio.ReporteNegocio;
import correos.acropolis.utils.Helper;
import correos.acropolis.utils.Utils;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class NuevaAcropolisMail {

    public void processMessage(String Message) {
        // Setteando Variables
        String destinatario = Utils.getDestinatario(Message);
        String content = Utils.getSubjectOrden(Message);
        Cinta cinta = new Cinta(content);
        Analex analex = new Analex(cinta);
        Parser parser = new Parser(analex);

        // Verificar Orden
        parser.Expresion();
        if (parser.errorFlag) {
            // Enviar Correo de Error
            ClienteSMTP.sendMail(destinatario, "Error de Comando",
                    "El comando introducido es incorrecto, trate consultando las ayudas con el comando HELP"
            );
            return;
        }

        // Si todo va bien, procesar el Comando
        analex.Init();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            ClienteSMTP.sendMail(destinatario, "Ayudas - Nueva Acropolis Mail", Helper.HELP_GLOBAL);
            return;
        }

        // Sino es HELP, es una funcionalidad
        switch (token.getAtributo()) {
            case Token.OBTENERALUMNOS:
                obtenerAlumnos(analex, destinatario);
                break;
            case Token.REGISTRARALUMNO:
                registrarAlumno(analex, destinatario);
                break;
            case Token.MODIFICARALUMNO:
                modificarAlumno(analex, destinatario);
                break;
            case Token.OBTENERPROFESORES:
                obtenerProfesores(analex, destinatario);
                break;
            case Token.REGISTRARPROFESOR:
                registrarProfesor(analex, destinatario);
                break;
            case Token.MODIFICARPROFESOR:
                modificarProfesor(analex, destinatario);
                break;
            case Token.OBTENERINSCRIPCIONES:
                obtenerInscripciones(analex, destinatario);
                break;
            case Token.OBTENERDETALLEINSCRIPCION:
                obtenerDetalleInscripcion(analex, destinatario);
                break;
            case Token.OBTENERCURSOSHABILITADOS:
                obtenerCursosHabilitados(analex, destinatario);
                break;
            case Token.REGISTRARINSCRIPCION:
                registrarInscripcion(analex, destinatario);
                break;
            case Token.MODIFICARINSCRIPCION:
                modificarInscripcion(analex, destinatario);
                break;
            case Token.ADICIONARGRUPOS:
                adicionarGrupos(analex, destinatario);
                break;
            case Token.RETIRARGRUPOS:
                retirarGrupos(analex, destinatario);
                break;
            case Token.OBTENERCURSOS:
                obtenerCursos(analex, destinatario);
                break;
            case Token.OBTENERPREREQUISITOS:
                obtenerPrerequisitos(analex, destinatario);
                break;
            case Token.REGISTRARCURSO:
                registrarCurso(analex, destinatario);
                break;
            case Token.MODIFICARCURSO:
                modificarCurso(analex, destinatario);
                break;
            case Token.OBTENERGRUPOS:
                obtenerGrupos(analex, destinatario);
                break;
            case Token.OBTENERHORARIOS:
                obtenerHorarios(analex, destinatario);
                break;
            case Token.REGISTRARGRUPO:
                registrarGrupo(analex, destinatario);
                break;
            case Token.MODIFICARGRUPO:
                modificarGrupo(analex, destinatario);
                break;
            case Token.ASIGNARGRUPO:
                asignarGrupo(analex, destinatario);
                break;
            case Token.REGISTRARHORARIO:
                registrarHorario(analex, destinatario);
                break;
            case Token.MODIFICARHORARIO:
                modificarHorario(analex, destinatario);
                break;
            case Token.ELIMINARHORARIO:
                eliminarHorario(analex, destinatario);
                break;
            case Token.OBTENERAULAS:
                obtenerAulas(analex, destinatario);
                break;
            case Token.REGISTRARAULA:
                registrarAula(analex, destinatario);
                break;
            case Token.MODIFICARAULA:
                modificarAula(analex, destinatario);
                break;
            case Token.OBTENERKARDEXS:
                obtenerKardexs(analex, destinatario);
                break;
            case Token.OBTENERASISTENCIAS:
                obtenerAsistencias(analex, destinatario);
                break;
            case Token.REGISTRARASISTENCIAS:
                registrarAsistencias(analex, destinatario);
                break;
            case Token.MODIFICARASISTENCIAS:
                modificarAsistencias(analex, destinatario);
                break;
            case Token.REGISTRARNOTA:
                registrarNota(analex, destinatario);
                break;
            case Token.REPORTEHISTORICO:
                reporteHistorico(analex, destinatario);
                break;
            case Token.REPORTEALUMNOSINSCRITOS:
                reporteAlumnosInscritos(analex, destinatario);
                break;
            case Token.REPORTEOFERTACURSOS:
                reporteOfertaCursos(analex, destinatario);
                break;
            case Token.AGREGARPREREQUISITO:
                agregarPrerequisito(analex, destinatario);
                break;
            case Token.QUITARPREREQUISITO:
                quitarPrerequisito(analex, destinatario);
                break;
        }
    }

    public void obtenerAlumnos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERALUMNOS);
            return;
        }

        // Sino, ejecutar el comando
        AlumnoNegocio alumnoNegocio = new AlumnoNegocio();
        String message = Utils.dibujarTabla(alumnoNegocio.obtenerAlumnos());
        ClienteSMTP.sendMail(correoDest, "Obtener Alumnos", message);
    }

    public void registrarAlumno(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        AlumnoNegocio alumnoNegocio = new AlumnoNegocio();
        analex.Avanzar();
        // Atributos
        String nombres = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int telefono = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_nacimiento = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_ingreso = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = analex.Preanalisis().getNombre() == Token.TRUE;
        alumnoNegocio.registrarAlumno(nombres, apellidos, telefono, fecha_nacimiento, fecha_ingreso, estado);
        ClienteSMTP.sendMail(correoDest, "Registrar Alumno", "Registro realizado Correctamente");

    }

    public void modificarAlumno(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        AlumnoNegocio alumnoNegocio = new AlumnoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel alumno = alumnoNegocio.obtenerAlumno(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        String nombres = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(alumno.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(alumno.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        int telefono = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(alumno.getValueAt(0, 3)));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_nacimiento = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) alumno.getValueAt(0, 4));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_ingreso = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) alumno.getValueAt(0, 5));
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = (analex.Preanalisis().getNombre() != Token.GB)
                ? (analex.Preanalisis().getNombre() == Token.TRUE)
                : Boolean.valueOf(String.valueOf(alumno.getValueAt(0, 6)));
        alumnoNegocio.modificarAlumno(id, nombres, apellidos, telefono, fecha_nacimiento, fecha_ingreso, estado);
        ClienteSMTP.sendMail(correoDest, "Modificar Alumno", "Modificacion realizada Correctamente");
    }

    public void registrarProfesor(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARPROFESOR);
            return;
        }

        // Sino, ejecutar el comando
        ProfesorNegocio profesorNegocio = new ProfesorNegocio();
        analex.Avanzar();
        // Atributos
        String nombres = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int telefono = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_postulacion = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = analex.Preanalisis().getNombre() == Token.TRUE;
        profesorNegocio.registrarProfesor(nombres, apellidos, telefono, fecha_postulacion, estado);
        ClienteSMTP.sendMail(correoDest, "Registrar Profesor", "Registro realizado Correctamente");
    }

    public void modificarProfesor(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARPROFESOR);
            return;
        }

        // Sino, ejecutar el comando
        ProfesorNegocio profesorNegocio = new ProfesorNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel profesor = profesorNegocio.obtenerProfesor(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        String nombres = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(profesor.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        String apellidos = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(profesor.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        int telefono = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(profesor.getValueAt(0, 3)));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_postulacion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) profesor.getValueAt(0, 4));
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = (analex.Preanalisis().getNombre() != Token.GB)
                ? (analex.Preanalisis().getNombre() == Token.TRUE)
                : Boolean.valueOf(String.valueOf(profesor.getValueAt(0, 5)));
        profesorNegocio.modificarProfesor(id, nombres, apellidos, telefono, fecha_postulacion, estado);
        ClienteSMTP.sendMail(correoDest, "Modificar Profesor", "Modificacion realizada Correctamente");
    }

    public void obtenerProfesores(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERPROFESORES);
            return;
        }

        // Sino, ejecutar el comando
        ProfesorNegocio profesorNegocio = new ProfesorNegocio();
        String message = Utils.dibujarTabla(profesorNegocio.obtenerProfesores());
        ClienteSMTP.sendMail(correoDest, "Obtener Profesores", message);
    }

    public void obtenerInscripciones(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERINSCRIPCIONES);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        analex.Avanzar();
        int id_alumno = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(inscripcionNegocio.obtenerInscripciones(id_alumno));
        ClienteSMTP.sendMail(correoDest, "Obtener Inscripciones", message);
    }

    public void obtenerDetalleInscripcion(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERDETALLEINSCRIPCION);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(inscripcionNegocio.obtenerDetalleInscripcion(id));
        ClienteSMTP.sendMail(correoDest, "Obtener Detalle Inscripcion", message);
    }

    public void obtenerCursosHabilitados(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERCURSOSHABILITADOS);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        analex.Avanzar();
        int id_alumno = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(inscripcionNegocio.obtenerCursosHabilitados(id_alumno));
        ClienteSMTP.sendMail(correoDest, "Obtener Cursos Habilitados", message);
    }

    public void registrarInscripcion(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARINSCRIPCION);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        analex.Avanzar();
        // Atributos
        Date fecha_inscripcion = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        int id_alumno = analex.Preanalisis().getAtributo();
        inscripcionNegocio.registrarInscripcion(fecha_inscripcion, id_alumno);
        ClienteSMTP.sendMail(correoDest, "Registrar Inscripcion", "Registro realizado Correctamente");
    }

    public void modificarInscripcion(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARINSCRIPCION);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel inscripcion = inscripcionNegocio.obtenerInscripcion(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_inscripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) inscripcion.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        int id_alumno = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(inscripcion.getValueAt(0, 2)));
        inscripcionNegocio.modificarInscripcion(id, fecha_inscripcion, id_alumno);
        ClienteSMTP.sendMail(correoDest, "Modificar Inscripcion", "Modificacion realizada Correctamente");
    }

    public void adicionarGrupos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_ADICIONARGRUPOS);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        // Atributos
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int mes = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int gestion = analex.Preanalisis().getAtributo();
        inscripcionNegocio.adicionarGrupos(id, id_grupo);
        DefaultTableModel inscripcion = inscripcionNegocio.obtenerInscripcion(id);
        int id_alumno = Integer.parseInt(String.valueOf(inscripcion.getValueAt(0, 2)));
        kardexNegocio.registrarKardex(mes, gestion, id_alumno, id_grupo);
        ClienteSMTP.sendMail(correoDest, "Adicionar Grupo", "Registro realizado Correctamente");
    }

    public void retirarGrupos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_RETIRARGRUPOS);
            return;
        }

        // Sino, ejecutar el comando
        InscripcionNegocio inscripcionNegocio = new InscripcionNegocio();
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        // Atributos
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int mes = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int gestion = analex.Preanalisis().getAtributo();
        inscripcionNegocio.retirarGrupos(id, id_grupo);
        DefaultTableModel inscripcion = inscripcionNegocio.obtenerInscripcion(id);
        int id_alumno = Integer.parseInt(String.valueOf(inscripcion.getValueAt(0, 2)));
        kardexNegocio.eliminarKardex(id_alumno, id_grupo, mes, gestion);
        ClienteSMTP.sendMail(correoDest, "Retirar Grupo", "Eliminacion realizada Correctamente");
    }

    public void obtenerCursos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERCURSOS);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        String message = Utils.dibujarTabla(cursoNegocio.obtenerCursos());
        ClienteSMTP.sendMail(correoDest, "Obtener Cursos", message);
    }

    public void obtenerPrerequisitos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERPREREQUISITOS);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(cursoNegocio.obtenerPrerequisitos(id));
        ClienteSMTP.sendMail(correoDest, "Obtener Prerequisitos", message);
    }

    public void registrarCurso(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARCURSO);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = analex.Preanalisis().getNombre() == Token.TRUE;
        cursoNegocio.registrarCurso(nombre, descripcion, estado);
        ClienteSMTP.sendMail(correoDest, "Registrar Curso", "Registro realizado Correctamente");
    }

    public void modificarCurso(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARCURSO);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel curso = cursoNegocio.obtenerCurso(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(curso.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(curso.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        boolean estado = (analex.Preanalisis().getNombre() != Token.GB)
                ? (analex.Preanalisis().getNombre() == Token.TRUE)
                : Boolean.valueOf(String.valueOf(curso.getValueAt(0, 5)));
        cursoNegocio.modificarCurso(id, nombre, descripcion, estado);
        ClienteSMTP.sendMail(correoDest, "Modificar Curso", "Modificacion realizada Correctamente");
    }

    public void obtenerGrupos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERGRUPOS);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        String message = Utils.dibujarTabla(grupoNegocio.obtenerGrupos());
        ClienteSMTP.sendMail(correoDest, "Obtener Grupos", message);
    }

    public void obtenerHorarios(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERHORARIOS);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(grupoNegocio.obtenerHorarios(id_grupo));
        ClienteSMTP.sendMail(correoDest, "Obtener Horarios", message);
    }

    public void registrarGrupo(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARGRUPO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int id_curso = analex.Preanalisis().getAtributo();
        grupoNegocio.registrarGrupo(nombre, id_curso);
        ClienteSMTP.sendMail(correoDest, "Registrar Grupo", "Registro realizado Correctamente");
    }

    public void modificarGrupo(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARGRUPO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel grupo = grupoNegocio.obtenerGrupo(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(grupo.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        int id_curso = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(grupo.getValueAt(0, 2)));
        grupoNegocio.modificarGrupo(id, nombre, id_curso);
        ClienteSMTP.sendMail(correoDest, "Modificar Grupo", "Modificacion realizada Correctamente");
    }

    public void asignarGrupo(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_ASIGNARGRUPO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_profesor = analex.Preanalisis().getAtributo();
        grupoNegocio.asignarGrupo(id_grupo, id_profesor);
        ClienteSMTP.sendMail(correoDest, "Asignar Grupo", "Profesor Asignado Correctamente");
    }

    public void registrarHorario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        // Atributos
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String dia = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String hora_inicio = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String hora_fin = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int id_aula = analex.Preanalisis().getAtributo();
        grupoNegocio.registrarHorario(id_grupo, dia, hora_inicio, hora_fin, id_aula);
        ClienteSMTP.sendMail(correoDest, "Registrar Horario", "Registro realizado Correctamente");
    }

    public void modificarHorario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel horarios = grupoNegocio.obtenerHorario(id, id_grupo);

        // Revisar los Guion bajo
        analex.Avanzar();
        analex.Avanzar();
        String dia = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(horarios.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        String hora_inicio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(horarios.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        String hora_fin = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(horarios.getValueAt(0, 4));
        analex.Avanzar();
        analex.Avanzar();
        int id_aula = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(horarios.getValueAt(0, 5)));
        grupoNegocio.modificarHorario(id_grupo, id, dia, hora_inicio, hora_fin, id_aula);
        ClienteSMTP.sendMail(correoDest, "Modificar Horario", "Modificacion realizada Correctamente");
    }

    public void eliminarHorario(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_ELIMINARHORARIO);
            return;
        }

        // Sino, ejecutar el comando
        GrupoNegocio grupoNegocio = new GrupoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        grupoNegocio.eliminarHorario(id, id_grupo);
        ClienteSMTP.sendMail(correoDest, "Eliminar Horario", "Eliminacion Completada Satisfactoriamente");
    }

    public void obtenerAulas(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERAULAS);
            return;
        }

        // Sino, ejecutar el comando
        AulaNegocio aulaNegocio = new AulaNegocio();
        String message = Utils.dibujarTabla(aulaNegocio.obtenerAulas());
        ClienteSMTP.sendMail(correoDest, "Obtener Aulas", message);
    }

    public void registrarAula(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARAULA);
            return;
        }

        // Sino, ejecutar el comando
        AulaNegocio aulaNegocio = new AulaNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int capacidad = analex.Preanalisis().getAtributo();
        aulaNegocio.registrarAula(nombre, capacidad);
        ClienteSMTP.sendMail(correoDest, "Registrar Aula", "Registro realizado Correctamente");
    }

    public void modificarAula(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARAULA);
            return;
        }

        // Sino, ejecutar el comando
        AulaNegocio aulaNegocio = new AulaNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel aula = aulaNegocio.obtenerAula(id);

        // Revisar los Guion bajo
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(aula.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        int capacidad = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(aula.getValueAt(0, 2)));
        aulaNegocio.modificarAula(id, nombre, capacidad);
        ClienteSMTP.sendMail(correoDest, "Modificar Aula", "Modificacion Realizada Correctamente");
    }

    public void obtenerKardexs(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERKARDEXS);
            return;
        }

        // Sino, ejecutar el comando
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        int id_alumno = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(kardexNegocio.obtenerKardexs(id_alumno));
        ClienteSMTP.sendMail(correoDest, "Obtener Kardexs", message);
    }

    public void registrarAsistencias(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARASISTENCIAS);
            return;
        }

        // Sino, ejecutar el comando
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        // Atributos
        String estado = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        int id_kardex = analex.Preanalisis().getAtributo();
        kardexNegocio.registrarAsistencia(estado, fecha, id_kardex);
        ClienteSMTP.sendMail(correoDest, "Registrar Asistencia", "Registro realizado Correctamente");
    }

    public void modificarAsistencias(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_MODIFICARASISTENCIAS);
            return;
        }

        // Sino, ejecutar el comando
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel kardex = kardexNegocio.obtenerAsistencia(id);

        // Revisar los Guion bajo
        analex.Avanzar();
        analex.Avanzar();
        String estado = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(kardex.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) kardex.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        int id_kardex = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(kardex.getValueAt(0, 3)));
        kardexNegocio.modificarAsistencia(id, estado, fecha, id_kardex);
        ClienteSMTP.sendMail(correoDest, "Modificar Asistencia", "Modificacion Realizada Correctamente");
    }

    public void obtenerAsistencias(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERASISTENCIAS);
            return;
        }

        // Sino, ejecutar el comando
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        int id_kardex = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(kardexNegocio.obtenerAsistencias(id_kardex));
        ClienteSMTP.sendMail(correoDest, "Obtener Asistencias", message);
    }

    public void registrarNota(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REGISTRARNOTA);
            return;
        }

        // Sino, ejecutar el comando
        KardexNegocio kardexNegocio = new KardexNegocio();
        analex.Avanzar();
        // Atributos
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String nota = Utils.quitarComillas(analex.Preanalisis().getToStr());
        kardexNegocio.registrarNota(id, nota);
        ClienteSMTP.sendMail(correoDest, "Registrar Nota", "Registro realizado Correctamente");
    }

    public void reporteHistorico(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REPORTEHISTORICO);
            return;
        }

        // Sino, ejecutar el comando
        ReporteNegocio reporteNegocio = new ReporteNegocio();
        analex.Avanzar();
        int id_alumno = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(reporteNegocio.reporteHistorico(id_alumno));
        ClienteSMTP.sendMail(correoDest, "Reporte Historico", message);
    }

    public void reporteAlumnosInscritos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REPORTEALUMNOSINSCRITOS);
            return;
        }

        // Sino, ejecutar el comando
        ReporteNegocio reporteNegocio = new ReporteNegocio();
        analex.Avanzar();
        int id_grupo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int mes = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int gestion = analex.Preanalisis().getAtributo();
        String message = Utils.dibujarTabla(reporteNegocio.reporteAlumnosInscritos(id_grupo, mes, gestion));
        ClienteSMTP.sendMail(correoDest, "Reporte Alumnos Inscritos", message);
    }

    public void reporteOfertaCursos(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_REPORTEOFERTACURSOS);
            return;
        }

        // Sino, ejecutar el comando
        ReporteNegocio reporteNegocio = new ReporteNegocio();
        String message = Utils.dibujarTabla(reporteNegocio.reporteOfertaCursos());
        ClienteSMTP.sendMail(correoDest, "Reporte Oferta de Cursos", message);
    }

    public void agregarPrerequisito(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_AGREGARPREREQUISITO);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        analex.Avanzar();
        int id_curso = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_prerequisito = analex.Preanalisis().getAtributo();
        cursoNegocio.agregarPrerequisito(id_curso, id_prerequisito);
        ClienteSMTP.sendMail(correoDest, "Agregar Prerequisito", "Se han agregado los prerequisitos de manera correcta");
    }

    public void quitarPrerequisito(Analex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_QUITARPREREQUISITO);
            return;
        }

        // Sino, ejecutar el comando
        CursoNegocio cursoNegocio = new CursoNegocio();
        analex.Avanzar();
        int id_curso = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_prerequisito = analex.Preanalisis().getAtributo();
        cursoNegocio.quitarPrerequisito(id_curso, id_prerequisito);
        ClienteSMTP.sendMail(correoDest, "Quitar Prerequisito", "Se han quitado los prerequisitos de manera correcta");
    }
}

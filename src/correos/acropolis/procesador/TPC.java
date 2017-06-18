/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.acropolis.procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author mauriballes
 */
public class TPC {

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
            "OBTENERALUMNOS",
            "REGISTRARALUMNO",
            "MODIFICARALUMNO",
            "OBTENERPROFESORES",
            "REGISTRARPROFESOR",
            "MODIFICARPROFESOR",
            "OBTENERINSCRIPCIONES",
            "OBTENERDETALLEINSCRIPCION",
            "OBTENERCURSOSHABILITADOS",
            "REGISTRARINSCRIPCION",
            "MODIFICARINSCRIPCION",
            "ADICIONARGRUPOS",
            "RETIRARGRUPOS",
            "OBTENERCURSOS",
            "OBTENERPREREQUISITOS",
            "REGISTRARCURSO",
            "MODIFICARCURSO",
            "OBTENERGRUPOS",
            "OBTENERHORARIOS",
            "REGISTRARGRUPO",
            "MODIFICARGRUPO",
            "ASIGNARGRUPO",
            "REGISTRARHORARIO",
            "MODIFICARHORARIO",
            "ELIMINARHORARIO",
            "OBTENERAULAS",
            "REGISTRARAULA",
            "MODIFICARAULA",
            "OBTENERKARDEXS",
            "OBTENERASISTENCIAS",
            "REGISTRARASISTENCIAS",
            "MODIFICARASISTENCIAS",
            "REGISTRARNOTA",
            "REPORTEHISTORICO",
            "REPORTEALUMNOSINSCRITOS",
            "REPORTEOFERTACURSOS",
            "AGREGARPREREQUISITO",
            "QUITARPREREQUISITO"
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            new Token(Token.FUNC, Token.OBTENERALUMNOS, "OBTENERALUMNOS"),
            new Token(Token.FUNC, Token.REGISTRARALUMNO, "REGISTRARALUMNO"),
            new Token(Token.FUNC, Token.MODIFICARALUMNO, "MODIFICARALUMNO"),
            new Token(Token.FUNC, Token.OBTENERPROFESORES, "OBTENERPROFESORES"),
            new Token(Token.FUNC, Token.REGISTRARPROFESOR, "REGISTRARPROFESOR"),
            new Token(Token.FUNC, Token.MODIFICARPROFESOR, "MODIFICARPROFESOR"),
            new Token(Token.FUNC, Token.OBTENERINSCRIPCIONES, "OBTENERINSCRIPCIONES"),
            new Token(Token.FUNC, Token.OBTENERDETALLEINSCRIPCION, "OBTENERDETALLEINSCRIPCION"),
            new Token(Token.FUNC, Token.OBTENERCURSOSHABILITADOS, "OBTENERCURSOSHABILITADOS"),
            new Token(Token.FUNC, Token.REGISTRARINSCRIPCION, "REGISTRARINSCRIPCION"),
            new Token(Token.FUNC, Token.MODIFICARINSCRIPCION, "MODIFICARINSCRIPCION"),
            new Token(Token.FUNC, Token.ADICIONARGRUPOS, "ADICIONARGRUPOS"),
            new Token(Token.FUNC, Token.RETIRARGRUPOS, "RETIRARGRUPOS"),
            new Token(Token.FUNC, Token.OBTENERCURSOS, "OBTENERCURSOS"),
            new Token(Token.FUNC, Token.OBTENERPREREQUISITOS, "OBTENERPREREQUISITOS"),
            new Token(Token.FUNC, Token.REGISTRARCURSO, "REGISTRARCURSO"),
            new Token(Token.FUNC, Token.MODIFICARCURSO, "MODIFICARCURSO"),
            new Token(Token.FUNC, Token.OBTENERGRUPOS, "OBTENERGRUPOS"),
            new Token(Token.FUNC, Token.OBTENERHORARIOS, "OBTENERHORARIOS"),
            new Token(Token.FUNC, Token.REGISTRARGRUPO, "REGISTRARGRUPO"),
            new Token(Token.FUNC, Token.MODIFICARGRUPO, "MODIFICARGRUPO"),
            new Token(Token.FUNC, Token.ASIGNARGRUPO, "ASIGNARGRUPO"),
            new Token(Token.FUNC, Token.REGISTRARHORARIO, "REGISTRARHORARIO"),
            new Token(Token.FUNC, Token.MODIFICARHORARIO, "MODIFICARHORARIO"),
            new Token(Token.FUNC, Token.ELIMINARHORARIO, "ELIMINARHORARIO"),
            new Token(Token.FUNC, Token.OBTENERAULAS, "OBTENERAULAS"),
            new Token(Token.FUNC, Token.REGISTRARAULA, "REGISTRARAULA"),
            new Token(Token.FUNC, Token.MODIFICARAULA, "MODIFICARAULA"),
            new Token(Token.FUNC, Token.OBTENERKARDEXS, "OBTENERKARDEXS"),
            new Token(Token.FUNC, Token.OBTENERASISTENCIAS, "OBTENERASISTENCIAS"),
            new Token(Token.FUNC, Token.REGISTRARASISTENCIAS, "REGISTRARASISTENCIAS"),
            new Token(Token.FUNC, Token.MODIFICARASISTENCIAS, "MODIFICARASISTENCIAS"),
            new Token(Token.FUNC, Token.REGISTRARNOTA, "REGISTRARNOTA"),
            new Token(Token.FUNC, Token.REPORTEHISTORICO, "REPORTEHISTORICO"),
            new Token(Token.FUNC, Token.REPORTEALUMNOSINSCRITOS, "REPORTEALUMNOSINSCRITOS"),
            new Token(Token.FUNC, Token.REPORTEOFERTACURSOS, "REPORTEOFERTACURSOS"),
            new Token(Token.FUNC, Token.AGREGARPREREQUISITO, "AGREGARPREREQUISITO"),
            new Token(Token.FUNC, Token.QUITARPREREQUISITO, "QUITARPREREQUISITO")
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}

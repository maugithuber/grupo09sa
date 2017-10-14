/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.procesador;

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
            "QUITARPREREQUISITO",
            "REGISTRARCLIENTE",
            "REGISTRARPRODUCTO"
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO")
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

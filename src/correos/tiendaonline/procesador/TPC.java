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
            "REGISTRARCLIENTE",
            "REGISTRARPRODUCTO",
            "MODIFICARPRODUCTO",
            "OBTENERPRODUCTOS",
            "OBTENERCLIENTES",
            "MODIFICARCLIENTE",
            "REGISTRARENCARGADO",
            "OBTENERENCARGADOS",
            "DATOSCLIENTE",
            "REGISTRARORDEN",
            "MODIFICARORDEN",
            "ELIMINARORDEN"
    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.REGISTRARPRODUCTO, "REGISTRARPRODUCTO"),
            new Token(Token.FUNC, Token.MODIFICARPRODUCTO, "MODIFICARPRODUCTO"),
            new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERPRODUCTOS"),
            new Token(Token.FUNC, Token.OBTENERPRODUCTOS, "OBTENERCLIENTES"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.REGISTRARENCARGADO, "REGISTRARENCARGADO"),
            new Token(Token.FUNC, Token.OBTENERENCARGADOS, "OBTENERENCARGADOS"),
            new Token(Token.FUNC, Token.DATOSCLIENTE, "DATOSCLIENTE"),
            new Token(Token.FUNC, Token.REGISTRARORDEN, "REGISTRARORDEN"),
            new Token(Token.FUNC, Token.MODIFICARORDEN, "MODIFICARORDEN"),
            new Token(Token.FUNC, Token.ELIMINARORDEN, "ELIMINARORDEN")
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

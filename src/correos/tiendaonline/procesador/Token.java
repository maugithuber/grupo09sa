/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.procesador;

/**
 *
 * @author mauriballes
 */
public class Token {

    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int FUNC = 2; // Alguna Funcion
    public static final int GB = 3; //Guion Bajo
    public static final int CA = 4; // Corchete Abierto
    public static final int CC = 5; // Corchete Cerrado
    public static final int COMA = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int TRUE = 9;
    public static final int FALSE = 10;
    public static final int HELP = 11;

    // Funciones
    public static final int OBTENERALUMNOS = 100;
    public static final int REGISTRARALUMNO = 101;
    public static final int MODIFICARALUMNO = 102;
    public static final int OBTENERPROFESORES = 103;
    public static final int REGISTRARPROFESOR = 104;
    public static final int MODIFICARPROFESOR = 105;
    public static final int OBTENERINSCRIPCIONES = 106;
    public static final int OBTENERDETALLEINSCRIPCION = 107;
    public static final int OBTENERCURSOSHABILITADOS = 108;
    public static final int REGISTRARINSCRIPCION = 109;
    public static final int MODIFICARINSCRIPCION = 110;
    public static final int ADICIONARGRUPOS = 111;
    public static final int RETIRARGRUPOS = 112;
    public static final int OBTENERCURSOS = 113;
    public static final int OBTENERPREREQUISITOS = 114;
    public static final int REGISTRARCURSO = 115;
    public static final int MODIFICARCURSO = 116;
    public static final int OBTENERGRUPOS = 117;
    public static final int OBTENERHORARIOS = 118;
    public static final int REGISTRARGRUPO = 119;
    public static final int MODIFICARGRUPO = 120;
    public static final int ASIGNARGRUPO = 121;
    public static final int REGISTRARHORARIO = 122;
    public static final int MODIFICARHORARIO = 123;
    public static final int ELIMINARHORARIO = 124;
    public static final int OBTENERAULAS = 125;
    public static final int REGISTRARAULA = 126;
    public static final int MODIFICARAULA = 127;
    public static final int OBTENERKARDEXS = 128;
    public static final int OBTENERASISTENCIAS = 129;
    public static final int REGISTRARASISTENCIAS = 130;
    public static final int MODIFICARASISTENCIAS = 131;
    public static final int REGISTRARNOTA = 132;
    public static final int REPORTEHISTORICO = 133;
    public static final int REPORTEALUMNOSINSCRITOS = 134;
    public static final int REPORTEOFERTACURSOS = 135;
    public static final int AGREGARPREREQUISITO = 136;
    public static final int QUITARPREREQUISITO = 137;
    public static final int REGISTRARCLIENTE = 138;
    public static final int REGISTRARPRODUCTO =139;

    private int nombre;
    private int atributo;
    private String toStr;

    public Token() {
    }

    public Token(int nombre, int atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getAtributo() {
        return atributo;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }

    public String getToStr() {
        return toStr;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }

}

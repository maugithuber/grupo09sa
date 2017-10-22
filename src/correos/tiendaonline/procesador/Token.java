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

//Funciones
    public static final int REGISTRARCLIENTE = 100;
    public static final int MODIFICARCLIENTE = 101;
    public static final int OBTENERCLIENTES = 102;

    public static final int REGISTRARPRODUCTO = 103;
    public static final int MODIFICARPRODUCTO = 104;
    public static final int OBTENERPRODUCTOS = 105;

    public static final int REGISTRARENCARGADO = 106;
    public static final int OBTENERENCARGADOS = 107;
    public static final int DATOSCLIENTE = 108;
    public static final int REGISTRARORDEN = 109;
    public static final int MODIFICARORDEN = 110;
    public static final int ELIMINARORDEN = 111;

    public static final int REGISTRARCATEGORIA = 112;
    public static final int MODIFICARCATEGORIA = 113;
    public static final int OBTENERCATEGORIAS = 114;

    public static final int REGISTRARPROMOCION = 115;
    public static final int MODIFICARPROMOCION = 116;
    public static final int OBTENERPROMOCIONES = 117;

    public static final int OBTENERMASVENDIDO = 118;
    public static final int VENTASPORZONAS = 119;
    public static final int OBTENERMASVENDIDOQ = 120;

    public static final int REGISTRARZONA = 121;
    public static final int MODIFICARZONA = 122;
    public static final int ELIMINARZONA = 123;
    public static final int OBTENERZONAS = 124;
   
     public static final int REGISTRARCARRITO = 125;
     public static final int AGREGARITEM = 126;
     public static final int FINCARRITO = 127;
      
     public static final int ELIMINARPRODUCTO = 128;
     public static final int ELIMINARCLIENTE = 129;
     public static final int ELIMINARENCARGADO = 130;      
     public static final int ELIMINARCATEGORIA = 131;
     public static final int ELIMINARPROMOCION = 132;      
       
     public static final int AGREGARITEMP = 133;  
     public static final int FINPROMOCION = 134;  

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

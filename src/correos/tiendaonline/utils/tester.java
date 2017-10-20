/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.utils;

import correos.tiendaonline.software.MiTiendaOnlineMail;

/**
 *
 * @author root
 */
public class tester {

    public static void main(String[] args) {
        MiTiendaOnlineMail m = new MiTiendaOnlineMail();
        //registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        // m.processMessage("subject:REGISTRARCLIENTE [\"Paul\",\"Grimaldo\",1,\"paulgrimaldo@hotmail.com\",\"123456\",1,\"72678858\",\"LaSalle\",22]");
        // m.processMessage("subject:REGISTRARPRODUCTO [1,\"CHORIZO\",\"deliciosa y cremosa\",8]"); 
        //   m.processMessage("subject:MODIFICARPRODUCTO [3,1,\"MORTADELA\",\"saladito\",13]");
        //  m.processMessage("subject:OBTENERPRODUCTOS");
        // m.processMessage("subject:MODIFICARCLIENTE [13,\"6666666\",\"siempre viva\"]");
        //  m.processMessage("subject:REGISTRARENCARGADO [\"alanoca\",\"juarez\",10,\"ala@hotmail.com\",\"66666\",2,34]");
        //m.processMessage("subject:OBTENERENCARGADOS");
        //  m.processMessage("subject:OBTENERCLIENTES");
        //   m.processMessage("subject:DATOSCLIENTE [1]");
        //registrarOrden(int id_orden, int id_cliente, int id_zona, int id_producto, int cantidad)
        // m.processMessage("subject:REGISTRARORDEN [9,14,1,7,3]");
   //     m.processMessage("subject:MODIFICARORDEN [8,14,1,7,3]");
        
        
               //    m.processMessage("subject:REGISTRARCATEGORIA [\"fiambres\",\"sabrosos\"]"); 
          //  m.processMessage("subject:MODIFICARCATEGORIA [3,\"embutidos\",\"ricos\"]"); 
            // m.processMessage("subject:OBTENERCATEGORIAS");
            
//       m.processMessage("subject:REGISTRARPROMOCION [\"todo 5x1\",\"solo los dias martes\",\"10-10-1995\",\"10-10-1996\"]"); 
           // m.processMessage("subject:MODIFICARPROMOCION [2,\"FOR FREE\",\"DE 10 A 11\",\"10-10-2000\",\"10-10-2001\"]"); 
        //    m.processMessage("subject:OBTENERPROMOCIONES");
             // m.processMessage("subject:OBTENERMASVENDIDO");
              //m.processMessage("subject:VENTASPORZONAS");
              m.processMessage("subject:OBTENERMASVENDIDOQ");
    }
}

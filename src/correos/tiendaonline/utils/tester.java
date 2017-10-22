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
        
//        
//      m.processMessage("subject:REGISTRARCATEGORIA [\"lacteos\",\"pil de calidad\"]");
//      m.processMessage("subject:REGISTRARCATEGORIA [\"embutidos\",\"en sofia se confia\"]");
//      m.processMessage("subject:REGISTRARCATEGORIA [\"limpiea\",\"para todo el hogar\"]");
      
     //m.processMessage("subject:MODIFICARCATEGORIA [3,\"limpieza\",\"para todo el y mucho mas\"]"); 
      //  m.processMessage("subject:OBTENERCATEGORIAS");
              
        //m.processMessage("subject:REGISTRARPRODUCTO [1,\"leche pil\",\"pasteurizada\",\"https://s3.amazonaws.com/mitiendaonline/lechepil.jpg\",6]"); 
        // m.processMessage("subject:REGISTRARPRODUCTO [2,\"mortadela\",\"salada\",\"https://s3.amazonaws.com/mitiendaonline/mortadela.jpg\",8]"); 
        //m.processMessage("subject:REGISTRARPRODUCTO [1,\"mantequilla\",\"salada\",\"https://s3.amazonaws.com/mitiendaonline/mantequilla.jpg\",6]"); 
   // m.processMessage("subject:MODIFICARPRODUCTO [2,2,\"mortadela\",\"jamonada\",\"https://s3.amazonaws.com/mitiendaonline/mortadela.jpg\",8]");
               //  m.processMessage("subject:OBTENERPRODUCTOS");
              
        //registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
       //  m.processMessage("subject:REGISTRARCLIENTE [\"Paul\",\"Grimaldo\",1,\"paulgrimaldo@hotmail.com\",\"123456\",1,\"72678858\",\"LaSalle\",1]");
      //   m.processMessage("subject:MODIFICARCLIENTE [1,\"6666666\",\"siempre viva\"]");
         // m.processMessage("subject:OBTENERCLIENTES");
        //  m.processMessage("subject:DATOSCLIENTE [1]");
        
        
      
      ///  m.processMessage("subject:REGISTRARCARRITO [1,1]");
    //  m.processMessage("subject:AGREGARITEM [2,1]");
    //    m.processMessage("subject:FINCARRITO");
    m.processMessage("subject:OBTENERPROMOCIONES");
     //   m.processMessage("subject:REGISTRARCARRITO [1,1]");        
     // m.processMessage("subject:REGISTRARPROMOCION [\"todo 5x1\",\"solo los dias martes\",\"10-10-1995\",\"10-10-1996\"]"); 
        // m.processMessage("subject:MODIFICARPROMOCION [2,\"FOR FREE\",\"DE 10 A 11\",\"10-10-2000\",\"10-10-2001\"]"); 
         //   m.processMessage("subject:OBTENERPROMOCIONES");
       
       
         // m.processMessage("subject:REGISTRARENCARGADO [\"alanoca\",\"juarez\",2,\"ala@hotmail.com\",\"66666\",2,2]");
      //  m.processMessage("subject:OBTENERENCARGADOS");

        //registrarOrden(int id_orden, int id_cliente, int id_zona, int id_producto, int cantidad)
        // m.processMessage("subject:REGISTRARORDEN [9,14,1,7,3]");
        //     m.processMessage("subject:MODIFICARORDEN [8,14,1,7,3]");


         
        // m.processMessage("subject:OBTENERMASVENDIDO");
        //m.processMessage("subject:VENTASPORZONAS");
        //m.processMessage("subject:OBTENERMASVENDIDOQ");
        
        //zonaNegocio.registrarZona(id_encargado, nombre, ubicacion);
    //   m.processMessage("subject:REGISTRARZONA [\"evans@hotmail.com\",\"123456\",1,\"LA RAMANDA\",\"1ER anillo\"]");
       //MODIFICARZONA
    //   m.processMessage("subject:MODIFICARZONA [\"evans@hotmail.com\",\"123456\",1,1,\"LA cuchilla\",\"1ER anillo\"]");
    }
}

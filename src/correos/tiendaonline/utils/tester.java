/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.utils;

import correos.tiendaonline.software.NuevaAcropolisMail;

/**
 *
 * @author root
 */
public class tester {
    public static void main(String[] args) {
        NuevaAcropolisMail m = new  NuevaAcropolisMail();
        //registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
       // m.processMessage("subject:REGISTRARCLIENTE [\"Paul\",\"Grimaldo\",1,\"paulgrimaldo@hotmail.com\",\"123456\",1,\"72678858\",\"LaSalle\",22]");
       m.processMessage("subject:REGISTRARPRODUCTO [1,\"leche pil\",\"muy rica\",5]");

    }
}

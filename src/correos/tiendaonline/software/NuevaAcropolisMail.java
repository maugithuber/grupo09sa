/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software;

import correos.tiendaonline.software.Negocio.AulaNegocio;
import correos.tiendaonline.software.Negocio.ClienteNegocio;
import correos.tiendaonline.software.Negocio.EncargadoNegocio;

import correos.tiendaonline.correo.ClienteSMTP;
import correos.tiendaonline.procesador.AnalizadorLex;
import correos.tiendaonline.procesador.Cinta;
import correos.tiendaonline.procesador.Parser;
import correos.tiendaonline.procesador.Token;
import correos.tiendaonline.software.Negocio.AlumnoNegocio;
import correos.tiendaonline.software.Negocio.ProductoNegocio;
import correos.tiendaonline.software.Negocio.CursoNegocio;
import correos.tiendaonline.software.Negocio.GrupoNegocio;
import correos.tiendaonline.software.Negocio.InscripcionNegocio;
import correos.tiendaonline.software.Negocio.KardexNegocio;
import correos.tiendaonline.software.Negocio.OrdenNegocio;
import correos.tiendaonline.software.Negocio.ProfesorNegocio;
import correos.tiendaonline.software.Negocio.ReporteNegocio;
import correos.tiendaonline.utils.Helper;
import correos.tiendaonline.utils.Utils;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class NuevaAcropolisMail {

    public void processMessage(String Message) {
//[12:27 PM, 10/14/2017] Paul Grimaldo: // Setteando Variables
        // System.out.println("Class Acropolis:processMessage:Message " + Message);
        String destinatario = Utils.getDestinatario(Message);
        System.out.println("Destinatario: " +destinatario);
        String content = Utils.getSubjectOrden(Message);
        System.out.println("Class Acropolis:processMessage:Content " + content);
        Cinta cinta = new Cinta(content);
        AnalizadorLex analex = new AnalizadorLex(cinta);
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
            ClienteSMTP.sendMail(destinatario, "Ayudas - mitiendaonline", Helper.HELP_GLOBAL);
            return;
        }

        // Sino es HELP, es una funcionalidad
        switch (token.getAtributo()) {
            case Token.REGISTRARCLIENTE:
                registrarCliente(analex, destinatario);
                break;
            case Token.REGISTRARPRODUCTO:
                registrarProducto(analex, destinatario);
                break;
            case Token.MODIFICARPRODUCTO:
                modificarProducto(analex, destinatario);
                break;
            case Token.OBTENERPRODUCTOS:
                obtenerProductos(analex, destinatario);
                break;
            case Token.MODIFICARCLIENTE:
                modificarCliente(analex, destinatario);
                break;
            case Token.REGISTRARENCARGADO:
                registrarEncargado(analex, destinatario);
                break;
            case Token.OBTENERENCARGADOS:
                obtenerEncargados(analex, destinatario);
                break;
            case Token.DATOSCLIENTE:
                obtenerCliente(analex, destinatario);
                break;
            case Token.REGISTRARORDEN:
                registrarOrden(analex, destinatario);
                break;
            case Token.MODIFICARORDEN:
                modificarOrden(analex, destinatario);
                break;
            case Token.ELIMINARORDEN:
                eliminarOrden(analex, destinatario);
                break;
        }
    }

    public void eliminarOrden(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        OrdenNegocio ordenNegocio = new OrdenNegocio();
        analex.Avanzar();
        int id_orden = analex.Preanalisis().getAtributo();
        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        int id_cliente = analex.Preanalisis().getAtributo();

        ordenNegocio.eliminarOrden(id_orden, id_cliente);
        ClienteSMTP.sendMail(correoDest, "Orden cancelada", "Orden cancelada correctamente");
    }

    public void modificarOrden(AnalizadorLex analex, String correoDest) {

        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        OrdenNegocio ordenNegocio = new OrdenNegocio();
        analex.Avanzar();
        int id_orden = analex.Preanalisis().getAtributo();
        DefaultTableModel orden = ordenNegocio.obtenerOrden(id_orden);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();

        int id_cliente = (int) ((analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : String.valueOf(orden.getValueAt(0, 1)));
        analex.Avanzar();
        analex.Avanzar();
        int id_zona = (int) ((analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : String.valueOf(orden.getValueAt(0, 3)));
        analex.Avanzar();
        analex.Avanzar();
        int id_producto = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int cantidad = analex.Preanalisis().getAtributo();

        ordenNegocio.modificarOrden(id_orden, id_cliente, id_zona, id_producto, cantidad);
        //System.out.println("Modificacion realizada: Apunto de Enviar Mail");
        ClienteSMTP.sendMail(correoDest, "Modificar orden","Modificacion realizada con exito");

    }

    public void registrarOrden(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        OrdenNegocio ordenNegocio = new OrdenNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        int id_orden = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_cliente = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_zona = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
//        float precio = analex.Preanalisis().getAtributo();
//        analex.Avanzar();
//        analex.Avanzar();
        int id_producto = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int cantidad = analex.Preanalisis().getAtributo();
        ordenNegocio.registrarOrden(id_orden, id_cliente, id_zona, id_producto, cantidad);
        ClienteSMTP.sendMail(correoDest, "La orden de compra ha sido generada exitosamente", "Registro realizado Correctamente");

    }

    public void obtenerCliente(AnalizadorLex analex, String correoDest) {
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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel cliente = clienteNegocio.obtenerCliente(id);

        String message = Utils.dibujarTabla(clienteNegocio.obtenerCliente(id));
        ClienteSMTP.sendMail(correoDest, "Obtener Clientes", message);
    }

    public void obtenerEncargados(AnalizadorLex analex, String correoDest) {

        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Nueva Acropolis Mail", Helper.HELP_OBTENERALUMNOS);
            return;
        }

        EncargadoNegocio encargadoNegocio = new EncargadoNegocio();
        String message = Utils.dibujarTabla(encargadoNegocio.obtenerEncargados());
        ClienteSMTP.sendMail(correoDest, "Obtener Encargados", message);
    }

    public void registrarEncargado(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        EncargadoNegocio encargadoNegocio = new EncargadoNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int id_user = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String pass = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int tipo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_persona = analex.Preanalisis().getAtributo();
        encargadoNegocio.registrarEncargado(nombre, apellido, id_user, email, pass, tipo, id_persona);
        ClienteSMTP.sendMail(correoDest, "Registrar Encargado", "Registro realizado Correctamente");

    }

    public void obtenerClients(AnalizadorLex analex, String correoDest) {
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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        String message = Utils.dibujarTabla(clienteNegocio.obtenerClientes());
        ClienteSMTP.sendMail(correoDest, "Obtener Clientes", message);
    }

    public void obtenerProductos(AnalizadorLex analex, String correoDest) {
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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        String message = Utils.dibujarTabla(clienteNegocio.obtenerClientes());
        ClienteSMTP.sendMail(correoDest, "Obtener Clientes", message);
    }

    public void modificarCliente(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel cliente = clienteNegocio.obtenerCliente(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();

        String telefono = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(cliente.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        String direccion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(cliente.getValueAt(0, 3));

        clienteNegocio.modificarCliente(id, telefono, direccion);
        ClienteSMTP.sendMail(correoDest, "Modificar producto", "Modificacion realizada Correctamente");
    }

    public void modificarProducto(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel producto = productoNegocio.obtenerProducto(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();
        int id_categoria = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                : Integer.parseInt(String.valueOf(producto.getValueAt(0, 1)));
        analex.Avanzar();
        analex.Avanzar();
        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(producto.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(producto.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        float precio = (analex.Preanalisis().getNombre() != Token.GB)
                ? analex.Preanalisis().getAtributo()
                //: Integer.parseInt(String.valueOf(producto.getValueAt(0, 4)));
                : ((float) producto.getValueAt(0, 4));
        //float precio = analex.Preanalisis().getAtributo();
        // analex.Avanzar();
        //  analex.Avanzar();
        productoNegocio.modificarProducto(id, id_categoria, nombre, descripcion, precio);
        ClienteSMTP.sendMail(correoDest, "Modificar producto", "Modificacion realizada Correctamente");
    }

    public void registrarProducto(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARALUMNO);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        int id_categoria = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        float precio = analex.Preanalisis().getAtributo();

        productoNegocio.registrarProducto(id_categoria, nombre, descripcion, precio);
        ClienteSMTP.sendMail(correoDest, "Registrar Producto", "Registro realizado Correctamente");

    }

    public void registrarCliente(AnalizadorLex analex, String correoDest) {
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
        ClienteNegocio clienteNegocio = new ClienteNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int id_user = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String pass = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int tipo = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        int id_persona = analex.Preanalisis().getAtributo();
        clienteNegocio.registrarCliente(nombre, apellido, id_user, email, pass, tipo, telefono, direccion, id_persona);
        ClienteSMTP.sendMail(correoDest, "Registrar Cliente", "Registro realizado Correctamente");

    }

    public void obtenerAlumnos(AnalizadorLex analex, String correoDest) {
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

    public void registrarAlumno(AnalizadorLex analex, String correoDest) {
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

    public void modificarAlumno(AnalizadorLex analex, String correoDest) {
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

    public void registrarProfesor(AnalizadorLex analex, String correoDest) {
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

    public void modificarProfesor(AnalizadorLex analex, String correoDest) {
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

    public void obtenerProfesores(AnalizadorLex analex, String correoDest) {
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

    public void obtenerInscripciones(AnalizadorLex analex, String correoDest) {
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

    public void obtenerDetalleInscripcion(AnalizadorLex analex, String correoDest) {
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

    public void obtenerCursosHabilitados(AnalizadorLex analex, String correoDest) {
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

    public void registrarInscripcion(AnalizadorLex analex, String correoDest) {
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

    public void modificarInscripcion(AnalizadorLex analex, String correoDest) {
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

    public void adicionarGrupos(AnalizadorLex analex, String correoDest) {
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

    public void retirarGrupos(AnalizadorLex analex, String correoDest) {
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

    public void obtenerCursos(AnalizadorLex analex, String correoDest) {
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

    public void obtenerPrerequisitos(AnalizadorLex analex, String correoDest) {
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

    public void registrarCurso(AnalizadorLex analex, String correoDest) {
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

    public void modificarCurso(AnalizadorLex analex, String correoDest) {
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

    public void obtenerGrupos(AnalizadorLex analex, String correoDest) {
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

    public void obtenerHorarios(AnalizadorLex analex, String correoDest) {
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

    public void registrarGrupo(AnalizadorLex analex, String correoDest) {
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

    public void modificarGrupo(AnalizadorLex analex, String correoDest) {
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

    public void asignarGrupo(AnalizadorLex analex, String correoDest) {
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

    public void registrarHorario(AnalizadorLex analex, String correoDest) {
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

    public void modificarHorario(AnalizadorLex analex, String correoDest) {
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

    public void eliminarHorario(AnalizadorLex analex, String correoDest) {
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

    public void obtenerAulas(AnalizadorLex analex, String correoDest) {
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

    public void registrarAula(AnalizadorLex analex, String correoDest) {
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

    public void modificarAula(AnalizadorLex analex, String correoDest) {
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

    public void obtenerKardexs(AnalizadorLex analex, String correoDest) {
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

    public void registrarAsistencias(AnalizadorLex analex, String correoDest) {
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

    public void modificarAsistencias(AnalizadorLex analex, String correoDest) {
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

    public void obtenerAsistencias(AnalizadorLex analex, String correoDest) {
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

    public void registrarNota(AnalizadorLex analex, String correoDest) {
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

    public void reporteHistorico(AnalizadorLex analex, String correoDest) {
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

    public void reporteAlumnosInscritos(AnalizadorLex analex, String correoDest) {
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

    public void reporteOfertaCursos(AnalizadorLex analex, String correoDest) {
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

    public void agregarPrerequisito(AnalizadorLex analex, String correoDest) {
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

    public void quitarPrerequisito(AnalizadorLex analex, String correoDest) {
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software;

import correos.tiendaonline.software.Negocio.ClienteNegocio;
import correos.tiendaonline.software.Negocio.EncargadoNegocio;
import correos.tiendaonline.software.Negocio.CategoriaNegocio;
import correos.tiendaonline.software.Negocio.PromocionNegocio;

import correos.tiendaonline.correo.ClienteSMTP;
import correos.tiendaonline.procesador.AnalizadorLex;
import correos.tiendaonline.procesador.Cinta;
import correos.tiendaonline.procesador.Parser;
import correos.tiendaonline.procesador.Token;
import correos.tiendaonline.software.Negocio.OrdenNegocio;
import correos.tiendaonline.software.Negocio.ProductoNegocio;
import correos.tiendaonline.software.Negocio.ReporteNegocio;
import correos.tiendaonline.software.Negocio.ZonaNegocio;
import correos.tiendaonline.utils.Helper;
import correos.tiendaonline.utils.Utils;
import java.sql.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mauriballes
 */
public class MiTiendaOnlineMail {

    public void processMessage(String Message) {
//[12:27 PM, 10/14/2017] Paul Grimaldo: // Setteando Variables
        // System.out.println("Class Acropolis:processMessage:Message " + Message);
        String destinatario = Utils.getDestinatario(Message);
        System.out.println("Destinatario: " + destinatario);
        String content = Utils.getSubjectOrden(Message);
        System.out.println("Class MiTiendaOnlineMail:processMessage:Content " + content);
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
            case Token.REGISTRARCATEGORIA:
                registrarCategoria(analex, destinatario);
                break;
            case Token.MODIFICARCATEGORIA:
                modificarCategoria(analex, destinatario);
                break;
            case Token.OBTENERCATEGORIAS:
                obtenerCategorias(analex, destinatario);
                break;

            case Token.REGISTRARPROMOCION:
                registrarPromocion(analex, destinatario);
                break;
            case Token.MODIFICARPROMOCION:
                modificarPromocion(analex, destinatario);
                break;
            case Token.OBTENERPROMOCIONES:
                obtnerPromociones(analex, destinatario);
                break;
            case Token.OBTENERMASVENDIDO:
                obtnermasvedido(analex, destinatario);
                break;
            case Token.VENTASPORZONAS:
                ventasporzonas(analex, destinatario);
                break;
            case Token.OBTENERMASVENDIDOQ:
                obtnermasvedidoq(analex, destinatario);
                break;
            case Token.REGISTRARZONA:
                registrarzona(analex, destinatario);
                break;

            case Token.MODIFICARZONA:
                modificarzona(analex, destinatario);
                break;

            case Token.ELIMINARZONA:
                elimiminarzona(analex, destinatario);
                break;
            case Token.OBTENERZONAS:
                obtenerzonas(analex, destinatario);

        }
    }

    public void obtenerzonas(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERZONAS);
            return;
        }

        ZonaNegocio productoNegocio = new ZonaNegocio();
        String message = Utils.dibujarTabla(productoNegocio.obtenerZonas());
        ClienteSMTP.sendMail(correoDest, "Obtener mas vedido", message);
    }

    public void elimiminarzona(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARZONA);
            return;
        }

        // Sino, ejecutar el comando
        ZonaNegocio zonaNegocio = new ZonaNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String pass = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        if (!zonaNegocio.validarCreador(email, pass)) {
            ClienteSMTP.sendMail(correoDest, "ELIMINAR ZONA", "No cuenta con privilegios suficiente para realizar esta accion");
            return;
        }
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();

        zonaNegocio.eliminarzona(id);
        ClienteSMTP.sendMail(correoDest, "ELIMINAR   ZONA", "ELIMINACION REALIZADA CORRECTAMENTE");
    }

    public void modificarzona(AnalizadorLex analex, String correoDest) {
// Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARZONA);
            return;
        }

        // Sino, ejecutar el comando
        ZonaNegocio zonaNegocio = new ZonaNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String pass = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        if (!zonaNegocio.validarCreador(email, pass)) {
            ClienteSMTP.sendMail(correoDest, "UPS, Ha ocurrido un error", "No cuenta con privilegios suficiente para realizar esta accion");
            return;
        }
        int id = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
        int id_encargado = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
//        float precio = analex.Preanalisis().getAtributo();
//        analex.Avanzar();
//        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String ubicacion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        zonaNegocio.modificarZona(id, id_encargado, nombre, ubicacion);
        ClienteSMTP.sendMail(correoDest, "MODIFICAR ZONA", "MODIFICACION realizado Correctamente");
    }

    public void registrarzona(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARZONA);
            return;
        }

        // Sino, ejecutar el comando
        ZonaNegocio zonaNegocio = new ZonaNegocio();
        analex.Avanzar();
        // Atributos
        // public int registrarCliente(String nombre, String apellido, int id_user, String email, String pass, int tipo, String telefono, String direccion, int id_persona)
        String email = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String pass = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        if (!zonaNegocio.validarCreador(email, pass)) {
            ClienteSMTP.sendMail(correoDest, "UPS, Ha ocurrido un error", "No cuenta con privilegios suficiente para realizar esta accion");
            return;
        }
        int id_encargado = analex.Preanalisis().getAtributo();
        analex.Avanzar();
        analex.Avanzar();
//        float precio = analex.Preanalisis().getAtributo();
//        analex.Avanzar();
//        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String ubicacion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        zonaNegocio.registrarZona(id_encargado, nombre, ubicacion);
        ClienteSMTP.sendMail(correoDest, "REGISTRAR ZONA", "Registro realizado Correctamente");

    }

    public void ventasporzonas(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_VENTASPORZONAS);
            return;
        }

        ProductoNegocio productoNegocio = new ProductoNegocio();
        String message = Utils.dibujarTabla(productoNegocio.obtenerventasporzonas());
        ClienteSMTP.sendMail(correoDest, "Obtener ventas por zonas", message);

    }

    public void obtnermasvedido(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERMASVENDIDO);
            return;
        }

        ProductoNegocio productoNegocio = new ProductoNegocio();
        String message = Utils.dibujarTabla(productoNegocio.obtenerProductomas());
        ClienteSMTP.sendMail(correoDest, "Obtener mas vedido", message);

    }

    public void obtnermasvedidoq(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERMASVENDIDOQ);
            return;
        }

        ProductoNegocio productoNegocio = new ProductoNegocio();
        String message = Utils.dibujarTabla(productoNegocio.obtenerProductomasq());
        ClienteSMTP.sendMail(correoDest, "Obtener mas vedido", message);

    }

    public void registrarPromocion(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARPROMOCION);
            return;
        }
        PromocionNegocio promocionNegocio = new PromocionNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_inicio = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_fin = Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()));

        promocionNegocio.registrarPromocion(nombre, descripcion, fecha_inicio, fecha_fin);
        ClienteSMTP.sendMail(correoDest, "Registrar Promocion", "Registro realizado Correctamente");

    }

    public void modificarPromocion(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARPROMOCION);
            return;
        }

        // Sino, ejecutar el comando
        PromocionNegocio promocionNegocio = new PromocionNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel promocion = promocionNegocio.obtenerPromocion(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();

        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(promocion.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(promocion.getValueAt(0, 2));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_inicio = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) promocion.getValueAt(0, 3));
        analex.Avanzar();
        analex.Avanzar();
        Date fecha_fin = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.convertirFechas(Utils.quitarComillas(analex.Preanalisis().getToStr()))
                : ((Date) promocion.getValueAt(0, 4));
        promocionNegocio.modificarPromocion(id, nombre, descripcion, fecha_inicio, fecha_fin);
        ClienteSMTP.sendMail(correoDest, "Modificar promocion", "Modificacion realizada Correctamente");
    }

    public void obtnerPromociones(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERPROMOCIONES);
            return;
        }

        PromocionNegocio promocionNegocio = new PromocionNegocio();
        String message = Utils.dibujarTabla(promocionNegocio.obtenerPromociones());
        ClienteSMTP.sendMail(correoDest, "Obtener Promociones", message);

    }

    public void registrarCategoria(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARCATEGORIA);
            return;
        }
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        analex.Avanzar();
        // Atributos
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = Utils.quitarComillas(analex.Preanalisis().getToStr());

        categoriaNegocio.registrarCategoria(nombre, descripcion);
        ClienteSMTP.sendMail(correoDest, "Registrar Categoria", "Registro realizado Correctamente");

    }

    public void modificarCategoria(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARCATEGORIA);
            return;
        }

        // Sino, ejecutar el comando
        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        analex.Avanzar();
        int id = analex.Preanalisis().getAtributo();
        DefaultTableModel categoria = categoriaNegocio.obtenerCategoria(id);

        // Revisar los GuionBajo
        analex.Avanzar();
        analex.Avanzar();

        String nombre = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(categoria.getValueAt(0, 1));
        analex.Avanzar();
        analex.Avanzar();
        String descripcion = (analex.Preanalisis().getNombre() != Token.GB)
                ? Utils.quitarComillas(analex.Preanalisis().getToStr())
                : String.valueOf(categoria.getValueAt(0, 2));

        categoriaNegocio.modificarCategoria(id, nombre, descripcion);
        ClienteSMTP.sendMail(correoDest, "Modificar categoria", "Modificacion realizada Correctamente");
    }

    public void obtenerCategorias(AnalizadorLex analex, String correoDest) {
        analex.Avanzar();
        Token token = analex.Preanalisis();

        if (token.getNombre() == Token.HELP) {

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERCATEGORIAS);
            return;
        }

        CategoriaNegocio categoriaNegocio = new CategoriaNegocio();
        String message = Utils.dibujarTabla(categoriaNegocio.obtenerCategorias());
        ClienteSMTP.sendMail(correoDest, "Obtener Categorias", message);

    }

    public void eliminarOrden(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_ELIMINARORDEN);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARORDEN);
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
        ClienteSMTP.sendMail(correoDest, "Modificar orden", "Modificacion realizada con exito");

    }

    public void registrarOrden(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARORDEN);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERCLIENTES);
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

            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERENCARGADOS);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARENCARGADO);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERCLIENTES);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_OBTENERPRODUCTOS);
            return;
        }

        // Sino, ejecutar el comando
        ProductoNegocio productoNegocio = new ProductoNegocio();
        String message = Utils.dibujarTabla(productoNegocio.obtenerProductos());
        ClienteSMTP.sendMail(correoDest, "Obtener productos", message);
    }

    public void modificarCliente(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARCLIENTE);
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
        ClienteSMTP.sendMail(correoDest, "Modificar cliente", "Modificacion realizada Correctamente");
    }

    public void modificarProducto(AnalizadorLex analex, String correoDest) {
        // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();

        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_MODIFICARPRODUCTO);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mitiendaonline Mail", Helper.HELP_REGISTRARPRODUCTO);
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
            ClienteSMTP.sendMail(correoDest, "Ayudas - Mi tienda Online", Helper.HELP_REGISTRARCLIENTE);
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

}

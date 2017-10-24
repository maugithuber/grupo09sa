/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.utils;

/**
 *
 * @author mauriballes
 */
public class Helper {

   
    public static final String HELP_GLOBAL = "Bienvenido!!!\n\n"
            + "A continuacion se listaran los comandos disponibles para interactuar con el sistema\n"
            + "Para acceder a la documentacion de cada uno, enviar el nombre del comando seguido de la palabra HELP\n\n"
            + "REGISTRARCLIENTE \n"
            + "REGISTRARPRODUCTO\n"
            + "MODIFICARPRODUCTO\n"
            + "OBTENERPRODUCTOS\n"
            + "OBTENERCLIENTES\n"
            + "MODIFICARCLIENTE\n"
            + "REGISTRARENCARGADO\n"
            + "OBTENERENCARGADOS\n"
            + "DATOSCLIENTE\n"
            + "REGISTRARORDEN\n"
            + "MODIFICARORDEN\n"
            + "ELIMINARORDEN\n"
            + "REGISTRARCATEGORIA\n"
            + "MODIFICARCATEGORIA\n"
            + "OBTENERCATEGORIAS\n"
            + "REGISTRARPROMOCION\n"
            + "MODIFICARPROMOCION\n"
            + "OBTENERPROMOCIONES\n"
            + "OBTENERMASVENDIDO\n"
            + "VENTASPORZONAS\n"
            + "OBTENERMASVENDIDOQ\n"
            + "REGISTRARZONA\n"
            + "MODIFICARZONA\n"
            + "ELIMINARZONA\n"
            +"REGISTRARCARRITO \n"
            +"AGREGARITEM \n"
            +"FINCARRITO \n"
            +"AGREGARITEMP \n"
            +"FINPROMOCION \n"
            + "OBTENERZONAS\n";
    
    public static final String HELP_REGISTRARCARRITO = "Registrar carrito \n"
            + "\n"
            + "El comando permite registrar un carrito de compras\n"
            + "parametros: [id_cliente, id_zona]\n";
    public static final String HELP_ELIMINARPRODUCTO = "ELIMINAR PRODUCTO \n"
            + "\n"
            + "EL COMANDO PERMITE ELMINAR UN PRODUCTO\n"
            + "parametros: [id_producto]\n";
    public static final String HELP_ELIMINARPROMOCION = "ELIMINAR PROMOCION \n"
            + "\n"
            + "EL COMANDO PERMITE ELMINAR UNA PROMOCION DEL SISTEMA\n"
            + "parametros: [id_promocion]\n";
    public static final String HELP_ELIMINARCATEGORIA= "ELIMINAR CATEGORIA \n"
            + "\n"
            + "EL COMANDO PERMITE ELMINAR UNA CATEGORIA DE PRODUCTOS DEL SISTEMA\n"
            + "parametros: [id_categoria]\n";
    public static final String HELP_ELIMINARCLIENTE= "ELIMINAR CLIENTE \n"
            + "\n"
            + "EL COMANDO PERMITE ELMINAR UN CLIENTE DEL SISTEMA\n"
            + "parametros: [id_cliente]\n";
    public static final String HELP_ELIMINARENCARGADO= "ELIMINAR ENCARGADO \n"
            + "\n"
            + "EL COMANDO PERMITE ELMINAR UN ENCARGADO  DEL SISTEMA\n"
            + "parametros: [id_encargado]\n";
    
    public static final String HELP_AGREGARITEM = "Agregar Item al carrito \n"
            + "\n"
            + "El comando permite agregar un item a su carrito de compras\n"
            + "parametros: [id_producto, cantidad]\n";
    public static final String HELP_FINCARRITO = "Fin carrito \n"
            + "\n"
            + "El comando permite cerrar su carrito de compras\n"
            + "parametros: ninguno\n";
    public static final String HELP_AGREGARITEMP = "Agregar Item a una promocion \n"
            + "\n"
            + "El comando permite agregar un item o producto a una promocion\n"
            + "parametros: [id_producto]\n";
    public static final String HELP_FINPROMOCION = "Cerrar una promocion con todos los items\n"
            + "\n"
            + "El comando permite terminar de registrar una promocion cont todos sus productos\n"
            + "parametros: ninguno\n";
    public static final String HELP_REGISTRARCLIENTE = "Registrar cliente \n"
            + "\n"
            + "El comando permite registrar un cliente en el sistema\n"
            + "parametros: [nombre,apellido,email,password,telefono,direccion]\n";
    public static final String HELP_REGISTRARPRODUCTO = "Registrar producto \n"
            + "\n"
            + "El comando permite registrar un producto en el sistema\n"
            + "parametros: [id_categoria,nombre,descripcion,precio]";
    public static final String HELP_MODIFICARPRODUCTO = "Modificar producto \n"
            + "\n"
            + "El comando permite modificar un producto en el sistema\n"
            + "parametros: [id_producto,id_categoria,nombre,descripcion,precio]";
    public static final String HELP_OBTENERPRODUCTOS = "Obtener productos \n"
            + "\n"
            + "El comando permite obtener todos los productos del sistema\n"
            + "El comando no requiere parametros";
    public static final String HELP_OBTENERCLIENTES = "Obtener clientes \n"
            + "\n"
            + "El comando permite obtener todos los clientes del sistema\n"
            + "El comando no requiere parametros";
    public static final String HELP_MODIFICARCLIENTE = "Modificar cliente \n"
            + "\n"
            + "El comando permite modificar los datos de un cliente\n"
            + "parametros: [id_cliente,telefono,direccion]"
            + "";
    public static final String HELP_REGISTRARENCARGADO = "Registrar encargado \n"
            + "\n"
            + "El comando permite registrar datos del encargado de zonas\n"
            + "parametros: [nombre,apellido,email,password]";
    public static final String HELP_OBTENERENCARGADOS = "Obtener encargados \n"
            + "\n"
            + "El comando permite recuperar todos los datos de los encargados registrados\n"
            + "El comando no requiere parametros";
    public static final String HELP_DATOSCLIENTE = "Datos cliente \n"
            + "\n"
            + "El comando permite recuperar todos los datos de un cliente especifico\n"
            + "parametros: [id_cliente]";
    public static final String HELP_REGISTRARORDEN = "Registrar orden \n"
            + "\n"
            + "El comando permite realizar un pedido de un respectivo producto\n"
            + "parametros: [id_orden,id_cliente,id_zona,id_producto,cantidad]";
    public static final String HELP_MODIFICARORDEN = "Modificar orden \n"
            + "\n"
            + "El comando permite Modificar un pedido de un respectivo producto para una orden especifica\n"
            + "parametros: [id_orden,id_cliente,id_producto,cantidad]";
    public static final String HELP_ELIMINARORDEN = "Eliminar  orden \n"
            + "\n"
            + "El comando permite eliminar un pedido de un respectivo producto de una orden especifica\n"
            + "parametros: [id_orden,id_cliente]";
    public static final String HELP_REGISTRARCATEGORIA = "Registrar categoria\n"
            + "\n"
            + "El comando permite registrar una categoria de producto para su clasificacion\n"
            + "parametros: [nombre,descripcion]";
    public static final String HELP_MODIFICARCATEGORIA = "Modificar categoria\n"
            + "\n"
            + "El comando permite modificar una categoria de producto para su clasificacion\n"
            + "parametros: [id_categoria,nombre,descripcion]";

    public static final String HELP_OBTENERCATEGORIAS = "Obtener  categorias\n"
            + "\n"
            + "El comando permite obtener todas las categorias registradas en el sistema\n"
            + "parametros: No requiere ningun parametro";

    public static final String HELP_REGISTRARPROMOCION = "Registrar promocion\n"
            + "\n"
            + "El comando permite registrar una promocion de producto en el sistema\n"
            + "parametros: [nombre,descripcion,fecha_inicio dd-mm-yyyy,fecha_fin dd-mm-yyyy]";
    public static final String HELP_MODIFICARPROMOCION = "Modificar promocion\n"
            + "\n"
            + "El comando permite modificar una promocion de producto en el sistema\n"
            + "parametros: [id_promocion,nombre,descripcion,fecha_inicio,fecha_fin]";

    public static final String HELP_OBTENERPROMOCIONES = "Obtener promociones\n"
            + "\n"
            + "El comando permite obtener todas las promociones registradas en el sistema\n"
            + "parametros: No requiere parametros";
    public static final String HELP_OBTENERMASVENDIDO = "Obtener promociones\n"
            + "\n"
            + "El comando permite obtener un reporte del producto mas vendido\n"
            + "parametros: No requiere parametros";

    public static final String HELP_VENTASPORZONAS = "Obtener ventas por zona\n"
            + "\n"
            + "El comando permite obtener un reporte de las ventas por zona\n"
            + "parametros: No requiere parametros";

    public static final String HELP_OBTENERMASVENDIDOQ = "Obtener resumen de productos\n"
            + "\n"
            + "El comando permite obtener un resumen tipo reporte de los productos mas vendidos\n"
            + "parametros: No requiere parametros";

    public static final String HELP_ELIMINARZONA = "Eliminar zona\n"
            + "\n"
            + "El comando permite eliminar una  zona de trabajo\n"
            + "parametros: [email,password,id_zona]";
    public static final String HELP_OBTENERZONAS = "obtener Zonas\n"
            + "\n"
            + "El comando permite obtener todas las zonas donde la tienda esta trabajando\n"
            + "parametros: no requiere parametros";

    public static final String HELP_REGISTRARZONA = "REGISTRAR ZONA \n"
            + "\n"
            + "El comando seleccionado registra una zona nueva de trabajo \n"
            + "Parametros: [email,pass,id_encargado,nombre,ubicacion] \n"
            + "el email y el pass es para verificar si la persona que ejecuta el comando cuenta con los privilegios";
}

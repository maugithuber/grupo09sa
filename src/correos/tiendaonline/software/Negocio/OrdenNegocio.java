/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Cliente;
import correos.tiendaonline.software.Datos.Detalle;
import correos.tiendaonline.software.Datos.Orden;
import correos.tiendaonline.software.Datos.Persona;
import correos.tiendaonline.software.Datos.Producto;
import correos.tiendaonline.software.Datos.User;
import java.sql.Date;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grupo09sa
 */
public class OrdenNegocio {

    private Orden m_orden;
    private Detalle m_detalle;

    public OrdenNegocio() {
        this.m_orden = new Orden();
        this.m_detalle = new Detalle();
    }

    public DefaultTableModel obtenerOrden(int id) {
        return this.m_orden.getOrden(id);
    }

    public DefaultTableModel obtenerClientes() {
        return this.m_orden.getOrdenes();
    }

    public int registrarOrden(int id_orden, int id_cliente, int id_zona, int id_producto, int cantidad) {
        float precio = obtenerPrecioProducto(id_producto);
        this.m_detalle.setDetalle(id_orden, id_producto, cantidad, cantidad * precio);
        this.m_orden.setOrden(id_orden, id_cliente, id_zona, cantidad * precio);
        this.m_orden.registrarOrden();
        return this.m_detalle.registrarDetalle();

    }

    protected float obtenerPrecioProducto(int id_producto) {
        ProductoNegocio producto = new ProductoNegocio();
        DefaultTableModel modelo = producto.obtenerProducto(id_producto);
        float precio = ((float) modelo.getValueAt(0, 4));
        return precio;
    }

    public void modificarOrden(int id_orden, int id_cliente, int id_zona, int id_producto, int cantidad) {
        float precio = obtenerPrecioProducto(id_producto);
        this.m_detalle.setDetalle(id_orden, id_producto, cantidad, cantidad * precio);
        this.m_orden.setOrden(id_orden, id_cliente, id_zona, cantidad * precio);
        this.m_orden.modificarOrden();
        this.m_detalle.modificarDetalle();
//        this.m_Cliente.setCliente(id, telefono, direccion);
//        this.m_Cliente.modificarCliente();
    }

    public void eliminarOrden(int id_orden, int id_cliente) {
        this.m_orden.setOrden(id_orden, id_cliente, 0, 0); // a los 2 ultimos les doy el valor de 0 por oque no los necesito
        this.m_orden.EliminarOrden();
    }
}

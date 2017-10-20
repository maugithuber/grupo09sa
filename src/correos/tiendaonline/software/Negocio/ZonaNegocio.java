/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.tiendaonline.software.Negocio;

import correos.tiendaonline.software.Datos.Categoria;
import correos.tiendaonline.software.Datos.Zona;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grupo09sa
 */
public class ZonaNegocio {

    private Zona zona;

    public ZonaNegocio() {
        this.zona = new Zona();
    }

    public DefaultTableModel obtenerZona(int id) {
        return this.zona.getZona(id);
    }

    public int registrarZona(int id_encargado, String nombre, String ubicacion) {
        this.zona.setZona(id_encargado, nombre, ubicacion);
        return this.zona.registrarzona();
    }

    public void modificarZona(int id, int id_encargado, String nombre, String ubicacion) {
        this.zona.setZona(id, id_encargado, nombre, ubicacion);
        this.zona.modificarZona();
    }

    public void eliminarzona(int id) {
        this.zona.setId(id);
        this.zona.eliminarZona();
    }
    public boolean validarCreador(String email, String pass){
        return this.zona.validarCreador(email,pass);
    }
    public DefaultTableModel obtenerZonas() {
        return this.zona.getZonas();
    }
}

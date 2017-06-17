/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correos.acropolis;

import correos.acropolis.software.Negocio.AlumnoNegocio;
import correos.acropolis.utils.Utils;

/**
 *
 * @author mauriballes
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // La clase negocio
        AlumnoNegocio alumnoNegocio = new AlumnoNegocio();
        // Llamar a los metodos

        System.out.println(alumnoNegocio.obtenerAlumnos());

//        alumnoNegocio.registrarAlumno("Alejandra", "Ballesteros", 3430670, fecha, fecha, true);
//        alumnoNegocio.modificarAlumno(1,"Alejandra", "Ballesteros", 3430670, Utils.convertirFechas("17-04-1997"), Utils.convertirFechas("17-06-2017"), true);

        System.out.println(alumnoNegocio.obtenerAlumno(1));
    }
}

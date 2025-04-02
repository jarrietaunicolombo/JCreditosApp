package com.jca2dev.Mvc.Excepciones;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class EntidadYaExisteExcepcion extends RuntimeException{

    public EntidadYaExisteExcepcion(String nombreEntidad, String id) {
        super("Error de duplicidad. Ya existe informacion sobre "+ nombreEntidad + " con ID: "+ id);
    }
    
}

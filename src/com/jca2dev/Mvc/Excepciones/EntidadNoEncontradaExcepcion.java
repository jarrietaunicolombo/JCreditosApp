package com.jca2dev.Mvc.Excepciones;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class EntidadNoEncontradaExcepcion extends RuntimeException {
    public EntidadNoEncontradaExcepcion(String nombreEntidad, String id){
        super("No existe informacion sobre "+ nombreEntidad + " con ID: "+ id);
    }
}

package com.jca2dev.Mvc.Excepciones;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class SinResultadosAlConsultarEntidadesExcepcion extends RuntimeException{

    public SinResultadosAlConsultarEntidadesExcepcion(String mensaje) {
        super(mensaje);
    }

}

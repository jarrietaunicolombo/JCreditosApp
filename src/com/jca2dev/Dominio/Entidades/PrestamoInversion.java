package com.jca2dev.Dominio.Entidades;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class PrestamoInversion {

    private double montoUtilizado;

    // Relaciones comentadas
    // private Inversion inversion;
    // private Prestamo prestamo;

    public PrestamoInversion(double montoUtilizado /*, Inversion inversion, Prestamo prestamo */) {
        this.montoUtilizado = montoUtilizado;
        // this.inversion = inversion;
        // this.prestamo = prestamo;
    }

    public double getMontoUtilizado() {
        return montoUtilizado;
    }

    public void setMontoUtilizado(double montoUtilizado) {
        this.montoUtilizado = montoUtilizado;
    }

    // public Inversion getInversion() {
    //     return inversion;
    // }

    // public void setInversion(Inversion inversion) {
    //     this.inversion = inversion;
    // }

    // public Prestamo getPrestamo() {
    //     return prestamo;
    // }

    // public void setPrestamo(Prestamo prestamo) {
    //     this.prestamo = prestamo;
    // }

    @Override
    public String toString() {
        return "PrestamoInversion\n" +
               "-----------------\n" +
               "Monto Utilizado: " + montoUtilizado + "\n";
               // + "Inversion ID: " + (inversion != null ? inversion.getId() : "null") + "\n"
               // + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n";
    }
}

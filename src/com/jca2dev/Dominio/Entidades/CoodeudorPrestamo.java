package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class CoodeudorPrestamo {

    private String relacionDeudor;
    private boolean activo;
    private LocalDateTime fechaAsignacion;

    // private Coodeudor coodeudor;
    // private Prestamo prestamo;

    public CoodeudorPrestamo(String relacionDeudor /*, Coodeudor coodeudor, Prestamo prestamo */) {
        this.relacionDeudor = relacionDeudor;
        this.activo = true;
        this.fechaAsignacion = LocalDateTime.now();
        // this.coodeudor = coodeudor;
        // this.prestamo = prestamo;
    }

    public String getRelacionDeudor() {
        return relacionDeudor;
    }

    public void setRelacionDeudor(String relacionDeudor) {
        this.relacionDeudor = relacionDeudor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    // public Coodeudor getCoodeudor() {
    //     return coodeudor;
    // }

    // public void setCoodeudor(Coodeudor coodeudor) {
    //     this.coodeudor = coodeudor;
    // }

    // public Prestamo getPrestamo() {
    //     return prestamo;
    // }

    // public void setPrestamo(Prestamo prestamo) {
    //     this.prestamo = prestamo;
    // }

    @Override
    public String toString() {
        return "CoodeudorPrestamo\n" +
               "-----------------\n" +
               "Relación con Deudor: " + relacionDeudor + "\n" +
               "Activo: " + activo + "\n" +
               "Fecha de Asignación: " + fechaAsignacion + "\n";
               // + "Coodeudor: " + (coodeudor != null ? coodeudor.getCodigo() : "null") + "\n"
               // + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n";
    }
}

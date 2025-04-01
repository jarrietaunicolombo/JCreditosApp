package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class CobradorPago {

    private LocalDateTime fechaAsignacion;
    private String observaciones;

    // Relaciones comentadas
    // private List<IntentoCobro> intentoCobro;
    // private Cobrador cobrador;
    // private Pago pago;

    public CobradorPago(/* Cobrador cobrador, Pago pago */) {
        this.fechaAsignacion = LocalDateTime.now();
        // this.cobrador = cobrador;
        // this.pago = pago;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // public List<IntentoCobro> getIntentoCobro() {
    //     return intentoCobro;
    // }

    // public void setIntentoCobro(List<IntentoCobro> intentoCobro) {
    //     this.intentoCobro = intentoCobro;
    // }

    // public Cobrador getCobrador() {
    //     return cobrador;
    // }

    // public void setCobrador(Cobrador cobrador) {
    //     this.cobrador = cobrador;
    // }

    // public Pago getPago() {
    //     return pago;
    // }

    // public void setPago(Pago pago) {
    //     this.pago = pago;
    // }

    @Override
    public String toString() {
        return "CobradorPago\n" +
               "-----------------\n" +
               "Fecha Asignación: " + fechaAsignacion + "\n" +
               "Observaciones: " + observaciones + "\n";
               // + "Cobrador: " + (cobrador != null ? cobrador.getCodigo() : "null") + "\n"
               // + "Pago ID: " + (pago != null ? pago.getId() : "null") + "\n";
    }
}

package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class IntentoDeCobro {

    private boolean exitoso;
    private LocalDateTime fechaIntento;
    private float valorPagado;
    private String observaciones;

    // Relación comentada con CobradorPago
    // private CobradorPago cobradorPago;

    public IntentoDeCobro(float valorPagado /* , CobradorPago cobradorPago */) {
        this.valorPagado = valorPagado;
        this.fechaIntento = LocalDateTime.now();
        this.exitoso = valorPagado > 0;
        // this.cobradorPago = cobradorPago;
    }

    public boolean isExitoso() {
        return exitoso;
    }

    public void setExitoso(boolean exitoso) {
        this.exitoso = exitoso;
    }

    public LocalDateTime getFechaIntento() {
        return fechaIntento;
    }

    public void setFechaIntento(LocalDateTime fechaIntento) {
        this.fechaIntento = fechaIntento;
    }

    public float getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(float valorPagado) {
        this.valorPagado = valorPagado;
        this.exitoso = valorPagado > 0;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // public CobradorPago getCobradorPago() {
    //     return cobradorPago;
    // }

    // public void setCobradorPago(CobradorPago cobradorPago) {
    //     this.cobradorPago = cobradorPago;
    // }

    @Override
    public String toString() {
        return "IntentoCobro\n" +
               "-----------------\n" +
               "Exitoso: " + exitoso + "\n" +
               "Fecha Intento: " + fechaIntento + "\n" +
               "Valor Pagado: " + valorPagado + "\n" +
               "Observaciones: " + observaciones + "\n";
               // + "CobradorPago: " + (cobradorPago != null ? cobradorPago.getId() : "null") + "\n";
    }
}

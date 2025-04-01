package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class IntentoDeCobro {

    private int id;
    private boolean exitoso;
    private LocalDateTime fechaIntento;
    private float valorPagado;
    private String observaciones;

    // Relaciones
    private CobradorPago cobradorPago;

    public IntentoDeCobro(float valorPagado, CobradorPago cobradorPago) {
        if (cobradorPago == null || cobradorPago.getId() <= 0) {
            var mensaje = "El CobradorPago no puede ser nulo o tener un Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.valorPagado = valorPagado;
        this.fechaIntento = LocalDateTime.now();
        this.exitoso = valorPagado > 0;
        this.cobradorPago = cobradorPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public CobradorPago getCobradorPago() {
        return cobradorPago;
    }

    public void setCobradorPago(CobradorPago cobradorPago) {
        if (cobradorPago == null || cobradorPago.getId() <= 0) {
            var mensaje = "El CobradorPago no puede ser nulo o tener un Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.cobradorPago = cobradorPago;
        cobradorPago.agregarIntentoDeCobro(this);
    }
    
    void sincronizarCobradorPago(CobradorPago cobradorPago){
        if (cobradorPago == null || cobradorPago.getId() <= 0) {
            var mensaje = "El CobradorPago no puede ser nulo o tener un Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.cobradorPago = cobradorPago;
 
    }

    @Override
    public String toString() {
        return "IntentoCobro\n"
                + "-----------------\n"
                + "Exitoso: " + exitoso + "\n"
                + "Fecha Intento: " + fechaIntento + "\n"
                + "Valor Pagado: " + valorPagado + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "CobradorPago: " + (cobradorPago != null ? cobradorPago.getId() : "null") + "\n";
    }
}

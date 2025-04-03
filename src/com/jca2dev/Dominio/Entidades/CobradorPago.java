package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class CobradorPago {

    private Integer id;
    private LocalDateTime fechaAsignacion;
    private String observaciones;

//     Relaciones comentadas
    private List<IntentoDeCobro> intentosCobros;
    private Cobrador cobrador;
    private Pago pago;

    // Constructor
    public CobradorPago(Cobrador cobrador, Pago pago) {
        if (cobrador == null || cobrador.getCodigo() == null
                || cobrador.getCodigo().trim().isEmpty()) {
            var mensaje = "El Cobrador no puede ser null, no tener codigo invalido";
            throw new IllegalArgumentException(mensaje);
        }
        if (pago == null || pago.getId() == null || pago.getId() <= 0) {
            var mensaje = "El Pago no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.fechaAsignacion = LocalDateTime.now();
        this.cobrador = cobrador;
        this.pago = pago;
        intentosCobros = new ArrayList<>();
    }

    // Gets y Sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<IntentoDeCobro> getIntentoCobro() {
        return intentosCobros;
    }

    public void setIntentoCobro(List<IntentoDeCobro> intentoCobro) {
        this.intentosCobros = intentoCobro;
    }

    public Cobrador getCobrador() {
        return cobrador;
    }

    public Pago getPago() {
        return pago;
    }

    // Metodos para garantizar las restrcciones de las relaciones
    public void setPago(Pago pago) {
        if (pago == null || pago.getId() == null || pago.getId() <= 0) {
            var mensaje = "El Pago no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.pago = pago;
        pago.agregarCobrador(this);
    }

    public void setCobrador(Cobrador cobrador) {
        if (cobrador == null || cobrador.getCodigo() == null
                || cobrador.getCodigo().trim().isEmpty()) {
            var mensaje = "El Cobrador no puede ser null, no tener codigo invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.cobrador = cobrador;
        cobrador.agregarPago(this);
    }

    void sincronizarCobrador(Cobrador cobrador) {
        if (cobrador == null || cobrador.getCodigo().trim().isEmpty()) {
            var mensaje = "El Cobrador no puede ser nulo, ni tener Codigo invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.cobrador = cobrador;
    }

    void sincronizarPago(Pago pago) {
        if (pago == null || pago.getId() == null || pago.getId() <= 0) {
            var mensaje = "El Cobrador no puede ser nulo, ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.pago = pago;
    }

    public void agregarIntentoDeCobro(IntentoDeCobro intentoCobro) {
        if (intentoCobro == null || intentoCobro.getId() == null || intentoCobro.getId() <= 0) {
            var mensaje = "El Intento de Cobro no puede ser null y debe tener Id valido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!intentoCobro.getCobradorPago().getId().equals(id)
                || !intentoCobro.getCobradorPago().getPago().getId()
                        .equals(this.getPago().getId())) {
            var mensaje = "El Intento de Cobro no pertenece al Pago asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.intentosCobros.stream()
                .anyMatch(intento -> intento != null
                && intento.getId().equals(intentoCobro.getId()));
        if (!existe) {
            intentosCobros.add(intentoCobro);
        }
        intentoCobro.sincronizarCobradorPago(this);
    }

    @Override
    public String toString() {
        return "CobradorPago\n"
                + "-----------------\n"
                + "Fecha Asignación: " + fechaAsignacion + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "Cobrador: " + (cobrador != null ? cobrador.getCodigo() : "null") + "\n"
                + "Pago ID: " + (pago != null ? pago.getId() : "null") + "\n";
    }

}

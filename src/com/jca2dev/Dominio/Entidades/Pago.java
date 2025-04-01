package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Pago {

    private String id;
    private LocalDateTime fechaCobro;
    private LocalDateTime fechaPago;
    private double monto;
    private double valorPagado;
    private double saldo;
    private int numeroCuota;
    private String observaciones;

    // Relaciones comentadas
    // private Prestamo prestamo;
    // private List<CobradorPago> cobradores;

    public Pago(String id, int numeroCuota, double monto /*, Prestamo prestamo */) {
        this.id = id;
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.valorPagado = 0;
        this.saldo = monto;
        this.fechaCobro = LocalDateTime.now();
        // this.prestamo = prestamo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(LocalDateTime fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getValorPagado() {
        return valorPagado;
    }

    public void setValorPagado(double valorPagado) {
        this.valorPagado = valorPagado;
        this.saldo = monto - valorPagado;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // public Prestamo getPrestamo() {
    //     return prestamo;
    // }

    // public void setPrestamo(Prestamo prestamo) {
    //     this.prestamo = prestamo;
    // }

    // public List<CobradorPago> getCobradores() {
    //     return cobradores;
    // }

    // public void setCobradores(List<CobradorPago> cobradores) {
    //     this.cobradores = cobradores;
    // }

    @Override
    public String toString() {
        return "Pago\n" +
               "-----------------\n" +
               "ID: " + id + "\n" +
               "Fecha Cobro: " + fechaCobro + "\n" +
               "Fecha Pago: " + fechaPago + "\n" +
               "Monto: " + monto + "\n" +
               "Valor Pagado: " + valorPagado + "\n" +
               "Saldo: " + saldo + "\n" +
               "Número Cuota: " + numeroCuota + "\n" +
               "Observaciones: " + observaciones + "\n";
               // + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n"
               // + "Cobradores asignados: " + (cobradores != null ? cobradores.size() : 0) + "\n";
    }
}

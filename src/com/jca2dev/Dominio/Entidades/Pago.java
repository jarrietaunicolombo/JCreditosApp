package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.EstadoDePagoEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Pago  implements Serializable{

    private Integer id;
    private LocalDateTime fechaCobro;
    private LocalDateTime fechaPago;
    private double monto;
    private double valorPagado;
    private double saldo;
    private int numeroCuota;
    private String observaciones;
    private EstadoDePagoEnum estado;

    private Prestamo prestamo;
    private List<CobradorPago> cobradores;
    // Propiedades de la clase, su valor es el mismo para todos los objetos
    private static AtomicInteger incrementoDeId = new AtomicInteger(1);

    public Pago(int numeroCuota, double monto, Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.id = incrementoDeId.getAndIncrement();
        this.numeroCuota = numeroCuota;
        this.monto = monto;
        this.valorPagado = 0;
        this.saldo = monto;
        this.fechaCobro = LocalDateTime.now();
        this.prestamo = prestamo;
        cobradores = new ArrayList<>();
    }

    public EstadoDePagoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoDePagoEnum estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
        prestamo.agregarPago(this);
    }

    public void sincronizarPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
    }

    public List<CobradorPago> getCobradores() {
        return cobradores;
    }

    public void setCobradores(List<CobradorPago> cobradores) {
        this.cobradores = cobradores;
    }

    public void agregarCobrador(CobradorPago cobrador) {
        if (cobrador == null || cobrador.getId() == null || cobrador.getId() <= 0
                || cobrador.getCobrador().getCodigo().trim().isEmpty()) {
            var mensaje = "El Cobrador no puede ser null, no tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!Objects.equals(cobrador.getPago().getPrestamo().getId(), this.getPrestamo().getId())) {
            var mensaje = "El Prestamo no correponde al Pago asignado";
            throw new IllegalArgumentException(mensaje);
        }

        if (!Objects.equals(cobrador.getPago().getId(), this.id)) {
            var mensaje = "El pago no correponde al Pago asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.cobradores.stream()
                .anyMatch(c -> c != null
                && c.getCobrador().getCodigo().equalsIgnoreCase(cobrador.getCobrador().getCodigo()));

        if (!existe) {
            this.cobradores.add(cobrador);
        }
        cobrador.sincronizarPago(this);
    }

    @Override
    public String toString() {
        return "Pago\n"
                + "-----------------\n"
                + "ID: " + id + "\n"
                + "Fecha Cobro: " + fechaCobro + "\n"
                + "Fecha Pago: " + fechaPago + "\n"
                + "Monto: " + monto + "\n"
                + "Valor Pagado: " + valorPagado + "\n"
                + "Saldo: " + saldo + "\n"
                + "Número Cuota: " + numeroCuota + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n"
                + "Cobradores asignados: " + (cobradores != null ? cobradores.size() : 0) + "\n";
    }
    
     public static void calibrarIncrementoDeId(int nexId){
        incrementoDeId.set(nexId);
    }
}

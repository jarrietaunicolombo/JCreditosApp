package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Inversion {

    private String id;
    private String nombre;
    private double monto;
    private String procedencia;
    private double saldo;
    private String observaciones;
    private LocalDateTime fechaCreacion;

    // Relaciones comentadas
    // private Prestamista prestamista;
    // private List<PrestamoInversion> prestamos;

    public Inversion(String id, String nombre, double monto) {
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.fechaCreacion = LocalDateTime.now();
        this.saldo = monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // public Prestamista getPrestamista() {
    //     return prestamista;
    // }

    // public void setPrestamista(Prestamista prestamista) {
    //     this.prestamista = prestamista;
    // }

    // public List<PrestamoInversion> getPrestamos() {
    //     return prestamos;
    // }

    // public void setPrestamos(List<PrestamoInversion> prestamos) {
    //     this.prestamos = prestamos;
    // }

    @Override
    public String toString() {
        return "Inversion\n" +
               "-----------------\n" +
               "ID: " + id + "\n" +
               "Nombre: " + nombre + "\n" +
               "Monto: " + monto + "\n" +
               "Procedencia: " + procedencia + "\n" +
               "Saldo: " + saldo + "\n" +
               "Observaciones: " + observaciones + "\n" +
               "Fecha de Creación: " + fechaCreacion + "\n";
               // + "Prestamista: " + (prestamista != null ? prestamista.getCodigo() : "null") + "\n"
               // + "Cantidad de préstamos: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

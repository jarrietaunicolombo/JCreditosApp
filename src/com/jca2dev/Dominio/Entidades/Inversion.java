package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Inversion {

    // propidades de instancia u objeto
    private int id;
    private String nombre;
    private double monto;
    private String procedencia;
    private double saldo;
    private String observaciones;
    private LocalDateTime fechaCreacion;

    //     Relaciones 
    private Prestamista prestamista;
    private List<PrestamoInversion> prestamos;

    // Constructores
    public Inversion(int id, String nombre, double monto, Prestamista prestamista) {
        if (prestamista == null || prestamista.getCodigo().trim().isEmpty()) {
            var mensaje = "El Prestamista no puede ser nulo o tener un Codigo invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.id = id;
        this.nombre = nombre;
        this.monto = monto;
        this.fechaCreacion = LocalDateTime.now();
        this.saldo = monto;
        this.prestamista = prestamista;
    }

    // Gets y Sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Prestamista getPrestamista() {
        return prestamista;
    }

    // Metodos para garantizar las restrcciones de las relaciones 
    
    public void setPrestamista(Prestamista prestamista) {
        if (prestamista == null) {
            throw new IllegalArgumentException("Prestamista no puede ser nulo");
        }
        this.prestamista = prestamista;
        prestamista.agregarInversion(this);
    }

    void sincronizarPrestamista(Prestamista prestamista) {
        if (prestamista == null) {
            throw new IllegalArgumentException("Prestamista inválido");
        }
        this.prestamista = prestamista;
    }

    public List<PrestamoInversion> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoInversion> prestamos) {
        this.prestamos = prestamos;
    }

    public void agregarPrestamo(PrestamoInversion prestamoInversion) {
        if (prestamoInversion == null || prestamoInversion.getId() <= 0
                || prestamoInversion.getPrestamo().getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (prestamoInversion.getInversion().getId() != id) {
            var mensaje = "La Inversion del Prestamo no corresponde a la Inversion asignada";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.prestamos.stream()
                .anyMatch(p -> p != null
                && p.getId() == prestamoInversion.getPrestamo().getId());
        if (!existe) {
            this.prestamos.add(prestamoInversion);
        }
        prestamoInversion.sincronizarInversion(this);
    }

    @Override
    public String toString() {
        return "Inversion\n"
                + "-----------------\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Monto: " + monto + "\n"
                + "Procedencia: " + procedencia + "\n"
                + "Saldo: " + saldo + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "Fecha de Creación: " + fechaCreacion + "\n"
                + "Prestamista: " + (prestamista != null ? prestamista.getCodigo() : "null") + "\n"
                + "Cantidad de préstamos: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

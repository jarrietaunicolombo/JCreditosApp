package com.jca2dev.Dominio.Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Prestamista extends Usuario implements Serializable {

    // propidades de instancia u objeto
    private double capital;

    // Propiedades de Relaciones 
    private List<Prestamo> prestamos;
    private List<Inversion> inversiones;

    // Constructor
    public Prestamista(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
        prestamos = new ArrayList<>();
        inversiones = new ArrayList<>();
    }

    // Gets y Sets
    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public List<Inversion> getInversiones() {
        return inversiones;
    }

    public void setInversiones(List<Inversion> inversiones) {
        this.inversiones = inversiones;
    }

    // Metodos para garantizar las restrcciones de las relaciones
    public void agregarInversion(Inversion inversion) {
        if (inversion == null || inversion.getId() <= 0
                || inversion.getMonto() <= 0) {
            var mensaje = "La Inversion no puede ser nula no tener monto invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!inversion.getPrestamista().getCodigo().equalsIgnoreCase(this.codigo)) {
            var mensaje = "El Prestamista de la Inversion no corresponde al Prestamisga asingando";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.inversiones.stream()
                .anyMatch(i -> i.getId() == inversion.getId());

        if (!existe) {
            this.inversiones.add(inversion);
        }
        inversion.sincronizarPrestamista(this);
    }

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() <= 0
                || prestamo.getMonto() <= 0) {
            var mensaje = "El Prestamo no puede ser nula no tener monto invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!prestamo.getPrestamista().getCodigo().equalsIgnoreCase(this.codigo)) {
            var mensaje = "El Prestamo al prestamista asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.prestamos.stream()
                .anyMatch(p -> p != null
                && p.getId() == prestamo.getId());

        if (!existe) {
            this.prestamos.add(prestamo);
        }
        prestamo.sincronizarPrestamista(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Prestamista that = (Prestamista) o;
        return this.codigo != null && this.codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo != null ? codigo.hashCode() : 0;
    }

    @Override
    public String toString() {
        return super.toString() + "\n"
                + "Prestamista\n"
                + "-----------------\n"
                + "Capital: " + capital + "\n"
                + "Prestamos realizados: " + (prestamos != null ? prestamos.size() : 0) + "\n"
                + "Inversiones registradas: " + (inversiones != null ? inversiones.size() : 0) + "\n";
    }
}

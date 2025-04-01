package com.jca2dev.Dominio.Entidades;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class PrestamoInversion {

    private int id;
    private double montoUtilizado;

//     Relaciones comentadas
    private Inversion inversion;
    private Prestamo prestamo;

    public PrestamoInversion(double montoUtilizado, Inversion inversion, Prestamo prestamo) {
        if (inversion == null || inversion.getId() <= 0) {
            var mensaje = "La Inversion no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (prestamo == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }

        this.montoUtilizado = montoUtilizado;
        this.inversion = inversion;
        this.prestamo = prestamo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontoUtilizado() {
        return montoUtilizado;
    }

    public void setMontoUtilizado(double montoUtilizado) {
        this.montoUtilizado = montoUtilizado;
    }

    public Inversion getInversion() {
        return inversion;
    }

    public void setInversion(Inversion inversion) {
        if (inversion == null || inversion.getId() <= 0) {
            var mensaje = "La Inversion no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.inversion = inversion;
        inversion.agregarPrestamo(this);
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
        prestamo.agreInversion(this);
    }

    void sincronizarInversion(Inversion inversion) {
        if (inversion == null || inversion.getId() <= 0) {
            var mensaje = "La Inversion no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.inversion = inversion;
    }

    void sincronizarPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
    }

    @Override
    public String toString() {
        return "PrestamoInversion\n"
                + "-----------------\n"
                + "Monto Utilizado: " + montoUtilizado + "\n"
                + "Inversion ID: " + (inversion != null ? inversion.getId() : "null") + "\n"
                + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n";
    }

}

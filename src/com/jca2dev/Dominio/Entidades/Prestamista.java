package com.jca2dev.Dominio.Entidades;

// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Prestamista extends Usuario {

    private double capital;

    // private List<Prestamo> prestamos;
    // private List<Inversion> inversiones;

    public Prestamista(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    // public List<Prestamo> getPrestamos() {
    //     return prestamos;
    // }

    // public void setPrestamos(List<Prestamo> prestamos) {
    //     this.prestamos = prestamos;
    // }

    // public List<Inversion> getInversiones() {
    //     return inversiones;
    // }

    // public void setInversiones(List<Inversion> inversiones) {
    //     this.inversiones = inversiones;
    // }

    @Override
    public String toString() {
        return "Prestamista\n" +
               "-----------------\n" +
               "Capital: " + capital + "\n";
               // + "Prestamos realizados: " + (prestamos != null ? prestamos.size() : 0) + "\n"
               // + "Inversiones registradas: " + (inversiones != null ? inversiones.size() : 0) + "\n";
    }
}

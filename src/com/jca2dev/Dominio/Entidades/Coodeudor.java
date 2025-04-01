package com.jca2dev.Dominio.Entidades;

// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Coodeudor extends Deudor {

    private boolean tieneViviendaPropia;
    private boolean tieneVehiculo;

    // private List<CoodeudorPrestamo> prestamos;

    public Coodeudor(String codigo, String primerNombre, String primerApellido, String email) {
        super(codigo, primerNombre, primerApellido, email);
    }

    public boolean isTieneViviendaPropia() {
        return tieneViviendaPropia;
    }

    public void setTieneViviendaPropia(boolean tieneViviendaPropia) {
        this.tieneViviendaPropia = tieneViviendaPropia;
    }

    public boolean isTieneVehiculo() {
        return tieneVehiculo;
    }

    public void setTieneVehiculo(boolean tieneVehiculo) {
        this.tieneVehiculo = tieneVehiculo;
    }

    // public List<CoodeudorPrestamo> getPrestamos() {
    //     return prestamos;
    // }

    // public void setPrestamos(List<CoodeudorPrestamo> prestamos) {
    //     this.prestamos = prestamos;
    // }

    @Override
    public String toString() {
        return "Coodeudor\n" +
               "-----------------\n" +
               "Tiene Vivienda Propia: " + tieneViviendaPropia + "\n" +
               "Tiene Vehículo Propio: " + tieneVehiculo + "\n" +
               super.toString();
               // + "Prestamos que respalda: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

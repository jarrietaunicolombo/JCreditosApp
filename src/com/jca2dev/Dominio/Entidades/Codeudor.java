package com.jca2dev.Dominio.Entidades;

// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Codeudor extends Deudor {

    private boolean tieneViviendaPropia;
    private boolean tieneVehiculo;

    // private List<CodeudorPrestamo> prestamos;

    public Codeudor(String codigo, String primerNombre, String primerApellido
            , String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
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

    // public List<CodeudorPrestamo> getPrestamos() {
    //     return prestamos;
    // }

    // public void setPrestamos(List<CodeudorPrestamo> prestamos) {
    //     this.prestamos = prestamos;
    // }

    @Override
    public String toString() {
        return "Codeudor\n" +
               "-----------------\n" +
               "Tiene Vivienda Propia: " + tieneViviendaPropia + "\n" +
               "Tiene Vehículo Propio: " + tieneVehiculo + "\n" +
               super.toString();
               // + "Prestamos que respalda: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

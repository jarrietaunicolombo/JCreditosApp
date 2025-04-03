package com.jca2dev.Dominio.Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Codeudor extends Deudor {

    // propidades de instancia u objeto
    private boolean tieneViviendaPropia;
    private boolean tieneVehiculo;

    //     Relaciones
    private List<CodeudorPrestamo> prestamos;

    // Constructores
    public Codeudor(String codigo, String primerNombre, String primerApellido,
            String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
        prestamos = new ArrayList<>();
    }

    //    Gets y Sets
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

    public List<CodeudorPrestamo> getCodeudorPrestamo() {
        return prestamos;
    }

    public void setCodeudorPrestamo(List<CodeudorPrestamo> prestamos) {
        this.prestamos = prestamos;
    }

    // Metodos para garantizar las restrcciones de las relaciones 
    
    public void agregarCodeudorPrestamo(CodeudorPrestamo codeudorPrestamo) {
        if (codeudorPrestamo == null || codeudorPrestamo.getId() <= 0
                || codeudorPrestamo.getPrestamo().getId() <= 0) {
            var mensaje = "El Presatamo no puede ser nulo, ni tener ID invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!codeudorPrestamo.getCodeudor().getCodigo().equalsIgnoreCase(codigo)) {
            var mensaje = "El Codeudor del prestamo no corresponde al deudor asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.prestamos.stream()
                .anyMatch(p -> p != null && 
                p.getId() == codeudorPrestamo.getId() &&
                p.getPrestamo().getId() == codeudorPrestamo.getPrestamo().getId());

        if (!existe) {
            this.prestamos.add(codeudorPrestamo);
        }
        codeudorPrestamo.sincronizarCodeudor(this); // asignación bidireccional
    }

    @Override
    public String toString() {
        return "Codeudor\n"
                + "-----------------\n"
                + "Tiene Vivienda Propia: " + tieneViviendaPropia + "\n"
                + "Tiene Vehículo Propio: " + tieneVehiculo + "\n"
                + super.toString()
                + "Prestamos que respalda: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

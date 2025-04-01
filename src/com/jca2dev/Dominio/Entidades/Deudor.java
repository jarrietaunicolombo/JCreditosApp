package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Deudor extends Usuario {

    protected String numeroIdentificacion;
    protected String tipoIdentificacion;
    protected LocalDateTime fechaExpedicion;
    protected String lugarExpedicion;
    protected String direccion;
    protected String telefono;
    protected double ingresos;
    protected String foto;
    protected String genero;
    protected LocalDateTime fechaNacimiento;
    protected int score;
    protected double capacidadDeuda;
    protected String oficio;
    protected boolean estaEmpleado;
    protected boolean esIndependiente;
    protected boolean esPensionado;

    // private List<Prestamo> prestamos;

    public Deudor(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public LocalDateTime getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(LocalDateTime fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getIngresos() {
        return ingresos;
    }

    public void setIngresos(double ingresos) {
        this.ingresos = ingresos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getCapacidadDeuda() {
        return capacidadDeuda;
    }

    public void setCapacidadDeuda(double capacidadDeuda) {
        this.capacidadDeuda = capacidadDeuda;
    }

    public String getOficio() {
        return oficio;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }

    public boolean isEstaEmpleado() {
        return estaEmpleado;
    }

    public void setEstaEmpleado(boolean estaEmpleado) {
        this.estaEmpleado = estaEmpleado;
    }

    public boolean isEsIndependiente() {
        return esIndependiente;
    }

    public void setEsIndependiente(boolean esIndependiente) {
        this.esIndependiente = esIndependiente;
    }

    public boolean isEsPensionado() {
        return esPensionado;
    }

    public void setEsPensionado(boolean esPensionado) {
        this.esPensionado = esPensionado;
    }

    // public List<Prestamo> getPrestamos() {
    //     return prestamos;
    // }

    // public void setPrestamos(List<Prestamo> prestamos) {
    //     this.prestamos = prestamos;
    // }

    @Override
    public String toString() {
        return "Deudor\n" +
               "-----------------\n" +
               "Número Identificación: " + numeroIdentificacion + "\n" +
               "Tipo Identificación: " + tipoIdentificacion + "\n" +
               "Fecha Expedición: " + fechaExpedicion + "\n" +
               "Lugar Expedición: " + lugarExpedicion + "\n" +
               "Dirección: " + direccion + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Ingresos: " + ingresos + "\n" +
               "Foto: " + foto + "\n" +
               "Género: " + genero + "\n" +
               "Fecha Nacimiento: " + fechaNacimiento + "\n" +
               "Score: " + score + "\n" +
               "Capacidad Deuda: " + capacidadDeuda + "\n" +
               "Oficio: " + oficio + "\n" +
               "Empleado: " + estaEmpleado + "\n" +
               "Independiente: " + esIndependiente + "\n" +
               "Pensionado: " + esPensionado + "\n";
               // + "Prestamos: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

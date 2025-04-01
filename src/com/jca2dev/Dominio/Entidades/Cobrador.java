package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Cobrador extends Usuario {

    private String numeroIdentificacion;
    private String tipoIdentificacion;
    private LocalDateTime fechaExpedicion;
    private String lugarExpedicion;
    private String genero;
    private String foto;
    private String direccion;
    private String telefono;
    private String vehiculo;
    private double efectividad;
    private double porcentajeGanancia;

    // Relaciones comentadas
    // private List<CobradorPago> pagos;

    public Cobrador(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getEfectividad() {
        return efectividad;
    }

    public void setEfectividad(double efectividad) {
        this.efectividad = efectividad;
    }

    public double getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(double porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    // public List<CobradorPago> getPagos() {
    //     return pagos;
    // }

    // public void setPagos(List<CobradorPago> pagos) {
    //     this.pagos = pagos;
    // }

    @Override
    public String toString() {
        return "Cobrador\n" +
               "-----------------\n" +
               "Número Identificación: " + numeroIdentificacion + "\n" +
               "Tipo Identificación: " + tipoIdentificacion + "\n" +
               "Fecha Expedición: " + fechaExpedicion + "\n" +
               "Lugar Expedición: " + lugarExpedicion + "\n" +
               "Género: " + genero + "\n" +
               "Foto: " + foto + "\n" +
               "Dirección: " + direccion + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Vehículo: " + vehiculo + "\n" +
               "Efectividad: " + efectividad + "\n" +
               "Porcentaje Ganancia: " + porcentajeGanancia + "\n";
               // + "Pagos asignados: " + (pagos != null ? pagos.size() : 0) + "\n";
    }
}

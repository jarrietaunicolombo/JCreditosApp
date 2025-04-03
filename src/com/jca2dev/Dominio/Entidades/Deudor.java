package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.GeneroEnum;
import com.jca2dev.Dominio.Constantes.TipoDeIdentificacionEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Deudor extends Usuario  implements Serializable{

    // propidades de instancia u objeto
    protected String numeroIdentificacion;
    protected TipoDeIdentificacionEnum tipoIdentificacion;
    protected LocalDateTime fechaExpedicion;
    protected String lugarExpedicion;
    protected String direccion;
    protected String telefono;
    protected double ingresos;
    protected String foto;
    protected GeneroEnum genero;
    protected LocalDateTime fechaNacimiento;
    protected int score;
    protected double capacidadDeuda;
    protected String oficio;
    protected boolean estaEmpleado;
    protected boolean esIndependiente;
    protected boolean esPensionado;

    //     Relaciones
    private List<Prestamo> prestamos;

    // Constructores
    public Deudor(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
        super(codigo, primerNombre, primerApellido, email, rol);
        prestamos = new ArrayList<>();
    }

    //    Gets y Sets
    public TipoDeIdentificacionEnum getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoDeIdentificacionEnum tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
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

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
    // Metodos para garantizar las restrcciones de las relaciones 

    public void agregarPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puedo ser nulo, ni tener Id invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (!prestamo.getDeudor().getCodigo().equalsIgnoreCase(codigo)) {
            var mensaje = "El Deudor del Prestamo no corresponde al Deudor asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.prestamos.stream()
                .anyMatch(p -> p != null
                && p.getId() == prestamo.getId());

        if (!existe) {
            this.prestamos.add(prestamo);
        }

        prestamo.sincronizarDeudor(this);
    }

    @Override
    public String toString() {
        return "Deudor\n"
                + "-----------------\n"
                + "Número Identificación: " + numeroIdentificacion + "\n"
                + "Tipo Identificación: " + tipoIdentificacion + "\n"
                + "Fecha Expedición: " + fechaExpedicion + "\n"
                + "Lugar Expedición: " + lugarExpedicion + "\n"
                + "Dirección: " + direccion + "\n"
                + "Teléfono: " + telefono + "\n"
                + "Ingresos: " + ingresos + "\n"
                + "Foto: " + foto + "\n"
                + "Género: " + genero + "\n"
                + "Fecha Nacimiento: " + fechaNacimiento + "\n"
                + "Score: " + score + "\n"
                + "Capacidad Deuda: " + capacidadDeuda + "\n"
                + "Oficio: " + oficio + "\n"
                + "Empleado: " + estaEmpleado + "\n"
                + "Independiente: " + esIndependiente + "\n"
                + "Pensionado: " + esPensionado + "\n"
                + "Prestamos: " + (prestamos != null ? prestamos.size() : 0) + "\n";
    }
}

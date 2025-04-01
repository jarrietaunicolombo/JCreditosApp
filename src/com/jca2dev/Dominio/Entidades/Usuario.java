package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */

public class Usuario {

    protected String codigo;
    protected String password;
    protected LocalDateTime fechaCreacion;
    protected String estado;
    protected String primerNombre;
    protected String segundoNombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected String email;
    // private Rol rol;

    public Usuario(String codigo, String primerNombre, String primerApellido
                    , String email /*, Rol rol */) {
        this.codigo = codigo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.email = email;
        // this.rol = rol;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "ACTIVO";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // public Rol getRol() {
    //     return rol;
    // }

    // public void setRol(Rol rol) {
    //     this.rol = rol;
    // }

    @Override
    public String toString() {
        return "Usuario\n" +
               "--------------------------\n" +
               "Codigo           : " + codigo + "\n" +
               "Primer Nombre    : " + primerNombre + "\n" +
               "Segundo Nombre   : " + segundoNombre + "\n" +
               "Primer Apellido  : " + primerApellido + "\n" +
               "Segundo Apellido : " + segundoApellido + "\n" +
               "Email            : " + email + "\n" +
               "Fecha Creación   : " + fechaCreacion + "\n" +
               "Estado           : " + estado + "\n";
    }
}    

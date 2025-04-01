package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

public class Rol {

    private String id;
    private String nombre;
    private String descripcion;
    private String icono;
    private String estado;
    private LocalDateTime fechaCreacion;
    // private List<Usuario> usuarios;

    public Rol(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "ACTIVO";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // public List<Usuario> getUsuarios() {
    //     return usuarios;
    // }

    // public void setUsuarios(List<Usuario> usuarios) {
    //     this.usuarios = usuarios;
    // }

    @Override
    public String toString() {
        return "Rol\n" +
               "-------------------------\n" +
               "ID: " + id + "\n" +
               "Nombre: " + nombre + "\n" +
               "Descripción: " + descripcion + "\n" +
               "Icono: " + icono + "\n" +
               "Estado: " + estado + "\n" +
               "Fecha de creación: " + fechaCreacion + "\n";
    }
} 

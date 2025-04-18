package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.EstadoDeRolEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Rol  implements Serializable{

    // Propiedades de instancia u objeto
    private Integer id;
    private String nombre;
    private String descripcion;
    private String icono;
    private EstadoDeRolEnum estado;

    //     Relaciones
    private LocalDateTime fechaCreacion;
    private List<Usuario> usuarios;
// Propiedades de la clase, su valor es el mismo para todos los objetos
    private static AtomicInteger incrementoDeId = new AtomicInteger(1);

    // Constructores
    public Rol(String nombre) {
        this.id = incrementoDeId.getAndIncrement();
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoDeRolEnum.ACTIVO;
        usuarios = new ArrayList<>();
    }

    //    Gets y Sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public EstadoDeRolEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeRolEnum estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    // Metodos para garantizar las restrcciones de las relaciones 
    public void agregarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getCodigo() == null || usuario.getCodigo()
                .trim().isEmpty()) {
            var mensaje = "El Usuario no puede ser nulo ni tener codigo invalido";
            throw new IllegalArgumentException(mensaje);
        }

        if (usuario.getRol().getId() != this.id) {
            var mensaje = "El Usuario no puede al Rol asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.usuarios.stream()
                .anyMatch(u -> u.getCodigo().equals(usuario.getCodigo()));
        if (!existe) {
            this.usuarios.add(usuario);
        }

        usuario.sincronizarRol(this);
    }

    @Override
    public String toString() {
        return "Rol\n"
                + "-------------------------\n"
                + "ID: " + id + "\n"
                + "Nombre: " + nombre + "\n"
                + "Descripción: " + descripcion + "\n"
                + "Icono: " + icono + "\n"
                + "Estado: " + estado + "\n"
                + "Fecha de creación: " + fechaCreacion + "\n"
                + "Usuarios: " + usuarios.size() + "\n";
    }
    
     public static void calibrarIncrementoDeId(int nexId){
        incrementoDeId.set(nexId);
    }
}

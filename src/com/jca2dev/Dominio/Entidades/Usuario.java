package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.EstadoDeUsuarioEnum;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Usuario  implements Serializable{

    // Propiedades de instancia u objeto
    protected String codigo;
    protected String password;
    protected LocalDateTime fechaCreacion;
    protected EstadoDeUsuarioEnum estado;
    protected String primerNombre;
    protected String segundoNombre;
    protected String primerApellido;
    protected String segundoApellido;
    protected String email;

    //     Relaciones
    protected Rol rol;

    // Constructores
    public Usuario(String codigo, String primerNombre, String primerApellido, String email, Rol rol) {
        if (rol == null || rol.getId() == null || rol.getId() <= 0 || rol.getNombre().trim().isEmpty()) {
            var menesaje = "Al crear un usuario se requiere un rol valido";
            throw new IllegalArgumentException(menesaje);
        }
        this.codigo = codigo;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.email = email;
        this.rol = rol;
        this.fechaCreacion = LocalDateTime.now();
        this.estado = EstadoDeUsuarioEnum.PENDIENTE;
    }

    // Gets y Sets
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

    public EstadoDeUsuarioEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeUsuarioEnum estado) {
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

    public Rol getRol() {
        return rol;
    }

    // Metodos para garantizar las restrcciones de las relaciones 
    public void setRol(Rol rol) {
        if (rol == null || rol.getId() == null || rol.getId() <= 0 || rol.getNombre().trim().isEmpty()) {
            var menesaje = "Al crear un usuario se requiere un rol valido";
            throw new IllegalArgumentException(menesaje);
        }
        this.rol = rol;
        rol.agregarUsuario(this);
    }

    void sincronizarRol(Rol aThis) {
        if (rol == null || rol.getId() == null || rol.getId() <= 0 || rol.getNombre().trim().isEmpty()) {
            var menesaje = "Al crear un usuario se requiere un rol valido";
            throw new IllegalArgumentException(menesaje);
        }
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario\n"
                + "--------------------------\n"
                + "Codigo           : " + codigo + "\n"
                + "Primer Nombre    : " + primerNombre + "\n"
                + "Segundo Nombre   : " + segundoNombre + "\n"
                + "Primer Apellido  : " + primerApellido + "\n"
                + "Segundo Apellido : " + segundoApellido + "\n"
                + "Email            : " + email + "\n"
                + "Fecha Creación   : " + fechaCreacion + "\n"
                + "Estado           : " + estado + "\n"
                + "Rol              : " + rol.getNombre() + "\n";
    }

}

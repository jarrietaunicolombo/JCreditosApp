package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Rol;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IRolRepositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RolRepositorio implements IRolRepositorio {

    @Override
    public void guardar(Rol rol) {
        if (rol == null || rol.getId() == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo y debe tener un ID válido.");
        }

        boolean existe = BaseDeDatosEnMemoria.getRolesEnBd().stream()
                .anyMatch(r -> r.getId().equals(rol.getId()));

        if (existe) {
            throw new EntidadYaExisteExcepcion("Rol", rol.getId().toString());
        }

        BaseDeDatosEnMemoria.getRolesEnBd().add(rol);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Rol buscarPorId(Integer id) {
         if (id == null || id.intValue() <= 0) {
            throw new IllegalArgumentException("Id incorrecto");
        }
        return BaseDeDatosEnMemoria.getRolesEnBd().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new EntidadNoEncontradaExcepcion(Rol.class.getSimpleName(), id.toString()));
    }

//    @Override
    public Rol buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es requerido");
        }

        return BaseDeDatosEnMemoria.getRolesEnBd().stream()
                .filter(r -> r.getNombre().toLowerCase().equals(nombre.toLowerCase()))
                .findFirst()
                .orElseThrow(()
                        -> new EntidadNoEncontradaExcepcion(Rol.class.getSimpleName(), nombre));

    }

    @Override
    public void editar(Rol rol) {
        Rol existente = this.buscarPorId(rol.getId());
        existente.setNombre(rol.getNombre());
        existente.setDescripcion(rol.getDescripcion());
        existente.setIcono(rol.getIcono());
        existente.setEstado(rol.getEstado());
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(Integer id) {
        Rol existente = buscarPorId(id);
        BaseDeDatosEnMemoria.getRolesEnBd().remove(existente);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Rol> obtenerTodos() {
        var resultado = BaseDeDatosEnMemoria.getRolesEnBd();
        if (resultado.isEmpty()) {
            var mensaje = "Actualmente no existen roles almacenados en el sistema";
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }
        return resultado;
    }
}

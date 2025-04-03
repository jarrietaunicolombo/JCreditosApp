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
        return BaseDeDatosEnMemoria.getRolesEnBd().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() 
                -> new EntidadNoEncontradaExcepcion(Rol.class.getSimpleName(), id.toString()));
    }

    @Override
    public List<Rol> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) return new ArrayList<>();

        var resultado = BaseDeDatosEnMemoria.getRolesEnBd().stream()
                .filter(r -> r.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());
        if (resultado.isEmpty()) {
            var mensaje = "No existen Roles con el nombre "+nombre;
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }
        return resultado;
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
        if(resultado.isEmpty()){
            var mensaje = "Actualmente no existen roles almacenados en el sistema";
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }
        return resultado;
    }
}

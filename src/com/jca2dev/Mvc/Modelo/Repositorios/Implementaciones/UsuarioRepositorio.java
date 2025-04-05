package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Usuario;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IUsuarioRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    @Override
    public void guardar(Usuario usuario) {
        if (usuario == null || usuario.getCodigo() == null || usuario.getCodigo().isBlank()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo ni tener código vacío.");
        }

        // validamos el formato del codigo antes de buscar y guardar
        ValidadorDeFormato.validarCodigo(usuario.getCodigo());
        ValidadorDeFormato.validarNombre(usuario.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(usuario.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(usuario.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(usuario.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(usuario.getEmail());
        // buscamos el usuario en la BD 
        boolean existe = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .anyMatch(u -> u.getCodigo().equalsIgnoreCase(usuario.getCodigo()));

        // validamos si el resultado es positivo
        if (existe) {
            throw new EntidadYaExisteExcepcion(Usuario.class.getSimpleName(), usuario.getCodigo());
        }
        // giardamos el usuario en la BD
        usuario.getRol().agregarUsuario(usuario);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        // validamos el formato del codigo antes de buscar
        ValidadorDeFormato.validarCodigo(codigo);
        // buscamos el usuario en la BD y lo retornamos, sino existe 
        // lanzamos un error controlado durente la ejecución (excepción)
        return BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> u.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion(Usuario.class.getSimpleName(), codigo));
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        // validamos el formato del codigo antes de buscar y 

        var resultado = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> (u.getPrimerNombre() + " " + u.getSegundoNombre())
                .toLowerCase()
                .contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new SinResultadosAlConsultarEntidadesExcepcion("No se encontraron usuarios con el nombre: " + nombre);
        }

        return resultado;
    }

    @Override
    public void editar(Usuario usuario) {
        if (usuario == null) {
            var mensaje = "El usuario es requerido";
            throw new IllegalArgumentException(mensaje);
        }

        // validar el formato del codigo antes de guardar en la BD
        ValidadorDeFormato.validarCodigo(usuario.getCodigo());
        ValidadorDeFormato.validarNombre(usuario.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(usuario.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(usuario.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(usuario.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(usuario.getEmail());
        // obtenemos el usuario (objeto) de la BD
        Usuario existente = buscarPorCodigo(usuario.getCodigo());
        // Le cmbiarmos el valor de las propiedades
        existente.setPrimerNombre(usuario.getPrimerNombre());
        existente.setSegundoNombre(usuario.getSegundoNombre());
        existente.setPrimerApellido(usuario.getPrimerApellido());
        existente.setSegundoApellido(usuario.getSegundoApellido());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        existente.setEstado(usuario.getEstado());
        // guardamos los cambios en la BD
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        //  Validar el formato del codigo antes de buscar y eliminar
        ValidadorDeFormato.validarCodigo(codigo);
        // buscarmos el usuario por su 
        var usuario = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> u.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion(Usuario.class.getSimpleName(), codigo));
        usuario.getRol().getUsuarios().remove(usuario);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        var resultado = BaseDeDatosEnMemoria.getUsuariosEnBd();
        if (resultado.isEmpty()) {
            throw new SinResultadosAlConsultarEntidadesExcepcion("No hay usuarios registrados en el sistema.");
        }
        return resultado;
    }

}

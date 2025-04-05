package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Deudor;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IDeudorRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeudorRepositorio implements IDeudorRepositorio {

    @Override
    public void guardar(Deudor deudor) {
        if (deudor == null || deudor.getCodigo() == null || deudor.getCodigo().isBlank()) {
            String mensaje = "El deudor es requerido y debe tener un código válido.";
            throw new IllegalArgumentException(mensaje);
        }

        ValidadorDeFormato.validarCodigo(deudor.getCodigo());
        ValidadorDeFormato.validarNombre(deudor.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(deudor.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(deudor.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(deudor.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(deudor.getEmail());

        boolean existe = BaseDeDatosEnMemoria.getDeudoresEnBd().stream()
                .anyMatch(d -> d.getCodigo().equalsIgnoreCase(deudor.getCodigo()));

        if (existe) {
            throw new EntidadYaExisteExcepcion("Deudor", deudor.getCodigo());
        }

        deudor.getRol().agregarUsuario(deudor);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Deudor buscarPorCodigo(String codigo) {
        ValidadorDeFormato.validarCodigo(codigo);

        return BaseDeDatosEnMemoria.getDeudoresEnBd().stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion("Deudor", codigo));
    }

    @Override
    public List<Deudor> buscarPorNombre(String nombre) {
        ValidadorDeFormato.validarNombre(nombre, "Primer nombre");

        List<Deudor> resultado = BaseDeDatosEnMemoria.getDeudoresEnBd().stream()
                .filter(d -> d.getPrimerNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            String mensaje = "No se encontraron deudores con el nombre: " + nombre;
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return resultado;
    }

    @Override
    public void editar(Deudor deudor) {
        if (deudor == null) {
            throw new IllegalArgumentException("El deudor es requerido.");
        }

        ValidadorDeFormato.validarCodigo(deudor.getCodigo());
        ValidadorDeFormato.validarNombre(deudor.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(deudor.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(deudor.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(deudor.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(deudor.getEmail());

        Deudor existente = buscarPorCodigo(deudor.getCodigo());

        existente.setPrimerNombre(deudor.getPrimerNombre());
        existente.setSegundoNombre(deudor.getSegundoNombre());
        existente.setPrimerApellido(deudor.getPrimerApellido());
        existente.setSegundoApellido(deudor.getSegundoApellido());
        existente.setEmail(deudor.getEmail());
        existente.setPassword(deudor.getPassword());
        existente.setEstado(deudor.getEstado());

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        Deudor existente = buscarPorCodigo(codigo);
        existente.getRol().getUsuarios().remove(existente);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Deudor> obtenerTodos() {
        List<Deudor> deudores = BaseDeDatosEnMemoria.getDeudoresEnBd();

        if (deudores.isEmpty()) {
            String mensaje = "No hay deudores registrados en el sistema.";
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return deudores;
    }
}

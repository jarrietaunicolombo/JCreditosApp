package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Cobrador;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.ICobradorRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.List;

public class CobradorRepositorio implements ICobradorRepositorio {

    @Override
    public void guardar(Cobrador cobrador) {
        if (cobrador == null || cobrador.getCodigo() == null || cobrador.getCodigo().isBlank()) {
            String mensaje = "El cobrador es requerido y debe tener un código válido.";
            throw new IllegalArgumentException(mensaje);
        }

        ValidadorDeFormato.validarCodigo(cobrador.getCodigo());
        ValidadorDeFormato.validarNombre(cobrador.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(cobrador.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(cobrador.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(cobrador.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(cobrador.getEmail());

        boolean existe = BaseDeDatosEnMemoria.getCobradoresEnBd().stream()
                .anyMatch(c -> c.getCodigo().equalsIgnoreCase(cobrador.getCodigo()));

        if (existe) {
            String mensaje = "Error de duplicidad. Ya existe información sobre Cobrador con ID: " + cobrador.getCodigo();
            throw new EntidadYaExisteExcepcion("Cobrador", cobrador.getCodigo());
        }

        cobrador.getRol().agregarUsuario(cobrador);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Cobrador buscarPorCodigo(String codigo) {
        ValidadorDeFormato.validarCodigo(codigo);

        return BaseDeDatosEnMemoria.getCobradoresEnBd().stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> {
                    String mensaje = "No existe información sobre Cobrador con ID: " + codigo;
                    return new EntidadNoEncontradaExcepcion("Cobrador", codigo);
                });
    }

    @Override
    public List<Cobrador> buscarPorNombre(String nombre) {
        ValidadorDeFormato.validarNombre(nombre, "Nombre");

        var resultado = BaseDeDatosEnMemoria.getCobradoresEnBd().stream()
                .filter(c -> c.getPrimerNombre().equalsIgnoreCase(nombre))
                .toList();

        if (resultado.isEmpty()) {
            String mensaje = "No se encontraron cobradores con el nombre: " + nombre;
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return resultado;
    }

    @Override
    public void editar(Cobrador cobrador) {
        if (cobrador == null) {
            String mensaje = "El cobrador es requerido";
            throw new IllegalArgumentException(mensaje);
        }

        ValidadorDeFormato.validarCodigo(cobrador.getCodigo());
        ValidadorDeFormato.validarNombre(cobrador.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(cobrador.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(cobrador.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(cobrador.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(cobrador.getEmail());

        Cobrador existente = buscarPorCodigo(cobrador.getCodigo());

        existente.setPrimerNombre(cobrador.getPrimerNombre());
        existente.setSegundoNombre(cobrador.getSegundoNombre());
        existente.setPrimerApellido(cobrador.getPrimerApellido());
        existente.setSegundoApellido(cobrador.getSegundoApellido());
        existente.setEmail(cobrador.getEmail());
        existente.setPassword(cobrador.getPassword());
        existente.setEstado(cobrador.getEstado());

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        Cobrador existente = buscarPorCodigo(codigo);
        existente.getRol().getUsuarios().remove(existente);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Cobrador> obtenerTodos() {
        var lista = BaseDeDatosEnMemoria.getCobradoresEnBd();
        if (lista.isEmpty()) {
            String mensaje = "No hay cobradores registrados en el sistema.";
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }
        return lista;
    }
}

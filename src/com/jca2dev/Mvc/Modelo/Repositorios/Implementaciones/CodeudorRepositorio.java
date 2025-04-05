package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Codeudor;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.ICodeudorRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del repositorio para gestionar Codeudores
 * desde la base de datos simulada en memoria.
 * 
 * @author John
 */
public class CodeudorRepositorio implements ICodeudorRepositorio {

    @Override
    public void guardar(Codeudor codeudor) {
        if (codeudor == null || codeudor.getCodigo() == null) {
            throw new IllegalArgumentException("El codeudor es requerido y debe tener un código válido.");
        }

        ValidadorDeFormato.validarCodigo(codeudor.getCodigo());
        ValidadorDeFormato.validarNombre(codeudor.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(codeudor.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(codeudor.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(codeudor.getSegundoApellido(), "Segundo apellido");

        boolean existe = BaseDeDatosEnMemoria.getCodeudoresEnBd().stream()
                .anyMatch(c -> c.getCodigo().equalsIgnoreCase(codeudor.getCodigo()));

        if (existe) {
            throw new EntidadYaExisteExcepcion("Codeudor", codeudor.getCodigo());
        }

        codeudor.getRol().getUsuarios().add(codeudor);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Codeudor buscarPorCodigo(String codigo) {
        ValidadorDeFormato.validarCodigo(codigo);

        return BaseDeDatosEnMemoria.getCodeudoresEnBd().stream()
                .filter(c -> c.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() ->
                        new EntidadNoEncontradaExcepcion(Codeudor.class.getSimpleName(), codigo));
    }

    @Override
    public List<Codeudor> buscarPorNombre(String nombre) {
        ValidadorDeFormato.validarNombre(nombre, "Primer nombre");
        List<Codeudor> resultado = BaseDeDatosEnMemoria.getCodeudoresEnBd().stream()
                .filter(c -> c.getPrimerNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            String mensaje = "No se encontraron codeudores con el nombre: " + nombre;
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return resultado;
    }

    @Override
    public void editar(Codeudor codeudor) {
        if (codeudor == null) {
            throw new IllegalArgumentException("El codeudor es requerido.");
        }

        ValidadorDeFormato.validarCodigo(codeudor.getCodigo());
         ValidadorDeFormato.validarCodigo(codeudor.getCodigo());
        ValidadorDeFormato.validarNombre(codeudor.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(codeudor.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(codeudor.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(codeudor.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(codeudor.getEmail());

        Codeudor existente = buscarPorCodigo(codeudor.getCodigo());

        existente.setPrimerNombre(codeudor.getPrimerNombre());
        existente.setSegundoNombre(codeudor.getSegundoNombre());
        existente.setPrimerApellido(codeudor.getPrimerApellido());
        existente.setSegundoApellido(codeudor.getSegundoApellido());
        existente.setEmail(codeudor.getEmail());
        existente.setPassword(codeudor.getPassword());
        existente.setEstado(codeudor.getEstado());
        existente.setTieneViviendaPropia(codeudor.isTieneViviendaPropia());
        existente.setTieneVehiculo(codeudor.isTieneVehiculo());

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        Codeudor existente = buscarPorCodigo(codigo);
        existente.getRol().getUsuarios().remove(existente);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Codeudor> obtenerTodos() {
        List<Codeudor> lista = BaseDeDatosEnMemoria.getCodeudoresEnBd();
        if (lista.isEmpty()) {
            throw new SinResultadosAlConsultarEntidadesExcepcion("No hay codeudores registrados en el sistema.");
        }
        return lista;
    }
}

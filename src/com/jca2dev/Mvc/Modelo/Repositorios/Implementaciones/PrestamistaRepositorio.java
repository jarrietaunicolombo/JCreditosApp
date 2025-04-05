package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Prestamista;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IPrestamistaRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.List;
import java.util.stream.Collectors;

public class PrestamistaRepositorio implements IPrestamistaRepositorio {

    @Override
    public void guardar(Prestamista prestamista) {
        boolean esCodigoInvalido = prestamista == null
                || prestamista.getCodigo() == null
                || prestamista.getCodigo().isBlank();

        if (esCodigoInvalido) {
            String mensaje = "El prestamista es requerido y debe tener un código válido.";
            throw new IllegalArgumentException(mensaje);
        }

        ValidadorDeFormato.validarCodigo(prestamista.getCodigo());
        ValidadorDeFormato.validarNombre(prestamista.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(prestamista.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(prestamista.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(prestamista.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(prestamista.getEmail());

        boolean existe = BaseDeDatosEnMemoria.getPrestamistasEnBd().stream()
                .anyMatch(p -> p.getCodigo()
                .equalsIgnoreCase(prestamista.getCodigo()));

        if (existe) {
            String mensaje = "Ya existe un prestamista con el código: " + prestamista.getCodigo();
            throw new EntidadYaExisteExcepcion("Prestamista", prestamista.getCodigo());
        }

        BaseDeDatosEnMemoria.getPrestamistasEnBd().add(prestamista);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Prestamista buscarPorCodigo(String codigo) {
        ValidadorDeFormato.validarCodigo(codigo);

        return BaseDeDatosEnMemoria.getPrestamistasEnBd().stream()
                .filter(p -> p.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> {
                    String mensaje = "No se encontró ningún prestamista con el código: " + codigo;
                    return new EntidadNoEncontradaExcepcion("Prestamista", codigo);
                });
    }

    @Override
    public List<Prestamista> buscarPorNombre(String nombre) {
        ValidadorDeFormato.validarNombre(nombre, "Primer nombre");

        List<Prestamista> resultado = BaseDeDatosEnMemoria.getPrestamistasEnBd().stream()
                .filter(p -> p.getPrimerNombre().toLowerCase().contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            String mensaje = "No se encontraron prestamistas con el nombre: " + nombre;
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return resultado;
    }

    @Override
    public void editar(Prestamista prestamista) {
        if (prestamista == null) {
            String mensaje = "El prestamista es requerido.";
            throw new IllegalArgumentException(mensaje);
        }

        ValidadorDeFormato.validarCodigo(prestamista.getCodigo());
        ValidadorDeFormato.validarNombre(prestamista.getPrimerNombre(), "Primer nombre");
        ValidadorDeFormato.validarNombre(prestamista.getSegundoNombre(), "Segundo nombre");
        ValidadorDeFormato.validarNombre(prestamista.getPrimerApellido(), "Primer apellido");
        ValidadorDeFormato.validarNombre(prestamista.getSegundoApellido(), "Segundo apellido");
        ValidadorDeFormato.validarEmail(prestamista.getEmail());

        Prestamista existente = buscarPorCodigo(prestamista.getCodigo());

        existente.setPrimerNombre(prestamista.getPrimerNombre());
        existente.setSegundoNombre(prestamista.getSegundoNombre());
        existente.setPrimerApellido(prestamista.getPrimerApellido());
        existente.setSegundoApellido(prestamista.getSegundoApellido());
        existente.setEmail(prestamista.getEmail());
        existente.setPassword(prestamista.getPassword());
        existente.setEstado(prestamista.getEstado());
        existente.setCapital(prestamista.getCapital());

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        Prestamista existente = buscarPorCodigo(codigo);

        // Eliminar desde el rol (que es donde realmente está vinculado)
        existente.getRol().getUsuarios().remove(existente);

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Prestamista> obtenerTodos() {
        List<Prestamista> lista = BaseDeDatosEnMemoria.getPrestamistasEnBd();

        if (lista.isEmpty()) {
            String mensaje = "Actualmente no hay prestamistas registrados.";
            throw new SinResultadosAlConsultarEntidadesExcepcion(mensaje);
        }

        return lista;
    }
}

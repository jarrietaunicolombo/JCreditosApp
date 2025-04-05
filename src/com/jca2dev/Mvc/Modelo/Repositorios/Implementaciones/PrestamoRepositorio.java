package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Prestamo;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IPrestamoRepositorio;
import com.jca2dev.UtilidadesComunes.ValidadorDeFormato;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrestamoRepositorio implements IPrestamoRepositorio {

    @Override
    public void guardar(Prestamo prestamo) {
        final String mensajeIdInvalido = "El préstamo es requerido y debe tener un ID válido.";
        final String mensajeDuplicado = "Error de duplicidad. Ya existe información sobre el préstamo con ID: " + prestamo.getId();

        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            throw new IllegalArgumentException(mensajeIdInvalido);
        }

        ValidadorDeFormato.validarMonto(prestamo.getMonto());
        ValidadorDeFormato.validarTasaInteres(prestamo.getTasaInteres());
        ValidadorDeFormato.validarNumeroCuotas(prestamo.getNumeroCuotas());

        var yaExiste = buscarPorId(prestamo.getId()) != null;
        if (yaExiste) {
            throw new IllegalArgumentException(mensajeDuplicado);
        }

        BaseDeDatosEnMemoria.getPrestamosEnBd().add(prestamo);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Prestamo buscarPorId(Integer id) {
        final String mensajeIdInvalido = "El ID del préstamo es requerido y debe ser mayor que cero.";

        if (id == null || id <= 0) {
            throw new IllegalArgumentException(mensajeIdInvalido);
        }

        return BaseDeDatosEnMemoria.getPrestamosEnBd().stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        var lista = BaseDeDatosEnMemoria.getPrestamosEnBd();
        if (lista.isEmpty()) {
            throw new IllegalStateException("No hay préstamos registrados en el sistema.");
        }
        return lista;
    }

    @Override
    public void editar(Prestamo prestamo) {
        final String mensajeIdInvalido = "El préstamo es requerido y debe tener un ID válido.";

        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            throw new IllegalArgumentException(mensajeIdInvalido);
        }

        ValidadorDeFormato.validarMonto(prestamo.getMonto());
        ValidadorDeFormato.validarTasaInteres(prestamo.getTasaInteres());
        ValidadorDeFormato.validarNumeroCuotas(prestamo.getNumeroCuotas());

        Prestamo existente = buscarPorId(prestamo.getId());
        if (existente == null) {
            throw new IllegalArgumentException("No existe información sobre préstamo con ID: " + prestamo.getId());
        }

        existente.setFechaDesembolso(prestamo.getFechaDesembolso());
        existente.setEstado(prestamo.getEstado());
        existente.setMonto(prestamo.getMonto());
        existente.setNumeroCuotas(prestamo.getNumeroCuotas());
        existente.setObservaciones(prestamo.getObservaciones());
        existente.setSaldo(prestamo.getSaldo());
        existente.setTasaInteres(prestamo.getTasaInteres());
        existente.setTipoDeCuota(prestamo.getTipoDeCuota());

        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(Integer id) {
        Prestamo existente = buscarPorId(id);
        if (existente == null) {
            throw new IllegalArgumentException("No existe información sobre préstamo con ID: " + id);
        }
        existente.getPrestamista().getPrestamos().remove(existente);
        existente.getDeudor().getPrestamos().remove(existente);
        BaseDeDatosEnMemoria.getPrestamosEnBd().remove(existente);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Prestamo> buscarPorCodigoPrestamista(String codigoPrestamista) {
        final String mensajeCodigoRequerido = "El código del prestamista es requerido.";

        if (codigoPrestamista == null || codigoPrestamista.isBlank()) {
            throw new IllegalArgumentException(mensajeCodigoRequerido);
        }

        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : BaseDeDatosEnMemoria.getPrestamosEnBd()) {
            if (p.getPrestamista() != null
                    && codigoPrestamista.equalsIgnoreCase(p.getPrestamista().getCodigo())) {
                resultado.add(p);
            }
        }

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron préstamos asociados al prestamista con código: " + codigoPrestamista);
        }

        return resultado;
    }

    @Override
    public List<Prestamo> buscarPorCodigoDeudor(String codigoDeudor) {
        final String mensajeCodigoRequerido = "El código del deudor es requerido.";

        if (codigoDeudor == null || codigoDeudor.isBlank()) {
            throw new IllegalArgumentException(mensajeCodigoRequerido);
        }

        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : BaseDeDatosEnMemoria.getPrestamosEnBd()) {
            if (p.getDeudor() != null
                    && codigoDeudor.equalsIgnoreCase(p.getDeudor().getCodigo())) {
                resultado.add(p);
            }
        }

        if (resultado.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron préstamos asociados al deudor con código: " + codigoDeudor);
        }

        return resultado;
    }
}

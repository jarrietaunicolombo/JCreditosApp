package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.IntentoDeCobro;
import java.util.List;

/**
 * Repositorio para la entidad IntentoDeCobro.
 * Gestiona los intentos de cobro realizados por cobradores sobre un pago.
 * Cada intento tiene un identificador único.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IIntentoDeCobroRepositorio {

    /**
     * Guarda un nuevo intento de cobro en el sistema.
     * @param intento intento de cobro a registrar
     */
    void guardar(IntentoDeCobro intento);

    /**
     * Busca un intento de cobro por su identificador único.
     * @param id identificador del intento
     * @return intento de cobro encontrado o null si no existe
     */
    IntentoDeCobro buscarPorId(Integer id);

    /**
     * Devuelve todos los intentos de cobro registrados.
     * @return lista completa de intentos
     */
    List<IntentoDeCobro> obtenerTodos();

    /**
     * Actualiza los datos de un intento de cobro existente.
     * @param intento intento con los nuevos datos
     */
    void editar(IntentoDeCobro intento);

    /**
     * Elimina un intento de cobro por su ID.
     * @param id identificador del intento
     */
    void eliminar(Integer id);

    /**
     * Devuelve los intentos de cobro asociados a un pago específico.
     * @param idPago identificador del pago
     * @return lista de intentos asociados al pago
     */
    List<IntentoDeCobro> buscarPorIdPago(Integer idPago);
}

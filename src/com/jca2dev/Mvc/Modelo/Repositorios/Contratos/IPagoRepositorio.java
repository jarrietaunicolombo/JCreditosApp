package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Pago;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Pago.
 * Permite registrar, buscar, editar y eliminar pagos asociados
 * a préstamos, identificados por su ID único.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IPagoRepositorio {

    /**
     * Guarda un nuevo pago en el sistema.
     * @param pago pago a registrar
     */
    void guardar(Pago pago);

    /**
     * Busca un pago por su identificador único.
     * @param id identificador del pago
     * @return pago encontrado o null si no existe
     */
    Pago buscarPorId(Integer id);

    /**
     * Obtiene todos los pagos registrados.
     * @return lista de pagos
     */
    List<Pago> obtenerTodos();

    /**
     * Actualiza un pago existente.
     * @param pago pago con los datos modificados
     */
    void editar(Pago pago);

    /**
     * Elimina un pago por su ID.
     * @param id identificador del pago
     */
    void eliminar(Integer id);

    /**
     * Devuelve todos los pagos registrados para un préstamo específico.
     * @param idPrestamo identificador del préstamo
     * @return lista de pagos relacionados
     */
    List<Pago> buscarPorIdPrestamo(Integer idPrestamo);
}

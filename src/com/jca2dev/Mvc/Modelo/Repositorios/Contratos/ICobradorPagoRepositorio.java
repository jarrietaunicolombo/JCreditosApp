package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.CobradorPago;
import java.util.List;

/**
 * Repositorio para la entidad CobradorPago.
 * Representa la relación entre un cobrador y los pagos que recauda.
 * Usa un identificador entero único por registro.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface ICobradorPagoRepositorio {

    /**
     * Guarda una nueva asignación de cobrador a pago.
     * @param cobradorPago objeto a registrar
     */
    void guardar(CobradorPago cobradorPago);

    /**
     * Busca un registro por su identificador único.
     * @param id identificador del registro
     * @return objeto encontrado o null si no existe
     */
    CobradorPago buscarPorId(Integer id);

    /**
     * Obtiene todos los registros existentes.
     * @return lista de CobradorPago
     */
    List<CobradorPago> obtenerTodos();

    /**
     * Edita los datos de una relación existente.
     * @param cobradorPago objeto con los nuevos datos
     */
    void editar(CobradorPago cobradorPago);

    /**
     * Elimina una relación por su ID.
     * @param id identificador del registro
     */
    void eliminar(Integer id);

    /**
     * Busca todos los registros asociados a un pago específico.
     * @param idPago identificador del pago
     * @return lista de registros asociados a ese pago
     */
    List<CobradorPago> buscarPorIdPago(Integer idPago);
}

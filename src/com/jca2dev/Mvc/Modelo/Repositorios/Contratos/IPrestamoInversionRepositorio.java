package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.PrestamoInversion;
import java.util.List;

/**
 * Repositorio para la entidad PrestamoInversion, que representa
 * la asociación entre un préstamo y una inversión específica.
 * Cada registro tiene un ID único, pero también se puede buscar
 * por el ID del préstamo o el ID de la inversión relacionados.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IPrestamoInversionRepositorio {

    /**
     * Guarda una nueva relación entre préstamo e inversión.
     * @param prestamoInversion entidad a registrar
     */
    void guardar(PrestamoInversion prestamoInversion);

    /**
     * Busca un registro por su ID.
     * @param id identificador único
     * @return objeto encontrado o null
     */
    PrestamoInversion buscarPorId(Integer id);

    /**
     * Devuelve todos los registros existentes.
     * @return lista completa de asociaciones préstamo-inversión
     */
    List<PrestamoInversion> obtenerTodos();

    /**
     * Edita una relación existente.
     * @param prestamoInversion objeto con los nuevos datos
     */
    void editar(PrestamoInversion prestamoInversion);

    /**
     * Elimina un registro por su ID.
     * @param id identificador del registro
     */
    void eliminar(Integer id);

    /**
     * Devuelve todas las inversiones asociadas a un préstamo.
     * @param idPrestamo ID del préstamo
     * @return lista de asociaciones correspondientes
     */
    List<PrestamoInversion> buscarPorIdPrestamo(Integer idPrestamo);

    /**
     * Devuelve todos los préstamos en los que participa una inversión.
     * @param idInversion ID de la inversión
     * @return lista de asociaciones correspondientes
     */
    List<PrestamoInversion> buscarPorIdInversion(Integer idInversion);
}

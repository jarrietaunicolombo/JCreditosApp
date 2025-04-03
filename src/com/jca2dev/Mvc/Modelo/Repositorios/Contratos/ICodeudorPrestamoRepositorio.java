package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.CodeudorPrestamo;
import java.util.List;

/**
 * Repositorio para la entidad de asociación CodeudorPrestamo.
 * Representa la relación entre un préstamo y sus codeudores,
 * permitiendo gestionar quién respalda qué préstamo.
 * 
 * Cada registro tiene un identificador único, y puede buscarse
 * también por el código del codeudor o el ID del préstamo.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface ICodeudorPrestamoRepositorio {

    /**
     * Guarda una nueva relación entre codeudor y préstamo.
     * @param codeudorPrestamo entidad a registrar
     */
    void guardar(CodeudorPrestamo codeudorPrestamo);

    /**
     * Busca una relación por su identificador único.
     * @param id identificador del registro
     * @return objeto encontrado o null
     */
    CodeudorPrestamo buscarPorId(Integer id);

    /**
     * Devuelve todos los registros de asociaciones existentes.
     * @return lista completa
     */
    List<CodeudorPrestamo> obtenerTodos();

    /**
     * Edita una relación existente.
     * @param codeudorPrestamo objeto con los nuevos datos
     */
    void editar(CodeudorPrestamo codeudorPrestamo);

    /**
     * Elimina una relación por su ID.
     * @param id identificador del registro a eliminar
     */
    void eliminar(Integer id);

    /**
     * Devuelve todos los codeudores asociados a un préstamo.
     * @param idPrestamo ID del préstamo
     * @return lista de relaciones correspondientes
     */
    List<CodeudorPrestamo> buscarPorIdPrestamo(Integer idPrestamo);

    /**
     * Devuelve todos los préstamos en los que participa un codeudor.
     * @param codigoCodeudor código del codeudor
     * @return lista de asociaciones correspondientes
     */
    List<CodeudorPrestamo> buscarPorCodigoCodeudor(String codigoCodeudor);
}

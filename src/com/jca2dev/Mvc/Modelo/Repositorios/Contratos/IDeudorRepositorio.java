package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Deudor;
import java.util.List;

/**
 * Repositorio para gestionar operaciones de acceso a datos
 * relacionadas con la entidad Deudor.
 * Usa el código como identificador único.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IDeudorRepositorio {

    /**
     * Guarda un nuevo deudor en el sistema.
     * @param deudor entidad a registrar
     */
    void guardar(Deudor deudor);

    /**
     * Busca un deudor por su código único.
     * @param codigo identificador del deudor
     * @return Deudor encontrado o null si no existe
     */
    Deudor buscarPorCodigo(String codigo);

    /**
     * Busca deudores por coincidencia en su nombre.
     * @param nombre texto a buscar en los nombres
     * @return lista de deudores que coinciden
     */
    List<Deudor> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un deudor existente.
     * @param deudor objeto con los nuevos datos
     */
    void editar(Deudor deudor);

    /**
     * Elimina un deudor por su código.
     * @param codigo código del deudor a eliminar
     */
    void eliminar(String codigo);

    /**
     * Devuelve todos los deudores registrados en el sistema.
     * @return lista de deudores
     */
    List<Deudor> obtenerTodos();
}

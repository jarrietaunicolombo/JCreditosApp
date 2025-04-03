package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Cobrador;
import java.util.List;

/**
 * Repositorio para gestionar operaciones relacionadas con la entidad Cobrador.
 * Usa el código como identificador único.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface ICobradorRepositorio {

    /**
     * Guarda un nuevo cobrador en el sistema.
     * @param cobrador cobrador a registrar
     */
    void guardar(Cobrador cobrador);

    /**
     * Busca un cobrador por su código único.
     * @param codigo identificador del cobrador
     * @return cobrador encontrado o null si no existe
     */
    Cobrador buscarPorCodigo(String codigo);

    /**
     * Busca cobradores cuyo nombre coincida total o parcialmente.
     * @param nombre texto a buscar
     * @return lista de cobradores que coinciden
     */
    List<Cobrador> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un cobrador existente.
     * @param cobrador objeto con la información modificada
     */
    void editar(Cobrador cobrador);

    /**
     * Elimina un cobrador por su código.
     * @param codigo identificador del cobrador a eliminar
     */
    void eliminar(String codigo);

    /**
     * Devuelve todos los cobradores registrados.
     * @return lista de cobradores
     */
    List<Cobrador> obtenerTodos();
}

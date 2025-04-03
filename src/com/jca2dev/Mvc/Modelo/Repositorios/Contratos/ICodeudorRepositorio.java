package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Codeudor;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Codeudor.
 * Utiliza el código como identificador único.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface ICodeudorRepositorio {

    /**
     * Guarda un nuevo codeudor en el sistema.
     * @param codeudor objeto a registrar
     */
    void guardar(Codeudor codeudor);

    /**
     * Busca un codeudor por su código.
     * @param codigo identificador único del codeudor
     * @return codeudor encontrado o null si no existe
     */
    Codeudor buscarPorCodigo(String codigo);

    /**
     * Busca codeudores que contengan cierto nombre.
     * @param nombre cadena a buscar en el nombre
     * @return lista de codeudores coincidentes
     */
    List<Codeudor> buscarPorNombre(String nombre);

    /**
     * Actualiza un codeudor existente.
     * @param codeudor objeto con los nuevos datos
     */
    void editar(Codeudor codeudor);

    /**
     * Elimina un codeudor por su código.
     * @param codigo código del codeudor a eliminar
     */
    void eliminar(String codigo);

    /**
     * Obtiene todos los codeudores del sistema.
     * @return lista de todos los codeudores
     */
    List<Codeudor> obtenerTodos();
}

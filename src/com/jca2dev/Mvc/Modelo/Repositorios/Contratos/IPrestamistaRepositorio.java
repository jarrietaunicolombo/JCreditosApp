package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Prestamista;
import java.util.List;

/**
 * Repositorio de acceso a datos para la entidad Prestamista.
 * Se encarga de guardar, buscar, editar y eliminar prestamistas
 * dentro del sistema. Usa como identificador único el "código".
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IPrestamistaRepositorio {

    /**
     * Guarda un nuevo prestamista en el sistema.
     * @param prestamista objeto a guardar
     */
    void guardar(Prestamista prestamista);

    /**
     * Busca un prestamista por su código único.
     * @param codigo identificador único del prestamista
     * @return objeto Prestamista si existe, null si no se encuentra
     */
    Prestamista buscarPorCodigo(String codigo);

    /**
     * Busca prestamistas cuyo nombre contenga una cadena dada.
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de prestamistas coincidentes
     */
    List<Prestamista> buscarPorNombre(String nombre);

    /**
     * Actualiza los datos de un prestamista existente.
     * @param prestamista objeto con los nuevos datos
     */
    void editar(Prestamista prestamista);

    /**
     * Elimina un prestamista por su código.
     * @param codigo identificador del prestamista a eliminar
     */
    void eliminar(String codigo);

    /**
     * Devuelve la lista completa de prestamistas.
     * @return lista de todos los prestamistas registrados
     */
    List<Prestamista> obtenerTodos();
}

package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Inversion;
import java.util.List;

/**
 * Repositorio para gestionar las inversiones realizadas por los prestamistas.
 * Usa el identificador entero como clave única.
 * 
 * Cada inversión pertenece a un prestamista y puede estar asociada a varios préstamos.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IInversionRepositorio {

    /**
     * Registra una nueva inversión en el sistema.
     * @param inversion objeto a guardar
     */
    void guardar(Inversion inversion);

    /**
     * Busca una inversión por su identificador.
     * @param id identificador único de la inversión
     * @return objeto Inversion si se encuentra, null en caso contrario
     */
    Inversion buscarPorId(Integer id);

    /**
     * Devuelve todas las inversiones registradas.
     * @return lista de inversiones
     */
    List<Inversion> obtenerTodos();

    /**
     * Edita una inversión existente.
     * @param inversion objeto con los datos modificados
     */
    void editar(Inversion inversion);

    /**
     * Elimina una inversión por su identificador.
     * @param id ID de la inversión a eliminar
     */
    void eliminar(Integer id);

    /**
     * Busca todas las inversiones realizadas por un prestamista específico.
     * @param codigoPrestamista código del prestamista
     * @return lista de inversiones asociadas a ese prestamista
     */
    List<Inversion> buscarPorCodigoPrestamista(String codigoPrestamista);
}

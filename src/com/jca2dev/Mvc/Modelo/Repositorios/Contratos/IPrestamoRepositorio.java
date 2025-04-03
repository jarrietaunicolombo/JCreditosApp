package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Prestamo;
import java.util.List;

/**
 * Repositorio para gestionar los préstamos registrados en el sistema.
 * Cada préstamo tiene un identificador único y está asociado a un prestamista,
 * un deudor, y varias inversiones a través de la entidad PrestamoInversion.
 * 
 * @author John Carlos Arrieta Arrieta
 */
public interface IPrestamoRepositorio {

    /**
     * Guarda un nuevo préstamo en el sistema.
     * @param prestamo objeto a registrar
     */
    void guardar(Prestamo prestamo);

    /**
     * Busca un préstamo por su identificador único.
     * @param id identificador del préstamo
     * @return objeto Prestamo encontrado o null
     */
    Prestamo buscarPorId(Integer id);

    /**
     * Devuelve todos los préstamos registrados.
     * @return lista completa de préstamos
     */
    List<Prestamo> obtenerTodos();

    /**
     * Edita un préstamo existente.
     * @param prestamo préstamo con los datos modificados
     */
    void editar(Prestamo prestamo);

    /**
     * Elimina un préstamo por su ID.
     * @param id identificador del préstamo a eliminar
     */
    void eliminar(Integer id);

    /**
     * Busca todos los préstamos asociados a un prestamista específico.
     * @param codigoPrestamista código del prestamista
     * @return lista de préstamos correspondientes
     */
    List<Prestamo> buscarPorCodigoPrestamista(String codigoPrestamista);

    /**
     * Busca todos los préstamos asociados a un deudor específico.
     * @param codigoDeudor código del deudor
     * @return lista de préstamos correspondientes
     */
    List<Prestamo> buscarPorCodigoDeudor(String codigoDeudor);
}

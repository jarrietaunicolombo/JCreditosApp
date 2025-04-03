package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Rol;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public interface IRolRepositorio {

    /**
     * Guarda un nuevo rol. El ID puede ser generado dentro de la implementación.
     * @param rol objeto Rol a guardar
     */
     void guardar(Rol rol);

    /**
     * Busca un rol por su identificador único.
     * @param id identificador del rol
     * @return Rol encontrado o null si no existe
     */
     Rol buscarPorId(Integer id);

    /**
     * Busca roles que contengan el texto en su nombre.
     * @param nombre texto a buscar
     * @return lista de roles cuyo nombre coincida parcial o totalmente
     */
     List<Rol> buscarPorNombre(String nombre);

    /**
     * Actualiza un rol existente.
     * @param rol objeto Rol con los datos modificados
     */
     void editar(Rol rol);

    /**
     * Elimina un rol por su identificador.
     * @param id identificador del rol a eliminar
     */
     void eliminar(Integer id);

    /**
     * Obtiene todos los roles registrados.
     * @return lista completa de roles
     */
     List<Rol> obtenerTodos();
}

package com.jca2dev.Mvc.Modelo.Repositorios.Contratos;

import com.jca2dev.Dominio.Entidades.Usuario;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public interface IUsuarioRepositorio {

    void guardar(Usuario usuario);

     Usuario buscarPorCodigo(String codigo);

    List<Usuario> buscarPorNombre(String nombre);

    void editar(Usuario usuario);

    void eliminar(Integer id);

    List<Usuario> obtenerTodos();
}

package com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones;

import com.jca2dev.Dominio.Entidades.Usuario;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Excepciones.EntidadYaExisteExcepcion;
import com.jca2dev.Mvc.Excepciones.SinResultadosAlConsultarEntidadesExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Contratos.IUsuarioRepositorio;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioRepositorio implements IUsuarioRepositorio {

    @Override
    public void guardar(Usuario usuario) {
        if (usuario == null || usuario.getCodigo() == null || usuario.getCodigo().isBlank()) {
            throw new IllegalArgumentException("El usuario no puede ser nulo ni tener código vacío.");
        }

        // validamos el formato del codigo antes de buscar y guardar
        validarFormatoCodigo(usuario.getCodigo());
        // buscamos el usuario en la BD 
        boolean existe = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .anyMatch(u -> u.getCodigo().equalsIgnoreCase(usuario.getCodigo()));

        // validamos si el resultado es positivo
        if (existe) {
            throw new EntidadYaExisteExcepcion(Usuario.class.getSimpleName(), usuario.getCodigo());
        }
        // giardamos el usuario en la BD
        usuario.getRol().agregarUsuario(usuario);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        // validamos el formato del codigo antes de buscar
        validarFormatoCodigo(codigo);
        // buscamos el usuario en la BD y lo retornamos, sino existe 
        // lanzamos un error controlado durente la ejecución (excepción)
        return BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> u.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion(Usuario.class.getSimpleName(), codigo));
    }

    @Override
    public List<Usuario> buscarPorNombre(String nombre) {
        // validamos el formato del codigo antes de buscar y 

        var resultado = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> (u.getPrimerNombre() + " " + u.getSegundoNombre())
                .toLowerCase()
                .contains(nombre.toLowerCase()))
                .collect(Collectors.toList());

        if (resultado.isEmpty()) {
            throw new SinResultadosAlConsultarEntidadesExcepcion("No se encontraron usuarios con el nombre: " + nombre);
        }

        return resultado;
    }

    @Override
    public void editar(Usuario usuario) {
        if (usuario == null) {
            var mensaje = "El usuario es requerido";
            throw new IllegalArgumentException(mensaje);
        }

        // validar el formato del codigo antes de guardar en la BD
        validarFormatoCodigo(usuario.getCodigo());
        validarFormatoNombre(usuario.getPrimerNombre());
        validarFormatoNombre(usuario.getSegundoNombre());
        validarFormatoNombre(usuario.getPrimerApellido());
        validarFormatoNombre(usuario.getSegundoApellido());
        validarFormatoEmail(usuario.getEmail());
        // obtenemos el usuario (objeto) de la BD
        Usuario existente = buscarPorCodigo(usuario.getCodigo());
        // Le cmbiarmos el valor de las propiedades
        existente.setPrimerNombre(usuario.getPrimerNombre());
        existente.setSegundoNombre(usuario.getSegundoNombre());
        existente.setPrimerApellido(usuario.getPrimerApellido());
        existente.setSegundoApellido(usuario.getSegundoApellido());
        existente.setEmail(usuario.getEmail());
        existente.setPassword(usuario.getPassword());
        existente.setEstado(usuario.getEstado());
        // guardamos los cambios en la BD
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public void eliminar(String codigo) {
        //  Validar el formato del codigo antes de buscar y eliminar
        validarFormatoCodigo(codigo);
        // buscarmos el usuario por su 
        var usuario = BaseDeDatosEnMemoria.getUsuariosEnBd().stream()
                .filter(u -> u.getCodigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new EntidadNoEncontradaExcepcion(Usuario.class.getSimpleName(), codigo));
        usuario.getRol().getUsuarios().remove(usuario);
        BaseDeDatosEnMemoria.persistir();
    }

    @Override
    public List<Usuario> obtenerTodos() {
        var resultado = BaseDeDatosEnMemoria.getUsuariosEnBd();
        if (resultado.isEmpty()) {
            throw new SinResultadosAlConsultarEntidadesExcepcion("No hay usuarios registrados en el sistema.");
        }
        return resultado;
    }

    /**
     * Valida que el código contenga únicamente letras y números, sin espacios
     * ni caracteres especiales.
     */
    private void validarFormatoCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es requerido.");
        }

        for (char c : codigo.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException(
                        "El código solo puede contener letras y números, sin espacios ni caracteres especiales.");
            }
        }

        // Versión con expresión regular (comentada para fines educativos):
        // if (!codigo.matches("^[a-zA-Z0-9]+$")) {
        //     throw new IllegalArgumentException("El código solo puede contener letras y números.");
        // }
    }

    /**
     * Valida que el nombre contenga únicamente letras y espacios. No se
     * permiten números ni caracteres especiales.
     */
    private void validarFormatoNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es requerido.");
        }

        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new IllegalArgumentException(
                        "El nombre solo puede contener letras y espacios.");
            }
        }

        // Versión con expresión regular (comentada para fines educativos):
        // if (!nombre.matches("^[a-zA-Z ]+$")) {
        //     throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        // }
    }

    /**
     * Valida que el email tenga exactamente una arroba (@), al menos un punto
     * después de la arroba, y solo contenga letras, números, guiones, guiones
     * bajos y puntos. Ni la arroba ni el punto pueden estar al inicio ni al
     * final.
     */
    /**
     * Valida que el email tenga exactamente una arroba (@), al menos un punto
     * después de la arroba, y solo contenga letras, números, guiones, guiones
     * bajos y puntos. Ni la arroba ni el punto pueden estar al inicio ni al
     * final.
     */
    private void validarFormatoEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es requerido.");
        }

        int arrobas = 0;
        int posicionArroba = -1;

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            if (c == '@') {
                arrobas++;
                posicionArroba = i;
            } else if (!Character.isLetterOrDigit(c) && c != '-' && c != '_' && c != '.') {
                throw new IllegalArgumentException("El email solo puede contener letras, números, guiones, guiones bajos y puntos.");
            }
        }

        if (arrobas != 1) {
            throw new IllegalArgumentException("El email debe contener exactamente una arroba.");
        }

        if (posicionArroba == 0 || posicionArroba == email.length() - 1) {
            throw new IllegalArgumentException("La arroba no puede estar al inicio ni al final del email.");
        }

        if (email.startsWith(".") || email.endsWith(".")) {
            throw new IllegalArgumentException("El punto no puede estar al inicio ni al final del email.");
        }

        if (!email.substring(posicionArroba).contains(".")) {
            throw new IllegalArgumentException("El email debe contener al menos un punto después de la arroba.");
        }

        // Regex (comentado para comparación)
        // if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,}$")) {
        //     throw new IllegalArgumentException("El email no tiene un formato válido.");
        // }
    }

}

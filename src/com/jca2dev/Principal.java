package com.jca2dev;

import com.jca2dev.Dominio.Constantes.EstadoDeUsuarioEnum;
import com.jca2dev.Dominio.Entidades.*;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.UsuarioRepositorio;



public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        RolRepositorio rolRepositorio = new RolRepositorio();
        UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();

        System.out.println("\n=== Prueba obtenerTodos() ===");
        try {
            usuarioRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorCodigo(\"U123\") ===");
        try {
            Usuario usuario = usuarioRepositorio.buscarPorCodigo("U123");
            System.out.println("Usuario encontrado: " + usuario.getPrimerNombre());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorNombre(\"Carlos\") ===");
        try {
            usuarioRepositorio.buscarPorNombre("Carlos").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba guardar() ===");
        try {
            Rol rolUsuario = rolRepositorio.buscarPorNombre("Usuario");

            Usuario nuevoUsuario = new Usuario(
                "U123",                   // Código
                "John",                   // Primer Nombre
                "Arrieta",                // Primer Apellido
                "john.arriera2@gmail.com",    // Email
                rolUsuario                // Rol
            );
            nuevoUsuario.setSegundoNombre("Carlos");
            nuevoUsuario.setSegundoApellido("Arrieta");
            nuevoUsuario.setPassword("123456");

            usuarioRepositorio.guardar(nuevoUsuario);
            System.out.println("Usuario guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba editar() ===");
        try {
            Usuario usuario = usuarioRepositorio.buscarPorCodigo("U123");
            usuario.setSegundoNombre("Andresito");
            usuario.setEmail("john.arriera2@gmail.com");
            usuario.setPassword("nuevaClaveSegura");
            usuario.setEstado(EstadoDeUsuarioEnum.ACTIVO);

            usuarioRepositorio.editar(usuario);
            System.out.println("Usuario editado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba eliminar(\"U123\") ===");
        try {
            usuarioRepositorio.eliminar("U123");
            System.out.println("Usuario eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


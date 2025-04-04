package com.jca2dev;

import com.jca2dev.Dominio.Entidades.*;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;


public class Principal {

    public static void main(String[] args) {
        // Siempre iniciar la BD antes de cualquier operación
        BaseDeDatosEnMemoria.iniciar();

        // PRUEBAS CON REPOSITORIO DE ROL
        RolRepositorio rolRepositorio = new RolRepositorio();

        System.out.println("\n=== Prueba obtenerTodos() ===");
        try {
            rolRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorId(1) ===");
        try {
            Rol rol = rolRepositorio.buscarPorId(1);
            System.out.println("Rol encontrado: " + rol.getNombre());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorNombre(\"admin\") ===");
        try {
            System.out.println("Rol: "+rolRepositorio.buscarPorNombre("admin"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba editar() ===");
        try {
            Rol rol = rolRepositorio.buscarPorId(1);
            rol.setDescripcion("Este es el rol de administración del sistema.");
            rol.setIcono("👑");
            rolRepositorio.editar(rol);
            System.out.println("Rol actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba guardar() ===");
        try {
            Rol nuevoRol = new Rol("Operador");
            nuevoRol.setDescripcion("Gestiona operaciones diarias.");
            nuevoRol.setIcono("⚙️");
            rolRepositorio.guardar(nuevoRol);
            System.out.println("Rol guardado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
System.out.println("\n=== Prueba obtenerTodos() ===");
        try {
            rolRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n=== Prueba eliminar() ===");
        try {
            var rol = rolRepositorio.buscarPorNombre("Operador");
            rolRepositorio.eliminar(rol.getId());
            System.out.println("Rol eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n=== Prueba obtenerTodos() ===");
        try {
            rolRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Los métodos crearDatosIniciales() y mostrarDatos() permanecen igual
}

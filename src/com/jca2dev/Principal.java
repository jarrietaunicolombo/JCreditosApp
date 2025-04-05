package com.jca2dev;

import com.jca2dev.Dominio.Entidades.Codeudor;
import com.jca2dev.Dominio.Entidades.Rol;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.CodeudorRepositorio;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;

public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        RolRepositorio rolRepositorio = new RolRepositorio();
        CodeudorRepositorio codeudorRepositorio = new CodeudorRepositorio();

        System.out.println("\n=== Codeudores actualmente en el sistema ===");
        try {
            codeudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Guardando nuevo codeudor ===");
        try {
            Rol rolCodeudor = rolRepositorio.buscarPorNombre("Codeudor");
            Codeudor codeudor = new Codeudor("C123", "John", "Arrieta", "john.arrieta@gmail.com", rolCodeudor);
            codeudor.setSegundoNombre("Carlos");
            codeudor.setSegundoApellido("Arrieta");
            codeudor.setPassword("clave123");
            codeudor.setTieneViviendaPropia(true);
            codeudor.setTieneVehiculo(false);

            codeudorRepositorio.guardar(codeudor);
            System.out.println("Codeudor guardado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        System.out.println("\n=== Codeudores después de guardar ===");
        try {
            codeudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscando codeudor por código 'C123' ===");
        try {
            Codeudor c = codeudorRepositorio.buscarPorCodigo("C123");
            System.out.println("Codeudor encontrado:");
            System.out.println(c);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscando codeudores por nombre 'Luis' ===");
        try {
            codeudorRepositorio.buscarPorNombre("Luis").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Editando codeudor con código 'C123' ===");
        try {
            Codeudor c = codeudorRepositorio.buscarPorCodigo("C123");
            c.setSegundoNombre("Jairo");
            c.setSegundoApellido("Villarreal");
            c.setTieneViviendaPropia(false);
            c.setTieneVehiculo(true);
            codeudorRepositorio.editar(c);
            System.out.println("Codeudor actualizado:");
            System.out.println(codeudorRepositorio.buscarPorCodigo("C123"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Eliminando codeudor con código 'C123' ===");
        try {
            codeudorRepositorio.eliminar("C123");
            System.out.println("Codeudor eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Codeudores después de eliminar ===");
        try {
            codeudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

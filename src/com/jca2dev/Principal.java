package com.jca2dev;

import com.jca2dev.Dominio.Constantes.GeneroEnum;
import com.jca2dev.Dominio.Constantes.TipoDeIdentificacionEnum;
import com.jca2dev.Dominio.Entidades.Deudor;
import com.jca2dev.Dominio.Entidades.Rol;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.DeudorRepositorio;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;

public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        RolRepositorio rolRepositorio = new RolRepositorio();
        DeudorRepositorio deudorRepositorio = new DeudorRepositorio();

        System.out.println("\n=== Prueba obtenerTodos() (Deudores) ===");
        try {
            deudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba guardar() (Deudor) ===");
        try {
            Rol rolDeudor = rolRepositorio.buscarPorNombre("Deudor");

            Deudor nuevoDeudor = new Deudor("D001", "John", "Arrieta", "john.arrieta2@hotmail.com", rolDeudor);
            nuevoDeudor.setSegundoNombre("Carlos");
            nuevoDeudor.setSegundoApellido("Arrieta");
            nuevoDeudor.setPassword("claveDeAcceso");
            nuevoDeudor.setDireccion("Calle 123");
            nuevoDeudor.setTelefono("3201234567");
            nuevoDeudor.setGenero(GeneroEnum.MASCULINO);
            nuevoDeudor.setTipoIdentificacion(TipoDeIdentificacionEnum.CEDULA);
            nuevoDeudor.setNumeroIdentificacion("1122334455");

            deudorRepositorio.guardar(nuevoDeudor);
            System.out.println("✔️ Deudor guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba obtenerTodos() luego de guardar ===");
        try {
            deudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorCodigo(\"D001\") ===");
        try {
            Deudor encontrado = deudorRepositorio.buscarPorCodigo("D001");
            System.out.println("? Deudor encontrado:\n" + encontrado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba buscarPorNombre(\"Carlos\") ===");
        try {
            deudorRepositorio.buscarPorNombre("Carlos").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba editar() (Deudor) ===");
        try {
            Deudor deudor = deudorRepositorio.buscarPorCodigo("D001");
            deudor.setSegundoNombre("Freddy");
            deudor.setEmail("john.freddy@hotmail.com");
            deudor.setPassword("claveNuevaSegura");

            deudorRepositorio.editar(deudor);
            System.out.println("? Deudor actualizado:\n" + deudorRepositorio.buscarPorCodigo("D001"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba eliminar(\"D001\") ===");
        try {
            deudorRepositorio.eliminar("D001");
            System.out.println("✔️ Deudor eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Prueba obtenerTodos() después de eliminar ===");
        try {
            deudorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

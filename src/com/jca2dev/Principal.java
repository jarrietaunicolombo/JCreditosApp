package com.jca2dev;

import com.jca2dev.Dominio.Constantes.EstadoDeUsuarioEnum;
import com.jca2dev.Dominio.Entidades.Prestamista;
import com.jca2dev.Dominio.Entidades.Rol;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.PrestamistaRepositorio;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;

public class Principal {

    public static void main(String[] args) {
        // 1. Inicializar la base de datos simulada
        BaseDeDatosEnMemoria.iniciar();

        // 2. Crear instancias de repositorios
        RolRepositorio rolRepositorio = new RolRepositorio();
        PrestamistaRepositorio prestamistaRepositorio = new PrestamistaRepositorio();

        // === PRUEBA: OBTENER TODOS (inicial) ===
        System.out.println("\n=== Prestamistas actualmente en el sistema ===");
        try {
            prestamistaRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Sin registros: " + e.getMessage());
        }

        // === PRUEBA: GUARDAR ===
        System.out.println("\n=== Guardando nuevo prestamista ===");
        try {
            Rol rolPrestamista = rolRepositorio.buscarPorNombre("Prestamista");

            Prestamista nuevo = new Prestamista(
                "P001", "John", "Arrieta", "johncarlos@ejemplo.com", rolPrestamista
            );
            nuevo.setSegundoNombre("Carlos");
            nuevo.setSegundoApellido("Arrieta");
            nuevo.setPassword("clave123");
            nuevo.setEstado(EstadoDeUsuarioEnum.PENDIENTE);
            nuevo.setCapital(15000000);
            rolPrestamista.agregarUsuario(nuevo);
            prestamistaRepositorio.guardar(nuevo);
            System.out.println("✅ Prestamista guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        // Mostrar después de guardar
        System.out.println("\n=== Prestamistas después de guardar ===");
        try {
            prestamistaRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error al listar: " + e.getMessage());
        }

        // === PRUEBA: BUSCAR POR CÓDIGO ===
        System.out.println("\n=== Buscando prestamista por código 'P001' ===");
        try {
            Prestamista encontrado = prestamistaRepositorio.buscarPorCodigo("P001");
            System.out.println("✅ Prestamista encontrado:\n" + encontrado);
        } catch (Exception e) {
            System.out.println("Error al buscar por código: " + e.getMessage());
        }

        // === PRUEBA: BUSCAR POR NOMBRE ===
        System.out.println("\n=== Buscando prestamistas con nombre 'John' ===");
        try {
            prestamistaRepositorio.buscarPorNombre("John").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error al buscar por nombre: " + e.getMessage());
        }

        // === PRUEBA: EDITAR ===
        System.out.println("\n=== Editando prestamista con código 'P001' ===");
        try {
            Prestamista prestamista = prestamistaRepositorio.buscarPorCodigo("P001");
            prestamista.setEmail("john.actualizado@ejemplo.com");
            prestamista.setEstado(EstadoDeUsuarioEnum.ACTIVO);
            prestamista.setPassword("claveActualizada456");
            prestamista.setCapital(20000000);

            prestamistaRepositorio.editar(prestamista);
            System.out.println("✅ Prestamista actualizado:\n" + prestamistaRepositorio.buscarPorCodigo("P001"));
        } catch (Exception e) {
            System.out.println("Error al editar: " + e.getMessage());
        }

        // === PRUEBA: ELIMINAR ===
        System.out.println("\n=== Eliminando prestamista con código 'P001' ===");
        try {
            prestamistaRepositorio.eliminar("P001");
            System.out.println("✅ Prestamista eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        // Mostrar después de eliminar
        System.out.println("\n=== Prestamistas después de eliminar ===");
        try {
            prestamistaRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Sin registros: " + e.getMessage());
        }
    }
}

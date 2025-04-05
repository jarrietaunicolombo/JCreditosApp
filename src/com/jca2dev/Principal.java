package com.jca2dev;

import com.jca2dev.Dominio.Constantes.EstadoDePrestamoEnum;
import com.jca2dev.Dominio.Constantes.EstadoDeUsuarioEnum;
import com.jca2dev.Dominio.Constantes.TipoDeCuotaEnum;
import com.jca2dev.Dominio.Entidades.*;
import com.jca2dev.Mvc.Excepciones.EntidadNoEncontradaExcepcion;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.*;
import java.time.LocalDateTime;

public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        RolRepositorio rolRepositorio = new RolRepositorio();
        PrestamistaRepositorio prestamistaRepositorio = new PrestamistaRepositorio();
        DeudorRepositorio deudorRepositorio = new DeudorRepositorio();
        PrestamoRepositorio prestamoRepositorio = new PrestamoRepositorio();

        System.out.println("\n=== Prueba obtenerTodos() ===");
        try {
            prestamoRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Insertar prestamista y deudor ===");
        Prestamista prestamista = null;
         Deudor deudor = null;
        try {
            Rol rolPrestamista = rolRepositorio.buscarPorNombre("Prestamista");
            Rol rolDeudor = rolRepositorio.buscarPorNombre("Deudor");
            var codigoPrestamista = "P001";
            try {
                prestamista = prestamistaRepositorio.buscarPorCodigo(codigoPrestamista);
            } catch (EntidadNoEncontradaExcepcion e) {
                prestamista = new Prestamista(codigoPrestamista, "John", "Arrieta", "john@ejemplo.com", rolPrestamista);
                prestamista.setSegundoNombre("Carlos");
                prestamista.setSegundoApellido("Arrieta");
                prestamista.setPassword("abc123");
                prestamista.setCapital(1000000);
                prestamista.setEstado(EstadoDeUsuarioEnum.ACTIVO);
                rolPrestamista.agregarUsuario(prestamista);
                prestamistaRepositorio.guardar(prestamista);
            }
            var codigoDeudor = "D001";
            try {
                deudor = deudorRepositorio.buscarPorCodigo(codigoDeudor);
            } catch (EntidadNoEncontradaExcepcion e) {
                deudor = new Deudor(codigoDeudor, "Ana", "López", "ana@ejemplo.com", rolDeudor);
                deudor.setSegundoNombre("María");
                deudor.setSegundoApellido("García");
                deudor.setPassword("123456");
                deudor.setEstado(EstadoDeUsuarioEnum.ACTIVO);
                rolDeudor.agregarUsuario(deudor);
                deudorRepositorio.guardar(deudor);
            }
            System.out.println("Prestamista y Deudor guardados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        System.out.println("\n=== Prueba guardar() ===");
        try {
            
            Prestamo prestamo = new Prestamo(500000, 2.5, 12, TipoDeCuotaEnum.MES, prestamista, deudor);
            prestamo.setObservaciones("Préstamo personal para remodelación");
            
            prestamoRepositorio.guardar(prestamo);
            System.out.println("Préstamo guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar préstamo: " + e.getMessage());
        }

        System.out.println("\n=== Préstamos después de guardar ===");
        try {
            prestamoRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscar préstamo por ID ===");
        try {
            Prestamo encontrado = prestamoRepositorio.buscarPorId(1);
            System.out.println("Préstamo encontrado:\n" + encontrado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Editar préstamo ===");
        try {
            Prestamo encontrado = prestamoRepositorio.buscarPorId(1);
            encontrado.setFechaDesembolso(LocalDateTime.now());
            encontrado.setTasaInteres(3.0);
            encontrado.setSaldo(400000);
            encontrado.setEstado(EstadoDePrestamoEnum.APROBADO);
            encontrado.setObservaciones("Actualizado tras aprobación");

            prestamoRepositorio.editar(encontrado);
            System.out.println("Préstamo actualizado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al editar: " + e.getMessage());
        }

        System.out.println("\n=== Buscar préstamos por código de prestamista ===");
        try {
            prestamoRepositorio.buscarPorCodigoPrestamista("P001").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscar préstamos por código de deudor ===");
        try {
            prestamoRepositorio.buscarPorCodigoDeudor("D001").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Eliminar préstamo ===");
        try {
            prestamoRepositorio.eliminar(1);
            System.out.println("Préstamo eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        System.out.println("\n=== Préstamos después de eliminar ===");
        try {
            prestamoRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

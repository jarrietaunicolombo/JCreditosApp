package com.jca2dev;

import com.jca2dev.Dominio.Constantes.GeneroEnum;
import com.jca2dev.Dominio.Constantes.TipoDeIdentificacionEnum;
import com.jca2dev.Dominio.Entidades.Cobrador;
import com.jca2dev.Dominio.Entidades.Rol;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.CobradorRepositorio;
import com.jca2dev.Mvc.Modelo.Repositorios.Implementaciones.RolRepositorio;
import java.time.LocalDateTime;

public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        RolRepositorio rolRepositorio = new RolRepositorio();
        CobradorRepositorio cobradorRepositorio = new CobradorRepositorio();

        System.out.println("\n=== Cobradores actualmente en el sistema ===");
        try {
            cobradorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Guardando nuevo cobrador ===");
        try {
            Rol rolCobrador = rolRepositorio.buscarPorNombre("Cobrador");
            Cobrador nuevo = new Cobrador("C123", "John", "Arrieta", "john.cobrador@ejemplo.com", rolCobrador);
            nuevo.setSegundoNombre("Carlos");
            nuevo.setSegundoApellido("Arrieta");
            nuevo.setNumeroIdentificacion("111222333");
            nuevo.setTipoIdentificacion(TipoDeIdentificacionEnum.CEDULA);
            nuevo.setFechaExpedicion(LocalDateTime.now().minusYears(3));
            nuevo.setLugarExpedicion("Cartagena");
            nuevo.setGenero(GeneroEnum.MASCULINO);
            nuevo.setFoto("perfil_cobrador.jpg");
            nuevo.setDireccion("Calle 123 #45-67");
            nuevo.setTelefono("3001234567");
            nuevo.setVehiculo("Moto - Yamaha FZ");
            nuevo.setEfectividad(0.92);
            nuevo.setPorcentajeGanancia(15.0);
            nuevo.setPassword("clave123");

            cobradorRepositorio.guardar(nuevo);
            System.out.println("Cobrador guardado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }

        System.out.println("\n=== Cobradores después de guardar ===");
        try {
            cobradorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscar cobrador por código 'C123' ===");
        try {
            var resultado = cobradorRepositorio.buscarPorCodigo("C123");
            System.out.println("Cobrador encontrado:\n" + resultado);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Buscar cobradores por nombre 'John' ===");
        try {
            cobradorRepositorio.buscarPorNombre("John").forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Editar cobrador con código 'C123' ===");
        try {
            var existente = cobradorRepositorio.buscarPorCodigo("C123");
            existente.setTelefono("3129876543");
            existente.setVehiculo("Bicicleta eléctrica");
            existente.setEmail("john.cobrador.editado@ejemplo.com");
            cobradorRepositorio.editar(existente);
            System.out.println("Cobrador actualizado:\n" + existente);
        } catch (Exception e) {
            System.out.println("Error al editar: " + e.getMessage());
        }

        System.out.println("\n=== Eliminar cobrador con código 'C123' ===");
        try {
            cobradorRepositorio.eliminar("C123");
            System.out.println("Cobrador eliminado correctamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar: " + e.getMessage());
        }

        System.out.println("\n=== Cobradores después de eliminar ===");
        try {
            cobradorRepositorio.obtenerTodos().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}

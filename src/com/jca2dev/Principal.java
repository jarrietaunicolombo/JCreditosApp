package com.jca2dev;

import com.jca2dev.Dominio.Constantes.*;
import com.jca2dev.Dominio.Entidades.*;
import com.jca2dev.Mvc.Modelo.Persistencia.BaseDeDatosEnMemoria;

import java.time.LocalDateTime;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        BaseDeDatosEnMemoria.iniciar();

        if (BaseDeDatosEnMemoria.getUsuariosEnBd().isEmpty()) {
            crearDatosIniciales();
            BaseDeDatosEnMemoria.persistir();
        } else {
            mostrarDatos();
        }
    }

    public static void crearDatosIniciales() {
        // Obtener roles
        Rol rolPrestamista = BaseDeDatosEnMemoria.getRolesEnBd().stream().filter(r -> r.getNombre().equals("Prestamista")).findFirst().orElse(null);
        Rol rolDeudor = BaseDeDatosEnMemoria.getRolesEnBd().stream().filter(r -> r.getNombre().equals("Deudor")).findFirst().orElse(null);
        Rol rolCodeudor = BaseDeDatosEnMemoria.getRolesEnBd().stream().filter(r -> r.getNombre().equals("Codeudor")).findFirst().orElse(null);
        Rol rolCobrador = BaseDeDatosEnMemoria.getRolesEnBd().stream().filter(r -> r.getNombre().equals("Cobrador")).findFirst().orElse(null);

        // Crear Prestamista
        Prestamista prestamista = new Prestamista("PRE001", "Carlos", "Lopez", "carlos@mail.com", rolPrestamista);
        prestamista.setSegundoNombre("Eduardo");
        prestamista.setSegundoApellido("Mejía");
        prestamista.setPassword("123456");
        prestamista.setCapital(1000000);
        rolPrestamista.agregarUsuario(prestamista);

        // Crear Deudor
        Deudor deudor = new Deudor("DEU001", "Laura", "Martinez", "laura@mail.com", rolDeudor);
        deudor.setSegundoNombre("Isabel");
        deudor.setSegundoApellido("Ríos");
        deudor.setPassword("deudor123");
        deudor.setNumeroIdentificacion("1122334455");
        deudor.setTipoIdentificacion(TipoDeIdentificacionEnum.CEDULA);
        deudor.setFechaExpedicion(LocalDateTime.of(2015, 5, 20, 0, 0));
        deudor.setLugarExpedicion("Cartagena");
        deudor.setDireccion("Calle 45 #10-20");
        deudor.setTelefono("3012345678");
        deudor.setIngresos(1500000);
        deudor.setFoto("foto_laura.jpg");
        deudor.setGenero(GeneroEnum.FEMENINO);
        deudor.setFechaNacimiento(LocalDateTime.of(1990, 7, 15, 0, 0));
        deudor.setScore(750);
        deudor.setCapacidadDeuda(900000);
        deudor.setOficio("Vendedora");
        deudor.setEstaEmpleado(true);
        rolDeudor.agregarUsuario(deudor);

        // Crear Codeudor
        Codeudor codeudor = new Codeudor("COD001", "Luis", "Gomez", "luis@mail.com", rolCodeudor);
        codeudor.setSegundoNombre("Andrés");
        codeudor.setSegundoApellido("Castro");
        codeudor.setPassword("codeudor123");
        codeudor.setNumeroIdentificacion("9988776655");
        codeudor.setTipoIdentificacion(TipoDeIdentificacionEnum.CEDULA);
        codeudor.setFechaExpedicion(LocalDateTime.of(2012, 8, 10, 0, 0));
        codeudor.setLugarExpedicion("Barranquilla");
        codeudor.setDireccion("Calle 50 #15-30");
        codeudor.setTelefono("3023456789");
        codeudor.setIngresos(1200000);
        codeudor.setFoto("foto_luis.jpg");
        codeudor.setGenero(GeneroEnum.MASCULINO);
        codeudor.setFechaNacimiento(LocalDateTime.of(1985, 3, 22, 0, 0));
        codeudor.setScore(700);
        codeudor.setCapacidadDeuda(500000);
        codeudor.setOficio("Transportador");
        codeudor.setEsIndependiente(true);
        codeudor.setTieneVehiculo(true);
        rolCodeudor.agregarUsuario(codeudor);

        // Crear Cobrador
        Cobrador cobrador = new Cobrador("COB001", "Ana", "Rios", "ana@mail.com", rolCobrador);
        cobrador.setSegundoNombre("Marcela");
        cobrador.setSegundoApellido("Torres");
        cobrador.setPassword("cobrador123");
        cobrador.setNumeroIdentificacion("3344556677");
        cobrador.setTipoIdentificacion(TipoDeIdentificacionEnum.CEDULA);
        cobrador.setFechaExpedicion(LocalDateTime.of(2018, 1, 12, 0, 0));
        cobrador.setLugarExpedicion("Sincelejo");
        cobrador.setGenero(GeneroEnum.FEMENINO);
        cobrador.setFoto("foto_ana.jpg");
        cobrador.setDireccion("Av 80 #40-90");
        cobrador.setTelefono("3009876543");
        cobrador.setVehiculo("Moto");
        cobrador.setEfectividad(95.5);
        cobrador.setPorcentajeGanancia(0.10);
        rolCobrador.agregarUsuario(cobrador);

        // Inversión
        Inversion inversion = new Inversion("Inversión Inicial", 500000, prestamista);
        inversion.setProcedencia("Ahorros personales");
        inversion.setSaldo(500000);
        inversion.setObservaciones("Primera inversión del prestamista");
        inversion.setFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 30));
        prestamista.agregarInversion(inversion);

        // Préstamo
        Prestamo prestamo = new Prestamo(300000, 0.10, 10, TipoDeCuotaEnum.MES, prestamista, deudor);
        prestamo.setEstado(EstadoDePrestamoEnum.APROBADO);
        prestamo.setFechaSolicitud(LocalDateTime.of(2024, 1, 25, 10, 0));
        prestamo.setFechaDesembolso(LocalDateTime.of(2024, 1, 30, 10, 0));
        prestamo.setObservaciones("Préstamo para capital de trabajo");
        prestamista.agregarPrestamo(prestamo);
        deudor.agregarPrestamo(prestamo);

        // Pago
        Pago pago = new Pago(1, 33000, prestamo);
        pago.setFechaCobro(LocalDateTime.of(2024, 2, 1, 8, 0));
        pago.setFechaPago(LocalDateTime.of(2024, 2, 1, 13, 15));
        pago.setValorPagado(33000);
        pago.setSaldo(0);
        pago.setObservaciones("Pago puntual sin contratiempos");
        pago.setEstado(EstadoDePagoEnum.COMPLETO);
        prestamo.agregarPago(pago);

        // PrestamoInversion
        PrestamoInversion prestamoInversion = new PrestamoInversion(300000, inversion, prestamo);
        prestamoInversion.setId(1);
        inversion.agregarPrestamo(prestamoInversion);
        prestamo.agregarInversion(prestamoInversion);

        // CodeudorPrestamo
        CodeudorPrestamo codeudorPrestamo = new CodeudorPrestamo("Hermano", codeudor, prestamo);
        codeudorPrestamo.setId(1);
        codeudorPrestamo.setFechaAsignacion(LocalDateTime.of(2024, 1, 28, 9, 0));
        codeudor.agregarCodeudorPrestamo(codeudorPrestamo);
        prestamo.agregarCodeudor(codeudorPrestamo);

        // CobradorPago
        CobradorPago cobradorPago = new CobradorPago(cobrador, pago);
        cobradorPago.setId(1);
        cobradorPago.setFechaAsignacion(LocalDateTime.of(2024, 2, 1, 8, 30));
        cobradorPago.setObservaciones("Asignación para recaudo domiciliario");
        pago.agregarCobrador(cobradorPago);

        // Intento de Cobro
        IntentoDeCobro intento = new IntentoDeCobro(30000f, cobradorPago);
        intento.setId(1);
        intento.setFechaIntento(LocalDateTime.of(2024, 2, 1, 13, 0));
        intento.setExitoso(true);
        intento.setObservaciones("Pago parcial de cuota");
        cobradorPago.agregarIntentoDeCobro(intento);
    }

    public static void mostrarDatos() {
        for (Prestamista prestamista : BaseDeDatosEnMemoria.getPrestamistasEnBd()) {
            System.out.println("========= PRESTAMISTA =========");
            System.out.println(prestamista);
            System.out.println("Rol: " + prestamista.getRol());

            System.out.println("\n--- Inversiones ---");
            for (Inversion inv : prestamista.getInversiones()) {
                System.out.println(inv);
            }

            System.out.println("\n--- Préstamos ---");
            for (Prestamo prestamo : prestamista.getPrestamos()) {
                System.out.println(prestamo);
                System.out.println("  Deudor:\n    " + prestamo.getDeudor());
                System.out.println("  Codeudores:");
                for (CodeudorPrestamo cp : prestamo.getCodeudores()) {
                    System.out.println("    " + cp.getCodeudor());
                }
                System.out.println("  Inversiones usadas:");
                for (PrestamoInversion pi : prestamo.getInversiones()) {
                    System.out.println("    " + pi.getInversion().getNombre() + " - Monto: " + pi.getMontoUtilizado());
                }
                System.out.println("  Pagos:");
                for (Pago pago : prestamo.getPagos()) {
                    System.out.println("    " + pago);
                    System.out.println("    Cobradores:");
                    for (CobradorPago cpago : pago.getCobradores()) {
                        System.out.println("      " + cpago.getCobrador());
                        System.out.println("      ROL: " + cpago.getCobrador().getRol());
                        System.out.println("      Intentos:");
                        for (IntentoDeCobro i : cpago.getIntentoCobro()) {
                            System.out.println("        Valor pagado: " + i.getValorPagado()
                                    + ", Exitoso: " + i.isExitoso() + ", Fecha: " + i.getFechaIntento());
                        }
                    }
                }
            }
        }
    }
}

package com.jca2dev;

import com.jca2dev.Dominio.Constantes.EstadoDePagoEnum;
import com.jca2dev.Dominio.Constantes.EstadoDePrestamoEnum;
import com.jca2dev.Dominio.Constantes.GeneroEnum;
import com.jca2dev.Dominio.Constantes.TipoDeCuotaEnum;
import com.jca2dev.Dominio.Constantes.TipoDeIdentificacionEnum;
import com.jca2dev.Dominio.Entidades.*;
import java.time.LocalDateTime;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // 1. Crear Roles
        // Crear roles y establecer sus IDs
        Rol rolPrestamista = new Rol("Prestamista");
        rolPrestamista.setId(1);

        Rol rolDeudor = new Rol("Deudor");
        rolDeudor.setId(2);

        Rol rolCodeudor = new Rol("Codeudor");
        rolCodeudor.setId(3);

        Rol rolCobrador = new Rol("Cobrador");
        rolCobrador.setId(4);

        // Crear Prestamista
        Prestamista prestamista = new Prestamista("PRE001", "Carlos", "Lopez", "carlos@mail.com", rolPrestamista);
        // Setear propiedades heredadas
        prestamista.setPassword("123456");
        prestamista.setFechaCreacion(LocalDateTime.now());
        prestamista.setEstado("Activo");
        prestamista.setSegundoNombre("Eduardo");
        prestamista.setSegundoApellido("Mejía");
        // Setear propiedades propias
        prestamista.setCapital(1000000);
        // Registrar el prestamista en su rol
        rolPrestamista.agregarUsuario(prestamista);

        // Crear Deudor
        Deudor deudor = new Deudor("DEU001", "Laura", "Martinez", "laura@mail.com", rolDeudor);
        // Setear propiedades heredadas de Usuario
        deudor.setPassword("deudor123");
        deudor.setFechaCreacion(LocalDateTime.now());
        deudor.setEstado("Activo");
        deudor.setSegundoNombre("Isabel");
        deudor.setSegundoApellido("Ríos");
        // Setear propiedades propias
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
        deudor.setEsIndependiente(false);
        deudor.setEsPensionado(false);
        // Agregar al rol
        rolDeudor.agregarUsuario(deudor);

        // Crear Codeudor
        Codeudor codeudor = new Codeudor("COD001", "Luis", "Gomez", "luis@mail.com", rolCodeudor);
        // Propiedades heredadas de Usuario
        codeudor.setPassword("codeudor123");
        codeudor.setFechaCreacion(LocalDateTime.now());
        codeudor.setEstado("Activo");
        codeudor.setSegundoNombre("Andrés");
        codeudor.setSegundoApellido("Castro");
        // Propiedades heredadas de Deudor
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
        codeudor.setEstaEmpleado(false);
        codeudor.setEsIndependiente(true);
        codeudor.setEsPensionado(false);
        // Propiedades propias de Codeudor
        codeudor.setTieneVehiculo(true);
        codeudor.setTieneViviendaPropia(false);
        // Agregar al rol
        rolCodeudor.agregarUsuario(codeudor);

        // Crear Cobrador
        Cobrador cobrador = new Cobrador("COB001", "Ana", "Rios", "ana@mail.com", rolCobrador);
        // Propiedades heredadas de Usuario
        cobrador.setPassword("cobrador123");
        cobrador.setFechaCreacion(LocalDateTime.now());
        cobrador.setEstado("Activo");
        cobrador.setSegundoNombre("Marcela");
        cobrador.setSegundoApellido("Torres");
        // Propiedades propias
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
        // Agregar al rol
        rolCobrador.agregarUsuario(cobrador);

        // Crear una inversión asociada al prestamista
        Inversion inversion = new Inversion(1, "Inversión Inicial", 500000, prestamista);
        // Propiedades adicionales
        inversion.setProcedencia("Ahorros personales");
        inversion.setSaldo(500000);  // Inicialmente igual al monto
        inversion.setObservaciones("Primera inversión del prestamista");
        inversion.setFechaCreacion(LocalDateTime.of(2024, 1, 15, 10, 30));
        // Relación con el prestamista
        prestamista.agregarInversion(inversion);

        // Crear préstamo asociado al prestamista y al deudor
        Prestamo prestamo = new Prestamo(
                1,
                300000,
                0.10,
                10,
                TipoDeCuotaEnum.MES,
                prestamista,
                deudor
        );
        // Propiedades adicionales
        prestamo.setEstado(EstadoDePrestamoEnum.APROBADO);
        prestamo.setFechaSolicitud(LocalDateTime.of(2024, 1, 25, 10, 0));
        prestamo.setFechaDesembolso(LocalDateTime.of(2024, 1, 30, 10, 0));
        prestamo.setObservaciones("Préstamo para capital de trabajo");
        // Sincronizar relaciones
        prestamista.agregarPrestamo(prestamo);
        deudor.agregarPrestamo(prestamo);

        // Crear pago asociado al préstamo
        Pago pago = new Pago(1, 1, 33000, prestamo);
        // Propiedades adicionales
        pago.setFechaCobro(LocalDateTime.of(2024, 2, 1, 8, 0));
        pago.setFechaPago(LocalDateTime.of(2024, 2, 1, 13, 15));
        pago.setValorPagado(33000);
        pago.setSaldo(0); // Como pagó completo
        pago.setObservaciones("Pago puntual sin contratiempos");
        pago.setEstado(EstadoDePagoEnum.COMPLETO);
        // Relación con el préstamo
        prestamo.agregarPago(pago);

        // Relacionar préstamo con inversión a través de PrestamoInversion
        PrestamoInversion prestamoInversion = new PrestamoInversion(300000, inversion, prestamo);
        prestamoInversion.setId(1); // Asegúrate de que sea único
        // Relación bidireccional
        inversion.agregarPrestamo(prestamoInversion);
        prestamo.agregarInversion(prestamoInversion);

        // Relacionar codeudor con el préstamo
        CodeudorPrestamo codeudorPrestamo = new CodeudorPrestamo("Hermano", codeudor, prestamo);
        codeudorPrestamo.setId(1);
        codeudorPrestamo.setActivo(true);
        codeudorPrestamo.setFechaAsignacion(LocalDateTime.of(2024, 1, 28, 9, 0));
        // Sincronizar relaciones
        codeudor.agregarCodeudorPrestamo(codeudorPrestamo);
        prestamo.agregarCodeudor(codeudorPrestamo);

        // Relacionar Cobrador con Pago
        CobradorPago cobradorPago = new CobradorPago(cobrador, pago);
        cobradorPago.setId(1);
        cobradorPago.setFechaAsignacion(LocalDateTime.of(2024, 2, 1, 8, 30));
        cobradorPago.setObservaciones("Asignación para recaudo domiciliario");
        // Sincronizar con el pago
        pago.agregarCobrador(cobradorPago);

        // Crear intento de cobro exitoso asociado a un cobradorPago
        IntentoDeCobro intento = new IntentoDeCobro(30000f, cobradorPago);
        intento.setId(1);
        intento.setFechaIntento(LocalDateTime.of(2024, 2, 1, 13, 0));
        intento.setExitoso(true);
        intento.setObservaciones("Pago parcial de cuota");
        // Sincronizar con CobradorPago
        cobradorPago.agregarIntentoDeCobro(intento);

        /*
         NAVEGAR POR LOS OBJETOS CREADOS A PARTIR DE LAS RELACIONES 
         */
        System.out.println("========= PRESTAMISTA =========");
        System.out.println(prestamista);

        // Inversiones
        System.out.println("\\n--- Inversiones ---");
        for (Inversion inv : prestamista.getInversiones()) {
            System.out.println(inv);
        }

        // Préstamos
        System.out.println("\\n--- Préstamos ---");
        for (Prestamo prestamoItem : prestamista.getPrestamos()) {
            System.out.println(prestamoItem);

            // Deudor
            System.out.println("  Deudor:");
            System.out.println("    " + prestamoItem.getDeudor());

            // Codeudores
            System.out.println("  Codeudores:");
            for (CodeudorPrestamo cp : prestamoItem.getCodeudores()) {
                System.out.println("    " + cp.getCodeudor());
            }

            // Inversiones
            System.out.println("  Inversiones usadas:");
            for (PrestamoInversion pi : prestamoItem.getInversiones()) {
                System.out.println("    " + pi.getInversion().getNombre() + " - Monto: " + pi.getMontoUtilizado());
            }

            // Pagos
            System.out.println("  Pagos:");
            for (Pago p : prestamoItem.getPagos()) {
                System.out.println("    " + p);

                // Cobradores
                System.out.println("    Cobradores:");
                for (CobradorPago cpago : p.getCobradores()) {
                    System.out.println("      " + cpago.getCobrador());

                    // Intentos de Cobro
                    System.out.println("      Intentos:");
                    for (IntentoDeCobro i : cpago.getIntentoCobro()) {
                        System.out.println("        Valor pagado: " + i.getValorPagado()
                                + ", Exitoso: " + i.isExitoso()
                                + ", Fecha: " + i.getFechaIntento());
                    }
                }
            }
        }

    }

}

package com.jca2dev.Mvc.Modelo.Persistencia;

import com.jca2dev.Dominio.Entidades.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class BaseDeDatosEnMemoria {

    // Carpeta donde se guarda el archivo
    private static final String NOMBRE_CARPETA = "JCreditosAppBd";
    // Nombre del archivo de persistencia
    private static final String NOMBRE_ARCHIVO = "JCreditosAppBd.dat";
    // Coleccion para simular las tablas de una BD relacional
    private static final List<Rol> rolesEnBd = new ArrayList<>();
    private static final List<Usuario> usuariosEnBd = new ArrayList<>();
    private static final List<Prestamista> prestamistasEnBd = new ArrayList<>();
    private static final List<Deudor> deudoresEnBd = new ArrayList<>();
    private static final List<Codeudor> codeudoresEnBd = new ArrayList<>();
    private static final List<Cobrador> cobradoresEnBd = new ArrayList<>();
    private static final List<Inversion> inversionesEnBd = new ArrayList<>();
    private static final List<Pago> pagosEnBd = new ArrayList<>();
    private static final List<IntentoDeCobro> intentosDeCobrosEnBd = new ArrayList<>();
    private static final List<CodeudorPrestamo> codeudoresPestamosEnBd = new ArrayList<>();
    private static final List<CobradorPago> cobradoresPagosEnBd = new ArrayList<>();
    private static final List<PrestamoInversion> prestamosInversionesEnBd = new ArrayList<>();
    private static final List<Prestamo> prestamosEnBd = new ArrayList<>();

    /**
     * Simula la conexion a una Base de datos
     */
    public static void iniciar() {
        System.out.println("Cargando los datos desde la BD....");
        List<Rol> rolesRecuperados = recuperarDatos();
        if (rolesRecuperados.isEmpty()) {
            insertarRolesPorIniciales();
            guardarDatos(rolesEnBd);
        } else {
            rolesEnBd.addAll(rolesRecuperados);
            calibrarIncrementoDeTodosLosIDs();
        }
    }

    /**
     * Simula el guardado fisico (de memoria a archivos) de los datos en la BD
     */
    public static void persistir() {
        System.out.println("Guardando los datos en la BD....");
        guardarDatos(rolesEnBd);
    }

    // Gets
    public static List<Rol> getRolesEnBd() {
        return rolesEnBd;
    }

    public static List<Usuario> getUsuariosEnBd() {
        List<Usuario> lista = new ArrayList<>();
        for (Rol rol : rolesEnBd) {
            lista.addAll(rol.getUsuarios());
        }
        return lista;
    }

    public static List<Prestamista> getPrestamistasEnBd() {
        List<Prestamista> lista = new ArrayList<>();
        for (Rol rol : rolesEnBd) {
            for (Usuario u : rol.getUsuarios()) {
                if (u instanceof Prestamista p) {
                    lista.add(p);
                }
            }
        }
        return lista;
    }

    public static List<Deudor> getDeudoresEnBd() {
        List<Deudor> lista = new ArrayList<>();
        for (Rol rol : rolesEnBd) {
            for (Usuario u : rol.getUsuarios()) {
                if (u instanceof Deudor d) {
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public static List<Codeudor> getCodeudoresEnBd() {
        List<Codeudor> lista = new ArrayList<>();
        for (Rol rol : rolesEnBd) {
            for (Usuario u : rol.getUsuarios()) {
                if (u instanceof Codeudor c) {
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public static List<Cobrador> getCobradoresEnBd() {
        List<Cobrador> lista = new ArrayList<>();
        for (Rol rol : rolesEnBd) {
            for (Usuario u : rol.getUsuarios()) {
                if (u instanceof Cobrador c) {
                    lista.add(c);
                }
            }
        }
        return lista;
    }

    public static List<Inversion> getInversionesEnBd() {
        List<Inversion> lista = new ArrayList<>();
        for (Prestamista p : getPrestamistasEnBd()) {
            lista.addAll(p.getInversiones());
        }
        return lista;
    }

    public static List<Pago> getPagosEnBd() {
        List<Pago> lista = new ArrayList<>();
        for (Prestamista p : getPrestamistasEnBd()) {
            for (Prestamo prestamo : p.getPrestamos()) {
                lista.addAll(prestamo.getPagos());
            }
        }
        return lista;
    }

    public static List<IntentoDeCobro> getIntentosDeCobrosEnBd() {
        List<IntentoDeCobro> lista = new ArrayList<>();
        for (Pago pago : getPagosEnBd()) {
            for (CobradorPago cp : pago.getCobradores()) {
                lista.addAll(cp.getIntentoCobro());
            }
        }
        return lista;
    }

    public static List<CodeudorPrestamo> getCodeudoresPestamosEnBd() {
        List<CodeudorPrestamo> lista = new ArrayList<>();
        for (Prestamista p : getPrestamistasEnBd()) {
            for (Prestamo prestamo : p.getPrestamos()) {
                lista.addAll(prestamo.getCodeudores());
            }
        }
        return lista;
    }

    public static List<CobradorPago> getCobradoresPagosEnBd() {
        List<CobradorPago> lista = new ArrayList<>();
        for (Pago pago : getPagosEnBd()) {
            lista.addAll(pago.getCobradores());
        }
        return lista;
    }

    public static List<PrestamoInversion> getPrestamosInversionesEnBd() {
        List<PrestamoInversion> lista = new ArrayList<>();
        for (Prestamista p : getPrestamistasEnBd()) {
            for (Prestamo prestamo : p.getPrestamos()) {
                lista.addAll(prestamo.getInversiones());
            }
        }
        return lista;
    }

    /**
     * Simula la insercion en la BD de los Roles iniciales del sistema
     */
    private static void insertarRolesPorIniciales() {
        rolesEnBd.clear();
        rolesEnBd.add(new Rol("Admin"));
        rolesEnBd.add(new Rol("Prestamista"));
        rolesEnBd.add(new Rol("Deudor"));
        rolesEnBd.add(new Rol("Codeudor"));
        rolesEnBd.add(new Rol("Cobrador"));
        rolesEnBd.add(new Rol("Usuario"));
    }

    /**
     * Limpia todas las colecciones que simulan las tablas de la base de datos.
     * Útil para reiniciar los datos en memoria durante pruebas o
     * demostraciones.
     */
    public static void reiniciar() {
        rolesEnBd.clear();
        usuariosEnBd.clear();
        prestamistasEnBd.clear();
        deudoresEnBd.clear();
        codeudoresEnBd.clear();
        cobradoresEnBd.clear();
        inversionesEnBd.clear();
        pagosEnBd.clear();
        intentosDeCobrosEnBd.clear();
        codeudoresPestamosEnBd.clear();
        cobradoresPagosEnBd.clear();
        prestamosInversionesEnBd.clear();
        prestamosEnBd.clear();
        System.out.println("Base de datos en memoria reiniciada correctamente.");
    }

    public static void guardarDatos(List<Rol> listaRoles) {
        String rutaArchivo = BaseDeDatosEnMemoria.obtenerRutaArchivoPersistencia();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(listaRoles);
            oos.flush();
            System.out.println("Datos guardados correctamente en: " + rutaArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Rol> recuperarDatos() {
        String rutaArchivo = BaseDeDatosEnMemoria.obtenerRutaArchivoPersistencia();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            List<Rol> listaRoles = (List<Rol>) ois.readObject();
            System.out.println("Datos recuperados correctamente desde: " + rutaArchivo);
            return listaRoles;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al recuperar los datos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static String obtenerRutaArchivoPersistencia() {
        String rutaUsuario = System.getProperty("user.home");
        String rutaCarpeta = rutaUsuario + File.separator + NOMBRE_CARPETA;
        File carpeta = new File(rutaCarpeta);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        return rutaCarpeta + File.separator + NOMBRE_ARCHIVO;
    }

    private static void calibrarIncrementoDeTodosLosIDs() {
        // Rol
        int proximoIdRol = rolesEnBd.stream()
                .mapToInt(Rol::getId)
                .max()
                .orElse(0) + 1;
        Rol.calibrarIncrementoDeId(proximoIdRol);

        // Prestamo
        int proximoIdPrestamo = prestamosEnBd.stream()
                .mapToInt(Prestamo::getId)
                .max()
                .orElse(0) + 1;
        Prestamo.calibrarIncrementoDeId(proximoIdPrestamo);

        // Inversion
        int proximoIdInversion = inversionesEnBd.stream()
                .mapToInt(Inversion::getId)
                .max()
                .orElse(0) + 1;
        Inversion.calibrarIncrementoDeId(proximoIdInversion);

        // Pago
        int proximoIdPago = pagosEnBd.stream()
                .mapToInt(Pago::getId)
                .max()
                .orElse(0) + 1;
        Pago.calibrarIncrementoDeId(proximoIdPago);

        // PrestamoInversion
        int proximoIdPrestamoInversion = prestamosInversionesEnBd.stream()
                .mapToInt(PrestamoInversion::getId)
                .max()
                .orElse(0) + 1;
        PrestamoInversion.calibrarIncrementoDeId(proximoIdPrestamoInversion);

        // CobradorPago
        int proximoIdCobradorPago = cobradoresPagosEnBd.stream()
                .mapToInt(CobradorPago::getId)
                .max()
                .orElse(0) + 1;
        CobradorPago.calibrarIncrementoDeId(proximoIdCobradorPago);

        // IntentoDeCobro
        int proximoIdIntentoCobro = intentosDeCobrosEnBd.stream()
                .mapToInt(IntentoDeCobro::getId)
                .max()
                .orElse(0) + 1;
        IntentoDeCobro.calibrarIncrementoDeId(proximoIdIntentoCobro);

        // CodeudorPrestamo
        int proximoIdCodeudorPrestamo = codeudoresPestamosEnBd.stream()
                .mapToInt(CodeudorPrestamo::getId)
                .max()
                .orElse(0) + 1;
        CodeudorPrestamo.calibrarIncrementoDeId(proximoIdCodeudorPrestamo);

 
    }

}

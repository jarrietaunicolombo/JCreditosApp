package com.jca2dev.Mvc.Modelo.Persistencia;

import com.jca2dev.Dominio.Entidades.*;
import java.util.*;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class BaseDeDatosEnMemoria {

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
        // Aquí colocaremos el codigo para cargar los objetos desde archivos
        System.out.println("Cargando los datos desde la BD....");
        insertarRolesPorIniciales();
    }

    /**
     * Simula el guardado fisico (de memora a archivos) de los datos en la BD
     */
    public static void persistir() {
        // Aqui colocaremos el codigo para guardar los objetos en archivo
        System.out.println("Guardando los datos en la BD....");

    }

    // Gets
    public static List<Rol> getRolesEnBd() {
        return rolesEnBd;
    }

    public static List<Usuario> getUsuariosEnBd() {
        return usuariosEnBd;
    }

    public static List<Prestamista> getPrestamistasEnBd() {
        return prestamistasEnBd;
    }

    public static List<Deudor> getDeudoresEnBd() {
        return deudoresEnBd;
    }

    public static List<Codeudor> getCodeudoresEnBd() {
        return codeudoresEnBd;
    }

    public static List<Cobrador> getCobradoresEnBd() {
        return cobradoresEnBd;
    }

    public static List<Inversion> getInversionesEnBd() {
        return inversionesEnBd;
    }

    public static List<Pago> getPagosEnBd() {
        return pagosEnBd;
    }

    public static List<IntentoDeCobro> getIntentosDeCobrosEnBd() {
        return intentosDeCobrosEnBd;
    }

    public static List<CodeudorPrestamo> getCodeudoresPestamosEnBd() {
        return codeudoresPestamosEnBd;
    }

    public static List<CobradorPago> getCobradoresPagosEnBd() {
        return cobradoresPagosEnBd;
    }

    public static List<PrestamoInversion> getPrestamosInversionesEnBd() {
        return prestamosInversionesEnBd;
    }

    /**
     * Simula la insercion en la BD de los Roles iniciales del sistema
     */
    private static void insertarRolesPorIniciales() {
        rolesEnBd.clear();
        rolesEnBd.add(new Rol(1, "Admin"));
        rolesEnBd.add(new Rol(2, "Prestamista"));
        rolesEnBd.add(new Rol(3, "Deudor"));
        rolesEnBd.add(new Rol(4, "Codeudor"));
        rolesEnBd.add(new Rol(5, "Cobrador"));
        rolesEnBd.add(new Rol(6, "Usuario"));
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

}

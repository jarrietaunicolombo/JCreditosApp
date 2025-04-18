package com.jca2dev.UtilidadesComunes;

/**
 * Clase utilitaria para validar el formato de datos de entrada. Incluye
 * validaciones básicas de código, nombre, y email.
 *
 * Este enfoque sigue el patrón MVC clásico, separando las reglas de formato del
 * modelo de dominio.
 *
 * @author John
 */
public class ValidadorDeFormato {

    /**
     * Valida que el código contenga únicamente letras y números, sin espacios
     * ni caracteres especiales.
     */
    public static void validarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código es requerido.");
        }

        for (char c : codigo.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                throw new IllegalArgumentException(
                        "El código solo puede contener letras y números, sin espacios ni caracteres especiales.");
            }
        }

        // Versión con expresión regular (comentada para fines educativos):
        // if (!codigo.matches("^[a-zA-Z0-9]+$")) {
        //     throw new IllegalArgumentException("El código solo puede contener letras y números.");
        // }
    }

    /**
     * Valida que el nombre contenga únicamente letras y espacios. No se
     * permiten números ni caracteres especiales.
     */
    public static void validarNombre(String nombre, String nombreDelCambo) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El " + nombreDelCambo + " es requerido.");
        }

        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new IllegalArgumentException(
                        "El " + nombreDelCambo + " solo puede contener letras y espacios.");
            }
        }

        // Versión con expresión regular (comentada para fines educativos):
        // if (!nombre.matches("^[a-zA-Z ]+$")) {
        //     throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
        // }
    }

    /**
     * Valida que el email tenga exactamente una arroba (@), al menos un punto
     * después de la arroba, y solo contenga letras, números, guiones, guiones
     * bajos y puntos. Ni la arroba ni el punto pueden estar al inicio ni al
     * final.
     */
    public static void validarEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email es requerido.");
        }

        int arrobas = 0;
        int posicionArroba = -1;

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);

            if (c == '@') {
                arrobas++;
                posicionArroba = i;
            } else if (!Character.isLetterOrDigit(c) && c != '-' && c != '_' && c != '.') {
                throw new IllegalArgumentException("El email solo puede contener letras, números, guiones, guiones bajos y puntos.");
            }
        }

        if (arrobas != 1) {
            throw new IllegalArgumentException("El email debe contener exactamente una arroba.");
        }

        if (posicionArroba == 0 || posicionArroba == email.length() - 1) {
            throw new IllegalArgumentException("La arroba no puede estar al inicio ni al final del email.");
        }

        if (email.startsWith(".") || email.endsWith(".")) {
            throw new IllegalArgumentException("El punto no puede estar al inicio ni al final del email.");
        }

        if (!email.substring(posicionArroba).contains(".")) {
            throw new IllegalArgumentException("El email debe contener al menos un punto después de la arroba.");
        }

        // Regex (comentado para comparación)
        // if (!email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,}$")) {
        //     throw new IllegalArgumentException("El email no tiene un formato válido.");
        // }
    }

    /**
     * Valida que el monto sea positivo.
     */
    public static void validarMonto(double monto) {
        if (monto <= 0) {
            String mensaje = "El monto del préstamo debe ser mayor a cero.";
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida que la tasa de interés sea un número real positivo.
     */
    public static void validarTasaInteres(double tasaInteres) {
        if (tasaInteres <= 0 || tasaInteres > 100) {
            String mensaje = "La tasa de interés debe ser mayor a cero y menor o igual a 100.";
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida que el número de cuotas sea positivo.
     */
    public static void validarNumeroCuotas(int numeroCuotas) {
        if (numeroCuotas <= 0) {
            String mensaje = "El número de cuotas debe ser mayor que cero.";
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida que el saldo sea igual o menor al monto del préstamo.
     */
    public static void validarSaldo(double saldo, double monto) {
        if (saldo < 0 || saldo > monto) {
            String mensaje = "El saldo no puede ser negativo ni mayor al monto del préstamo.";
            throw new IllegalArgumentException(mensaje);
        }
    }

}

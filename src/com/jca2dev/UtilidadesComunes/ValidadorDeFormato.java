package com.jca2dev.UtilidadesComunes;

/**
 * Clase utilitaria para validar el formato de datos de entrada.
 * Incluye validaciones básicas de código, nombre, y email.
 * 
 * Este enfoque sigue el patrón MVC clásico, separando las reglas
 * de formato del modelo de dominio.
 * 
 * @author John
 */
public class ValidadorDeFormato {

    /**
     * Valida que el código contenga únicamente letras y números, sin espacios
     * ni caracteres especiales.
     */
    private void validarFormatoCodigo(String codigo) {
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
    private void validarFormatoNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es requerido.");
        }

        for (char c : nombre.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                throw new IllegalArgumentException(
                        "El nombre solo puede contener letras y espacios.");
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
    private void validarFormatoEmail(String email) {
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
}

package com.jca2dev.Dominio.Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class CodeudorPrestamo  implements Serializable{

    private Integer id;
    private String relacionDeudor;
    private boolean activo;
    private LocalDateTime fechaAsignacion;

    private Codeudor codeudor;
    private Prestamo prestamo;
    // Propiedades de la clase, su valor es el mismo para todos los objetos
    private static AtomicInteger incrementoDeId = new AtomicInteger(1);

    public CodeudorPrestamo(String relacionDeudor, Codeudor codeudor, Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener ID invalido.";
            throw new IllegalArgumentException(mensaje);
        }

        if (codeudor == null || codeudor.getCodigo() == null || codeudor.getCodigo().trim().isEmpty()) {
            var mensaje = "El codeudor no puede ser nulo ni tener Codigo vacío.";
            throw new IllegalArgumentException(mensaje);
        }
        this.id = incrementoDeId.getAndIncrement();
        this.relacionDeudor = relacionDeudor;
        this.activo = true;
        this.fechaAsignacion = LocalDateTime.now();
        this.codeudor = codeudor;
        this.prestamo = prestamo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelacionDeudor() {
        return relacionDeudor;
    }

    public void setRelacionDeudor(String relacionDeudor) {
        this.relacionDeudor = relacionDeudor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El codeudor no puede ser nulo ni tener Id invacío.";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
        prestamo.agregarCodeudor(this);
    }

    public Codeudor getCodeudor() {
        return codeudor;
    }

    public void setCodeudor(Codeudor codeudor) {
        if (codeudor == null || codeudor.getCodigo() == null || codeudor.getCodigo().trim().isEmpty()) {
            var mensaje = "El codeudor no puede ser nulo ni tener Codigo vacío.";
            throw new IllegalArgumentException(mensaje);
        }
        this.codeudor = codeudor;
        codeudor.agregarCodeudorPrestamo(this);
    }

    void sincronizarCodeudor(Codeudor codeudor) {
        if (codeudor == null || codeudor.getCodigo() == null || codeudor.getCodigo().trim().isEmpty()) {
            var mensaje = "El Codeudor no puede ser nulo ni tener Codigo invalido.";
            throw new IllegalArgumentException(mensaje);
        }
        this.codeudor = codeudor;
    }

    void sincronizarPrestamo(Prestamo prestamo) {
        if (prestamo == null || prestamo.getId() == null || prestamo.getId() <= 0) {
            var mensaje = "El Prestamo no puede ser nulo ni tener Id invalido.";
            throw new IllegalArgumentException(mensaje);
        }
        this.prestamo = prestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CodeudorPrestamo that = (CodeudorPrestamo) o;

        return Objects.equals(codeudor, that.codeudor)
                && Objects.equals(prestamo, that.prestamo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeudor, prestamo);
    }

    @Override
    public String toString() {
        return "CodeudorPrestamo\n"
                + "-----------------\n"
                + "Relación con Deudor: " + relacionDeudor + "\n"
                + "Activo: " + activo + "\n"
                + "Fecha de Asignación: " + fechaAsignacion + "\n"
                + "Codeudor: " + (codeudor != null ? codeudor.getCodigo() : "null") + "\n"
                + "Prestamo ID: " + (prestamo != null ? prestamo.getId() : "null") + "\n";
    }
    
     public static void calibrarIncrementoDeId(int nexId){
        incrementoDeId.set(nexId);
    }
}

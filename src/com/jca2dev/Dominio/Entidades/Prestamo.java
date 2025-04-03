package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.EstadoDePrestamoEnum;
import com.jca2dev.Dominio.Constantes.TipoDeCuotaEnum;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Prestamo  implements Serializable{

    private Integer id;
    private LocalDateTime fechaSolicitud;
    private EstadoDePrestamoEnum estado;
    private double monto;
    private LocalDateTime fechaDesembolso;
    private double tasaInteres;
    private int numeroCuotas;
    private TipoDeCuotaEnum tipoDeCuota;
    private double saldo;
    private String observaciones;

    private Prestamista prestamista;
    private Deudor deudor;
    private List<CodeudorPrestamo> codeudores;
    private List<PrestamoInversion> inversiones;
    private List<Pago> pagos;
    // Propiedades de la clase, su valor es el mismo para todos los objetos
    private static AtomicInteger incrementoDeId = new AtomicInteger(1);

    public Prestamo(double monto, double tasaInteres, int numeroCuotas,
            TipoDeCuotaEnum tipoDeCuota, Prestamista prestamista, Deudor deudor) {
       this.id = incrementoDeId.getAndIncrement();
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
        this.tipoDeCuota = tipoDeCuota;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = EstadoDePrestamoEnum.PENDIENTE;
        this.saldo = monto;
        this.prestamista = prestamista;
        this.deudor = deudor;
        codeudores = new ArrayList<>();
        inversiones = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoDePrestamoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoDePrestamoEnum estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaDesembolso() {
        return fechaDesembolso;
    }

    public void setFechaDesembolso(LocalDateTime fechaDesembolso) {
        this.fechaDesembolso = fechaDesembolso;
    }

    public double getTasaInteres() {
        return tasaInteres;
    }

    public void setTasaInteres(double tasaInteres) {
        this.tasaInteres = tasaInteres;
    }

    public int getNumeroCuotas() {
        return numeroCuotas;
    }

    public void setNumeroCuotas(int numeroCuotas) {
        this.numeroCuotas = numeroCuotas;
    }

    public TipoDeCuotaEnum getTipoDeCuota() {
        return tipoDeCuota;
    }

    public void setTipoDeCuota(TipoDeCuotaEnum tipoDeCuota) {
        this.tipoDeCuota = tipoDeCuota;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Prestamista getPrestamista() {
        return prestamista;
    }

    public void setPrestamista(Prestamista prestamista) {
        if (prestamista == null || prestamista.getCodigo() == null
                || prestamista.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El Prestamista no puede ser nulo ni tener un Codigo invalido.");
        }

        this.prestamista = prestamista;
        prestamista.agregarPrestamo(this);
    }

    public Deudor getDeudor() {
        return deudor;
    }

    public void setDeudor(Deudor deudor) {
        if (deudor == null || deudor.getCodigo() == null
                || deudor.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El Deudor no puede ser nulo ni tener un Codigo invalido.");
        }

        this.deudor = deudor;
        deudor.agregarPrestamo(this);
    }

    public List<CodeudorPrestamo> getCodeudores() {
        return codeudores;
    }

    public void setCodeudores(List<CodeudorPrestamo> codeudores) {
        this.codeudores = codeudores;
    }

    public List<PrestamoInversion> getInversiones() {
        return inversiones;
    }

    public void setInversiones(List<PrestamoInversion> inversiones) {
        this.inversiones = inversiones;
    }

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    public void agregarCodeudor(CodeudorPrestamo codeudorPrestamo) {
        if (codeudorPrestamo == null || codeudorPrestamo.getId() == null || codeudorPrestamo.getId() <= 0
                || codeudorPrestamo.getCodeudor().getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El Codeudor no puede ser nulo ni tener un codigo invalido.");
        }

        if (!Objects.equals(codeudorPrestamo.getPrestamo().getId(), this.id)) {
            throw new IllegalArgumentException("El Codeudor no corresponde al Prestamo asignado.");
        }

        var existe = this.codeudores.stream()
                .anyMatch(c -> c != null
                && Objects.equals(c.getId(), codeudorPrestamo.getId())
                && c.getCodeudor().getCodigo()
                        .equalsIgnoreCase(codeudorPrestamo.getCodeudor().getCodigo()));

        if (!existe) {
            this.codeudores.add(codeudorPrestamo);
        }

        codeudorPrestamo.sincronizarPrestamo(this);
    }

    public void agregarInversion(PrestamoInversion prestamoInverson) {
        if (prestamoInverson == null || prestamoInverson.getId() == null || prestamoInverson.getId() <= 0
                || prestamoInverson.getInversion().getId() == null || prestamoInverson.getInversion().getId() <= 0) {
            throw new IllegalArgumentException("La inversion no puede ser nula ni tener un Id invalido.");
        }

        if (!Objects.equals(prestamoInverson.getPrestamo().getId(), this.getId())) {
            throw new IllegalArgumentException("La Inversion no corresponde al Prestamo asignado.");
        }

        if (!prestamoInverson.getPrestamo().getPrestamista().getCodigo()
                .equalsIgnoreCase(this.prestamista.getCodigo())) {
            throw new IllegalArgumentException("La Inversion no corresponde al Prestamista del prestamo asignado");
        }

        var existe = this.inversiones.stream()
                .anyMatch(i -> i != null
                && Objects.equals(i.getId(), prestamoInverson.getId()));

        if (!existe) {
            this.inversiones.add(prestamoInverson);
        }

        prestamoInverson.sincronizarPrestamo(this);
    }

    public void agregarPago(Pago pago) {
        if (pago == null || pago.getId() == null || pago.getId() <= 0) {
            throw new IllegalArgumentException("El Pago no puede ser nulo ni tener un Id invalido.");
        }

        if (!Objects.equals(pago.getPrestamo().getId(), this.id)) {
            throw new IllegalArgumentException("El Pago no corresponde al Prestamo asignado.");
        }

        var existe = this.pagos.stream()
                .anyMatch(p -> Objects.equals(p.getId(), pago.getId()));

        if (!existe) {
            this.pagos.add(pago);
        }

        pago.sincronizarPrestamo(this);
    }

    void sincronizarDeudor(Deudor deudor) {
        if (deudor == null || deudor.getCodigo() == null
                || deudor.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El Deudor no puede ser nulo ni tener un Codigo invalido.");
        }

        this.deudor = deudor;
    }

    void sincronizarPrestamista(Prestamista prestamista) {
        if (prestamista == null || prestamista.getCodigo() == null
                || prestamista.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("El Prestamista no puede ser nulo ni tener un Codigo invalido.");
        }

        this.prestamista = prestamista;
    }

    @Override
    public String toString() {
        return "Prestamo\n"
                + "-----------------\n"
                + "ID: " + id + "\n"
                + "Fecha Solicitud: " + fechaSolicitud + "\n"
                + "Estado: " + estado + "\n"
                + "Monto: " + monto + "\n"
                + "Fecha Desembolso: " + fechaDesembolso + "\n"
                + "Tasa Interés: " + tasaInteres + "\n"
                + "Número Cuotas: " + numeroCuotas + "\n"
                + "Tipo Cuota: " + tipoDeCuota + "\n"
                + "Saldo: " + saldo + "\n"
                + "Observaciones: " + observaciones + "\n"
                + "Prestamista: " + (prestamista != null ? prestamista.getCodigo() : "null") + "\n"
                + "Deudor: " + (deudor != null ? deudor.getCodigo() : "null") + "\n"
                + "Codeudores: " + (codeudores != null ? codeudores.size() : 0) + "\n"
                + "Inversiones usadas: " + (inversiones != null ? inversiones.size() : 0) + "\n"
                + "Pagos generados: " + (pagos != null ? pagos.size() : 0) + "\n";
    }
    
     public static void calibrarIncrementoDeId(int nexId){
        incrementoDeId.set(nexId);
    }
}

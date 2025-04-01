package com.jca2dev.Dominio.Entidades;

import java.time.LocalDateTime;
// import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Prestamo {

    private String id;
    private LocalDateTime fechaSolicitud;
    private String estado;
    private double monto;
    private LocalDateTime fechaDesembolso;
    private double tasaInteres;
    private int numeroCuotas;
//    private TipoDeCuotaEnum tipoDeCuota;
    private double saldo;
    private String observaciones;

    // Relaciones comentadas
    // private Prestamista prestamista;
    // private Deudor deudor;
    // private List<CoodeudorPrestamo> coodeudores;
    // private List<PrestamoInversion> inversiones;
    // private List<Pago> pagos;

    public Prestamo(String id, double monto, double tasaInteres, int numeroCuotas /*, TipoDeCuotaEnum tipoDeCuota , Prestamista prestamista, Deudor deudor */) {
        this.id = id;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
//        this.tipoDeCuota = tipoDeCuota;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = "PENDIENTE";
        this.saldo = monto;
        // this.prestamista = prestamista;
        // this.deudor = deudor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

//    public TipoDeCuotaEnum getTipoDeCuota() {
//        return tipoDeCuota;
//    }
//
//    public void setTipoDeCuota(TipoDeCuotaEnum tipoCuota) {
//        this.tipoDeCuota = tipoDeCuota;
//    }

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

    // public Prestamista getPrestamista() {
    //     return prestamista;
    // }

    // public void setPrestamista(Prestamista prestamista) {
    //     this.prestamista = prestamista;
    // }

    // public Deudor getDeudor() {
    //     return deudor;
    // }

    // public void setDeudor(Deudor deudor) {
    //     this.deudor = deudor;
    // }

    // public List<CoodeudorPrestamo> getCoodeudores() {
    //     return coodeudores;
    // }

    // public void setCoodeudores(List<CoodeudorPrestamo> coodeudores) {
    //     this.coodeudores = coodeudores;
    // }

    // public List<PrestamoInversion> getInversiones() {
    //     return inversiones;
    // }

    // public void setInversiones(List<PrestamoInversion> inversiones) {
    //     this.inversiones = inversiones;
    // }

    // public List<Pago> getPagos() {
    //     return pagos;
    // }

    // public void setPagos(List<Pago> pagos) {
    //     this.pagos = pagos;
    // }

    @Override
    public String toString() {
        return "Prestamo\n" +
               "-----------------\n" +
               "ID: " + id + "\n" +
               "Fecha Solicitud: " + fechaSolicitud + "\n" +
               "Estado: " + estado + "\n" +
               "Monto: " + monto + "\n" +
               "Fecha Desembolso: " + fechaDesembolso + "\n" +
               "Tasa Interés: " + tasaInteres + "\n" +
               "Número Cuotas: " + numeroCuotas + "\n" +
//               "Tipo Cuota: " + tipoDeCuota + "\n" +
               "Saldo: " + saldo + "\n" +
               "Observaciones: " + observaciones + "\n";
               // + "Prestamista: " + (prestamista != null ? prestamista.getCodigo() : "null") + "\n"
               // + "Deudor: " + (deudor != null ? deudor.getCodigo() : "null") + "\n"
               // + "Coodeudores: " + (coodeudores != null ? coodeudores.size() : 0) + "\n"
               // + "Inversiones usadas: " + (inversiones != null ? inversiones.size() : 0) + "\n"
               // + "Pagos generados: " + (pagos != null ? pagos.size() : 0) + "\n";
    }
}

package com.jca2dev.Dominio.Entidades;

import com.jca2dev.Dominio.Constantes.EstadoDePrestamoEnum;
import com.jca2dev.Dominio.Constantes.TipoDeCuotaEnum;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author John Carlos Arrieta Arrieta
 */
public class Prestamo {

    //  // propidades de instancia u objeto
    private int id;
    private LocalDateTime fechaSolicitud;
    private EstadoDePrestamoEnum estado;
    private double monto;
    private LocalDateTime fechaDesembolso;
    private double tasaInteres;
    private int numeroCuotas;
    private TipoDeCuotaEnum tipoDeCuota;
    private double saldo;
    private String observaciones;

    // Relaciones 
    private Prestamista prestamista;
    private Deudor deudor;
    private List<CodeudorPrestamo> codeudores;
    private List<PrestamoInversion> inversiones;
    private List<Pago> pagos;

    // Constructores
    public Prestamo(int id, double monto, double tasaInteres, int numeroCuotas,
            TipoDeCuotaEnum tipoDeCuota, Prestamista prestamista, Deudor deudor) {
        this.id = id;
        this.monto = monto;
        this.tasaInteres = tasaInteres;
        this.numeroCuotas = numeroCuotas;
        this.tipoDeCuota = tipoDeCuota;
        this.fechaSolicitud = LocalDateTime.now();
        this.estado = estado.PENDIENTE;
        this.saldo = monto;
        this.prestamista = prestamista;
        this.deudor = deudor;
        codeudores = new ArrayList<>();
        inversiones = new ArrayList<>();
        pagos = new ArrayList<>();
    }

    // Gets y Sets
    public EstadoDePrestamoEnum getEstado() {
        return estado;
    }

    public void setEstado(EstadoDePrestamoEnum estado) {
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setTipoDeCuota(TipoDeCuotaEnum tipoCuota) {
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
            var mensaje = "El Prestamista no puede ser nulo ni tener un Codigo invalido.";
            throw new IllegalArgumentException(mensaje);
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
            var mensaje = "El Deudor no puede ser nulo ni tener un Codigo invalido.";
            throw new IllegalArgumentException(mensaje);
        }
        this.deudor = deudor;
        deudor.agregarPrestamo(this);
    }

    public List<CodeudorPrestamo> getCodeudores() {
        return codeudores;
    }

    public void setCodeudores(List<CodeudorPrestamo> coodeudores) {
        this.codeudores = coodeudores;
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

    // Metodos para garantizar las restrcciones de las relaciones
    
    public void agregarCodeudor(CodeudorPrestamo codeudorPrestamo) {
        if (codeudorPrestamo == null || codeudorPrestamo.getId() <= 0
                || codeudorPrestamo.getCodeudor().getCodigo().trim().isEmpty()) {
            var mensaje = "El Codeudor no puede ser nulo ni tener un codigo invalido.";
            throw new IllegalArgumentException(mensaje);
        }

        if (codeudorPrestamo.getPrestamo().getId() != this.id) {
            var mensaje = "El Codeudor no corresponde al Prestamo asignado.";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.codeudores.stream()
                .anyMatch(c -> c != null
                && c.getId() == codeudorPrestamo.getId()
                && c.getCodeudor().getCodigo()
                        .equalsIgnoreCase(codeudorPrestamo.getCodeudor()
                                .getCodigo()));

        if (!existe) {
            this.codeudores.add(codeudorPrestamo);
        }

        codeudorPrestamo.sincronizarPrestamo(this);
    }

    public void agregarInversion(PrestamoInversion prestamoInverson) {
        if (prestamoInverson == null || prestamoInverson.getId() <= 0
                || prestamoInverson.getInversion().getId() <= 0) {
            var mensaje = "La inversion no puede ser nulo ni tener un In invalido.";
            throw new IllegalArgumentException(mensaje);
        }

        if (prestamoInverson.getPrestamo().getId() != this.getId()) {
            var mensaje = "La Inversion no correspoonde al Prestamo asignado.";
            throw new IllegalArgumentException(mensaje);
        }

        if (! prestamoInverson.getPrestamo().prestamista.getCodigo()
            .equalsIgnoreCase(this.prestamista.getCodigo())) 
        {
            var mensaje = "La Inversion no corresponde al Prestamista del prestamo asignado";
            throw new IllegalArgumentException(mensaje);
        }

        var existe = this.inversiones.stream()
                .anyMatch(i -> i != null &&
                i.getId() == prestamoInverson.getId());
        
        if (!existe) {
            this.inversiones.add(prestamoInverson);
        }
        prestamoInverson.sincronizarPrestamo(this);
    }

    public void agregarPago(Pago pago) {
        if (pago == null || pago.getId() <= 0) {
            var mensaje = "El Pago no puede ser nulo ni tener un Id invalido.";
            throw new IllegalArgumentException(mensaje);
        }
        
         if (pago.getPrestamo().getId() != this.id) {
            var mensaje = "El Pago no corresponde al Prestamo asignado.";
            throw new IllegalArgumentException(mensaje);
        }
         
         
        var existe = this.pagos.stream()
                .anyMatch(p -> p.getId() == pago.getId());

        if (!existe) {
            this.pagos.add(pago);
        }
        pago.sincronizarPrestamo(this);
    }

    void sincronizarDeudor(Deudor aThis) {
        if (deudor == null || deudor.getCodigo() == null
                || deudor.getCodigo().trim().isEmpty()) {
            var mensaje = "El Deudor no puede ser nulo ni tener un Codigo invalido.";
            throw new IllegalArgumentException(mensaje);
        }

        this.deudor = deudor;
    }

    void sincronizarPrestamista(Prestamista prestamista) {
        if (prestamista == null || prestamista.getCodigo() == null
                || prestamista.getCodigo().trim().isEmpty()) {
            var mensaje = "El Prestamista no puede ser nulo ni tener un Codigo invalido.";
            throw new IllegalArgumentException(mensaje);
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

}

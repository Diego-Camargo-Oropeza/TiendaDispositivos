package pck_tienda_electronicos;

import pck_date.DateClass;

public class Venta {

    //Variables miembro
    private int idVenta;
    private int idDispositivo;
    private int idCliente;
    private DateClass fechaPedido;
    private DateClass fechaEntrega;

    //Constructores
    public Venta() {
        this(0, 0, 0, 1, 1, 1960, 1, 1, 1970);
    }

    public Venta(int idVenta, int idDispositivo, int idCliente, DateClass fechaPedido, DateClass fechaEntrega) {
        this.idVenta = idVenta;
        this.idDispositivo = idDispositivo;
        this.idCliente = idCliente;
        this.fechaPedido = new DateClass();
        this.fechaEntrega = new DateClass();
    }

    public Venta(int idVenta, int idDispositivo, int idCliente, int d, int m, int a, int d2, int m2, int a2) {
        this.idVenta = idVenta;
        this.idDispositivo = idDispositivo;
        this.idCliente = idCliente;
        this.fechaPedido = new DateClass(d, m, a);
        this.fechaEntrega = new DateClass(d2, m2, a2);
    }

    //Setters
    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean setFechaPedido(int d, int m, int a) {
        this.fechaPedido.setDate(d, m, a);
        return fechaPedido.isDateCorrect();
    }

    public void setFechaPedido(DateClass fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public boolean setFechaEntrega(int d, int m, int a) {
        this.fechaEntrega.setDate(d, m, a);
        return fechaEntrega.isDateCorrect();
    }

    public void setFechaEntrega(DateClass fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    

    //Getters
    public int getIdVenta() {
        return idVenta;
    }

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public String getFechaPedido() {
        return fechaPedido.getDate();
    }

    public String getFechaEntrega() {
        return fechaEntrega.getDate();
    }

    public String getDatos() {
        return "ID de la Venta: " + getIdVenta() + "\n"
                + "ID del Dispositivo: " + getIdDispositivo() + "\n"
                + "ID del Cliente" + getIdCliente() + "\n"
                + "Fecha del pedido: " + getFechaPedido() + "\n"
                + "Fecha de Entrega: " + getFechaEntrega() + "\n";
    }
}

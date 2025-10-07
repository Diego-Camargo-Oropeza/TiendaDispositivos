package pck_tienda_electronicos;

import pck_date.DateClass;

public class Cliente {

    //Variables miembro
    private int idCliente;
    private String nombre;
    private String direccion;
    private String identificacion;
    private String telefono;
    private String tipo;
    private DateClass fechaNacimiento;
    private String correoElectronico;

    //Constructores
    public Cliente() {
        this(0, null, null, null, null, null, 1, 1, 1960, null);
    }

    public Cliente(int idCliente, String nombre, String direccion, String identificacion, String telefono, String tipo, DateClass fechaNacimiento, String correoElectronico) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.fechaNacimiento = new DateClass();
        this.correoElectronico = correoElectronico;
    }

    public Cliente(int idCliente, String nombre, String direccion, String identificacion, String telefono, String tipo, int d, int m, int a, String correoElectronico) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.fechaNacimiento = new DateClass(d, m, a);
        this.correoElectronico = correoElectronico;
    }

    //Setters
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFechaNacimiento(DateClass fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean setFechaNacimiento(int d, int m, int a) {
        this.fechaNacimiento.setDate(d, m, a);
        return fechaNacimiento.isDateCorrect();
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    //Getters
    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.getDate();
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getDatos() {
        return "    --Datos del cliente--   \n"
                + "ID: " + getIdCliente() + "\n"
                + "Nombre: " + getNombre() + "\n"
                + "Dirección: " + getDireccion() + "\n"
                + "Identificacion: " + getIdentificacion() + "\n"
                + "Teléfono: " + getTelefono() + "\n"
                + "Tipo: " + getTipo() + "\n"
                + "Fecha de Nacimiento: " + getFechaNacimiento() + "\n"
                + "Correo Electrónico: " + getCorreoElectronico() + "\n";
    }

}

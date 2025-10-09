/*
Desarrollado por:
Camargo Oropeza Diego
Licona Ibarra Diego Alejandro
Villegas Turrubiartes Melinna
*/
package pck_tienda_electronicos;

import java.io.Serializable;

public class Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;

    //Variables miembro
    protected String idDispositivo;
    protected String marca;
    protected String modelo;
    protected String sistemaOperativo;
    protected float peso;
    protected float precio;

    //Constructores
    public Dispositivo() {
        this(null, null, null, null, 0.0f, 0.0f);
    }

    public Dispositivo(String idDispositivo, String marca, String modelo, String sistemaOperativo, float peso, float precio) {
        this.idDispositivo = idDispositivo;
        this.marca = marca;
        this.modelo = modelo;
        this.sistemaOperativo = sistemaOperativo;
        this.peso = peso;
        this.precio = precio;
    }

    //Setters
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    //Getters 
    public String getIdDispositivo() {
        return idDispositivo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public float getPeso() {
        return peso;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDatos() {
        return "ID del Dispositivo: " + getIdDispositivo() + "\n"
                + "Marca: " + getMarca() + "\n"
                + "Modelo: " + getModelo() + "\n"
                + "Sistema operativo: " + getSistemaOperativo() + "\n"
                + "Peso: " + getPeso() + "\n"
                + "Precio: " + getPrecio() + "\n";
    }

}

/*
Desarrollado por:
Camargo Oropeza Diego
Licona Ibarra Diego Alejandro
Villegas Turrubiartes Melinna
 */
package pck_tienda_electronicos;

import java.io.Serializable;

public class Smartphone extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int memoriaRam;
    private int almacenamiento;
    private float tamanioPantalla;

    public Smartphone() {
        this(null, null, null, null, 0.0f, 0.0f, 0, 0, 0);
    }

    public Smartphone(String idDispositivo, String marca, String modelo, String sistemaOperativo, float peso, float precio, int memoriaRam, int almacenamiento, float tamanioPantalla) {
        super(idDispositivo, marca, modelo, sistemaOperativo, peso, precio);
        this.memoriaRam = memoriaRam;
        this.almacenamiento = almacenamiento;
        this.tamanioPantalla = tamanioPantalla;
    }

    //Setters
    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public void setTamanioPantalla(float tamanioPantalla) {
        this.tamanioPantalla = tamanioPantalla;
    }
    //Getters

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public float getTamanioPantalla() {
        return tamanioPantalla;
    }

    @Override
    public String getDatos() {
        return "--      DATOS DEL SMARTPHONE       -- \n"
                + super.getDatos() + "\n"
                + "Tamaño de la memoria RAM: " + getMemoriaRam() + "gb\n"
                + "Tamaño de almacenamiento: " + getAlmacenamiento() + "gb\n"
                + "Tamaño de pantalla: " + getTamanioPantalla() + "\n";
    }

}

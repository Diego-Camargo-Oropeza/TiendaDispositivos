package pck_tienda_electronicos;

import java.io.Serializable;

public class Laptop extends Dispositivo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int memoriaRam;
    private String procesador;
    private String tarjetaGrafica;

    public Laptop(String idDispositivo, String marca, String modelo, String sistemaOperativo, float peso, float precio, int memoriaRam, String procesador, String tarjetaGrafica) {
        super(idDispositivo, marca, modelo, sistemaOperativo, peso, precio);
        this.memoriaRam = memoriaRam;
        this.procesador = procesador;
        this.tarjetaGrafica = tarjetaGrafica;
    }

    public Laptop() {
        this(null, null, null, null, 0.0f, 0.0f, 0, null, null);
    }

    //Setters
    public void setMemoriaRam(int memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public void setTarjetaGrafica(String tarjetaGrafica) {
        this.tarjetaGrafica = tarjetaGrafica;
    }

    //Getters
    public int getMemoriaRam() {
        return memoriaRam;
    }

    public String getProcesador() {
        return procesador;
    }

    public String getTarjetaGrafica() {
        return tarjetaGrafica;
    }

    @Override
    public String getDatos() {
        return "--      DATOS DE LA LAPTOP      --\n"
                + super.getDatos() + "\n"
                + "Tamaño de la memoria RAM: " + getMemoriaRam() + "\n"
                + "Procesador: " + getProcesador() + "\n"
                + "Tarjeta gráfica: " + getTarjetaGrafica() + "\n";
    }

}

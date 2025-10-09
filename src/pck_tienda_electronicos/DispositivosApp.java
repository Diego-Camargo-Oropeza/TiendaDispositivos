/*
Desarrollado por:
Camargo Oropeza Diego
Licona Ibarra Diego Alejandro
Villegas Turrubiartes Melinna
 */
package pck_tienda_electronicos;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DispositivosApp {

    // Decláralo como constante en la clase
    private static final Object[] SO_OPCIONES = {
        "Android",
        "iOS",
        "HarmonyOS",
        "KaiOS",
        "Sailfish OS",
        "Windows Phone (legacy)",
        "Firefox OS (legacy)"
    };

    public static final String MENU
            = "1) Alta de un smartphone\n"
            + "2) Alta de una laptop\n"
            + "3) Alta de un cliente\n"
            + "4) Alta de una venta\n"
            + "5) Listar smartphones\n"
            + "6) Listar laptops\n"
            + "7) Listar clientes\n"
            + "8) Listar ventas\n"
            + "9) Ver detalle de un smartphone\n"
            + "10) Ver detalle de una laptop\n"
            + "11) Ver detalle de un cliente\n"
            + "12) Ver detalle de una venta\n"
            + "13) Eliminar un smartphone\n"
            + "14) Eliminar una laptop\n"
            + "15) Eliminar un cliente\n"
            + "16) Eliminar una venta\n"
            + "17) Salir\n"
            + "Por favor seleccione una opción\n";

    public static void main(String[] args) {
        int op, id, memoriaRam, almacenamiento, posCoincidencia;
        float peso, precio, tamanioPantalla;
        String idDispositivo, marca, modelo, sistemaOperativo, nombre, direccion, identificacion, telefono, tipo, correoElectronico;
        ArrayList<Dispositivo> dispositivos = new ArrayList<>();
        boolean valido;

        MENU_LOOP:
        do {
            do {
                op = 0;
                try {
                    op = Integer.parseInt(JOptionPane.showInputDialog(null, MENU, "Menú de opciones", JOptionPane.QUESTION_MESSAGE));
                    if (op <= 0 || op > 17) {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese una opción válida", "Error", JOptionPane.ERROR_MESSAGE);
                        op = 0;
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese numéricamente la opción a elegir", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } while (op == 0);

            switch (op) {
                case 1 -> {
                    JOptionPane.showMessageDialog(null, "Alta de un smartphone");

                    do {
                        valido = false;
                        idDispositivo = JOptionPane.showInputDialog(null, "Por favor, ingrese el ID del Smartphone", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                        if (idDispositivo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        //Corta espacios en blanco antes y después de la cadena ej. "  Hola   " -> "Hola"
                        idDispositivo = idDispositivo.trim();
                        posCoincidencia = buscarId(dispositivos, idDispositivo);
                        if (posCoincidencia != -1) {
                            JOptionPane.showMessageDialog(null, "ID duplicado, vuelva a ingresar otro", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        valido = cadenaValida(idDispositivo, 1);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    do {
                        valido = false;
                        marca = JOptionPane.showInputDialog(null, "Marca del Smartphone", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                        if (marca == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        valido = cadenaValida(marca, 2);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    do {
                        valido = false;
                        modelo = JOptionPane.showInputDialog(null, "Modelo del Smartphone", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                        if (modelo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        valido = cadenaValida(modelo, 2);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    Object selSO = JOptionPane.showInputDialog(null, "Sistema Operativo", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE, null, SO_OPCIONES, SO_OPCIONES[0]);
                    if (selSO == null) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                        continue MENU_LOOP;
                    }
                    sistemaOperativo = selSO.toString();

                    do {
                        String p;
                        peso = -1.0f;
                        try {
                            p = JOptionPane.showInputDialog(null, "Ingrese el peso del dispositivo en gramos", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                            if (p == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            peso = Float.parseFloat(p);
                            if (peso < 0.0f || peso > 2000.0f) {
                                JOptionPane.showMessageDialog(null, "El peso del dispositivo debe de ser mayor a 0 y menor que 2000gr", "Error", JOptionPane.ERROR_MESSAGE);
                                peso = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            peso = -1.0f;
                        }
                    } while (peso < 0.0f || peso > 2000.0f);

                    do {
                        String p;
                        precio = -1.0f;
                        try {
                            p = JOptionPane.showInputDialog(null, "Ingrese el precio del dispositivo", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                            if (p == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            precio = Float.parseFloat(p);
                            if (precio < 0.0f) {
                                JOptionPane.showMessageDialog(null, "El peso del dispositivo debe de ser mayor a 0 y menor que 2000gr", "Error", JOptionPane.ERROR_MESSAGE);
                                precio = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            precio = -1.0f;
                        }
                    } while (precio < 0.0f);


                }

                case 2 -> {

                }
                case 3 -> {
// alta de cliente
                    do {
                        id = 0;
                        try {
                            id = Integer.parseInt(JOptionPane.showInputDialog(null,
                                    "ID del Cliente: ", "Alta de Cliente",
                                    3));
                            if (id <= 0) {
                                JOptionPane.showMessageDialog(null, "El id "
                                        + "debe ser mayor a cero",
                                        "Error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "El id"
                                    + " debe de ser numerico", "Error de"
                                    + " entrada", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (id <= 0);
                    
                    boolean cadVal;
                    do{
                        nombre = JOptionPane.showInputDialog(null, 
                                "Nombre del cliente", "Alta de un cliente", 3);
                        cadVal = cadenaValida(nombre, 2);
                        if(cadVal == false){
                            JOptionPane.showMessageDialog(null, "Ingrese un nombre valido",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    }while(cadVal == false);
                    
                    do{
                        direccion = JOptionPane.showInputDialog(null, 
                                "Direccion del cliente", "Alta de un cliente", 3);
                        if(direccion.isEmpty()){
                            
                        }
                    }while();
                    
                }
                case 4 -> {
// alta de venta
                }
                case 5 -> {

                }
                case 6 -> {

                }
                case 7 -> {

                }
                case 8 -> {

                }
                case 9 -> {

                }
                case 10 -> {

                }
                case 11 -> {

                }
                case 12 -> {

                }
                case 13 -> {

                }
                case 14 -> {

                }
                case 15 -> {

                }
                case 16 -> {

                }
                case 17 -> {

                }
                default -> {

                }
            }

        } while (op != 17);

    }

    public static int buscarId(ArrayList<Dispositivo> dispositivos, String id) {
        for (Dispositivo dis : dispositivos) {
            if (dis instanceof Smartphone s) {
                if (id.equals(s.getIdDispositivo())) {
                    return dispositivos.indexOf(dis);
                }
            }
            if (dis instanceof Laptop l) {
                if (id.equals(l.getIdDispositivo())) {
                    return dispositivos.indexOf(dis);
                }
            }
        }
        return -1;
    }

    public static boolean cadenaValida(String cadena, int dec) {
        switch (dec) {
            case 1 -> {
                return !cadena.isEmpty() && cadena.matches("^[A-Za-z0-9]+$");
            }

            case 2 -> {
                return !cadena.isEmpty() && cadena.matches("^[\\p{L}\\p{N} ]*$");
            }

            default -> {
                return false;
            }
        }
    }

}

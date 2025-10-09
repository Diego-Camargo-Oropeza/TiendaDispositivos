/*
Desarrollado por:
Camargo Oropeza Diego
Licona Ibarra Diego Alejandro
Villegas Turrubiartes Melinna
 */
package pck_tienda_electronicos;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import pck_date.DateClass;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

public class DispositivosApp {

    // Decláralo como constante en la clase
    private static final Object[] SO_OPCIONES_SMARTPHONES = {
        "Android",
        "iOS",
        "HarmonyOS",
        "KaiOS",
        "Sailfish OS",
        "Windows Phone (legacy)",
        "Firefox OS (legacy)"
    };

    private static final Object[] SO_OPCIONES_LAPTOPS = {
        "Windows",
        "Linux",
        "Ubuntu",
        "TempleOS",
        "MacOS",
        "AmogOS",};

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
        int op, id, memoriaRam, almacenamiento, posCoincidencia, cuenta;
        float peso, precio, tamanioPantalla;
        String idDispositivo, marca, modelo, sistemaOperativo, nombre, direccion, identificacion, telefono, tipo, correoElectronico, procesador, tarjetaGrafica;
        String tablaSmartphone, tablaLaptop, listaCliente, listaVenta;
        DateClass fechaNacimiento, fechaPedido, fechaEntrega;
        ArrayList<Dispositivo> dispositivos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        boolean valido;
        LocalDate date = LocalDate.now();
        int aActual = date.getYear();

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

                //ALTA DE SMARTPHONE ------------
                case 1 -> {
                    JOptionPane.showMessageDialog(null, "Alta de un smartphone");

                    do {
                        valido = true;
                        idDispositivo = JOptionPane.showInputDialog(null, "Por favor, ingrese el ID del Smartphone", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                        if (idDispositivo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        //Corta espacios en blanco antes y después de la cadena ej. "  Hola   " -> "Hola"
                        idDispositivo = idDispositivo.trim();

                        boolean noDuped, formato;

                        formato = cadenaValida(idDispositivo, 1);
                        if (!formato) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }

                        posCoincidencia = buscarId(dispositivos, idDispositivo);

                        noDuped = (posCoincidencia == -1);
                        if (!noDuped) {
                            JOptionPane.showMessageDialog(null, "ID duplicado, vuelva a ingresar otro", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        valido = noDuped && formato;

                    } while (!valido);

                    do {
                        valido = true;
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
                        valido = true;
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

                    Object selSO = JOptionPane.showInputDialog(null, "Sistema Operativo", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE, null, SO_OPCIONES_SMARTPHONES, SO_OPCIONES_SMARTPHONES[0]);
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
                            if (peso <= 0.0f || peso > 2000.0f) {
                                JOptionPane.showMessageDialog(null, "El peso del dispositivo debe de ser mayor a 0 y menor que 2000gr", "Error", JOptionPane.ERROR_MESSAGE);
                                peso = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            peso = -1.0f;
                        }
                    } while (peso <= 0.0f || peso > 2000.0f);

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
                            if (precio <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "El precio del dispositivo debe de ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                                precio = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            precio = -1.0f;
                        }
                    } while (precio <= 0.0f);

                    do {
                        String m;
                        memoriaRam = -1;
                        try {
                            m = JOptionPane.showInputDialog(null, "Ingrese la capacidad de memoria RAM en GB del dispositivo", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            memoriaRam = Integer.parseInt(m);
                            if (memoriaRam <= 0) {
                                JOptionPane.showMessageDialog(null, "La memoria debe de ser mayor a 0GB", "Error", JOptionPane.ERROR_MESSAGE);
                                memoriaRam = -1;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            memoriaRam = -1;
                        }
                    } while (memoriaRam <= 0);

                    do {
                        String a;
                        almacenamiento = -1;
                        try {
                            a = JOptionPane.showInputDialog(null, "Ingrese la capacidad de almacenamiento en GB", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                            if (a == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            almacenamiento = Integer.parseInt(a);
                            if (almacenamiento <= 0) {
                                JOptionPane.showMessageDialog(null, "El almacenamiento del dispositivo debe de ser mayor a 0GB", "Error", JOptionPane.ERROR_MESSAGE);
                                almacenamiento = -1;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            almacenamiento = -1;
                        }
                    } while (memoriaRam <= 0);

                    do {
                        String t;
                        tamanioPantalla = -1.0f;
                        try {
                            t = JOptionPane.showInputDialog(null, "Ingrese el área de la pantalla en centímetros", "Alta de Smartphone", JOptionPane.QUESTION_MESSAGE);
                            if (t == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            tamanioPantalla = Float.parseFloat(t);
                            if (tamanioPantalla <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "La pantalla no pude tener tamaño de cero o menor a cero", "Error", JOptionPane.ERROR_MESSAGE);
                                tamanioPantalla = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe de ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            tamanioPantalla = -1.0f;
                        }
                    } while (tamanioPantalla <= 0.0f);

                    dispositivos.add(new Smartphone(idDispositivo, marca, modelo, sistemaOperativo, peso, precio, memoriaRam, almacenamiento, tamanioPantalla));
                    JOptionPane.showMessageDialog(null, "Dispositivo registrado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                }

                //ALTA DE LAPTOP ------------
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Alta de una laptop");

                    do {
                        valido = true;
                        idDispositivo = JOptionPane.showInputDialog(null, "Por favor, ingrese el ID de la Laptop", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);
                        if (idDispositivo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        //Corta espacios en blanco antes y después de la cadena ej. "  Hola   " -> "Hola"
                        idDispositivo = idDispositivo.trim();

                        boolean noDuped, formato;

                        formato = cadenaValida(idDispositivo, 1);
                        if (!formato) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }

                        posCoincidencia = buscarId(dispositivos, idDispositivo);

                        noDuped = (posCoincidencia == -1);
                        if (!noDuped) {
                            JOptionPane.showMessageDialog(null, "ID duplicado, vuelva a ingresar otro", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        valido = noDuped && formato;

                    } while (!valido);

                    do {
                        valido = false;
                        marca = JOptionPane.showInputDialog(null, "Marca de la Laptop", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

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
                        modelo = JOptionPane.showInputDialog(null, "Modelo de la Laptop", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

                        if (modelo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        valido = cadenaValida(modelo, 2);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    Object selSO_Laptop = JOptionPane.showInputDialog(null, "Sistema Operativo", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE, null, SO_OPCIONES_LAPTOPS, SO_OPCIONES_LAPTOPS[0]);
                    if (selSO_Laptop == null) {
                        JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                        continue MENU_LOOP;
                    }
                    sistemaOperativo = selSO_Laptop.toString();

                    do {
                        String p;
                        peso = -1.0f;
                        try {
                            p = JOptionPane.showInputDialog(null, "Ingrese el peso de la laptop en gramos", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

                            if (p == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }

                            peso = Float.parseFloat(p);

                            if (peso <= 0.0f || peso >= 10000.0f) {
                                JOptionPane.showMessageDialog(null, "El peso debe ser mayor a 0 y menor que 10000 g", "Error", JOptionPane.ERROR_MESSAGE);
                                peso = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            peso = -1.0f;
                        }
                    } while (peso < 0.0f);

                    do {
                        String pr;
                        precio = -1.0f;
                        try {
                            pr = JOptionPane.showInputDialog(null, "Ingrese el precio de la laptop", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

                            if (pr == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }

                            precio = Float.parseFloat(pr);

                            if (precio <= 0.0f) {
                                JOptionPane.showMessageDialog(null, "El precio debe ser mayor a 0", "Error", JOptionPane.ERROR_MESSAGE);
                                precio = -1.0f;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            precio = -1.0f;
                        }
                    } while (precio < 0.0f);
                    do {
                        String m;
                        memoriaRam = -1;
                        try {
                            m = JOptionPane.showInputDialog(null, "Ingrese la capacidad de memoria RAM (GB)", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

                            if (m == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }

                            m = m.trim();
                            memoriaRam = Integer.parseInt(m);

                            if (memoriaRam <= 0) {
                                JOptionPane.showMessageDialog(null, "La memoria RAM debe ser mayor a 0 GB", "Error", JOptionPane.ERROR_MESSAGE);
                                memoriaRam = -1;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "La entrada debe ser numérica", "Error", JOptionPane.ERROR_MESSAGE);
                            memoriaRam = -1;
                        }
                    } while (memoriaRam < 0);

                    do {
                        valido = false;
                        procesador = JOptionPane.showInputDialog(null, "Procesador incorporado a la laptop", "Alta de Laptop", JOptionPane.QUESTION_MESSAGE);

                        if (procesador == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        valido = cadenaValida(procesador, 2);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Use solo letras/números y espacios (sin caracteres especiales).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    do {
                        valido = false;
                        tarjetaGrafica = JOptionPane.showInputDialog(null, "Tarjeta gráfica incorporada en la laptop", "Alta de Laptop",
                                JOptionPane.QUESTION_MESSAGE);

                        if (tarjetaGrafica == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        valido = cadenaValida(tarjetaGrafica, 2);
                        if (!valido) {
                            JOptionPane.showMessageDialog(null, "Use solo letras/números y espacios (sin caracteres especiales).", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!valido);

                    dispositivos.add(new Laptop(idDispositivo, marca, modelo, sistemaOperativo, peso, precio, memoriaRam, procesador, tarjetaGrafica));

                    JOptionPane.showMessageDialog(null, "Laptop registrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                }

                // alta de cliente
                case 3 -> {

                    do {
                        // id
                        String i;
                        id = 0;
                        try {
                            i = JOptionPane.showInputDialog(null,
                                    "ID del Cliente: ", "Alta de Cliente",
                                    3);
                            if (i == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            id = Integer.parseInt(i);
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
                    // nombre
                    boolean cadVal;
                    do {
                        nombre = JOptionPane.showInputDialog(null,
                                "Nombre del cliente", "Alta de un cliente", 3);
                        if (nombre == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        cadVal = cadenaValida(nombre, 2);
                        if (!cadVal) {
                            JOptionPane.showMessageDialog(null, "Ingrese un nombre valido",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!cadVal);
                    // direccion
                    do {
                        direccion = JOptionPane.showInputDialog(null,
                                "Direccion del cliente", "Alta de un cliente", 3);
                        if (direccion == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        if (direccion.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Ingrese una direccion",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (direccion.isEmpty());
                    // identificacion
                    do {
                        identificacion = JOptionPane.showInputDialog(null,
                                "Identificacion del cliente", "Alta de un cliente", 3);
                        if (identificacion == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        if (identificacion.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Ingrese una identificacion",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (identificacion.isEmpty());
                    // telefono
                    do {
                        telefono = JOptionPane.showInputDialog(null,
                                "Telefono del cliente", "Alta de un cliente", 3);
                        if (telefono == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }

                        cadVal = cadenaValida(telefono, 3);
                        if (!cadVal) {
                            JOptionPane.showMessageDialog(null, "Ingrese un telefono valido",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!cadVal);
                    // tipo
                    do {
                        tipo = JOptionPane.showInputDialog(null,
                                "Tipo de cliente", "Alta de un cliente", 3);
                        if (tipo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        if (tipo.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Ingrese un tipo de cliente",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (tipo.isEmpty());
                    // fecha de nacimiento
                    boolean fechaCorrecta = false;
                    boolean menor;
                    do {
                        fechaNacimiento = new DateClass();
                        String ds, ms, as;
                        int d, m, a;
                        do {
                            d = 0;
                            try {
                                ds = JOptionPane.showInputDialog(null,
                                        "Dia:", "Fecha de Nacimiento", 3);
                                if (ds == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                d = Integer.parseInt(ds);
                                if (d <= 0 || d > 31) {
                                    JOptionPane.showMessageDialog(null, "El dia debe ser mayor a cero y menor a 31",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (d <= 0 || d > 31);
                        do {
                            m = 0;
                            try {
                                ms = JOptionPane.showInputDialog(null,
                                        "Mes:", "Fecha de Nacimiento", 3);
                                if (ms == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                m = Integer.parseInt(ms);
                                if (m <= 0 || m > 12) {
                                    JOptionPane.showMessageDialog(null, "El mes debe ser mayor a cero y menor a 12",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (m <= 0 || m > 12);
                        do {
                            a = 0;
                            try {
                                as = JOptionPane.showInputDialog(null,
                                        "Anio:", "Fecha de Nacimiento", 3);
                                if (as == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                a = Integer.parseInt(as);
                                if (a < 1930 || a > aActual) {
                                    JOptionPane.showMessageDialog(null, "El anio esta debe de estar "
                                            + "dentro de 1930 a " + aActual,
                                            "error de entrada", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (a < 1930 || a > aActual);
                        fechaNacimiento.setDate(d, m, a);
                        LocalDate birthdate = LocalDate.of(a, m, d);
                        fechaCorrecta = fechaNacimiento.isDateCorrect();
                        if (!fechaCorrecta) {
                            JOptionPane.showMessageDialog(null, "La fecha es invalida\n",
                                    "error de entrada", JOptionPane.ERROR_MESSAGE);
                        }
                        Period age = Period.between(birthdate, date);
                        menor = (age.getYears() < 18);
                        if (menor) {
                            JOptionPane.showMessageDialog(null, "El cliente no puede ser menor de edad",
                                    "error de entrada", JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!fechaCorrecta || menor);
                    //correo electronico
                    do {
                        correoElectronico = JOptionPane.showInputDialog(null,
                                "Correo del cliente", "Alta de un cliente", 3);
                        if (correoElectronico == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        cadVal = cadenaValida(correoElectronico, 4);
                        if (!cadVal) {
                            JOptionPane.showMessageDialog(null, "Ingrese un correo valido",
                                    "Error de entrada", JOptionPane.WARNING_MESSAGE);
                        }
                    } while (!cadVal);
                    clientes.add(new Cliente(id, nombre, direccion, identificacion,
                            telefono, tipo, fechaNacimiento, correoElectronico));
                    JOptionPane.showMessageDialog(null, "El cliente fue dado de alta exitosamente\n",
                            "Alta de cliente", 3);
                }
                case 4 -> {
// alta de venta
                    String iv, ic;
                    // id venta
                    do {
                        id = 0;
                        try {
                            iv = JOptionPane.showInputDialog(null,
                                    "ID de la venta: ", "Alta de venta",
                                    3);
                            if (iv == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            id = Integer.parseInt(iv);
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
                    // id dispositivo
                    do {
                        valido = true;
                        idDispositivo = JOptionPane.showInputDialog(null, "ID del dispositivo",
                                "Alta de Venta", 3);
                        if (idDispositivo == null) {
                            JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                            continue MENU_LOOP;
                        }
                        idDispositivo = idDispositivo.trim();

                        boolean noDuped, formato;

                        formato = cadenaValida(idDispositivo, 1);
                        if (!formato) {
                            JOptionPane.showMessageDialog(null, "Revisa la entrada, existen caracteres especiales o espacios",
                                    "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }

                        posCoincidencia = buscarId(dispositivos, idDispositivo);

                        noDuped = (posCoincidencia == -1);
                        if (!noDuped) {
                            JOptionPane.showMessageDialog(null, "ID duplicado, vuelva a ingresar otro",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        valido = noDuped && formato;

                    } while (!valido);
                    // id cliente
                    do {
                        id = 0;
                        try {
                            ic = JOptionPane.showInputDialog(null,
                                    "ID de la venta: ", "Alta de venta",
                                    3);
                            if (ic == null) {
                                JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                continue MENU_LOOP;
                            }
                            id = Integer.parseInt(ic);
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
                    // fecha pedido
                    LocalDate localDPedido;
                    boolean fechaCorrecta = false;
                    Period fpedido;
                    do {
                        fechaPedido = new DateClass();
                        String ds, ms, as;
                        int d, m, a;
                        do {
                            d = 0;
                            try {
                                ds = JOptionPane.showInputDialog(null,
                                        "Dia:", "Fecha de Pedido", 3);
                                if (ds == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                d = Integer.parseInt(ds);
                                if (d <= 0 || d > 31) {
                                    JOptionPane.showMessageDialog(null, "El dia debe ser mayor a cero y menor a 31",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (d <= 0 || d > 31);
                        do {
                            m = 0;
                            try {
                                ms = JOptionPane.showInputDialog(null,
                                        "Mes:", "Fecha de Pedido", 3);
                                if (ms == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                m = Integer.parseInt(ms);
                                if (m <= 0 || m > 12) {
                                    JOptionPane.showMessageDialog(null, "El mes debe ser mayor a cero y menor a 12",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (m <= 0 || m > 12);
                        do {
                            a = 0;
                            try {
                                as = JOptionPane.showInputDialog(null,
                                        "Anio:", "Fecha de Pedido", 3);
                                if (as == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                a = Integer.parseInt(as);
                                LocalDate fPedido = LocalDate.of(a, m, d);
                                if (fPedido.isAfter(date)) {
                                    JOptionPane.showMessageDialog(null, "El Pedido no pudo haber sido realizado despues de la fecha de hoy",
                                            "error de entrada", JOptionPane.ERROR_MESSAGE);
                                } else if (a != aActual) {
                                    JOptionPane.showMessageDialog(null, "El Pedido tiene que ser de este mismo anio",
                                            "error de entrada", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (a != aActual);
                        fechaPedido.setDate(d, m, a);
                        localDPedido = LocalDate.of(a, m, d);
                        fpedido = Period.between(localDPedido, date);
                        fechaCorrecta = fechaPedido.isDateCorrect();
                        if (!fechaCorrecta) {
                            JOptionPane.showMessageDialog(null, "La fecha es invalida\n",
                                    "error de entrada", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(fpedido.getMonths() > 6){
                            JOptionPane.showMessageDialog(null, 
                                    "El pedido no puede ser de hace mas de 6 meses",
                                    "fecha invalida", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } while (!fechaCorrecta || fpedido.getMonths() > 6);
                // fecha entrega
                    Period fentrega;
                    do {
                        fechaEntrega = new DateClass();
                        String ds, ms, as;
                        int d, m, a;
                        do {
                            d = 0;
                            try {
                                ds = JOptionPane.showInputDialog(null,
                                        "Dia:", "Fecha de Entrega", 3);
                                if (ds == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                d = Integer.parseInt(ds);
                                if (d <= 0 || d > 31) {
                                    JOptionPane.showMessageDialog(null, "El dia debe ser mayor a cero y menor a 31",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (d <= 0 || d > 31);
                        do {
                            m = 0;
                            try {
                                ms = JOptionPane.showInputDialog(null,
                                        "Mes:", "Fecha de Entrega", 3);
                                if (ms == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                m = Integer.parseInt(ms);
                                if (m <= 0 || m > 12) {
                                    JOptionPane.showMessageDialog(null, "El mes debe ser mayor a cero y menor a 12",
                                            "error de entrada",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (m <= 0 || m > 12);
                        do {
                            a = 0;
                            try {
                                as = JOptionPane.showInputDialog(null,
                                        "Anio:", "Fecha de Entrega", 3);
                                if (as == null) {
                                    JOptionPane.showMessageDialog(null, "Operación cancelada, regresando al menú");
                                    continue MENU_LOOP;
                                }
                                a = Integer.parseInt(as);
                                LocalDate fEntrega = LocalDate.of(a, m, d);
                                if (a < 2025 || a > 2026) {
                                    JOptionPane.showMessageDialog(null, "El anio debe de estar entre 2025 2026",
                                            "error de entrada", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (NumberFormatException e) {
                                JOptionPane.showMessageDialog(null, "Los datos de la fecha deben ser numericos"
                                        + " numerica", "error de entrada",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } while (a < 2025 || a > 2026);
                        fechaEntrega.setDate(d, m, a);
                        fentrega = Period.between(LocalDate.of(a, m, d), date);
                        fechaCorrecta = fechaEntrega.isDateCorrect();
                        Period antesPedido = Period.between(LocalDate.of(a, m, d), localDPedido);
                        if (!fechaCorrecta) {
                            JOptionPane.showMessageDialog(null, "La fecha es invalida\n",
                                    "error de entrada", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(fentrega.getMonths() > 6 || fentrega.getMonths() < -6){
                            JOptionPane.showMessageDialog(null, 
                                    "La fecha de entrega no puede ser de hace mas de 6 meses o dentro de mas de 6 meses",
                                    "fecha invalida", JOptionPane.ERROR_MESSAGE);
                        }
                        else if(antesPedido.getMonths() < 0){
                            JOptionPane.showMessageDialog(null, 
                                    "La fecha de entrega no puede ser antes de la fecha de pedido",
                                    "fecha invalida", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } while (!fechaCorrecta || fpedido.getMonths() > 6);

                }
                case 5 -> {
                    cuenta = 0;
                    tablaSmartphone = "         LISTA DE SMARTPHONES        \n"
                            + "IdDispositivo Marca    Modelo    Sistema     Precio     RAM     TamPantalla\n";
                    if (dispositivos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No existen dispositivos dados de alta", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (Dispositivo dis : dispositivos) {
                            if (dis instanceof Smartphone s) {
                                cuenta++;
                                tablaSmartphone += s.getIdDispositivo() + "          " + s.getMarca() + "           " + s.getModelo() + "          " + s.getSistemaOperativo() + "       " + s.getPrecio()
                                        + "      " + s.getMemoriaRam() + "        " + s.getTamanioPantalla() + "\n";
                            }
                        }
                        if (cuenta == 0) {
                            JOptionPane.showMessageDialog(null, "No existen Smartphones dados de alta, pero si Laptops", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            continue MENU_LOOP;
                        } else {
                            JOptionPane.showMessageDialog(null, tablaSmartphone, "Lista de Smartphones", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                }
                case 6 -> {
                    cuenta = 0;
                    tablaLaptop = "         LISTA DE LAPTOPS        \n"
                            + "IdDispositivo    Marca    Modelo    Sistema     Precio     RAM     Procesador\n";
                    if (dispositivos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No existen dispositivos dados de alta", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    } else {
                        for (Dispositivo dis : dispositivos) {
                            if (dis instanceof Laptop l) {
                                cuenta++;
                                tablaLaptop += l.getIdDispositivo() + "         " + l.getMarca() + "          " + l.getModelo() + "         " + l.getSistemaOperativo() + "       " + l.getPrecio()
                                        + "      " + l.getMemoriaRam() + "       " + l.getProcesador() + "\n";
                            }
                        }
                        if (cuenta == 0) {
                            JOptionPane.showMessageDialog(null, "No existen Laptops dadas de alta, pero si Smartphones", "Advertencia", JOptionPane.WARNING_MESSAGE);
                            continue MENU_LOOP;
                        } else {
                            JOptionPane.showMessageDialog(null, tablaLaptop, "Lista de Laptops", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }

                }
                case 7 -> {
// listar cliente
                }
                case 8 -> {
// listar ventas
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

    public static int buscarIdCliente(ArrayList<Cliente> clientes, int id) {
        for (Cliente c : clientes) {
            if (id == c.getIdCliente()) {
                return clientes.indexOf(c);
            }
        }
        return -1;
    }

    public static int buscarIdVenta(ArrayList<Venta> ventas, int id) {
        for (Venta v : ventas) {
            if (id == v.getIdCliente()) {
                return ventas.indexOf(v);
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
            // phone number regex
            case 3 -> {
                return !cadena.isEmpty() && cadena.matches("^\\+?[0-9\\s\\-()]{7,20}$");

            }
            // email regex
            case 4 -> {
                return !cadena.isEmpty() && cadena.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
            }

            default -> {
                return false;
            }
        }
    }

}

package model;

import java.util.ArrayList;

/**
 * Clase centralizada de almacenamiento de datos.
 * Utiliza ArrayLists para organizar y guardar diferentes tipos de variables (textos, números, decimales).
 * Permite acceder a la configuración del juego y del jugador de manera organizada.
 */
public class Data {
    // Almacén de textos (títulos, etiquetas, etc.)
    private ArrayList<String> textos = new ArrayList<>();
    // Almacén de números enteros (dimensiones, FPS, escala)
    private ArrayList<Integer> numeros = new ArrayList<>();
    // Almacén de números decimales (posiciones de entidades, velocidades precisas)
    private ArrayList<Double> decimales = new ArrayList<>();

    /**
     * Constructor por defecto. Inicializa todas las colecciones de datos.
     */
    public Data() {
        crearTextos();
        crearNumeros();
        crearDecimales();

    }

    // Métodos de inicialización de datos

    /**
     * Define los textos utilizados en la aplicación.
     */
    private void crearTextos() {
        textos.add("2D Game"); // Índice 0 - Título de la ventana principal
    }

    /**
     * Define los valores enteros (configuración de pantalla y juego).
     */
    private void crearNumeros() {
        // Configuración ventana
        numeros.add(16);  // Índice 0 - ORIGINAL_TILESIZE (Tamaño base del tile: 16x16)
        numeros.add(4);   // Índice 1 - SCALE (Factor de escala aumentado para que el jugador sea más grande)
        numeros.add(16);  // Índice 2 - MAX_SCREENCOL (Columnas máximas en pantalla)
        numeros.add(12);  // Índice 3 - MAX_SCREENROW (Filas máximas en pantalla)
        numeros.add(165); // Índice 4 - FPS (Objetivo de fotogramas por segundo)

        // Configuración jugador
        numeros.add(30);  // Índice 5 - playerSpeed (Velocidad base del jugador)
    }

    /**
     * Define los valores decimales (posicionamiento preciso).
     */
    private void crearDecimales() {
        // Posición inicial del jugador calculada para el centro de la pantalla
        decimales.add(384.0); // Índice 0 - playerX
        decimales.add(288.0); // Índice 1 - playerY
    }

    // Getters y Setters de las listas completas

    public ArrayList<String> getTextos() {
        return textos;
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public ArrayList<Double> getDecimales() {
        return decimales;
    }

    public void setTextos(ArrayList<String> textos) {
        this.textos = textos;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

    public void setDecimales(ArrayList<Double> decimales) {
        this.decimales = decimales;
    }

    // Métodos de acceso mediante índice (Polimorfismo conceptual)

    /**
     * Obtiene un texto por su índice.
     * @param index Posición en la lista.
     * @return El String correspondiente o null si el índice es inválido.
     */
    public String getTexto(int index) {
        if (index >= 0 && index < textos.size()) {
            return textos.get(index);

        } else {
            return null;
        }

    }

    /**
     * Obtiene un número entero por su índice.
     * @param index Posición en la lista.
     * @return El Integer correspondiente o null si el índice es inválido.
     */
    public Integer getNumero(int index) {
        if (index >= 0 && index < numeros.size()) {
            return numeros.get(index);

        } else {
            return null;
        }
    }

    /**
     * Obtiene un número decimal por su índice.
     * @param index Posición en la lista.
     * @return El Double correspondiente o null si el índice es inválido.
     */
    public Double getDecimal(int index) {
        if (index >= 0 && index < decimales.size()) {
            return decimales.get(index);

        } else {
            return null;
        }
    }


}

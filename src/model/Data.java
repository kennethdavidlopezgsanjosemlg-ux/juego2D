package model;

import java.util.ArrayList;

public class Data {
    private ArrayList<String> textos = new ArrayList<>();
    private ArrayList<Integer> numeros = new ArrayList<>();

    // Constructor por defecto
    public Data() {
        crearTextos();
        crearNumeros();

    }

    // Métodos

    private void crearTextos() {
        textos.add("2D Game"); // 0 titulo de la ventana principal
    }

    private void crearNumeros() {

    }

    // Getters y Setters

    public ArrayList<String> getTextos() {
        return textos;
    }

    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    public void setTextos(ArrayList<String> textos) {
        this.textos = textos;
    }

    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }

    // Polimorfismo para obtener texto y numero mediante indice

    public String getTexto(int index) {
        if (index >= 0 && index < textos.size()) {
            return textos.get(index);

        } else {
            return null;
        }

    }

    public Integer getNumero(int index) {
        if (index >= 0 && index < numeros.size()) {
            return numeros.get(index);

        } else {
            return null;
        }
    }


}

package view;

import model.Data;

import javax.swing.*;

public class MainView extends JFrame {
    private Data data;

    public MainView(Data data) {
        this.data = data;
        settings();
    }

    public void settings() {
        this.setTitle(data.getTexto(0));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}

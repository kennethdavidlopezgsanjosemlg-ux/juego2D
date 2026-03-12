package controller;

import listener.handListener;
import model.Data;
import view.GamePanel;
import view.MainView;

public class MainController {
    private GamePanel gamePanel;
    private MainView mainView;
    private Data data;

    private handListener listener;

    public MainController() {
        // Creación de los datos
        data = new Data();

        // Creacion de la ventana principal
        mainView = new MainView(data);
        gamePanel = new GamePanel(data);

        // Estructura de la ventana
        listener = new handListener();
        gamePanel.addHandListener(listener);

        mainView.add(gamePanel); // A la ventana le añadimos el JPanel
        mainView.pack(); // Ajustamos el tamaño de la ventana
        mainView.setVisible(true);

    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public handListener getListener() {
        return listener;
    }

    public void setListener(handListener listener) {
        this.listener = listener;
    }
}

package controller;

import listener.handListener;
import model.Data;
import view.GamePanel;
import view.MainView;

/**
 * Controlador principal de la aplicación
 * Se encarga de instanciar el Modelo (Data), la Vista (MainView/GamePanel)
 * y de conectar los Listeners para que el sistema funcione como un todo.
 */
public class MainController {
    // Referencias a los componentes principales
    private GamePanel gamePanel;
    private MainView mainView;
    private Data data;
    private handListener listener;

    /**
     * Constructor del controlador
     * Realiza el ensamblaje de todos los módulos del juego
     */
    public MainController() {
        // 1. Instanciamos el almacenamiento de datos (MODELO)
        data = new Data();

        // 2. Creamos las vistas (VISTA) pasando los datos necesarios
        mainView = new MainView(data);
        gamePanel = new GamePanel(data);

        // 3. Inicializamos el gestor de teclado (CONTROLADOR DE ENTRADA)
        listener = new handListener();
        
        // 4. Conectamos el listener al panel del juego
        // Esto también inicializa al jugador internamente en GamePanel
        gamePanel.addHandListener(listener);

        // 5. Montamos la interfaz gráfica
        mainView.add(gamePanel); 
        mainView.pack(); // Ajusta el tamaño de la ventana al tamaño del GamePanel
        mainView.setLocationRelativeTo(null); // Centra la ventana tras el pack()
        mainView.setVisible(true);

    }

    // Getters y Setters para acceso externo (si fuera necesario)

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

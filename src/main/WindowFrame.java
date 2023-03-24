package main;

import main.panels.LoginPanel;
import main.panels.SplashPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class WindowFrame extends JFrame {

    /**
     * El panel visible actual
     */
    private JPanel activePanel;

    /**
     * Fuentes personalizadas
     */
    private FontManager fontManager;

    private WindowFrame instance;

    public WindowFrame() {
        instance = this;
        fontManager = new FontManager();
        init();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                changePanel(new SplashPanel(instance));
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                changePanel(new LoginPanel(instance));
            }
        });
        thread.start();
    }

    /**
     * Inicializa la Ventana
     */
    private void init() {
        setTitle("Gestor de Usuarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        InputStream inputStream = Utils.getStream("resources/icon.png");
        setIconImage(Utils.resizeImage(64,64, inputStream));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Cambia al panel que se pasa por parametro
     * @param panel el panel que se mostrara
     */
    public void changePanel(JPanel panel) {
        if(activePanel != null) {
            remove(activePanel);
            activePanel = null;
        }
        if(panel != null) {
            add(panel);
            activePanel = panel;
        }
        revalidate();
        repaint();
    }

    public FontManager getFontManager() {
        return fontManager;
    }
}

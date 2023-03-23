package main;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        init();
    }

    public void init() {
        setTitle("Gestor de Usuarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        setIconImage(Utils.resizeImage(64,64,"src\\resources\\icon.png"));
    }

}

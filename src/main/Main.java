package main;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatLightLaf.setup(); // Look and feel
                WindowFrame windowFrame = new WindowFrame();
            }
        });
    }
}
package main.panels;

import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class SplashPanel extends JPanel {

    public SplashPanel(WindowFrame instance) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A7E2FF"));

        InputStream inputStream = Utils.getStream("resources/icon.png");
        JLabel icon = new JLabel(new ImageIcon(Utils.resizeImage(240,240, inputStream)));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icon.setBounds(115,40,240,240);
        add(icon);

        JLabel power = new JLabel("Power by Yahir Emmanuel Romo Palomino");
        power.setFont(instance.getFontManager().quicksand_bold.deriveFont(18F));
        power.setForeground(Color.BLACK);
        power.setBounds(56,500,380,40);
        add(power);
    }

}

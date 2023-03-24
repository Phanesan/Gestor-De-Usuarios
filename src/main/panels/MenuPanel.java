package main.panels;

import main.LoggedUser;
import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class MenuPanel extends JPanel {

    public MenuPanel(WindowFrame instance, LoggedUser user) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A2D6EF"));

        InputStream inputStream = Utils.getStream("resources/userIcon.png");
        JLabel userIcon = new JLabel(new ImageIcon(Utils.resizeImage(160,160,inputStream)));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userIcon.setBounds(160,60,160,160);
        add(userIcon);

        JLabel welcomeText = new JLabel("Bienvenido " + user.getName(),JLabel.CENTER);
        welcomeText.setFont(instance.getFontManager().quicksand_light.deriveFont(28f));
        welcomeText.setBounds(50,240,380,50);
        add(welcomeText);

    }

}

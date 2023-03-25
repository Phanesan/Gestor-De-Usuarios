package main.panels;

import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class HelpPanel extends JPanel {

    public HelpPanel(WindowFrame instance) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A2D6EF"));

        InputStream inputStream = Utils.getStream("resources/helpIcon.png");
        JLabel icon = new JLabel(new ImageIcon(Utils.resizeImage(180,180, inputStream)));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icon.setBounds(150,40,180,180);
        add(icon);

        JLabel text = new JLabel("<html>1.- Irse a la pestaña de Usuarios<br>" +
                                "2.- Click en Crear Usuario<br>" +
                                "3.- Rellena los datos que se solicitan<br>" +
                                "4.- Una vez rellenado los datos, hacer click en el boton Crear Cuenta<br>" +
                                "Listo, tu usuario se ha creado.</html>");

        text.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        text.setBounds(75,190,350,300);
        add(text);

        JButton createAccountNow = new JButton("Crear Cuenta Ahora");
        createAccountNow.setBounds(160,490,150,30);
        createAccountNow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.changePanel(new CreateUserPanel(instance));
            }
        });
        add(createAccountNow);
    }

}

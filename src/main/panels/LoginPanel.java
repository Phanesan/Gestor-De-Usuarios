package main.panels;

import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class LoginPanel extends JPanel {

    public LoginPanel(WindowFrame instance) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A7E2FF"));

        InputStream inputStream = Utils.getStream("resources/icon.png");
        JLabel icon = new JLabel(new ImageIcon(Utils.resizeImage(180,180, inputStream)));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        icon.setBounds(150,40,180,180);
        add(icon);

        JLabel userText = new JLabel("Usuario");
        userText.setFont(new Font("Arial",Font.PLAIN,30));
        userText.setBounds(113,255,150,40);
        add(userText);

        JTextField userField = new JTextField();
        userField.setBounds(110,300,260,40);
        userField.setFont(instance.getFontManager().quicksand_light.deriveFont(25f));
        add(userField);

        JLabel passwordText = new JLabel("Contraseña");
        passwordText.setFont(new Font("Arial",Font.PLAIN,30));
        passwordText.setBounds(113,366,190,40);
        add(passwordText);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110,410,260,40);
        add(passwordField);

        JButton login = new JButton("Iniciar Sesión");
        login.setBounds(181,490,110,30);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        add(login);

        revalidate();
        repaint();
    }

}

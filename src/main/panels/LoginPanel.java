package main.panels;

import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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

        JLabel mailText = new JLabel("Correo");
        mailText.setFont(new Font("Arial",Font.PLAIN,30));
        mailText.setBounds(113,255,150,40);
        add(mailText);

        JTextField mailField = new JTextField();
        mailField.setBounds(110,300,260,40);
        mailField.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        add(mailField);

        JLabel passwordText = new JLabel("Contraseña");
        passwordText.setFont(new Font("Arial",Font.PLAIN,30));
        passwordText.setBounds(113,366,190,40);
        add(passwordText);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(110,410,260,40);
        add(passwordField);

        JButton login = new JButton("Iniciar Sesión");
        login.setFont(instance.getFontManager().quicksand_bold.deriveFont(13f));
        login.setBounds(241,490,130,30);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mail = mailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                File file = new File("src\\resources\\users.txt");

                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    String line = br.readLine();
                    int iterator = 0;
                    while(line != null) {
                        String data[] = line.split(",");

                        if(data[2].equals(mail)) {
                            if(data[3].equals(password)) {
                                JOptionPane.showMessageDialog(null, "Acceso correcto", "Login", JOptionPane.PLAIN_MESSAGE);
                                instance.getUser().login(data[0],data[1],data[2],data[3],iterator);
                                instance.changePanel(new MenuPanel(instance)); // Cambia al menu
                                instance.blockBarMenu(false);
                                return;
                            } else {
                                JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Login", JOptionPane.PLAIN_MESSAGE);
                                return;
                            }
                        }
                        line = br.readLine();
                        iterator++;
                    }
                    JOptionPane.showMessageDialog(null, "El correo no existe", "Login", JOptionPane.PLAIN_MESSAGE);

                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(login);

        JButton register = new JButton("Registrarse ahora");
        register.setFont(instance.getFontManager().quicksand_bold.deriveFont(9f));
        register.setBounds(110,490,110,30);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.changePanel(new RegisterPanel(instance));
            }
        });
        add(register);


        revalidate();
        repaint();
    }

}

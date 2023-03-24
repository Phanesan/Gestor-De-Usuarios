package main.panels;

import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RegisterPanel extends JPanel {

    public RegisterPanel(WindowFrame instance) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A7E2FF"));

        JLabel registroText = new JLabel("Registro");
        registroText.setFont(instance.getFontManager().quicksand_light.deriveFont(30f));
        registroText.setBounds(190,70,170,40);
        add(registroText);

        JLabel nombreText = new JLabel("Nombre");
        nombreText.setBounds(90,150,180,30);
        add(nombreText);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(220,150,170,30);
        add(nombreField);

        JLabel apellidosText = new JLabel("Apellidos");
        apellidosText.setBounds(90,190,180,30);
        add(apellidosText);

        JTextField apellidosField = new JTextField();
        apellidosField.setBounds(220,190,170,30);
        add(apellidosField);

        JLabel correoText = new JLabel("Correo");
        correoText.setBounds(90,230,180,30);
        add(correoText);

        JTextField correoField = new JTextField();
        correoField.setBounds(220,230,170,30);
        add(correoField);

        JLabel passwordText = new JLabel("Contraseña");
        passwordText.setBounds(90,270,180,30);
        add(passwordText);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(220,270,170,30);
        add(passwordField);

        JLabel repetirPasswordText = new JLabel("Repetir contraseña");
        repetirPasswordText.setBounds(90,310,180,30);
        add(repetirPasswordText);

        JPasswordField repetirPasswordField = new JPasswordField();
        repetirPasswordField.setBounds(220,310,170,30);
        add(repetirPasswordField);

        JLabel errorMessage = new JLabel();
        errorMessage.setBounds(95,450,280,30);
        errorMessage.setForeground(Color.RED);
        errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(errorMessage);

        JButton crearBoton = new JButton("Crear Cuenta");
        crearBoton.setBounds(170,400,130,25);
        crearBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!String.valueOf(passwordField.getPassword()).equals(String.valueOf(repetirPasswordField.getPassword()))) {
                    errorMessage.setText("La contraseña no es la misma");
                    return;
                }
                errorMessage.setText("");

                try {
                    File file = new File("src\\resources\\users.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    while(line != null) {
                        String data[] = line.split(",");
                        if(data[2].equals(correoField.getText())) {
                            errorMessage.setText("El correo ya esta en uso");
                            return;
                        }
                        line = br.readLine();
                    }
                    br.close();

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));

                    bw.write(nombreField.getText() + "," +
                            apellidosField.getText() + "," +
                            correoField.getText() + "," +
                            String.valueOf(passwordField.getPassword()));
                    bw.newLine();
                    bw.flush();
                    bw.close();
                    JOptionPane.showMessageDialog(null, "La cuenta se ha registrado correctamente", "Registro", JOptionPane.PLAIN_MESSAGE);
                    instance.changePanel(new LoginPanel(instance));
                } catch (IOException ex) {
                    System.out.println("Archivo de datos no encontrado.");
                    ex.printStackTrace();
                }
            }
        });
        add(crearBoton);
    }

}

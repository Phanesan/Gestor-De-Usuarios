package main.panels;

import main.LoggedUser;
import main.Utils;
import main.WindowFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EditUserPanel extends JPanel {

    public EditUserPanel(WindowFrame instance, LoggedUser user) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A2D6EF"));

        InputStream inputStream = Utils.getStream("resources/createUser.png");
        JLabel userIcon = new JLabel(new ImageIcon(Utils.resizeImage(100,100,inputStream)));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        userIcon.setBounds(197,60,100,100);
        add(userIcon);

        JLabel title = new JLabel("Editar Usuario",JLabel.CENTER);
        title.setFont(instance.getFontManager().quicksand_bold.deriveFont(28f));
        title.setBounds(140,10,200,40);
        add(title);

        JLabel nombreText = new JLabel("Nombre:");
        nombreText.setFont(instance.getFontManager().quicksand_bold.deriveFont(20f));
        nombreText.setBounds(65,172,180,30);
        add(nombreText);

        JTextField nombreField = new JTextField();
        nombreField.setBounds(64,200,350,30);
        nombreField.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        add(nombreField);

        JLabel lastnameText = new JLabel("Apellidos:");
        lastnameText.setFont(instance.getFontManager().quicksand_bold.deriveFont(20f));
        lastnameText.setBounds(65,232,180,30);
        add(lastnameText);

        JTextField lastnameField = new JTextField();
        lastnameField.setBounds(64,260,350,30);
        lastnameField.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        add(lastnameField);

        JLabel correoText = new JLabel("Correo:");
        correoText.setFont(instance.getFontManager().quicksand_bold.deriveFont(20f));
        correoText.setBounds(65,292,180,30);
        add(correoText);

        JTextField correoField = new JTextField();
        correoField.setBounds(64,320,350,30);
        correoField.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        add(correoField);

        JLabel passwordText = new JLabel("Contraseña:");
        passwordText.setFont(instance.getFontManager().quicksand_bold.deriveFont(20f));
        passwordText.setBounds(65,352,180,30);
        add(passwordText);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(64,380,350,30);
        passwordField.setFont(instance.getFontManager().quicksand_light.deriveFont(20f));
        add(passwordField);

        JButton createAccount = new JButton("Guardar Cambios");
        createAccount.setFont(instance.getFontManager().quicksand_bold.deriveFont(15f));
        createAccount.setBounds(135,500,180,30);
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File file = new File("src\\resources\\users.txt");
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    ArrayList<String> lines = new ArrayList<String>();
                    while (line != null) {
                        lines.add(line);
                        String data[] = line.split(",");
                        if (data[2].equals(correoField.getText()) && !user.getMail().equals(correoField.getText())) {
                            JOptionPane.showMessageDialog(instance,"El correo ya esta en uso","Gestor de Usuarios",JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        line = br.readLine();
                    }
                    br.close();

                    lines.set(user.getPos(),nombreField.getText() + "," +
                            lastnameField.getText() + "," +
                            correoField.getText() + "," +
                            String.valueOf(passwordField.getPassword()));

                    BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                    for(String l : lines) {
                        bw.write(l);
                        bw.newLine();
                        bw.flush();
                    }
                    bw.close();
                } catch (IOException ex) {
                    System.out.println("Archivo de datos no encontrado.");
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(instance,"Cambios aplicados correctamente","Gestor de Usuarios",JOptionPane.PLAIN_MESSAGE);
                instance.getUser().update(nombreField.getText(),
                        lastnameField.getText(),
                        correoField.getText(),
                        String.valueOf(passwordField.getPassword()));
                instance.changePanel(new MenuPanel(instance));
            }
        });
        add(createAccount);

        if(instance.getUser().isAccountOpen()) {
            nombreField.setText(user.getName());
            lastnameField.setText(user.getLastname());
            correoField.setText(user.getMail());
            passwordField.setText(user.getPassword());
        } else
            JOptionPane.showMessageDialog(this,"Algo salio mal y no se pudieron cargar los datos","Gestor de Usuarios",JOptionPane.ERROR_MESSAGE);
    }

}

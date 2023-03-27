package main.panels;

import main.TableActionEvent;
import main.Utils;
import main.component.ButtonDelete;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class ButtonPanel extends JPanel {
    ButtonDelete button = new ButtonDelete();

    public ButtonPanel() {
        setSize(80,20);
        setLayout(null);

        InputStream is = Utils.getStream("resources/deleteIcon.png");
        button = new ButtonDelete();
        button.setIcon(new ImageIcon(Utils.resizeImage(20,20,is)));
        try {
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        button.setBounds(40,0,20,20);
        add(button);
    }

    public void initButtonEvent(TableActionEvent event, int row) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.onClick(row);
            }
        });
    }

}

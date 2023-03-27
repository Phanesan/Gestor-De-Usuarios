package main.panels;

import main.*;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class ListUsersPanel extends JPanel {

    public ListUsersPanel(WindowFrame instance) {
        setSize(500,600);
        setLayout(null);
        setBackground(Color.decode("#A2D6EF"));

        JLabel title = new JLabel("Lista de Usuarios",JLabel.CENTER);
        title.setFont(instance.getFontManager().quicksand_bold.deriveFont(26f));
        title.setBounds(113,30,260,40);
        add(title);

        JLabel editText = new JLabel("Editar");
        editText.setFont(instance.getFontManager().quicksand_bold.deriveFont(18f));
        editText.setBounds(71,100,150,30);
        add(editText);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBounds(70,130,340,30);
        File file = new File("src\\resources\\users.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null) {
                String data[] = line.split(",");
                comboBox.addItem(data[0]+"-" + data[2]);
                line = br.readLine();
            }
            comboBox.setSelectedIndex(0);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(comboBox);

        JButton edit = new JButton("Editar Usuario");
        edit.setFont(instance.getFontManager().quicksand_bold.deriveFont(16f));
        edit.setBounds(70,180,340,30);
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String item = (String) comboBox.getSelectedItem();
                String[] split = item.split("-");

                LoggedUser user = null;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    int iterator = 0;
                    String line = br.readLine();
                    while (line != null) {
                        String data[] = line.split(",");

                        if (data[2].equals(split[1])) {
                            user = new LoggedUser();
                            user.login(data[0],data[1],data[2],data[3],iterator);
                            break;
                        }
                        line = br.readLine();
                        iterator++;
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                if(user != null) {
                    instance.changePanel(new EditUserPanel(instance, user));
                } else
                    JOptionPane.showMessageDialog(instance,"Algo salio mal y no se puede editar el usuario","Gestor de Usuarios",JOptionPane.ERROR_MESSAGE);
            }
        });
        add(edit);

        DefaultTableModel tableModel = new DefaultTableModel();
        String[] columns = {"Nombre","Apellido","Correo","Acciones"};
        ArrayList<String> rowsList = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null) {
                rowsList.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        tableModel.setColumnIdentifiers(columns);
        Object[] rows = new Object[4];
        for(String s : rowsList) {
            String[] split = s.split(",");
            for(int j = 0; j < 3; j++) {
                rows[j] = split[j];
            }
            rows[3] = new JButton();
            tableModel.addRow(rows);
        }
        JTable table = new JTable(tableModel);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onClick(int row) {
                int opc = JOptionPane.showConfirmDialog(instance,"¿Seguro que quieres eliminar esta cuenta?","Gestor de Usuario",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(opc == 0) {
                    if(table.isEditing()) {
                        table.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    String mail = (String) model.getValueAt(row,2);

                    File file = new File("src\\resources\\users.txt");
                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader(file));
                        String line = br.readLine();
                        ArrayList<String> lines = new ArrayList<String>();
                        while (line != null) {
                            lines.add(line);
                            line = br.readLine();
                        }
                        br.close();

                        int iterator = 0;
                        for(String s : lines) {
                            String[] split = s.split(",");
                            if(split[2].equals(mail)) {
                                if(split[2].equals(instance.getUser().getMail())) {
                                    JOptionPane.showMessageDialog(instance,"No puedes eliminar tu cuenta si estas logeado","Gestor de Usuarios",JOptionPane.ERROR_MESSAGE);
                                    return;
                                }
                                lines.remove(iterator);
                                break;
                            }
                            iterator++;
                        }

                        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                        for(String l : lines) {
                            bw.write(l);
                            bw.newLine();
                            bw.flush();
                        }
                        bw.close();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    model.removeRow(row);
                    instance.changePanel(new ListUsersPanel(instance));
                }
            }
        };
        table.getColumnModel().getColumn(3).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(3).setCellEditor(new TableActionCellEditor(event));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.setColumnSelectionAllowed(false);
        JScrollPane js = new JScrollPane(table);
        js.setBounds(20,260,440,250);
        js.setVisible(true);
        add(js);

    }
}

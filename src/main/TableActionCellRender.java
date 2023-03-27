package main;

import main.panels.ButtonPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        ButtonPanel panel = new ButtonPanel();
        if(isSelected == false && row % 2 == 0) {
            panel.setBackground(Color.WHITE);
        } else
            panel.setBackground(comp.getBackground());
        return panel;
    }
}

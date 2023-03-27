package main;

import main.component.ButtonDelete;
import main.panels.ButtonPanel;

import javax.swing.*;
import java.awt.*;

public class TableActionCellEditor extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        ButtonPanel button = new ButtonPanel();
        button.initButtonEvent(event,row);
        button.setBackground(table.getSelectionBackground());
        return button;
    }
}

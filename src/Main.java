import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatLightLaf.setup(); // Look and feel
                Window window = new Window();
            }
        });
    }
}
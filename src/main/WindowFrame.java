package main;

import main.component.ButtonDelete;
import main.panels.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class WindowFrame extends JFrame {

    private JPanel activePanel;
    private FontManager fontManager;
    private WindowFrame instance;
    private LoggedUser user;
    private JMenu cuenta;
    private JMenu usuarios;
    private JMenu ayuda;

    public WindowFrame() {
        instance = this;
        user = new LoggedUser();
        fontManager = new FontManager();
        init();
        initUsersDB();
        initBar();
        blockBarMenu(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                changePanel(new SplashPanel(instance));
                try {
                    Thread.sleep(2500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                changePanel(new LoginPanel(instance));
            }
        });
        thread.start();
    }

    /**
     * Inicializa la Ventana
     */
    private void init() {
        setTitle("Gestor de Usuarios");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        InputStream inputStream = Utils.getStream("resources/icon.png");
        setIconImage(Utils.resizeImage(64,64, inputStream));
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicializa el Bar Menu
     */
    private void initBar() {
        JMenuBar bar = new JMenuBar();
        cuenta = new JMenu("Cuenta");
        usuarios = new JMenu("Usuarios");
        ayuda = new JMenu("Ayuda");

        JMenuItem mi_cuenta = new JMenuItem("Mi Cuenta");
        JMenuItem cerrar_sesion = new JMenuItem("Cerrar Sesión");

        JMenuItem lista_usuarios = new JMenuItem("Lista de Usuarios");
        JMenuItem crear_usuarios = new JMenuItem("Crear Usuarios");

        JMenuItem como_crear = new JMenuItem("¿Como crear usuarios?");

        cuenta.add(mi_cuenta);
        cuenta.add(cerrar_sesion);

        usuarios.add(lista_usuarios);
        usuarios.add(crear_usuarios);

        ayuda.add(como_crear);

        bar.add(cuenta);
        bar.add(usuarios);
        bar.add(ayuda);

        mi_cuenta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new AccountPanel(instance));
            }
        });

        crear_usuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new CreateUserPanel(instance));
            }
        });

        como_crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new HelpPanel(instance));
            }
        });

        lista_usuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePanel(new ListUsersPanel(instance));
            }
        });

        cerrar_sesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opc = JOptionPane.showConfirmDialog(instance,"¿Seguro que quieres cerrar sesión?","Gestor de Usuario",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                if(opc == 0) {
                    if(user.isAccountOpen()) {
                        user.logout();
                    }
                    blockBarMenu(true);
                    changePanel(new LoginPanel(instance));
                }
            }
        });

        setJMenuBar(bar);
    }

    /**
     * Inicializa el archivo users.txt
     */
    private void initUsersDB() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("resources/users.txt");

        if (inputStream != null) {
            System.out.println("users.txt cargado correctamente");
        } else {
            JOptionPane.showMessageDialog(instance, "La base de datos no ha sido encontrada por lo que el programa no puede continuar", "Gestor de Usuarios", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    /**
     * Bloquea el Menu Bar
     * @param block true para bloquear el menu y false para desbloquear
     */
    public void blockBarMenu(boolean block) {
        cuenta.setEnabled(!block);
        usuarios.setEnabled(!block);
        ayuda.setEnabled(!block);
    }

    /**
     * Cambia al panel que se pasa por parametro
     * @param panel el panel que se mostrara
     */
    public void changePanel(JPanel panel) {
        if(activePanel != null) {
            remove(activePanel);
            activePanel = null;
        }
        if(panel != null) {
            add(panel);
            activePanel = panel;
        }
        revalidate();
        repaint();
    }

    public FontManager getFontManager() {
        return fontManager;
    }

    public LoggedUser getUser() {
        return user;
    }
}

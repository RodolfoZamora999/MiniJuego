package com.rodolfozamora.minigame;

import com.rodolfozamora.user.Registro;
import com.rodolfozamora.user.VerPuntos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

import com.rodolfozamora.character.PanelPersonajes;

/**
 * Clase destinada de administrar el panel principal del programa.
 *
 * @author rodol
 */
public class PanelPrincipal extends JPanel {
    private final JFrame context;
    private JButton btnIniciar, btnVerPuntuacion;
    private JLabel lblTexto, logo, lblCuenta;
    private JLabel lblLeyenda, lblEstadoConexion;


    public PanelPrincipal(JFrame context) {
        super();
        this.context = context;
        initComponents();
    }

    /**
     * Método que inicializa los componentes necesarios de de la clase.
     */
    private void initComponents() {
        //Propiedades del panel
        this.setLocation(0, 0);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setBackground(new Color(64, 29, 118));

        this.lblTexto = new JLabel();
        this.lblTexto.setText("''Mini-Juego''");
        this.lblTexto.setSize(480, 80);
        this.lblTexto.setLocation(400, 50);
        this.lblTexto.setFont(new Font("Tahoma", Font.BOLD, 70));
        this.lblTexto.setHorizontalAlignment(JLabel.CENTER);
        this.lblTexto.setForeground(Color.WHITE);

        this.logo = new JLabel();
        this.logo.setSize(300, 300);
        this.logo.setLocation(490, 150);
        //this.logo.setOpaque(true);
        this.logo.setIcon(new ImageIcon(getClass().getResource("/images/logo.png")));

        this.btnIniciar = new JButton();
        this.btnIniciar.setSize(250, 100);
        this.btnIniciar.setLocation(515, 550);
        this.btnIniciar.setBorder(null);
        this.btnIniciar.setBackground(new Color(68, 43, 169));
        this.btnIniciar.setText("Jugar");
        this.btnIniciar.setFont(new Font("Tahoma", Font.BOLD, 30));
        this.btnIniciar.setForeground(Color.WHITE);
        this.btnIniciar.addActionListener((ActionEvent e) ->
        {
            PanelPersonajes panel = new PanelPersonajes(context);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint();
        });

        this.lblLeyenda = new JLabel();
        this.lblLeyenda.setSize(280, 450);
        this.lblLeyenda.setLocation(960, 100);
        this.lblLeyenda.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true),
                "Recomendación", TitledBorder.CENTER, TitledBorder.TOP, new Font("Calibri", Font.BOLD, 20), Color.WHITE));
        this.lblLeyenda.setText("<html>Se recomienda iniciar sesión para poder almacenar datos "
                + "de progreso de los distintos jugadores."
                + "<br><br><br><br>"
                + "<b>Nota:</b>  En esta versión de prueba, la creación de usuarios esta deshabilitada "
                + "por cuestiones de comodidad.</html>");
        this.lblLeyenda.setVerticalAlignment(JLabel.TOP);
        this.lblLeyenda.setFont(new Font("Arial", Font.PLAIN, 14));
        this.lblLeyenda.setForeground(Color.WHITE);

        this.btnVerPuntuacion = new JButton();
        this.btnVerPuntuacion.setSize(200, 30);
        this.btnVerPuntuacion.setLocation(this.getWidth() - 280, this.getHeight() - 150);
        this.btnVerPuntuacion.setBorder(null);
        this.btnVerPuntuacion.setBackground(new Color(68, 43, 169));
        this.btnVerPuntuacion.setText("Ver puntos");
        this.btnVerPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.btnVerPuntuacion.setForeground(Color.WHITE);
        this.btnVerPuntuacion.addActionListener((ActionEvent e) ->
        {
            if (btnVerPuntuacion.isEnabled()) {
                //Deshabilita los botones
                btnIniciar.setEnabled(false);
                btnVerPuntuacion.setEnabled(false);
                lblCuenta.setEnabled(false);

                //Prepara la interfaz
                VerPuntos panel = new VerPuntos(context);
                context.add(panel, 0);
                context.revalidate();
                context.repaint();
            }
        });

        this.lblCuenta = new JLabel();
        this.lblCuenta.setText("¿No tienes cuenta? Regístrate aquí");
        this.lblCuenta.setLocation(this.getWidth() - 300, this.getHeight() - 100);
        this.lblCuenta.setSize(250, 30);
        this.lblCuenta.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.lblCuenta.setHorizontalAlignment(JLabel.CENTER);
        this.lblCuenta.setForeground(Color.WHITE);
        this.lblCuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (lblCuenta.isEnabled()) {
                    //Se deshabilitan los controles.
                    btnIniciar.setEnabled(false);
                    btnVerPuntuacion.setEnabled(false);
                    lblCuenta.setEnabled(false);

                    //Se prepara la interfaz.
                    Registro panel = new Registro(context);
                    context.add(panel, 0);
                    context.revalidate();
                    context.repaint();
                }
            }
        });

        //Estado que describe la conexión
        this.lblEstadoConexion = new JLabel();
        this.lblEstadoConexion.setSize(300, 25);
        this.lblEstadoConexion.setLocation(10, 20);
        this.lblEstadoConexion.setText("<html>Estado de conexión: "
                + "<font color=\"green\">Conectado</font></html>");
        this.lblEstadoConexion.setForeground(Color.WHITE);
        this.lblEstadoConexion.setFont(new Font("Calibri", Font.BOLD, 18));

        //Añadir los componentes al panel
        this.add(this.lblTexto);
        this.add(this.logo);
        this.add(this.btnIniciar);
        this.add(this.lblLeyenda);
        this.add(this.btnVerPuntuacion);
        this.add(this.lblCuenta);
        this.add(this.lblEstadoConexion);
    }

    /**
     * Método que habilita los botones.
     */
    public void enableComponents() {
        this.btnIniciar.setEnabled(true);
        this.btnVerPuntuacion.setEnabled(true);
        this.lblCuenta.setEnabled(true);
    }
}

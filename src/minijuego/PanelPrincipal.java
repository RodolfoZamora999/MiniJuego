package minijuego;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import seccion_personajes.PanelPersonajes;

/**
 * Clase destinada de administrar el panel principal del programa.
 * @author rodol
 */
public class PanelPrincipal extends JPanel
{
    private final JFrame context;
    private JButton btnIniciar;
    private JLabel lblTexto, logo;
    
    public PanelPrincipal(JFrame context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes necesarios de de la clase.
     */
    private void initComponents()
    {
        //Propiedades del panel
        this.setLocation(0, 0);
        this.setSize(1280, 720);
        this.setLayout(null);
        this.setBackground(new Color(43, 81, 169));
        
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
        this.logo.setIcon(new ImageIcon(getClass().getResource("/imagenes/Logo.png")));
        
        this.btnIniciar = new JButton();
        this.btnIniciar.setSize(250, 100);
        this.btnIniciar.setLocation(515, 550);
        this.btnIniciar.setBorder(null);
        this.btnIniciar.setBackground(new Color(68, 43, 169));
        this.btnIniciar.setText("Iniciar");
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
        
       
        
        //Añadir los componentes al panel
        this.add(this.lblTexto);
        this.add(this.logo);
        this.add(this.btnIniciar);
    }
}

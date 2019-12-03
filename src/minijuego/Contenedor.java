package minijuego;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * Clase que sirve como contenedor del programa.
 * @author rodol
 */
public class Contenedor extends JFrame 
{
    private PanelPrincipal panel;
    
    public Contenedor()
    {
        super();
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes principales de la clase.
     */
    private void initComponents()
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        
        //Propiedades del JFrame 
        this.setTitle("Mini-Juego");
        this.setSize(1280, 720);
        this.setLocation((dimension.width - this.getWidth()) / 2, (dimension.height - this.getHeight()) / 2);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        
        this.panel = new PanelPrincipal(this);
        
        this.add(panel);   
    }
    
}

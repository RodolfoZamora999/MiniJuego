package seccion_escenarios;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import logica_juego.Fondo;
import logica_juego.PanelArena;
import seccion_personajes.PanelPersonajes;

/**
 * Clase destiana para administrar el panel de los niveles.
 * @author rodol
 */
public class PanelEscenario extends JPanel
{
    private final JFrame context;
    private JLabel lblTexto;
    private TarjetaEscenario tarjeta1, tarjeta2;
    private JButton btnAdelante, btnAtras, btnRegresar;
    
    public PanelEscenario(JFrame context)
    {
        super();
        this.context = context;
        initComponets();
    }
    
    /**
     * Método que inicializa los componentes necesarios de la clase.
     */
    private void initComponets()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setBackground(new Color(43, 81, 169)); 
        this.setLayout(null);
        
        this.lblTexto = new JLabel();
        this.lblTexto.setText("<html>Selecciona un escenario  para iniciar con el juego</html>");
        this.lblTexto.setHorizontalAlignment(JLabel.CENTER);
        this.lblTexto.setSize(1280, 80);
        this.lblTexto.setLocation(0, 0);
        this.lblTexto.setForeground(Color.WHITE);
        this.lblTexto.setFont(new Font("Tahoma", Font.BOLD, 24));
        
        this.tarjeta1 = new TarjetaEscenario("Explanada");
        this.tarjeta1.setLocation(190, 85);
       // this.tarjeta1.setText("<html>Bievenidos a la explanada</html>");
        this.tarjeta1.add(new Fondo());
        this.tarjeta1.getComponent(0).setSize(850, 500);
        this.tarjeta1.getComponent(0).setLocation(25, 40);
        this.tarjeta1.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                super.mouseClicked(e);
                iniciarJuego("Explanada");
            }
        });
        
        this.tarjeta2 = new TarjetaEscenario("Entrada");
        this.tarjeta2.setLocation(1280, 85);
        this.tarjeta2.setForeground(Color.RED);
        this.tarjeta2.setText("Proximamente...");
        this.tarjeta2.setFont(new Font("Tahoma", Font.BOLD, 36));
        this.tarjeta2.setHorizontalAlignment(JLabel.CENTER);
        this.tarjeta2.setVerticalAlignment(JLabel.CENTER);
        this.tarjeta2.setEnabled(false);
        
        //Más malas practicas
        this.btnAtras = new JButton()
        {
            @Override
            public void paint(Graphics g) 
            {
                //super.paint(g);
                
                Graphics2D graphics = (Graphics2D)g;
                graphics.setStroke(new BasicStroke(30));
                
                graphics.setColor(new Color(0, 0, 0));
                graphics.drawLine(70, 0, 10, 150);
                graphics.drawLine(0, 150, 70, 300);
                
                graphics.setColor(new Color(131, 43, 169));
                graphics.drawLine(60, 0, 0, 150);
                graphics.drawLine(0, 150, 60, 300);
            }  
        };
        this.btnAtras.setOpaque(false);
        this.btnAtras.setSize(60, 300);
        this.btnAtras.setLocation(10, (this.getHeight() - this.btnAtras.getHeight()) / 2);
        this.btnAtras.setText("Back");
        this.btnAtras.addActionListener(((e) ->
        {
           if(this.tarjeta1.getX() != 190)
            {
                Thread thread = new Thread(()-> animar(5));
                thread.start();
            }
        }));
        
        this.btnAdelante = new JButton()
        {
            @Override
            public void paint(Graphics g)
            {
                //super.paint(g); Se anula el método paint
                
                Graphics2D graphics = (Graphics2D)g;
                graphics.setStroke(new BasicStroke(30));
                
                graphics.setColor(new Color(0, 0, 0));
                graphics.drawLine(0, 0, 50, 150);
                graphics.drawLine(0, 300, 50, 150);
                
                graphics.setColor(new Color(131, 43, 169));
                graphics.drawLine(0, 0, 60, 150);
                graphics.drawLine(0, 300, 60, 150);
            }
            
        };
        this.btnAdelante.setSize(60, 300);
        this.btnAdelante.setOpaque(false);
        this.btnAdelante.setLocation(this.getWidth() - (this.btnAdelante.getWidth() + 30),
                (this.getHeight() - this.btnAtras.getHeight()) / 2);
        this.btnAdelante.setText("Next");
        this.btnAdelante.addActionListener(((e) ->
        {
            if(this.tarjeta2.getX() != 190)
            {
                Thread thread = new Thread(()-> animar(-5));
                thread.start();
            } 
        }));
        
        this.btnRegresar = new JButton();
        this.btnRegresar.setBackground(new Color(68, 43, 169));
        this.btnRegresar.setText("Regresar");
        this.btnRegresar.setForeground(Color.WHITE);
        this.btnRegresar.setBorder(null);
        this.btnRegresar.setLocation(1100, 620);
        this.btnRegresar.setSize(150, 40);
        this.btnRegresar.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.btnRegresar.addActionListener((e)->
        {
            PanelPersonajes panel = new PanelPersonajes(context);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint();
        });
        
        //Integración de los componentes al panel
        this.add(this.btnAdelante);
        this.add(this.btnRegresar);
        this.add(this.lblTexto);
        this.add(this.tarjeta1);
        this.add(this.tarjeta2);
        this.add(this.btnAtras);          
    }
    
   /**
     * Método para el evento de transicción
     * @param aumento 
     */
    private synchronized void animar(int aumento)
    {
            for(int i = 0; i < 218; i++)
            {
                this.tarjeta1.setLocation(this.tarjeta1.getX() + aumento, this.tarjeta1.getY());
                this.tarjeta2.setLocation(this.tarjeta2.getX() + aumento, this.tarjeta2.getY());  
                
                try 
                {
                    Thread.sleep(1);
                } 
                catch (InterruptedException ex) 
                {
                    Logger.getLogger(PanelPersonajes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    /**
     * Método para el evento de pasar al juego.
     * @param nombre 
     */
    private void iniciarJuego(String nombre)
    {
        PanelArena panel = new PanelArena(context);
        context.remove(this);
        context.add(panel);
        context.revalidate();
        context.repaint();
    }
}

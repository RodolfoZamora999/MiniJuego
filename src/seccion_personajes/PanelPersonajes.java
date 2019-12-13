package seccion_personajes;

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
import minijuego.PanelPrincipal;
import seccion_escenarios.PanelEscenario;

/**
 * Clase destinada para la seccíón de escenarios del juego.
 * @author rodol
 */
public class PanelPersonajes extends JPanel
{
    private final JFrame context;
    private Thread thread;
    private JButton btnAdelante, btnAtras, btnRegresar;
    private JLabel lblTexto;
    private TarjetaPersonaje tarjeta1, tarjeta2, tarjeta3, tarjeta4;
    
    public PanelPersonajes(JFrame context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes necesarios para la clase.
     */
    private void initComponents()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBackground(new Color(64, 29, 118));
        
        this.lblTexto = new JLabel();
        this.lblTexto.setText("<html>Selecciona un personaje para continuar</html>");
        this.lblTexto.setSize(1280, 80);
        this.lblTexto.setLocation(0, 0);
        this.lblTexto.setHorizontalAlignment(JLabel.CENTER);
        this.lblTexto.setForeground(Color.WHITE);
        this.lblTexto.setFont(new Font("Tahoma", Font.BOLD, 24));
        
        //Otra muy mala practica de programación
        this.tarjeta1 = new TarjetaPersonaje("Jose Chuy")
        {
            @Override
            public void paint(Graphics g) 
            {
                super.paint(g); 
                
                Graphics2D graphics = (Graphics2D)g;
                graphics.setColor(Color.RED);
                
                //Se dibuja la cabeza
                graphics.setColor(new Color(239, 188, 128));
                graphics.fillOval(185, 140, 50, 50);
                
                //Se dibuja el pelo
                graphics.setColor(Color.BLACK);
                graphics.fillArc(185, 140, 50, 30, 0, 180);

                //Se dibujan los ojos
                graphics.setColor(Color.WHITE);
                graphics.fillOval(191, 150, 18, 15);
                graphics.fillOval(212, 150, 18, 15);
                graphics.setColor(Color.BLACK);
                graphics.fillOval(198, 154, 6, 6);
                graphics.fillOval(218, 154, 6, 6);

                //Se dibuja la "sonrisa"
                graphics.setColor(Color.WHITE);
                graphics.fillArc(200, 175, 18, 20, 0, 180);
        
                //Se dibuja el cuerpo
                graphics.setColor(new Color(41, 123, 200));
                graphics.fillRect(180, 190, 60, 80);

                //Se dibuja los brazos
                graphics.setColor(new Color(150, 100, 100));
                graphics.fillRect(170, 190, 15, 70);
                graphics.fillRect(235, 190, 15, 70);
                
                //Se dibuja las lineas
                graphics.setColor(new Color(13, 46, 77));
                graphics.fillRect(180, 270, 20, 40);
                graphics.fillRect(220, 270, 20, 40);
                
                //Se dibuja los zapatos
                graphics.setColor(new Color(82, 68, 40));
                graphics.fillOval(170, 300, 30, 20);
                graphics.fillOval(220, 300, 30, 20);  
                
                //Barras de estado
                graphics.setStroke(new BasicStroke(3));
                graphics.setColor(Color.WHITE);
                graphics.setFont(new Font("Tahoma", Font.BOLD, 18));
                graphics.drawString("Salud", 20, 370);
                graphics.drawString("Velocidad", 20, 420);
                graphics.drawString("Resistencia", 20, 470);
                
                graphics.setColor(Color.GREEN);
                graphics.fillRect(150, 350, 100, 20);
                graphics.fillRect(150, 400, 60, 20);
                graphics.fillRect(150, 450, 90, 20);
                
            }
            
        };
        this.tarjeta1.setText("<html><center>El primer personaje de nuestro juego.<br>"
                + "¡Con el vivirás aventuras increibles!</center></html>");
        this.tarjeta1.setVerticalAlignment(JLabel.TOP);
        this.tarjeta1.setHorizontalAlignment(JLabel.CENTER);
        this.tarjeta1.setLocation(200, 100);
        
        
        this.tarjeta1.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                super.mouseClicked(e); 
                escenario(tarjeta1.getNombre());
            }
        });

        this.tarjeta2 = new TarjetaPersonaje("Ramses");
        this.tarjeta2.setLocation(700, 100);
        this.tarjeta2.setText("Proximamente...");
        this.tarjeta2.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.tarjeta2.setEnabled(false);
        
        this.tarjeta3 = new TarjetaPersonaje("Saúl");
        this.tarjeta3.setLocation(1300, 100);
        this.tarjeta3.setText("Proximamente...");
        this.tarjeta3.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.tarjeta3.setEnabled(false);
        
        this.tarjeta4 = new TarjetaPersonaje("Rodolfo");
        this.tarjeta4.setLocation(1800, 100);
        this.tarjeta4.setText("Proximamente...");
        this.tarjeta4.setFont(new Font("Tahoma", Font.BOLD, 32));
        this.tarjeta4.setEnabled(false);
        
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
            if(this.tarjeta1.getX() != 200)
            {
                this.thread = new Thread(()-> {animar(4); });
                this.thread.start();
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
        this.btnAdelante.setOpaque(false);
        this.btnAdelante.setSize(60, 300);
        this.btnAdelante.setLocation(this.getWidth() - (this.btnAdelante.getWidth() + 30),
                (this.getHeight() - this.btnAtras.getHeight()) / 2);
        this.btnAdelante.setText("Next");
        this.btnAdelante.addActionListener(((e) ->
        {
            if(this.tarjeta4.getX() != 700)
            {
                this.thread = new Thread(()-> {animar(-4); });
                this.thread.start();
            }
        }));
        
        this.btnRegresar = new JButton();
        this.btnRegresar.setLocation(1100, 620);
        this.btnRegresar.setBackground(new Color(68, 43, 169));
        this.btnRegresar.setText("Regresar");
        this.btnRegresar.setForeground(Color.WHITE);
        this.btnRegresar.setBorder(null);
        this.btnRegresar.setSize(150, 40);
        this.btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.btnRegresar.addActionListener((e)->
        {
            PanelPrincipal panel = new PanelPrincipal(context);
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
       this.add(this.tarjeta3);
       this.add(this.tarjeta4);
       this.add(this.btnAtras);
    }
    
    /**
     * Método para el evento de transicción
     * @param aumento 
     */
    private synchronized void animar(int aumento)
    {
            for(int i = 0; i < 275; i++)
            {
                this.tarjeta1.setLocation(this.tarjeta1.getX() + aumento, this.tarjeta1.getY());
                this.tarjeta2.setLocation(this.tarjeta2.getX() + aumento, this.tarjeta2.getY());
                this.tarjeta3.setLocation(this.tarjeta3.getX() + aumento, this.tarjeta3.getY());
                this.tarjeta4.setLocation(this.tarjeta4.getX() + aumento, this.tarjeta4.getY());
                
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
     * Método para el evento de pasar al escenario
     * @param nombre 
     */
    private void escenario(String nombre)
    {
        PanelEscenario panel = new PanelEscenario(context);
        context.remove(this);
        context.add(panel);
        context.revalidate();
        context.repaint();
    }
}

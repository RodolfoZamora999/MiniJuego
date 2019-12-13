package logica_juego;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 * Clase que representara el personaje del juego
 * @author rodol
 */
public class Personaje extends JLabel
{
    private Thread threadDesplazamiento;
    private Thread threadSalto;
    
    public Personaje()
    {
        super();
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes principales de la clase.
     */
    private void initComponents()
    {
        this.setSize(80, 180);
        this.setLocation(0, 0);
        //this.setOpaque(true);
        this.setBackground(Color.BLACK); 
        
        
        //Se inicializan los hilos
        this.threadDesplazamiento = new Thread();
        this.threadSalto = new Thread();
        
        //Se llama al método para animar
        animacionMovimiento();
    }
    
    /**
     * Método encargado de gestionar toda la logica del movimiento del personaje.
     */
    private void animacionMovimiento()
    {
        //Arriba
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "derecha");
        this.getActionMap().put("derecha", new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!threadDesplazamiento.isAlive())
                {
                    threadDesplazamiento = new Thread(() ->
                    {
                        for(int i = 0; i <20; i++)
                        {
                            //Esto es un mala practica, pero no tengo de otra
                            if(Personaje.this.getX() < 1200)
                                Personaje.this.setLocation(Personaje.this.getX() + 5, Personaje.this.getY());
                            else
                                return;
                            try
                            {
                                Thread.sleep(4);
                            }
                            catch (InterruptedException ex)
                            {
                                Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                    threadDesplazamiento.start();
                }
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "izquierda");
        this.getActionMap().put("izquierda", new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!threadDesplazamiento.isAlive())
                {
                    threadDesplazamiento  = new Thread(() ->
                    {
                        for(int i = 0; i <20; i++)
                        {
                            //Esto es un mala practica, pero no tengo de otra
                            if(Personaje.this.getX() > 0)
                                Personaje.this.setLocation(Personaje.this.getX() - 5, Personaje.this.getY());
                            else
                                return;
                            try
                            {
                                Thread.sleep(4);
                            }
                            catch (InterruptedException ex)
                            {
                                Logger.getLogger(PanelArena.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    threadDesplazamiento.start();
                }
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "espacio");
        this.getActionMap().put("espacio", new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!threadSalto.isAlive())
                {
                    threadSalto = new Thread(()->
                    {
                        int posicionY = Personaje.this.getLocation().y;
                        int nuevaY;
                        double t = 0;
                        double tem;
                        
                        do
                        {
                            tem = ((150 * Math.sin(Math.toRadians(90)) * t) - ((32 * 0.5) * Math.pow(t, 2)));
                            nuevaY = (int)tem;
                            
                            Personaje.this.setLocation(Personaje.this.getX() , posicionY - nuevaY);
                            t += 0.1;
                            
                            try
                            {
                                Thread.sleep(15);
                            }
                            catch (InterruptedException ex)
                            {
                                Logger.getLogger(Personaje.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } while ((posicionY - nuevaY) <= posicionY);
                        
                        Personaje.this.setLocation(Personaje.this.getLocation().x, posicionY);
                    });
                    
                    threadSalto.start();
                }
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "arriba");

        this.getActionMap().put("arriba", new AbstractAction() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!threadSalto.isAlive())
                {
                    threadSalto = new Thread(()->
                    {
                        int posicionY = Personaje.this.getLocation().y;
                        int nuevaY;
                        double t = 0;
                        double tem;
                        
                        do
                        {
                            tem = ((150 * Math.sin(Math.toRadians(90)) * t) - ((32 * 0.5) * Math.pow(t, 2)));
                            nuevaY = (int)tem;
                            
                            Personaje.this.setLocation(Personaje.this.getX() , posicionY - nuevaY);
                            
                            t += 0.1;
                            
                            try
                            {
                                Thread.sleep(15);
                            }
                            catch (InterruptedException ex)
                            {
                                Logger.getLogger(PanelArena.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } while ((posicionY - nuevaY) <= posicionY);
                        
                        Personaje.this.setLocation(Personaje.this.getLocation().x, posicionY);
                        
                    });
                    
                    threadSalto.start();
                }
            }
        });
    } 
    
    /**
     * Este método tiene como objetivo animar(aturdir) el personaje cuando se invoque.
     */
    public void aturdirPersoanje()
    {
        //Pausar el desplazamiento por 5 segundos
        if(this.threadDesplazamiento != null && !threadDesplazamiento.isAlive())
        {
            this.threadDesplazamiento = new Thread(()->
            {
                try
                {
                    System.out.println("¡Personaje aturdido!");
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException ex)
                {
                    System.err.println("Thread Exception in threadMovimiento");
                }
            });
            this.threadDesplazamiento.start();
        }

        //Pausar el salto por 5 segundos.
        if(this.threadSalto != null && !threadSalto.isAlive())
        {
            this.threadSalto = new Thread(()->
            {
                try
                {
                    System.out.println("¡Personaje aturdido!");
                    TimeUnit.SECONDS.sleep(3);
                }
                catch (InterruptedException ex)
                {
                    System.err.println("Thread Exception in ThreadSalto");
                }
            });
            
            this.threadSalto.start();
       }
    }

    @Override
    public void paint(Graphics g) 
    {
        super.paint(g);
        
        Graphics2D graphics = (Graphics2D)g;
        graphics.setStroke(new BasicStroke(3));
        
        //Se dibuja la cabeza
        graphics.setColor(new Color(239, 188, 128));
        graphics.fillOval(15, 0, 50, 50);
        
        //Se dibuja el pelo
        graphics.setColor(Color.BLACK);
        graphics.fillArc(15, 0, 50, 30, 0, 180);
        
        //Se dibujan los ojos
        graphics.setColor(Color.WHITE);
        graphics.fillOval(21, 10, 18, 15);
        graphics.fillOval(42, 10, 18, 15);
        graphics.setColor(Color.BLACK);
        graphics.fillOval(28, 14, 6, 6);
        graphics.fillOval(48, 14, 6, 6);
        
        //Se dibuja la "sonrisa"
        graphics.setColor(Color.WHITE);
        graphics.fillArc(30, 35, 18, 20, 0, 180);
        
        //Se dibuja el cuerpo
        graphics.setColor(new Color(41, 123, 200));
        graphics.fillRect(10, 50, 60, 80);
        
        //Se dibuja los brazos
        graphics.setColor(new Color(150, 100, 100));
        graphics.fillRect(0, 50, 15, 70);
        graphics.fillRect(65, 50, 15, 70);
        
        
        //Se dibuja las lineas
        graphics.setColor(new Color(13, 46, 77));
        graphics.fillRect(10, 130, 20, 40);
        graphics.fillRect(50, 130, 20, 40);
        
        //Se dibuja los zapatos
        graphics.setColor(new Color(82, 68, 40));
        graphics.fillOval(0, 160, 30, 20);
        graphics.fillOval(50, 160, 30, 20);
    }
    
    /**
     * Metodo publico que sirve para detener la ejecución del hilo del personaje.
     */
    public void detenerPersonaje()
    {
        //Detiene el hilo del desplazamiento
        if(this.threadDesplazamiento != null)
            this.threadDesplazamiento.stop();
        
        //Detiene el hilo del salto
        if(this.threadSalto != null)
            this.threadSalto.stop();
    }
}

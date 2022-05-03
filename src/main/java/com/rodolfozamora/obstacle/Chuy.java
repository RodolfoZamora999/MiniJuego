package com.rodolfozamora.obstacle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JLabel;
import com.rodolfozamora.gamelogic.PanelArena;

/**
 * Clase destiana para la creación de chuy.
 * @author rodol
 */
public class Chuy extends JLabel implements Runnable
{
    private final PanelArena context;
            
    public Chuy(PanelArena context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Método que se encarga de inicializar los componentes necesarios para la clase.
     */
    private void initComponents()
    {
        this.setSize(240, 100);
        this.setLocation(context.getWidth(), context.getHeight() - (this.getHeight() + 40));
    }
     
    /**
     * Método sobre-escrito para su ejecucuón en un hilo
     */
    @Override
    public void run()
    {
        try 
        {
            //De esta manera empieza de manera aleatoria cada bloque
            TimeUnit.SECONDS.sleep(new Random().nextInt(20) + 10);
            TimeUnit.SECONDS.sleep(1);
        } 
        catch (InterruptedException ex)
        {
            //Logger.getLogger(Nota.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Se detuvo chuy");
        }
        
        int x = getX();
        int y = getY();
        
        while(true)
        {
            if(Chuy.this.getLocation().x < (context.getX() - this.getWidth()))
                break;
            
            Chuy.this.setLocation(getX() - 1, getY());
            
             try
             {
                 TimeUnit.MILLISECONDS.sleep(3);
             }
             catch (InterruptedException ex)
             {
                 //Logger.getLogger(Nota.class.getName()).log(Level.SEVERE, null, ex);
                 System.err.println("Se detuvo chuy");
             }
             
             if(comprobarChoque())
                 this.context.aturdirPersonaje();
                 

        }
        
        this.setLocation(x, y);
    }
    
     /**
     * Método que comprueba la colición de objetos.
     * @return 
     */
    private boolean comprobarChoque()
    { 
        //Algoritmo basico para las coliciones
        if(this.getX() < (context.getLocalizacion().x + context.getDimensionPersonaje().width)
                && (this.getX() + this.getWidth()) > context.getLocalizacion().x)
        {
            if(this.getY() < (context.getLocalizacion().y + context.getDimensionPersonaje().height)
                    && (this.getY() + this.getHeight()) > context.getLocalizacion().y)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g); 
        
        //Se crea el objeto Graphics
        Graphics2D graphics = (Graphics2D)g;
        
        
        //Se dibuja el modelo del pollo
        graphics.setColor(Color.WHITE);
        graphics.fillOval(20, 60, 50, 35);
        graphics.fillOval(15, 50, 25, 15);
        graphics.fillOval(40, 90, 8, 25);
        graphics.fillOval(50, 90, 8, 25);
        graphics.setColor(Color.RED);
        graphics.fillOval(15, 45, 15, 10);
        graphics.setColor(Color.YELLOW);
        graphics.fillOval(5, 55, 15, 8);
        
        
        //Se hace el dibujo del modelo de chuy
        graphics.setColor(new Color(142, 98 , 11));
        graphics.fillOval(100, 25, 120, 65);
        graphics.fillOval(70, 5, 60, 50);
        graphics.fillOval(50, 25, 30, 20);
        graphics.fillOval(120, 50, 20, 60);
        graphics.fillOval(180, 50, 20, 60);
        graphics.fillOval(200, 40, 40, 20);
        graphics.setColor(Color.WHITE);
        graphics.fillOval(75, 20, 15, 15);
        graphics.setStroke(new BasicStroke(10));
        graphics.setColor(Color.BLACK);
        graphics.drawArc(90, 30, 30, 30, 0, 100);
        graphics.fillOval(75, 23, 10, 10);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawLine(75, 20, 85, 15);
    }
    
    
    
    
}

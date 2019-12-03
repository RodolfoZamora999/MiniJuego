package obstaculos;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import logica_juego.PanelArena;

/**
 * Clase destiana para la creación de las notas.
 * @author rodol
 */
public class Nota extends JLabel implements Runnable
{
    private final PanelArena context;
            
    public Nota(PanelArena context)
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
        super.setSize(60, 35);
        this.setLocation(0, -80);
        //this.setOpaque(true);
        //this.setBackground(new Color(255, 255, 255));
        this.setFont(new Font("Tahoma", Font.BOLD, 28));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
    }
    
    /**
     * Método sobre-escrito para su ejecucuón en un hilo
     */
    @Override
    public void run() 
    {
        //Determina el color. Si es negro (Normal), Rojo (Peligroso)
        if(new Random().nextInt(4) == 3)
        {
            //Nota.this.setBackground(Color.RED);
            Nota.this.setText("++");
            Nota.this.setForeground(Color.GREEN);
        }
        else
        {
           //Nota.this.setBackground(new Color(50, 50 ,200)); 
             Nota.this.setText("N/A");
            Nota.this.setForeground(Color.RED);
        }
            
        
        try 
        {
            //De esta manera empieza de manera aleatoria cada bloque
            TimeUnit.SECONDS.sleep(new Random().nextInt(5));
        } 
        catch (InterruptedException ex)
        {
            //Logger.getLogger(Nota.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Se detuvo el hilo");
        }
        
        int x = getX();
        int y = getY();
        
        while(true)
        {
            if(Nota.this.getLocation().y > context.getHeight())
                break;
            
            Nota.this.setLocation(getX(), getY() + 1);
         
            try
            {
                TimeUnit.MILLISECONDS.sleep(5);
            }
            catch (InterruptedException ex)
            {
                //Logger.getLogger(Nota.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Se detuvo el hilo");
            } 
            
            //Se comprueba si hay alguna colisión
            if(comprobarChoque())
            {
                if(this.getText().equals("++"))
                    this.context.incrementarPuntuacion();
                
                else if(this.getText().equals("N/A"))
                    this.context.resetearPuntuacion();
                
                break;
            }
                
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
}

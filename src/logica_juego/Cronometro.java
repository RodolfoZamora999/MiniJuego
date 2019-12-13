package logica_juego;

import java.awt.Color;
import java.awt.Font;
import java.util.concurrent.TimeUnit;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *Clase que se encargara de administrar el tiempo.
 * @author Rodolfo Zamora.
 */
public class Cronometro extends JLabel implements Runnable
{
    private Thread thread;
    
    private final JComponent context;
    private int time;
    
    /**
     * Único método constructor de la clase.
     * @param context Contexto de la clase.
     * @param time Tiempo de duración del juego. 
     */
    public Cronometro(JComponent context, int time)
    {
        super();
        this.context = context;
        this.time = time;
        initComponents();
    }
    
    /**
     * Método encargado de inicializar los componentes escenciales de la clase.
     */
    private void initComponents()
    {
        this.setSize(200, 70);
        this.setLocation(0, 0);
        this.setFont(new Font("Calibri", Font.BOLD, 60));
        this.setForeground(new Color(255, 255, 255, 200));
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setText(time+":00");
    }
    
    /**
     * Método publico que inicializa el contador del juego.
     */
    public void iniciarContador()
    {
        this.thread = new Thread(this);
        this.thread.start();
    }
    
    /**
     * Método que finaliza el hilo interno del objeto.
     */
    public void detenerContador()
    {
        if(this.thread != null && this.thread.isAlive())
            this.thread.stop();
    }
    
    /**
     * Método que retorna el tiempo del cronometro
     * @return Tiempo del cronometro.
     */
    public int getTime()
    {
        return this.time;
    }

    /**
     * Método sobre-escrito de la clase Runnable.
     */
    @Override
    public void run() 
    {
        while(time > 0)
        {
            try 
            {
                time -= 1;
                
                for(int i = 59; i >=0; i--)
                {
                    if(i > 9)
                        this.setText(time+":"+i);
                    else
                        this.setText(time+":0"+i);
    
                    TimeUnit.SECONDS.sleep(1);
                }
                
                //Detener la partida cuando el tiempo termine
                ((PanelArena)context).detenerPartida();
            } 
            catch (InterruptedException ex) 
            {
                System.err.println("Finalizo hilo 'Cronometro'");
            }
        } 
    }
}

package logica_juego;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import minijuego.PanelPrincipal;
import obstaculos.Chuy;
import obstaculos.Nota;

/**
 * Clase destiana de administrar el panel del area del juego.
 * @author rodol
 */
public class PanelArena extends JPanel
{
    private final JFrame context;
    private JLabel lblPuntuacion, lblMateria;
    private byte puntuacion;
    private JButton btnRegresar;
    private Personaje personaje;
    private Nota[] notas; 
    private Chuy chuy;
    private ScheduledExecutorService controlNotas;
    private ScheduledExecutorService controlChuy;
    private Fondo fondo;
    
    
    public PanelArena(JFrame context)
    {
        super();
        this.context = context;
        initComponents();  
        iniciarObstaculos();
    }
    
    /**
     * Método que inicializa los componentes necesarios de la clase.
     */
    private void initComponents()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBackground(new Color(255, 255, 255));
        this.setDoubleBuffered(true);
        
        this.puntuacion = 0;

        this.personaje = new Personaje();
        this.personaje.setLocation((this.getWidth() - this.personaje.getWidth()) / 2, this.getHeight() - (this.personaje.getHeight() + 40));
        this.add(this.personaje);
        
        this.lblPuntuacion = new JLabel();
        this.lblPuntuacion.setSize(200, 50);
        this.lblPuntuacion.setLocation(100, 10);
        this.lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 36));
        this.lblPuntuacion.setText("0/100");
        this.lblPuntuacion.setForeground(new Color(133, 12, 190));
        this.add(this.lblPuntuacion);
        
        this.lblMateria = new JLabel();
        this.lblMateria.setSize(200, 65);
        //this.lblMateria.setOpaque(true);
        this.lblMateria.setLocation(100, 60);
        this.lblMateria.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.lblMateria.setText("<html>Nivel:<br>Tópicos avanzados de<br>"
                +"programación</html>");
        this.lblMateria.setForeground(new Color(10, 10, 200));
        this.add(this.lblMateria);
        
        //Se integran los componentes al panel
        this.btnRegresar = new JButton();
        this.btnRegresar.setBackground(new Color(250, 0, 0));
        this.btnRegresar.setText("Salir");
        this.btnRegresar.setForeground(Color.WHITE);
        this.btnRegresar.setBorder(null);
        this.btnRegresar.setLocation(10, 10);
        this.btnRegresar.setSize(60, 30);
        this.btnRegresar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.btnRegresar.addActionListener((e)->
        {
            //Apagar los ThreadPools
            this.controlNotas.shutdownNow();
            this.controlChuy.shutdownNow();
            //Regresar al panel de inicio
            PanelPrincipal panel = new PanelPrincipal(context);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint();
        });
        this.add(this.btnRegresar);
        
        //Se integra el personaje de chuy al juego.
        this.chuy = new Chuy(this);
        this.add(chuy);
        
        //Creación de los obstaculos.
        this.notas = new Nota[7];
        for(int i = 0; i < this.notas.length; i++)
        {
            this.notas[i] = new Nota(this);
            this.notas[i].setLocation((i + 1) * 150, this.notas[i].getY());
            this.add(this.notas[i]);
        }
        
        //Creación e implementación del fondo
        this.fondo = new Fondo();
        this.add(this.fondo);
    }
    
    /**
     * Método que se encarga de iniciar las animaciones de los obstaculos.
     */
    private void iniciarObstaculos()
    {
        this.controlNotas = Executors.newScheduledThreadPool(7);
        
        for(Nota nota : this.notas)
            controlNotas.scheduleAtFixedRate(nota, 0, 1, TimeUnit.SECONDS);
        
        this.controlChuy = Executors.newScheduledThreadPool(1);
        this.controlChuy.scheduleAtFixedRate(chuy, 0, 1, TimeUnit.SECONDS);
    }
    
    
    //Esto es una mala practica, pero vamos
    public Dimension getDimensionPersonaje()
    {
       return this.personaje.getSize();
    }
    
    public Point getLocalizacion()
    {
        return this.personaje.getLocation();
    }
    
    //Otra mala practica
    public void aturdirPersonaje()
    {
        this.personaje.aturdirPersoanje();
    }
    
    
    
    public synchronized void resetearPuntuacion()
    {
        this.puntuacion = 0;
        this.lblPuntuacion.setText(this.puntuacion+"/100");
    }
    
    public synchronized void incrementarPuntuacion()
    {
        this.puntuacion++;
        this.lblPuntuacion.setText(this.puntuacion+"/100");
    }
 

}

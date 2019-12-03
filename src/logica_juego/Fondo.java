package logica_juego;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JLabel;

/**
 * Clase destiana para ser el fondo del juego.
 * @author rodol
 */
public class Fondo extends JLabel
{
    public Fondo()
    {
        super();
        initComponets();
    }
    
    private void initComponets()
    {
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.setLayout(null);
    }

    /**
     * Método sobre-escrito Paint.
     * @param g 
     */
    @Override
    public void paint(Graphics g)
    {
        super.paint(g); 
        
        Graphics2D graphics = (Graphics2D)g;
        
        graphics.setStroke(new BasicStroke(5));
        
        //Se dibuja el cielo
        graphics.setColor(new Color(54, 139, 229));
        graphics.fillRect(0, 0, 1280, 450);
        
        
        //Se dibuja el suelo
        graphics.setColor(new Color(125, 125, 125));
        graphics.fillRect(0, 450, 1280, 400);
        
        
        //Se dibuja un edificio
        graphics.setColor(new Color(63, 150, 22));
        graphics.fillRect(150, 150, 350, 300);
        graphics.setColor(new Color(220, 220, 220));
        //Cuadro blanco superior
        graphics.fillRect(165, 315, 320, 130);
        //Cuadro blanco inferior
        graphics.fillRect(165, 175, 320, 130);
        graphics.setColor(Color.BLACK);
        //Numero edificio
        graphics.setFont(new Font("Tahoma", Font.BOLD, 24));
        graphics.drawString("200", 430, 210);
        //Tejado
        graphics.setColor(new Color(84, 21, 2));
        graphics.fillRect(120, 140, 410, 10);
        
        
        //Se dibuja un edificio
        graphics.setColor(new Color(63, 150, 22));
        graphics.fillRect(1200, 150, 350, 300);
        graphics.setColor(new Color(220, 220, 220));
        //Cuadro blanco superior
        graphics.fillRect(1215, 315, 320, 130);
        //Cuadro blanco inferior
        graphics.fillRect(1215, 175, 320, 130);
        graphics.setColor(Color.BLACK);
        //Tejado
        graphics.setColor(new Color(84, 21, 2));
        graphics.fillRect(1190, 140, 410, 10);
        
        
        //Creación de los árboles
        graphics.setColor(new Color(55, 55, 43));
        graphics.fillRect(600, 300, 30, 150);
        graphics.fillRect(800, 300, 30, 150);
        graphics.fillRect(990, 300, 30, 150);
        graphics.fillRect(1150, 300, 60, 150);
        
        //Se dibuajan las "hojas"
        graphics.setColor(new Color(5, 75, 29));
        graphics.fillOval(545, 120, 140, 210);
        graphics.fillOval(750, 120, 130, 250);
        graphics.fillOval(930, 110, 150, 270);
        graphics.fillOval(1100, 100, 165, 280);
        
        //Se dibuja el cerco
        graphics.setStroke(new BasicStroke(3));
        graphics.setColor(new Color(21, 73, 69));
        //Creación de la parte superior del cerco
        graphics.drawLine(150, 400, 1280, 400);
        //Creación de la decoración del cerco
        for(int i = 0; i < 64; i++)
            graphics.drawArc(150 + (i * 20), 400, 20, 100, 0, 180);
        //Creación de la parte inferior del cerco
        graphics.drawLine(150, 450, 1280, 450);
        
        
        //Se dibuja la banca
        graphics.setColor(new Color(30, 60, 30));
        graphics.setStroke(new BasicStroke(6));
        graphics.drawRect(900, 400, 150, 50);
        graphics.drawRect(930, 380, 80, 20);
        
        
        
        //Se dibujan las decoraciones en el suelo (grietas)
        graphics.setColor(new Color(70, 70, 70));
        graphics.setStroke(new BasicStroke(2));
        graphics.drawLine(500, 720, 600, 450);
        graphics.drawLine(480, 720, 580, 450);
        graphics.drawLine(100, 720, 200, 450);
        graphics.drawLine(80, 720, 180, 450);
        graphics.drawLine(900, 720, 1000, 450);
        graphics.drawLine(880, 720, 980, 450);
        
        graphics.drawLine(0, 500, 1280, 500);
        graphics.drawLine(0, 590, 1280, 590);
        graphics.drawLine(0, 680, 1280, 680);
        
        
    }
    
    
}

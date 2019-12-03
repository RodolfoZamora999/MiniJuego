import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Prueba_Loca 
{
    public static void main(String[] args)
    {
        Frame frame = new Frame();
        frame.setVisible(true);
    }
}


class Frame extends JFrame
{
    public Frame()
    {
        super();
        initComponets();
    }
    
    private void initComponets()
    {
        this.setSize(1280,720);
        this.setLocation(200, 200);
        this.setTitle("Prueba");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
        JPanel panel = new JPanel();
        panel.setLocation(0, 0);
        panel.setSize(1280,720);
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        
        
        Objeto objeto = new Objeto();
        panel.add(objeto);
        
        Objeto objeto2 = new Objeto();
        objeto2.setLocation(objeto2.getX() + 300, objeto2.getY());
        panel.add(objeto2);
        
        
        var control = Executors.newScheduledThreadPool(2);
        control.scheduleAtFixedRate(objeto, 0,  1, TimeUnit.SECONDS);
        control.scheduleAtFixedRate(objeto2, 0, 1, TimeUnit.SECONDS);
        
        
        this.add(panel); 
    }
}


class Objeto extends JLabel implements Runnable
{
    
    public Objeto()
    {
        super();
        initCompoents();
    }
    
    private void initCompoents()
    {
        this.setSize(60, 60);
        this.setLocation(300, -80);
        this.setOpaque(true);
        this.setBackground(Color.BLACK);
    }
    
    @Override
    public void run() 
    {
        int x = getX();
        int y = getY();
        
        while(true)
        {
            if(Objeto.this.getLocation().y > 700)
                break;
            
            Objeto.this.setLocation(getX(), getY() + 1);
            
            
            Objeto.this.setBackground(new Color(0, 0, 0));
           
             try
             {
                 TimeUnit.MILLISECONDS.sleep(5);
             }
             catch (InterruptedException ex)
             {
                 Logger.getLogger(Objeto.class.getName()).log(Level.SEVERE, null, ex);
             }      
        }
        
        System.out.println("Ha finalizado....");
   
        this.setLocation(x, y);
    }   
}

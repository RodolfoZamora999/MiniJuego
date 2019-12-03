
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rodol
 */
public class Prueba_Coliciones extends JFrame
{
    
    public Prueba_Coliciones()
    {
        this.initComponents();
    }
    
    private void initComponents()
    {
        this.setSize(720, 1280);
        this.setLocation(100, 200);
        this.setLayout(null);
        
        
        JPanel panel = new JPanel();
        
        
    }
    
    
    public static void main(String[] args)
    {
        Prueba_Coliciones prueba = new Prueba_Coliciones();
        prueba.setVisible(true);
    }
}



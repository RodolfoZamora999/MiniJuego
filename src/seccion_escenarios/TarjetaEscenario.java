package seccion_escenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

/**
 * Clase destinada para la creación de las tarjetas de los escenarios.
 * @author rodol
 */
public class TarjetaEscenario extends JLabel
{
    private final String nombre;
    
    public TarjetaEscenario(String nombre)
    {
        super();
        this.nombre = nombre;
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes fundamentales de la clase.
     */
    private void initComponents()
    {
        //Propiedades de la tarjeta
        this.setSize(900, 550);
        this.setLocation(0, 0);
        this.setBackground(new Color(28, 51, 105));
        this.setBorder(BorderFactory.createTitledBorder(null, nombre, TitledBorder.CENTER,
                TitledBorder.TOP, new Font("Tahoma", Font.BOLD, 24), Color.WHITE));
        this.setForeground(Color.BLACK);
        this.setFont(new Font("Tahoma", Font.PLAIN, 18));
        this.setVerticalAlignment(JLabel.TOP);
        this.setForeground(Color.WHITE);
        this.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                super.mouseEntered(e); 
                TarjetaEscenario.this.setOpaque(true);
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                super.mouseExited(e);
                TarjetaEscenario.this.setOpaque(false);
                repaint();
                revalidate();
            }
             
        });
    }
    
    public String getNombre()
    {
        return this.nombre;
    }
}

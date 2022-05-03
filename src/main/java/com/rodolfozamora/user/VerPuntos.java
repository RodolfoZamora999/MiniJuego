package com.rodolfozamora.user;

import com.rodolfozamora.database.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import com.rodolfozamora.minigame.PanelPrincipal;

/**
 *Clase destiana para ver la puntuació del juego.
 * @author Rodolfo Zamora.
 */
public class VerPuntos extends JPanel
{
    private final JFrame context;
    private JPanel contenedor;
    private JLabel lblCerrar, lblPuntuacion;
    private JTable tabla;
    private Conexion conexion;
    private JButton btnBorrar;
    
    public VerPuntos(JFrame context)
    {
        super();
        this.context = context;
        initComponets();
    }
    
    /**
     * Método para iniciar con los componentes necesarios para la clase.
     */
    private void initComponets()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 200));
        
        //Inicio de la conexión con la BBDD
        this.conexion = new Conexion(context);
        this.conexion.iniciarConexion();
        
        //Creación de la salida
        this.lblCerrar = new JLabel();
        this.lblCerrar.setSize(60, 60);
        this.lblCerrar.setLocation(this.getWidth() - 90, 10);
        this.lblCerrar.setText("x");
        this.lblCerrar.setFont(new Font("Calibri", Font.BOLD, 40));
        this.lblCerrar.setForeground(new Color(255, 255, 255, 200));
        this.lblCerrar.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                if(conexion != null)
                    conexion.cerrarConexion();
                
                context.remove(VerPuntos.this);
                ((PanelPrincipal)context.getContentPane().getComponent(0)).enableComponents();
                context.revalidate();
                context.repaint();  
            }
        });
        
        //Creación del contenedor
        this.contenedor = new JPanel();
        this.contenedor.setSize(500, 600);
        this.contenedor.setPreferredSize(new Dimension(400, 700));
        this.contenedor.setLocation((this.getWidth() - this.contenedor.getWidth()) / 2, 
                (this.getHeight() - this.contenedor.getHeight()) / 2);
        this.contenedor.setBackground(Color.WHITE);
        this.contenedor.setLayout(null);
        
        //Crear el titulo
        this.lblPuntuacion = new JLabel();
        this.lblPuntuacion.setSize(200, 40);
        this.lblPuntuacion.setLocation((this.contenedor.getWidth() - this.lblPuntuacion.getWidth()) / 2, 30);
        this.lblPuntuacion.setText("Puntuaciones");
        this.lblPuntuacion.setForeground(new Color(27, 103, 193));
        this.lblPuntuacion.setFont(new Font("Tahoma", Font.BOLD, 26));
        this.contenedor.add(this.lblPuntuacion);
        
        //Prototipo-----------------------------------------
        String consulta = "SELECT * FROM DATOS_JUGADOR;";
        var result = this.conexion.consularMySQL(consulta);
        
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<Integer> record = new ArrayList<>();
        ArrayList<Integer> tiempo = new ArrayList<>();
        ArrayList<String> usuario = new ArrayList<>();
        
        try 
        {
            while(result.next())
            {
                id.add(result.getInt(1));
                record.add(result.getInt(2));
                tiempo.add(result.getInt(3));
                usuario.add(result.getString(4));
            }
        } 
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(context, "Error con la conexión...");
        }
        //--------------------------------------------------
        
        String[] columnNames = {"ID", "Puntuación", "Tiempo", "Jugador"};
        String[][] rowData = new String[id.size()][4];
        
        for(int i = 0; i < id.size(); i++)
        {
            rowData[i][0] = id.get(i).toString();
            rowData[i][1] = record.get(i).toString()+"/100";
            rowData[i][2] = tiempo.get(i).toString()+" min.";
            rowData[i][3] = usuario.get(i);
        }
        
        //Crear tabla
        this.tabla = new JTable();
        this.tabla.setSize(450, 400);
        this.tabla.setLocation(25, 100);
        this.tabla.setModel(new DefaultTableModel(rowData, columnNames));
        this.tabla.setFont(new Font("Tahoma", Font.BOLD, 12));
        this.contenedor.add(tabla);
        
        this.btnBorrar = new JButton();
        this.btnBorrar.setSize(110, 30);
        this.btnBorrar.setLocation(this.contenedor.getWidth() - 120, 565);
        this.btnBorrar.setBackground(new Color(117, 27, 193));
        this.btnBorrar.setText("Limpiar");
        this.btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 18));
        this.btnBorrar.setForeground(Color.WHITE);
        this.btnBorrar.setBorder(null);
        this.btnBorrar.addActionListener((ActionEvent e)->
        {
            String comando = "DELETE FROM DATOS_JUGADOR;";
            
            if(this.conexion.eliminarMySQL(comando) > 0)
            {
                JOptionPane.showMessageDialog(context, "¡Eliminación exitosa!", "Exito", JOptionPane.INFORMATION_MESSAGE);
               
                for(int i = tabla.getRowCount() - 1; i >= 0; i--)
                    ((DefaultTableModel)tabla.getModel()).removeRow(i); 
            }        
               
        });
        this.contenedor.add(this.btnBorrar);
        
        this.add(this.lblCerrar);
        this.add(this.contenedor);
    }
}

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
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import com.rodolfozamora.gamelogic.PanelArena;
import com.rodolfozamora.minigame.PanelPrincipal;

/**
 *Clase destinada para almacenar la puntuación.
 * @author Rodolfo Zamora.
 */
public class GuardarPuntuacion extends JPanel
{
    private final JFrame context;
    private JLabel lblFin, lblPuntuación;
    private JPanel contenedor;
    private JButton btnSalir, btnGuardar, btnRepetir;
    private final int puntuacion, tiempo;
    private Conexion conexion;
    
    public GuardarPuntuacion(JFrame context, int puntuacion, int tiempo)
    {
        super();
        this.context = context;
        this.puntuacion = puntuacion;
        this.tiempo = tiempo;
        initComponets();
        
    }
    
    /**
     * Método que se encarga de iniciar 
     */
    private void initComponets()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBackground(new Color(30, 30, 30));
        
        //Creación del contenedor
        this.contenedor = new JPanel();
        this.contenedor.setSize(400, 600);
        this.contenedor.setPreferredSize(new Dimension(400, 600));
        this.contenedor.setLocation((this.getWidth() - this.contenedor.getWidth()) / 2,
                (this.getHeight() - this.contenedor.getHeight()) / 2);
        this.contenedor.setBackground(Color.WHITE);
        this.contenedor.setLayout(null);
        
        //Creación del label de puntuación
        this.lblPuntuación = new JLabel();
        this.lblPuntuación.setSize(320, 60);
        this.lblPuntuación.setLocation((this.contenedor.getWidth() - this.lblPuntuación.getWidth()) / 2, 30);
        this.lblPuntuación.setText("Tu puntuación: "+puntuacion+"/100");
        this.lblPuntuación.setForeground(new Color(27, 103, 193));
        this.lblPuntuación.setFont(new Font("Tahoma", Font.BOLD, 24));
        this.contenedor.add(this.lblPuntuación);
        
        //Creación del label de puntuación
        this.lblFin = new JLabel();
        this.lblFin.setSize(200, 40);
        this.lblFin.setLocation((this.contenedor.getWidth() - this.lblFin.getWidth()) / 2, 150);
        this.lblFin.setText("GAME OVER");
        this.lblFin.setForeground(Color.RED);
        this.lblFin.setFont(new Font("Tahoma", Font.BOLD, 28));
        this.contenedor.add(this.lblFin);
        
        //Creación del botoón de guardar
        this.btnGuardar = new JButton();
        this.btnGuardar.setSize(170, 50);
        this.btnGuardar.setLocation((this.contenedor.getWidth() - this.btnGuardar.getWidth()) / 2, 400);
        this.btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 22));
        this.btnGuardar.setForeground(Color.WHITE);
        this.btnGuardar.setText("Guardar");
        this.btnGuardar.setBackground(new Color(117, 27, 193));
        this.btnGuardar.addActionListener((ActionEvent e)->
        {
            try
            {
                this.guardarDatos();
            } 
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(context, "Fallo conexión...", "Aviso", JOptionPane.ERROR);
                System.err.println(ex);
            }
        });
        this.contenedor.add(this.btnGuardar);
        
         //Creación del botoón de guardar
        this.btnRepetir = new JButton();
        this.btnRepetir.setSize(170, 50);
        this.btnRepetir.setLocation((this.contenedor.getWidth() - this.btnRepetir.getWidth()) / 2, 460);
        this.btnRepetir.setFont(new Font("Tahoma", Font.BOLD, 22));
        this.btnRepetir.setForeground(Color.WHITE);
        this.btnRepetir.setText("Reintentar");
        this.btnRepetir.setBackground(new Color(117, 27, 193));
        this.btnRepetir.addActionListener((ActionEvent e)->
        {
            this.cerrarBBDD();
            
            PanelArena panel = new PanelArena(context);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint();
        });
        this.contenedor.add(this.btnRepetir);
        
        //Creación del botoón de guardar
        this.btnSalir = new JButton();
        this.btnSalir.setSize(170, 50);
        this.btnSalir.setLocation((this.contenedor.getWidth() - this.btnSalir.getWidth()) / 2, 520);
        this.btnSalir.setFont(new Font("Tahoma", Font.BOLD, 22));
        this.btnSalir.setForeground(Color.WHITE);
        this.btnSalir.setText("Salir");
        this.btnSalir.setBackground(new Color(117, 27, 193));
        this.btnSalir.addActionListener((ActionEvent e)->
        {
            this.cerrarBBDD();
            
            PanelPrincipal panel = new PanelPrincipal(context);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint();
        });
        this.contenedor.add(this.btnSalir);
        
        this.add(this.contenedor);
    }
    
    /**
     * Método privado que se encarga de crear la interfaz para guardar la puntuación.
     */
    private void guardarDatos() throws SQLException
    {
        if(this.contenedor == null)
            return;
        
        //Se limpia el contenedor
        this.contenedor.removeAll();
        
        this.context.revalidate();
        this.context.repaint();
        
        //Se inicia la conexión con la BBDD
        this.conexion = new Conexion(context);
        this.conexion.iniciarConexion();
        
        //Se crea una lista para almacenar los usuarios
        ArrayList<String> listaUsuarios = new ArrayList<>();
        
        //Se extraen los datos
        String comando = "SELECT * FROM REGISTRO";
        var rs = this.conexion.consularMySQL(comando);
        
        //Se almacenan los valores
        while(rs.next())
             listaUsuarios.add(rs.getString(1));
        
        //Creación de las cajas de texto
        JTextField txtPuntuacion = new JTextField();
        txtPuntuacion.setSize(300, 60);
        txtPuntuacion.setLocation((this.contenedor.getWidth() - txtPuntuacion.getWidth()) / 2, 100);
        txtPuntuacion.setText(this.puntuacion+"");
        txtPuntuacion.setHorizontalAlignment(JTextField.CENTER);
        txtPuntuacion.setFont(new Font("Calibri", Font.PLAIN, 20));
        txtPuntuacion.setForeground(Color.BLACK);
        txtPuntuacion.setBorder(this.crearBorde("Tú puntuación", Color.BLACK));
        txtPuntuacion.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtPuntuacion.setBorder(crearBorde("Tú puntuación", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtPuntuacion.setBorder(crearBorde("Tú puntuación", Color.BLACK));
            }   
        });
        txtPuntuacion.setEnabled(false);
        this.contenedor.add(txtPuntuacion);
        
        JTextField txtTiempo = new JTextField();
        txtTiempo.setSize(300, 60);
        txtTiempo.setLocation((this.contenedor.getWidth() - txtTiempo.getWidth()) / 2, 170);
        txtTiempo.setText(tiempo+"");
        txtTiempo.setHorizontalAlignment(JTextField.CENTER);
        txtTiempo.setFont(new Font("Calibri", Font.PLAIN, 20));
        txtTiempo.setForeground(Color.BLACK);
        txtTiempo.setBorder(this.crearBorde("Tiempo", Color.BLACK));
        txtTiempo.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtTiempo.setBorder(crearBorde("Tiempo", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtTiempo.setBorder(crearBorde("Tiempo", Color.BLACK));
            }   
        });
        txtTiempo.setEnabled(false);
        this.contenedor.add(txtTiempo);
        
        JComboBox<String> comboUsuario = new JComboBox<>();
        comboUsuario.setSize(300, 60);
        comboUsuario.setLocation((this.contenedor.getWidth() - comboUsuario.getWidth()) / 2, 240);
        //comboUsuario.setBackground(new Color(200, 100, 255));
        comboUsuario.setFont(new Font("Calibri", Font.PLAIN, 20));
        comboUsuario.setForeground(Color.BLACK);
        //Se agregan los usuarios
        if(!listaUsuarios.isEmpty())
            listaUsuarios.forEach((usuario) -> {
                comboUsuario.addItem(usuario);});
        else
            comboUsuario.addItem("Registré un usuario");
        this.contenedor.add(comboUsuario);
        
        //Creación del botón de guardar
        JButton btnGuardarP = new JButton();
        btnGuardarP.setSize(170, 50);
        btnGuardarP.setLocation(20, 450);
        btnGuardarP.setText("Guardar");
        btnGuardarP.setForeground(Color.WHITE);
        btnGuardarP.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnGuardarP.setBackground(new Color(117, 27, 193));
        btnGuardarP.addActionListener((ActionEvent e) ->
        {
            String comandoInsertar = "INSERT INTO DATOS_JUGADOR(RECORD, TIEMPO, JUGADOR) VALUES "
                    + "("+txtPuntuacion.getText()+","
                    + " "+txtTiempo.getText()+","
                    + " '"+comboUsuario.getSelectedItem().toString()+"');";
           
            if(!this.conexion.insertarMySQL(comandoInsertar))
                JOptionPane.showMessageDialog(context, "¡Registro completado!", "Exito", JOptionPane.INFORMATION_MESSAGE);
            else
               JOptionPane.showMessageDialog(context, "Registro no completado...", "Fracaso", JOptionPane.ERROR_MESSAGE);
            
            
            cerrarBBDD();
            
            GuardarPuntuacion panel = new GuardarPuntuacion(context, puntuacion, tiempo);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint(); 
            
        });
        this.contenedor.add(btnGuardarP);
        
        //Creación del botón de cancelar
        JButton btnCancelarP = new JButton();
        btnCancelarP.setSize(170, 50);
        btnCancelarP.setLocation(this.contenedor.getWidth() - (btnCancelarP.getWidth() + 20), 450);
        btnCancelarP.setText("Cancelar");
        btnCancelarP.setForeground(Color.WHITE);
        btnCancelarP.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnCancelarP.setBackground(new Color(117, 27, 193));
        btnCancelarP.addActionListener((ActionEvent e) ->
        {
            cerrarBBDD();
            
            GuardarPuntuacion panel = new GuardarPuntuacion(context, puntuacion, tiempo);
            context.remove(this);
            context.add(panel);
            context.revalidate();
            context.repaint(); 
        });
        this.contenedor.add(btnCancelarP);   
    }
    
    /**
     * Método privado que ayuda a la creación de bordes.
     * @return 
     */
    private Border crearBorde(String titulo, Color color)
    {
        Border border = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color,
                2, true), titulo, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION,
                new Font("Calibri", Font.BOLD, 16), color);
        
        return border;
    }
    
    /**
     * Método privado para cerrar la conexión con la base de datos.
     */
    private void cerrarBBDD()
    {
        if(this.conexion != null)
            this.conexion.cerrarConexion();
    }
}

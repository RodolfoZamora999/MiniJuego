package com.rodolfozamora.user;

import com.rodolfozamora.database.Conexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import com.rodolfozamora.minigame.PanelPrincipal;

/**
 *Clase destinada para gestionar el registro de los usuarios.
 * @author Rodolfo Zamora.
 */
public class Registro extends JPanel
{
    private final JFrame context;
    private JScrollPane scroll;
    private JPanel contenedor; 
    private JLabel lblCerrar, lblCrear, lblNota;
    private JTextField txtNombreUsuario, txtNombre, txtApell_P, txtApell_M, txtCorreo, txtEdad, txtPais;
    private JPasswordField txtContrasena;
    private JButton btnRegistrar, btnCancelar;
    private Conexion conexion;
    
    public Registro(JFrame context)
    {
        super();
        this.context = context;
        initComponents();
    }
    
    /**
     * Método que inicializa los componentes escenciales de la clase.
     */
    private void initComponents()
    {
        //Propiedades del panel
        this.setSize(1280, 720);
        this.setLocation(0, 0);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 200));
        
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
                
                context.remove(Registro.this);
                ((PanelPrincipal)context.getContentPane().getComponent(0)).enableComponents();
                context.revalidate();
                context.repaint();  
            }
        });
        
        //Creación del contenedor
        this.contenedor = new JPanel();
        this.contenedor.setSize(400, 700);
        this.contenedor.setPreferredSize(new Dimension(400, 700));
        this.contenedor.setLocation(0, 0);
        this.contenedor.setBackground(Color.WHITE);
        this.contenedor.setLayout(null);
        
        this.scroll = new JScrollPane(this.contenedor);
        this.scroll.setSize(400, 580);
        this.scroll.setLocation((this.getWidth() - this.scroll.getWidth()) / 2, 
                (this.getHeight() - this.scroll.getHeight()) / 2);
        this.scroll.getVerticalScrollBar().setUnitIncrement(15);
        this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.scroll.setBorder(null);
        
        //Crear el titulo
        this.lblCrear = new JLabel();
        this.lblCrear.setSize(200, 40);
        this.lblCrear.setLocation((this.contenedor.getWidth() - this.lblCrear.getWidth()) / 2, 30);
        this.lblCrear.setText("Crear cuenta");
        this.lblCrear.setForeground(new Color(27, 103, 193));
        this.lblCrear.setFont(new Font("Calibri", Font.BOLD, 36));
        this.contenedor.add(this.lblCrear);
        
        this.txtNombreUsuario = new JTextField();
        this.txtNombreUsuario.setSize(300, 60);
        this.txtNombreUsuario.setLocation((this.contenedor.getWidth() - this.txtNombreUsuario.getWidth()) / 2, 90);
        this.txtNombreUsuario.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtNombreUsuario.setForeground(Color.BLACK);
        this.txtNombreUsuario.setBorder(crearBorde("Nombre Usuario", Color.BLACK));
        this.txtNombreUsuario.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtNombreUsuario.setBorder(crearBorde("Nombre Usuario", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtNombreUsuario.setBorder(crearBorde("Nombre Usuario", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtNombreUsuario);
        
        this.txtNombre = new JTextField();
        this.txtNombre.setSize(300, 60);
        this.txtNombre.setLocation((this.contenedor.getWidth() - this.txtNombre.getWidth()) / 2, 160);
        this.txtNombre.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtNombre.setForeground(Color.BLACK);
        this.txtNombre.setBorder(crearBorde("Nombre", Color.BLACK));
        this.txtNombre.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtNombre.setBorder(crearBorde("Nombre", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtNombre.setBorder(crearBorde("Nombre", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtNombre);
        
        this.txtApell_P = new JTextField();
        this.txtApell_P.setSize(300, 60);
        this.txtApell_P.setLocation((this.contenedor.getWidth() - this.txtApell_P.getWidth()) / 2, 230);
        this.txtApell_P.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtApell_P.setForeground(Color.BLACK);
        this.txtApell_P.setBorder(crearBorde("Apellido Paterno", Color.BLACK));
        this.txtApell_P.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtApell_P.setBorder(crearBorde("Apellido Paterno", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtApell_P.setBorder(crearBorde("Apellido Paterno", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtApell_P);
        
        this.txtApell_M = new JTextField();
        this.txtApell_M.setSize(300, 60);
        this.txtApell_M.setLocation((this.contenedor.getWidth() - this.txtApell_P.getWidth()) / 2, 300);
        this.txtApell_M.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtApell_M.setForeground(Color.BLACK);
        this.txtApell_M.setBorder(crearBorde("Apellido Materno", Color.BLACK));
        this.txtApell_M.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtApell_M.setBorder(crearBorde("Apellido Materno", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtApell_M.setBorder(crearBorde("Apellido Materno", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtApell_M);
        
        this.txtCorreo = new JTextField();
        this.txtCorreo.setSize(300, 60);
        this.txtCorreo.setLocation((this.contenedor.getWidth() - this.txtApell_P.getWidth()) / 2, 370);
        this.txtCorreo.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtCorreo.setForeground(Color.BLACK);
        this.txtCorreo.setBorder(crearBorde("Correo", Color.BLACK));
        this.txtCorreo.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtCorreo.setBorder(crearBorde("Correo", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtCorreo.setBorder(crearBorde("Correo", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtCorreo);
        
        this.txtEdad = new JTextField();
        this.txtEdad.setSize(100, 60);
        this.txtEdad.setLocation((this.contenedor.getWidth() - this.txtApell_P.getWidth()) / 2, 440);
        this.txtEdad.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtEdad.setForeground(Color.BLACK);
        this.txtEdad.setBorder(crearBorde("Edad", Color.BLACK));
        this.txtEdad.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtEdad.setBorder(crearBorde("Edad", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtEdad.setBorder(crearBorde("Edad", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtEdad);
        
        this.txtPais = new JTextField();
        this.txtPais.setSize(150, 60);
        this.txtPais.setLocation((this.contenedor.getWidth() - this.txtApell_P.getWidth()) / 2 + 120, 440);
        this.txtPais.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtPais.setForeground(Color.BLACK);
        this.txtPais.setBorder(crearBorde("País", Color.BLACK));
        this.txtPais.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtPais.setBorder(crearBorde("País", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtPais.setBorder(crearBorde("País", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtPais);
        
        this.txtContrasena = new JPasswordField();
        this.txtContrasena.setSize(300, 60);
        this.txtContrasena.setLocation((this.contenedor.getWidth() - this.txtContrasena.getWidth()) / 2, 510);
        this.txtContrasena.setFont(new Font("Calibri", Font.PLAIN, 20));
        this.txtContrasena.setForeground(Color.BLACK);
        this.txtContrasena.setBorder(crearBorde("Contraseña", Color.BLACK));
        this.txtContrasena.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e) 
            {
                txtContrasena.setBorder(crearBorde("Contraseña", new Color(27, 103, 193)));
            }

            @Override
            public void mouseExited(MouseEvent e) 
            {
                txtContrasena.setBorder(crearBorde("Contraseña", Color.BLACK));
            }   
        });
        this.contenedor.add(this.txtContrasena);
        
        this.btnRegistrar = new JButton();
        this.btnRegistrar.setSize(140, 50);
        this.btnRegistrar.setLocation((this.contenedor.getWidth() - this.txtNombre.getWidth()) / 2, 580);
        this.btnRegistrar.setText("Registrar");
        this.btnRegistrar.setFont(new Font("Calibri", Font.BOLD, 24));
        this.btnRegistrar.setBackground(new Color(117, 27, 193));
        this.btnRegistrar.setForeground(Color.WHITE);
        this.btnRegistrar.addActionListener((ActionEvent e)->
        {
            //Iniciar objeto
            this.conexion = new Conexion(this.context);
            
            //Iniciar la conexión
            this.conexion.iniciarConexion();
            
            //Creación del comando
            String comando = "INSERT INTO REGISTRO(NOMBRE_USUARIO, NOMBRE, APELLIDO_P, APELLIDO_M, CORREO, EDAD, PAIS, CONTRASENA)"
                    +" VALUES"
                    + "('"+this.txtNombreUsuario.getText()+"',"
                    + "' "+this.txtNombre.getText()+"',"
                    + "' "+this.txtApell_P.getText()+"', "
                    + "' "+this.txtApell_M.getText()+"',"
                    + "' "+this.txtCorreo.getText()+"',"
                    + " "+Integer.parseInt(this.txtEdad.getText())+", "
                    + "'"+this.txtPais.getText()+"', "
                    + "'"+this.txtContrasena.getText()+"');";
            
            //Inserción del comando
            if(!this.conexion.insertarMySQL(comando))
                JOptionPane.showMessageDialog(context, "¡Registro completado!", "Exito", JOptionPane.INFORMATION_MESSAGE);
            else
               JOptionPane.showMessageDialog(context, "Registro no completado...", "Fracaso", JOptionPane.ERROR_MESSAGE);
        });
        this.contenedor.add(this.btnRegistrar);
        
        this.btnCancelar = new JButton();
        this.btnCancelar.setSize(140, 50);
        this.btnCancelar.setLocation((this.contenedor.getWidth() - this.txtNombre.getWidth()) / 2 + 150, 580);
        this.btnCancelar.setText("Cancelar");
        this.btnCancelar.setFont(new Font("Calibri", Font.BOLD, 24));
        this.btnCancelar.setBackground(new Color(117, 27, 193));
        this.btnCancelar.setForeground(Color.WHITE);
        this.btnCancelar.addActionListener( (ActionEvent e)->
        {
            if(this.conexion != null)
                this.conexion.cerrarConexion();
            
            context.remove(Registro.this);
            ((PanelPrincipal)context.getContentPane().getComponent(0)).enableComponents();
            context.revalidate();
            context.repaint();  
        });
        this.contenedor.add(this.btnCancelar);
        
        this.lblNota = new JLabel();
        this.lblNota.setSize(300, 50);
        this.lblNota.setLocation((this.contenedor.getWidth() - this.txtNombre.getWidth()) / 2, 640);
        this.lblNota.setText("<html>Al presionar el botón de 'Registrar' usted está aceptando todos"
                + "  nuestros términos y condicione.s</html>");
        this.lblNota.setForeground(new Color(0, 0, 0));
        this.lblNota.setFont(new Font("Calibri", Font.BOLD, 13));
        this.contenedor.add(this.lblNota);
        
        this.add(this.lblCerrar);
        this.add(this.scroll);  
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
}

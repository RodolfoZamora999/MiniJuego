package Base_Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *Clase destiana para generar la conexión de la base de datos.
 * @author RodolfoZamora.
 */
public class Conexion
{
    private final JFrame context;
    private Connection conexion;
    private Statement statement;
    
    public Conexion(JFrame context)
    {
        this.context = context;
    }
    
    /**
     * Método que se encarga de iniciar la conexión con la base de datos.
     */
    public void iniciarConexion() 
    {
        try
        {
            //Crear la conexión

            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
                    + "minijuegodb?serverTimezone=UTC&useSSL=false",
                    "root", "rodolfozamora123456789");
            
            //Crear el objeto statement.
            this.statement = conexion.createStatement();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(context, "Fallo con la conexión...");
            System.err.println(ex);
        }
    }
    
    /**
     * Método que comprueba el estado de la conexión.
     * @return Verdadero en caso de estar conectado, falso en caso contrario.
     */
    public boolean estadoConexion()
    {
        return false;
    }
    
    /**
     * Método que permite consultar datos de la BBDD.
     * @param comando Instrucción SQL para consultar la BBDD.
     * @return ResulSet con los valores obtenidos;
     */
    public ResultSet consularMySQL(String comando)
    {   
        try 
        {
            if(statement != null)
                return statement.executeQuery(comando);
            
            return null;
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(context, "Fallo con la conexión...");
        }
        
        return null;
    }
    
    /**
     * Método que permite insertar datos a BBDD.
     * @param comando Intrucción SQL para insertar los datos.
     * @return True en caso de exito, false en caso contrario.
     */
    public boolean insertarMySQL(String comando)
    {
        try 
        {
            if(this.statement != null)
                return statement.execute(comando);
            
            return true;
        } 
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(context, "Fallo con la conexión...");
            
            System.err.println(ex);
        }
        
        return true;
    }
    
    /**
     * Método que permite la eliminación de datos en la BBDD.
     * @param comando Instrucción SQL para eliminar los datos.
     * @return Un valor del tipo entero.
     */
    public int eliminarMySQL(String comando)
    {
        try 
        {
            if(this.statement != null)
                return this.statement.executeUpdate(comando);
            
            return 0;
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(context, "Fallo con la conexión...");
        }
        
        return 0;
    }
    
    /**
     * Método que sirve para finalizar de ma nera segura la conexión BBDD.
     */
    public void cerrarConexion()
    {
        try
        {
            if(this.conexion != null)
                this.conexion.close();
            
            if(this.statement != null)
                this.statement.close();
        }
        catch (SQLException ex) 
        {
            JOptionPane.showMessageDialog(context, "Fallo con la conexión...");
        }
    }
}

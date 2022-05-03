package com.rodolfozamora.minigame;

import com.rodolfozamora.database.Conexion;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Clase de entrada para el programa.
 * @author rodol
 */
public class MainClass 
{
    public static void main(String[] args)
    {
        Contenedor contenedor = new Contenedor();
        contenedor.setVisible(true);
    }

}

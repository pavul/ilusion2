/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.util;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;
import javax.imageio.ImageIO;

/**
 * clase de utilidades
 * @author pavulzavala
 */
public class Util 
{
    
    /**
     * funcion que regresa un objeto imagen segun la ruta que del archivo
     * @param imgFile
     * @return 
     * @throws java.io.FileNotFoundException 
     */
    public  static Image getImage(String pathFile)throws FileNotFoundException, IOException
    {
//     BufferedImage bf= ImageIO.read(imgFile);    
     BufferedImage bf= ImageIO.read( java.lang.String.class.getClass().getResourceAsStream(pathFile));    
     return bf;
    }//
    
    /**
     * funcion que regresa un color aleatorio
     * @return 
     */
    public static Color getRandomColor()
    {
        Random r= new Random();
        Color myColor = new Color(r.nextFloat(),r.nextFloat(), r.nextFloat());
        return myColor;        
    }//
    
    
    /**
     * funcion random que regresa un numero entre el maximo y minino establecido,
     * se le pueden agregar numeros negativos
     * @param max
     * @param min
     * @return 
     */
    public static int generatRandomPositiveNegitiveValue(int max , int min) {
    //Random rand = new Random();
    int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
    return ii;
}
    
    /**
     * funcion que regresa el array de tiles, que acepta uno de los metodos
     * DRAWBGTILE
     * @param imageFile
     * @return 
     */
    public static Image[] getArrayTiles(URL imageFile )
    {
    
    return null;
    }//
    
   /**
    * metodo que regresa el archivo de propiedades para poder manipularlo
    * @param pathFile
    * @return 
    */
    public static Properties getPropertyFile(String pathFile)
    {
        Properties prop = new Properties();
        try {
//          prop.load(new FileInputStream(pathFile));
          InputStream in =  java.lang.String.class.getClass().getResourceAsStream(pathFile);
          prop.load(in);
          in.close();
          } catch (IOException e) 
        {
            System.out.println("No se pudo cargar el Archivo de Propiedaes: "+ pathFile);
            e.printStackTrace();
        }//
        
      return prop;
    }//
    
//    /**
//     * funcion que reproduce un efecto de sonido, estos tienen que estar en 
//     * formato .WAV
//     */
//    public static void playSound(final String fileName)
//    {
//     Sound.play(fileName);  //(fileName);
//    }//
    
    
    /**
     * funcion que genera un String con formato UUID, utilizado para
     * crear los ID de los sockets de los clientes en una conexion TCP
     * @return 
     */
    public static UUID generateUUID()
    {
        return UUID.randomUUID();
    }//
    
    
    
    /**
     * this function help to resolve proporsional ammounts,
     * util if you want to get hp ammount, with a fixed width 
     * healt bar
     * 
     * example 50 --> 100% hp
     *         25 --> ?
     * 100 x 25 = 2500 / 50 = 50%
     * 
     * @return 
     */
    public static int ruleOf3( 
            int proporcion, 
            int valueProportion ,
            int unknowProportion)
    {
        
       return (unknowProportion * valueProportion) / proporcion;
        
    }
    
}//class

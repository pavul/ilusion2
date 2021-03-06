/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
     //BufferedImage bf= ImageIO.read(imgFile);    
     BufferedImage bf= ImageIO.read( java.lang.String.class.getClass().getResourceAsStream(pathFile));    
     return bf;
    }//
    
    
    /**
     * this function return a TTF ( True Type Font )font,
     * NOTE: .ttf must be placed in resources folder, if ttf file is inside
     * a package in resource folder then should be added like:
     * /packageName/fontName.ttf
     * NOTE2: only TTF file format allowed
     * NOTE3: be careful to put the name correclty with capital letters
     * @param loadFrom class where the font must be loaded
     * @param pathFile
     * @param fontSize
     * @return
     * @throws IOException
     * @throws FontFormatException 
     */
    public  static Font  getFont( Class loadFrom,String pathFile, float fontSize )throws IOException, FontFormatException
    {
        InputStream is = loadFrom.getResourceAsStream( pathFile )  ;

        return Font.createFont( Font.TRUETYPE_FONT, is ).deriveFont( fontSize ) ;
        //Font a = Font.createFont( Font.TRUETYPE_FONT, is );

    }//
    
    /**
     * this method return the width in point of the specified string when
     * NOTE: this function have to be used inside render method
     * some font is used
     * @param g2
     * @param font
     * @param text
     * @return 
     */
    public static int getStringWidth( Graphics2D g2,Font font, String text )
    { 
      return g2.getFontMetrics(font).stringWidth(text);
    }
    
    
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
    public static int generatRandomPositiveNegitiveValue(int max , int min) 
    {
    //Random rand = new Random();
    int ii = -min + (int) (Math.random() * ((max - (-min)) + 1));
    return ii;
    }
    
    /**
     * funcion que regresa el array de imagenes, que acepta uno de los metodos
     * DRAWBGTILE
     * @param numFrames
     * @param w
     * @param h
     * @param imgRoute
     * @return 
     */
    public static Image[] getArrayFrames(int numFrames, int w, int h, String imgRoute )
    {
         BufferedImage bigImg = null;
        try 
        {
//            bigImg = ImageIO.read(this.getClass().getResource( imgRoute ) );
         bigImg = ImageIO.read( java.lang.String.class.getClass().getResourceAsStream( imgRoute )); 
        } 
        catch (IOException ioe) 
        { }

        Image[] im = new Image[numFrames];

        for (int i = 0; i < numFrames; i++) 
        {
            im[i] = bigImg.getSubimage(i * w, 0, w, h);
        }// for
    return im;
    }//
    
   /**
    * metodo que regresa el archivo de propiedades para poder manipularlo
    * @param pathFile
    * @return 
    */
    public static Properties getPropertyFile(String pathFile)
    {
        Properties prop = new Properties();
        try 
        {  
          InputStream in =  java.lang.String.class.getClass().getResourceAsStream(pathFile);
          prop.load(in);
          in.close();
        } 
        catch (IOException e) 
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
     * @param proporcion
     * @param valueProportion
     * @param unknowProportion
     * @return 
     */
    public static int ruleOf3( 
            int proporcion, 
            int valueProportion ,
            int unknowProportion)
    {
       return (unknowProportion * valueProportion) / proporcion;   
    }
    
    
//    /**
//     * this function get the count number of certain sprites
//     * that are inside of the view,
//     * those sprites have to be grouped with label property,
//     * this is to be used inside update method, 
//     * @param level
//     * @param spr
//     * @param label
//     * @return 
//     */
//    public static int getSpriteNumber(GameLevel level, List<Sprite> spritePool, String label)
//    {
//        int count = 0;
//           
//        for( Sprite spr: spritePool )
//        {
//            if( spr.getCenterX() >= 0 && 
//            spr.getCenterX() <= ( 0 + level.getViewWidth() ) &&  
//            spr.getCenterY() >= 0 &&
//            spr.getCenterY() <= ( 0 + level.getViewHeight()) && spr.getLabel().equals( label ) )
//            {
//            count ++; 
//            }//
//        
//        }//for
//        
//        
//        
//        return count;
//    }//
    
}//class

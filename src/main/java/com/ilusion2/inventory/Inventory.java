/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.inventory;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;



/**
 * clase que guarda una serie de items para mostrarlas en pantalla como
 * una especie de inventario
 * @author pavulzavala
 * 
 * 
 * this class save a list of items to show them on the screen as a kind
 * of inventory, in a grid, vertical list or horizontal list
 */
public class Inventory 
{
    
    
    public final static int LAYOUT_GRID = 1;
    public final static int LAYOUT_VERTICAL = 2;
    public final static int LAYOUT_HORIZONTAL = 3;
    
    
    /**
     * indicate if the inventory must be shown on screen
     */
    boolean visible; 
    
    /**
     * list of the items of this inventory
     */
    private List<Item> slots;
    
    /**
     * x position where the inventory will be shown
     */
    int x; //posicion x del inventario
    
    /**
     * y position where the inventory will be shown
     */
    int y; //posicion y del inventario
    
    /**
     * indicate what slot of the inventory is selected
     */
    int cursor; //indica que parte del inventario esta seleccionado
    
    /**
     * background image for each slot
     */
    BufferedImage bgSlot; //imagen de fondo del slot, los items van sobrepuestos de esta foto
    
    /**
     * slot padding
     */
    int padding;//el padding de cada slot, por default 20;
    
    
    /**
     * constructor donde se le pasan los items que va a contener y las
     * posiciones X e y donde se va a dibujar en pantalla
     * @param slots
     * @param x
     * @param y
     */
    public Inventory(List<Item> slots, int x, int y)
    {
        this.slots = slots;
        this.x = x;
        this.y = y;   
        this.padding = 20;
    }//
    
    
    /**
     * funcion que dibuja el inventario en forma de grid
     * @param g 
     * @param rows 
     * @param columns 
     * @param width 
     * @param height 
     */
    public void drawGrid(Graphics g, int rows, int columns, int width, int height)
    {
        
        x = 64;
        y = 64;
        
        Graphics2D g2 = (Graphics2D)g;
        
        if(columns <=0 || rows <= 0 || slots.isEmpty())
        {return;}
        
        int index=0;
        int totalSlots= slots.size()-1;
        
        //renglones
        for(int i=0;i < rows;i++)
                {
                        //columnas
                        for(int j=0;j < columns;j++)
                        {
                         if(slots.get(index).getIcon() != null)
                         {
                             //dibujar imagen
                                  g2.drawImage(slots.get(index).getIcon(), 
                                  x + ( width * j ), 
                                  y +( height * i ), 
                                  null);
                                  //cantidad que se tiene
                                  //etiqueta
                                  
                                  
                         }//if validacion si se dibuja
                             
                          if(index < totalSlots)
                          index++;
                        }//for cols
                }//for rows

    }//drawGrid
    
    
    /**
     * funcion que dibuja el inventario de manera de lista vertical
     * @param g 
     */
    public void drawLine(Graphics g, boolean drawLabel)
    {
    
        if(slots.isEmpty())return;
        
        Graphics2D g2 = (Graphics2D)g;
        
        int len = slots.size();
        
        for( int i = 0 ; i < len; i++ )
        {
            int width = x + slots.get(i).getIcon().getWidth() + padding;
            int heigth = y + (slots.get(i).getIcon().getHeight() * i) + padding;
        
            //primero se muestran los iconos
            if( slots.get(i).getIcon() != null )
            {
            g2.drawImage( slots.get(i).getIcon() , 
                    width ,
                    heigth , 
                    null );
            }   
            
            //para mostrar el texto
            if( drawLabel )
            {
//                int stringW = g.getFont().getM    slots.get( i ).getLabel() );
//                int stringH = g.getFontMetrics().  ( slots.get( i ).getLabel() );
                
            g2.drawString(  slots.get( i ).getLabel(), 
                            width + slots.get(i).getIcon().getWidth() ,
                            heigth );
            }
             
        }//for
        
    }//
    
}//class

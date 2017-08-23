/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.inventory;

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
    
    /**
     * those constants indicate the layout to show
     * the inventory, vertical | horizontal line or as grid
     */
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
    private int x; //posicion x del inventario
    
    /**
     * y position where the inventory will be shown
     */
    private int y; //posicion y del inventario
    
    /**
     * indicate what slot of the inventory is selected
     */
    private int cursor; //indica que parte del inventario esta seleccionado
    
    /**
     * @TODO add this to inventory to show this as background
     * background image for each slot
     */
    BufferedImage bgSlot; //imagen de fondo del slot, los items van sobrepuestos de esta foto
    
    /**
     * slot padding
     */
    int paddingX;//el padding a lo ancho
    int paddingY;//el padding a lo alto
    
    
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
        this.paddingX = 0;
        this.paddingY = 0;
    }//
    
    
    /**
     * funcion que dibuja el inventario en forma de grid
     * @param g2
     * @param rows 
     * @param columns 
     * @param width 
     * @param height 
     */
    public void drawGrid(Graphics2D g2, int rows, int columns, int width, int height)
    {
        
        if(columns <=0 || rows <= 0 || slots.isEmpty())
        {return;}
        
        int index = 0;
        int totalSlots = slots.size() -1;
        
        //rows
        for( int i = 0 ; i < rows ; i++ )
                {
                        //columns
                        for( int j = 0 ; j < columns ; j++ )
                        {
                         if( slots.get( index ).getIcon() != null )
                         {
                             //draw img
                                  g2.drawImage(slots.get(index).getIcon(), 
                                  x + ( width * j ) + paddingX, 
                                  y + ( height * i ) + paddingY, 
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
     * 
     * this function draw the inventory in vertical list,
     * if draw value is true then it will show the value 
     * of the item.
     * NOTE: the color of the text must be set before
     * call this method
     * @param g2
     * @param drawValue
     */
    public void drawLine(Graphics2D g2, boolean drawValue)
    {
    
        if(slots.isEmpty())return;
        
        int len = slots.size();
        
        for( int i = 0 ; i < len; i++ )
        {
            int width = x + slots.get(i).getIcon().getWidth() + paddingX;
            int heigth = y + (slots.get(i).getIcon().getHeight() * i) + paddingY;
        
            //primero se muestran los iconos
            if( slots.get(i).getIcon() != null )
            {
            g2.drawImage( slots.get(i).getIcon() , 
                    width ,
                    heigth , 
                    null );
            }   
            
            //para mostrar el texto
            if( drawValue )
            {
//                int stringW = g.getFont().getM    slots.get( i ).getLabel() );
//                int stringH = g.getFontMetrics().  ( slots.get( i ).getLabel() );
                
//            g2.drawString(  slots.get( i ).getLabel(), 
            g2.drawString(  "x "+slots.get( i ).getCurrentQty() , 
                            width + slots.get(i).getIcon().getWidth() ,
                            heigth + slots.get(i).getIcon().getHeight() );
            }
             
        }//for
        
    }//

    
    
    
    
    /**
     * getters & setters
     */
    
    
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Item> getSlots() {
        return slots;
    }

    public void setSlots(List<Item> slots) {
        this.slots = slots;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public BufferedImage getBgSlot() {
        return bgSlot;
    }

    public void setBgSlot(BufferedImage bgSlot) {
        this.bgSlot = bgSlot;
    }

    public int getPaddingX() {
        return paddingX;
    }

    public void setPaddingX(int paddingX) {
        this.paddingX = paddingX;
    }

    public int getPaddingY() {
        return paddingY;
    }

    public void setPaddingY(int paddingY) {
        this.paddingY = paddingY;
    }

    
    
    
     /**
     * /getters & setters
     */
    
}//class

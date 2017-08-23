/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.inventory;

import java.awt.image.BufferedImage;

/**
 * esta es una clase generica que puede indicar un item, un skill, 
 * alguna magia o cualquier otra forma que se puede mostrar en 
 * pantalla
 * 
 * 
 * this is a generic class who can be item, skill, spell, etc, 
 * to show that data in an inventory
 * 
 * NOTE: if you want to crate a grid of skills, then your items
 * need to be pictures and/or data of skills
 * 
 * @author pavulzavala
 */
public class Item 
{
    
    /**
     * item id, usefull when this item is clicked
     */
    private int itemId; //item id usefull when we click on it
    
    /**
     * quantity of this item
     */
    private int currentQty; //current quantity of this item, skill, etc
    
    /**
     * minimum quantity that this item can accept
     */
    private int minQty; //minimal quantity who is required for this item
    
    /**
     * maximum quantity that this item can accept
     */
    private int maxQty; //maximal quantity who is required for this item
    
    /**
     * image of this icon
     */
    private BufferedImage icon;//icon of the item
    
    /**
     * label/text of this icon to show in screen
     */
    private String label; //label, or text of this item
    
    /**
     * description of this item
     */
    private String desc; //description of this item, skill, spell, etc
    
    /**
     * if this item is for skill, spell, etc, it can be some 
     * string containing the attribute for that.
     */
    private String attr; //attribute of this item, skill, spell, etc
    
    /**
     * constructor 1, este deja libre al usuario de establecer los valores con los
     * setters
     * 
     * this contrusctor allow the programmer stablish all the values
     * using setters
     */
    public Item(){}//
    
    
    /**
     * constructor 2, crea el item y le establece sus valores iniciales
     * 
     * this constructor creates a new item and stablish its default 
     * values
     * 
     * @param itemId
     * @param currentQty
     * @param minQty
     * @param maxQty
     * @param icon
     * @param label 
     * @param desc 
     * @param attr 
     */
    public Item(
            int itemId ,
            int currentQty ,
            int minQty ,
            int maxQty ,
            BufferedImage icon ,
            String label, 
            String desc ,
            String attr)
    {
    this.itemId = itemId ;
    this.currentQty=currentQty;
    this.minQty=minQty;
    this.maxQty=maxQty;
    this.icon=icon;
    this.label=label;
    }//

    public int getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(int currentQty) {
        this.currentQty = currentQty;
    }

    public int getMinQty() {
        return minQty;
    }

    public void setMinQty(int minQty) {
        this.minQty = minQty;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public BufferedImage getIcon() {
        return icon;
    }

    public void setIcon(BufferedImage icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
    
    
    
    
}//

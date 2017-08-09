/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.gamemanager;

import java.awt.Graphics2D;

/**
 * this interface is used to draw 
 * custom loading screen when a new
 * level is loaded
 * @author nigthmare
 */
public interface LoadingRenderer 
{
   
    public void renderLoading( Graphics2D g );
    
}//

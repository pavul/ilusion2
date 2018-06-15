/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.level;

/**
 * this interface only provides a method
 * where all logic update is made for a level
 * @author pavul
 */
public interface Updatable 
{

    /**
     *this method update every thick of the game
     * @param delta
     */
    public void update( double delta );   
}//

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.audio;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * clase que reproduce un efecto de sonido en formato WAV
 * 
 * this class is used to play .wav files for sound effects
 * @author pavulzavala
 */
 public class Sound 
 {
     
     
     boolean once = false;
     
     InputStream is;
     AudioInputStream ais;
     Clip clip;
     
     //list of sounds
     Map< String, Clip > soundList;
     
     
     //constructor here...
     public Sound()
     {
         soundList = new HashMap<>();   
     }//
     
     
     
     /**
      * this song plays a sound in wav format, havent test midi
      * yet, NOTE: this function should be optimized
      * 
      * @param fileName 
      */
    public  synchronized void play(final String fileName) 
    {
        // Note: use .wav files             
//        new Thread(new Runnable() { 
//            @Override
//            public void run() 
//            {
                try 
                {
                    
                    InputStream is = getClass().getResourceAsStream( fileName );
                    
                    AudioInputStream ais = 
                        AudioSystem.getAudioInputStream( new BufferedInputStream( is ) );
                    
                    clip = AudioSystem.getClip( );
                    
                    clip.open( ais );
                
                    FloatControl gainControl = (FloatControl) clip.getControl( FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue( 0.1f );
                    
                    clip.start( );
                    
                }
                catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) 
                {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
//            }
//        }).start();
    }

    
    public void loadSound( String label, String fileName )
    {
       
         try 
         {    
         //InputStream
         is = getClass().getResourceAsStream( fileName );

         //AudioInputStream
         ais = AudioSystem.getAudioInputStream( new BufferedInputStream( is ) );
        
         clip = AudioSystem.getClip( );
         clip.open( ais );
         
         soundList.put( label , clip  );     
         } 
         catch ( UnsupportedAudioFileException | IOException | LineUnavailableException ex) 
         {
             Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
         }
        
    }//
    
    
    public void playSound( String label )
    {
    soundList.get( label ).start();
    }
    
    
  
}//class
        

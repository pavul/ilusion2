/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.audio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *this class is used to create a music player, with
 * play, stop, pause, loop, functionality, this class
 * must be used to put background music to a level
 * NOTE: the files to play must be in resources folder
 * @author pavulzavala
 */
public class MusicPlayer 
{
    
    
    private FileInputStream fis;
    private BufferedInputStream bis;
    
    private Player player;
    private boolean repeat; 
    private boolean paused; 
    private long pauseLocation;
    private long totalSongLength;
    private String musicFilePath;
    
    //@TODO consider add a counter to play a song
    // X number of times

    /**
     * this method is used to play a song, if u want to
     * repeat this song,  set Repeat to true before 
     * call this method
     * NOTE: the files to play must be in resources folder
     * @param musicFilePath
     * @throws FileNotFoundException
     * @throws JavaLayerException
     * @throws IOException 
     * @throws java.net.URISyntaxException 
     */
    public void play( String musicFilePath ) throws FileNotFoundException, JavaLayerException, IOException, URISyntaxException
    {
    
        
        System.out.println("::: playing "+musicFilePath );
        //stop previous music if exists, to start a new one
        //this prevent to run more than one song at same time
        
        
    this.musicFilePath = musicFilePath;
        
    fis = new FileInputStream( 
            new File( getClass().getResource( musicFilePath ).toURI() )  );
    
    totalSongLength =  fis.available();
     
    bis = new BufferedInputStream( fis );
    
    player = new Player( bis );
    
   
    
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    player.play();
                    
                    if( player.isComplete() && repeat )
                    {
                    play( musicFilePath );
                    }
                    
                    
                }
                catch ( JavaLayerException | IOException ex) 
                {
                    System.err.println("::: there was an error to play " + musicFilePath );
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MusicPlayer.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }////
        }.start();///
    
    }//
    
    /**
     * use this method to remuse current paused song
     * @throws FileNotFoundException
     * @throws JavaLayerException
     * @throws IOException 
     * @throws java.net.URISyntaxException 
     */
    public void resume( ) throws FileNotFoundException, JavaLayerException, IOException, URISyntaxException
    {
    
        paused = false;
        
    fis = new FileInputStream( 
            new File(getClass().getResource( musicFilePath ).toURI() )  );
    
    fis.skip( totalSongLength - pauseLocation );
    
    bis = new BufferedInputStream( fis );
    
    player = new Player( bis );
    
        new Thread()
        {
            @Override
            public void run()
            {
                try 
                {
                    player.play();
                }
                catch (JavaLayerException ex) 
                {
                    System.err.println("::: there was an error to play " + musicFilePath );
                }
            }////
        }.start();///
    
    }//
    
    
    
    /**
     * use this method to stop current song that is being 
     * played
     */
    public void stop()
    {
    paused = false;
        
        if( null != player)
        {
        player.close();
        
        totalSongLength = 0;
        pauseLocation = 0;
        }///
        
    }//
    
    /**
     * use this method to pause current played song
     */
    public void pause()
    {
    
        paused = true;
        if( null != player)
        {
            try 
            {
                pauseLocation = fis.available();
                player.close();
            } 
            catch (IOException ex) 
            {
                System.out.println("::: error when song is paused");
            }
        
        }///
        
    }//

    /**
     * 
     * @return true if the song i will start once is done,
     * false if not
     */
    public boolean isRepeat() {
        return repeat;
    }

    /**
     * set if the song will start once is done
     * @param repeat 
     */
    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
    
    
    
    
}//class

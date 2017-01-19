/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.control;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 *
 * @author pavulzavala
 * 
 * this class have some GpioPins mapped to the hardware buttons for the console
 * of raspberry pi 3, the objects of this class will have all references to
 * hardware buttons, this controll will be always present for each level or for
 * each game, but if the game is not for ilusion2 ( raspberry console ) dont
 * setup this class ( dont make instances of this class )
 * 
 * this class is final because is not intended to modify the behavior
 * however you can implement your control using PI4J library.
 */
public final class GpioGameControl 
{
    
    GpioPinDigitalInput leftPad;
    GpioPinDigitalInput rigthPad;
    GpioPinDigitalInput upPad;
    GpioPinDigitalInput downPad;
    GpioPinDigitalInput redBtn;
    GpioPinDigitalInput greenBtn;
    GpioPinDigitalInput blueBtn;
    GpioPinDigitalInput alphaBtn;
    GpioPinDigitalInput goBtn;
    
    
    /**
     * this constructor sets the instances for each button of
     * GPIO PINS MAPPED:
     * GPIO_21
     * GPIO_22
     * GPIO_23
     * GPIO_24
     * GPIO_25
     * GPIO_26
     * GPIO_27
     * GPIO_28
     * GPIO_29
     * NOTE: these gpio are mapped for pi4j library or WiringPi, there buttons
     * are mapped for pullUp resistance it means that when the button is pushed
     * you will get a low Response and when is released a high response.
     * Basically:
     * pressed = low
     * released = high
     */
    public GpioGameControl( )
    {
        
         final GpioController gpio = GpioFactory.getInstance( );
        
         downPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_21, PinPullResistance.PULL_UP );;
         leftPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_22, PinPullResistance.PULL_UP );
         upPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_23, PinPullResistance.PULL_UP );
         rigthPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_24, PinPullResistance.PULL_UP );
         goBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_25, PinPullResistance.PULL_UP );
         redBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_26, PinPullResistance.PULL_UP );
         blueBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_27, PinPullResistance.PULL_UP );
         greenBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_28, PinPullResistance.PULL_UP );
         alphaBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_29, PinPullResistance.PULL_UP );
   
         
         downPad.setShutdownOptions( true );
         leftPad.setShutdownOptions( true ); 
         upPad.setShutdownOptions( true );
         rigthPad.setShutdownOptions( true ); 
         goBtn.setShutdownOptions( true ); 
         redBtn.setShutdownOptions( true ); 
         blueBtn.setShutdownOptions( true ); 
         greenBtn.setShutdownOptions( true );
         alphaBtn.setShutdownOptions( true );
         
         
         
        
    }//const
    
    
    /**
     * this fucntion implements the GpioPinListenerDigital to each button
     * this is when the listener is implemented in the GameLevel class to have
     * all the logic for GpioController in one place
     * @param pinListener
     */
    public void setGpioListener( GpioPinListenerDigital pinListener )
    {

         downPad.addListener( pinListener );
         leftPad.addListener( pinListener );
         upPad.addListener( pinListener );
         rigthPad.addListener( pinListener );
         goBtn.addListener( pinListener );
         redBtn.addListener( pinListener );
         blueBtn.addListener( pinListener );
         greenBtn.addListener( pinListener );
         alphaBtn.addListener( pinListener );
         
    }//

    public GpioPinDigitalInput getLeftPad() {
        return leftPad;
    }

    public void setLeftPad(GpioPinDigitalInput leftPad) {
        this.leftPad = leftPad;
    }

    public GpioPinDigitalInput getRigthPad() {
        return rigthPad;
    }

    public void setRigthPad(GpioPinDigitalInput rigthPad) {
        this.rigthPad = rigthPad;
    }

    public GpioPinDigitalInput getUpPad() {
        return upPad;
    }

    public void setUpPad(GpioPinDigitalInput upPad) {
        this.upPad = upPad;
    }

    public GpioPinDigitalInput getDownPad() {
        return downPad;
    }

    public void setDownPad(GpioPinDigitalInput downPad) {
        this.downPad = downPad;
    }

    public GpioPinDigitalInput getRedBtn() {
        return redBtn;
    }

    public void setRedBtn(GpioPinDigitalInput redBtn) {
        this.redBtn = redBtn;
    }

    public GpioPinDigitalInput getGreenBtn() {
        return greenBtn;
    }

    public void setGreenBtn(GpioPinDigitalInput greenBtn) {
        this.greenBtn = greenBtn;
    }

    public GpioPinDigitalInput getBlueBtn() {
        return blueBtn;
    }

    public void setBlueBtn(GpioPinDigitalInput blueBtn) {
        this.blueBtn = blueBtn;
    }

    public GpioPinDigitalInput getAlphaBtn() {
        return alphaBtn;
    }

    public void setAlphaBtn(GpioPinDigitalInput alphaBtn) {
        this.alphaBtn = alphaBtn;
    }

    public GpioPinDigitalInput getGoBtn() {
        return goBtn;
    }

    public void setGoBtn(GpioPinDigitalInput goBtn) {
        this.goBtn = goBtn;
    }
    
    
    /**
     * this method return the state of a GPIO to low value
     * @return 
     */
    public PinState getBtnStateLow(){ return PinState.LOW;}
    
    /**
     * this method return the state of a GPIO to high value
     * @return 
     */
    public PinState getBtnStateHigh(){ return PinState.HIGH;}
    
    
}//class

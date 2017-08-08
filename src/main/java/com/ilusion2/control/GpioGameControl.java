/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.control;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
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
 * 
 *  GpioPinDigitalInput upPad; //gpio 21
    GpioPinDigitalInput leftPad; //gpio 22
    GpioPinDigitalInput rigthPad; //gpio 23
    GpioPinDigitalInput downPad; //gpio 25
    
    GpioPinDigitalInput redBtn; //gpio 29
    GpioPinDigitalInput greenBtn; //gpio 28
    GpioPinDigitalInput blueBtn; //gpio 27
    GpioPinDigitalInput alphaBtn; //gpio 31
    
    GpioPinDigitalInput chooseBtn; //gpio 1
    GpioPinDigitalInput goBtn; //gpio 2
    
    GpioPinDigitalInput upperLeftBtn; //gpio 3
    GpioPinDigitalInput upperRightBtn; //gpio 4
 * 
 */
public final class GpioGameControl 
{
    
    GpioPinDigitalInput upPad; //gpio 21
    GpioPinDigitalInput leftPad; //gpio 22
    GpioPinDigitalInput rigthPad; //gpio 23
    GpioPinDigitalInput downPad; //gpio 25
    
    GpioPinDigitalInput redBtn; //gpio 29
    GpioPinDigitalInput greenBtn; //gpio 28
    GpioPinDigitalInput blueBtn; //gpio 27
    GpioPinDigitalInput alphaBtn; //gpio 31
    
    GpioPinDigitalInput chooseBtn; //gpio 1
    GpioPinDigitalInput goBtn; //gpio 2
    
    GpioPinDigitalInput upperLeftBtn; //gpio 3
    GpioPinDigitalInput upperRightBtn; //gpio 4
    
//    GpioPinDigitalInput screenShootBtn; NOT USED YET
    
    
    /**
     * this constructor sets the instances for each button of
     * GPIO PINS MAPPED:
     * NOTE: those GPOI are free, not used by tft 3.5" and tft 5"
     * --------------------------
     *  TFT 3.5"                    |     TFT 5"
     * --------------------------
     * GPIO_1   chooseBtn           |     GPIO_0  
     * GPIO_2   goBtn               |     GPIO_1 chooseBtn
     * GPIO_3   upperLeft           |     GPIO_2 goBtn
     * GPIO_4   upperRight          |     GPIO_3 upperLeft
     * GPIO_7                       |     GPIO_4 upperRight
     * GPIO_8                       |     GPIO_5
     * GPIO_9                       |     GPIO_7
     * GPIO_15                      |     GPIO_8
     * GPIO_16                      |     GPIO_9
     *                              |     GPIO_10
     *                              |     GPIO_15 TXD UART
     * GPIO_21  upBtn               |     GPIO_16 RXD UART
     * GPIO_22  leftBtn             |     GPIO_21 upBtn
     * GPIO_23  rightBtn            |     GPIO_22 ;eftBtn
     * GPIO_24 /PWM0 for audio output for rpi zero
     * GPIO_25  downBtn             |     GPIO_23 rightBtn
     * GPIO_26 /PWM1 for audio output for rpi zero
     * GPIO_27   blueBtn            |     GPIO_24 /PWM0 for audio rpi zero
     * GPIO_28   greenBtn           |     GPIO_25 downBtn
     * GPIO_29   redBtn             |     GPIO_26 /PWM1 for audio rpi zero
     * GPIO_30                      |     GPIO_27 blueBtn
     * GPIO_31   alphaBtn           |     GPIO_28 greenBtn
     *                              |     GPIO_29 redBtn
     *                              |     GPIO_30 
     *                              |     GPIO_31 alphaBtn
     * NOTE: these gpio are mapped for pi4j library or WiringPi, there buttons
     * are mapped for pullUp resistance it means that when the button is pushed
     * you will get a low Response and when is released a high response.
     * Basically:
     * pressed = low
     * released = high
     */
    public GpioGameControl( )
    {
        
//    GpioPinDigitalInput chooseBtn; //gpio 1
//    GpioPinDigitalInput goBtn; //gpio 2
        
         final GpioController gpio = GpioFactory.getInstance( );
        
         
         //pad buttos
         upPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_21, PinPullResistance.PULL_UP );
         leftPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_22, PinPullResistance.PULL_UP );
         rigthPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_23, PinPullResistance.PULL_UP );
         downPad = gpio.provisionDigitalInputPin( RaspiPin.GPIO_25, PinPullResistance.PULL_UP );
         
         
         //game buttons
         redBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_29, PinPullResistance.PULL_UP );
         blueBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_27, PinPullResistance.PULL_UP );
         greenBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_28, PinPullResistance.PULL_UP );
         alphaBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_31, PinPullResistance.PULL_UP );
         upperLeftBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_03, PinPullResistance.PULL_UP );
         upperRightBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_04, PinPullResistance.PULL_UP );
         
         //SystemControl buttons
         chooseBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_01, PinPullResistance.PULL_UP );
         goBtn = gpio.provisionDigitalInputPin( RaspiPin.GPIO_02, PinPullResistance.PULL_UP );
         
         upPad.setShutdownOptions( true );
         leftPad.setShutdownOptions( true ); 
         rigthPad.setShutdownOptions( true ); 
         downPad.setShutdownOptions( true );
         
         redBtn.setShutdownOptions( true ); 
         blueBtn.setShutdownOptions( true ); 
         greenBtn.setShutdownOptions( true );
         alphaBtn.setShutdownOptions( true );
         upperLeftBtn.setShutdownOptions( true );
         upperRightBtn.setShutdownOptions( true );
         
         chooseBtn.setShutdownOptions( true );
         goBtn.setShutdownOptions( true ); 
         
    }//const
    
    
    /**
     * this fucntion implements the GpioPinListenerDigital to each button
     * this is when the listener is implemented in the GameLevel class to have
     * all the logic for GpioController in one place
     * @param pinListener
     */
    public void setGpioListener( GpioPinListenerDigital pinListener )
    {

         upPad.addListener( pinListener );
         leftPad.addListener( pinListener );
         rigthPad.addListener( pinListener );
         downPad.addListener( pinListener );
         
         redBtn.addListener( pinListener );
         blueBtn.addListener( pinListener );
         greenBtn.addListener( pinListener );
         alphaBtn.addListener( pinListener );
         upperLeftBtn.addListener( pinListener );
         upperRightBtn.addListener( pinListener );
         
         chooseBtn.addListener( pinListener );
         goBtn.addListener( pinListener );
         
    }//

    
    //getter and setters

    public GpioPinDigitalInput getUpPad() {
        return upPad;
    }

    public void setUpPad(GpioPinDigitalInput upPad) {
        this.upPad = upPad;
    }

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

    public GpioPinDigitalInput getChooseBtn() {
        return chooseBtn;
    }

    public void setChooseBtn(GpioPinDigitalInput chooseBtn) {
        this.chooseBtn = chooseBtn;
    }

    public GpioPinDigitalInput getGoBtn() {
        return goBtn;
    }

    public void setGoBtn(GpioPinDigitalInput goBtn) {
        this.goBtn = goBtn;
    }

    public GpioPinDigitalInput getUpperLeftBtn() {
        return upperLeftBtn;
    }

    public void setUpperLeftBtn(GpioPinDigitalInput upperLeftBtn) {
        this.upperLeftBtn = upperLeftBtn;
    }

    public GpioPinDigitalInput getUpperRightBtn() {
        return upperRightBtn;
    }

    public void setUpperRightBtn(GpioPinDigitalInput upperRightBtn) {
        this.upperRightBtn = upperRightBtn;
    }
    
    
    
    ///getter and setters
    
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

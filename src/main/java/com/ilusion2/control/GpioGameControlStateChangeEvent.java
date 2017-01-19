/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilusion2.control;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

/**
 * this class is a wrapper of GpioGameControlStateChangeEvent
 * to call this functionality from ilusion2
 * @author pi
 */
public class GpioGameControlStateChangeEvent 
extends GpioPinDigitalStateChangeEvent{
    
    public GpioGameControlStateChangeEvent(Object obj, GpioPin pin, PinState state) {
        super(obj, pin, state);
    }
    
}

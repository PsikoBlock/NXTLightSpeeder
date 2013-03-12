package me.psiko.lightspeeder;

import lejos.nxt.*;
import lejos.util.*;

public class LightSpeeder {
	static LightSensor sens1;
	static LightSensor sens2;
	public static void main(String[] args) {
		int distance = 80;
		int watchDiff;
		
		double speedCmMs;
		double speedMS;
		double speedKmH;
		
		sens1 = new LightSensor(SensorPort.S1, false);
		sens2 = new LightSensor(SensorPort.S4, false);
		
		
		System.out.println("NXTLightSpeeder");
		System.out.println("Connect sensors");
		System.out.println("to port 1 and 4");
		System.out.println("and press a btn");
		Button.waitForAnyPress();
		
		turnLaserOn();
		
		System.out.println("Distance is" + distance + "cm");
		System.out.println("Press a button!");
		Button.waitForAnyPress();
		
		watchDiff = measureBarrier();
		
		turnLaserOff();
		System.out.println("Results:");
		System.out.println("Delta-t: " + watchDiff);
		speedCmMs = distance/watchDiff;
		speedMS = speedCmMs * 10;
		speedKmH = speedMS * 3.6;
		
		System.out.println("v/cm/ms: " + speedCmMs);
		System.out.println("v/ m/s:  " + speedMS);
		System.out.println("v/ km/h: " + speedKmH);
		
	}
	
	public static void turnLaserOn() {
		//TODO Bluetooth slave to turn lasers on
		System.out.println("Turn lasers on");
		System.out.println("and press a btn");
		Button.waitForAnyPress();
	}
	
	public static void turnLaserOff() {
		//TODO Bluetooth slave to turn lasers off
		System.out.println("Turn lasers off");
		System.out.println("and press a btn");
		Button.waitForAnyPress();
	}
	
	public static int measureBarrier() {
		int lightThreshold = 90;
		int watchStart;
		int watchStop;
		int watchDiff;
		
		Stopwatch watch = new Stopwatch();
		
		
		Sound.beepSequenceUp();
		watch.reset();
		
		while(lightThreshold > sens1.readNormalizedValue());
		watchStart = watch.elapsed();
		
		while(lightThreshold > sens2.readNormalizedValue());
		watchStop = watch.elapsed();
		
		Sound.buzz();
		watchDiff = watchStop - watchStart;
		return watchDiff;
	}

}

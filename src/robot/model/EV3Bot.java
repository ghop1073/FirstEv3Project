 package robot.model;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.MovePilot;

public class EV3Bot
{

	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	private MovePilot botPilot;
	
	private EV3UltrasonicSensor distanceSensor;
	private float [] ultrasonicSamples;
	
	public EV3Bot()
	{
		this.botMessage = "Gage codes gageBot";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
		
		distanceSensor= new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
		distanceSensor.enable();
		setupPilot();
	}
	
	public void setupPilot()
	{
		Wheel leftWheel = WheeledChassis.modelWheel(Motor.A, 55.0).offset(-72);
		Wheel rightWheel = WheeledChassis.modelWheel(Motor.B, 55.0).offset(72);
		Chassis baseChassis = new WheeledChassis(new Wheel []{leftWheel, rightWheel}, WheeledChassis.TYPE_DIFFERENTIAL);
		botPilot = new MovePilot(baseChassis);
		botPilot.setLinearSpeed(20000);
	}
	
	public void driveRoom()
	{
		ultrasonicSamples = new float [distanceSensor.sampleSize()];
		distanceSensor.fetchSample(ultrasonicSamples, 0);
		if(ultrasonicSamples[0] < 56)
		{
			botPilot.rotate(160);
			botPilot.travel(1900);
			botPilot.rotate(-60);
			botPilot.travel(10000);
			botPilot.rotate(-75);
			botPilot.travel(10000);
			botPilot.rotate(-90);
			botPilot.travel(2130.36);
			
		}
//		while(ultrasonicSamples[0] < 1)
//		{
//			botPilot.travel(-100);
//			botPilot.rotate(-60);
//		}
		if(ultrasonicSamples[0] > 200000)
		{
			botPilot.rotate(180);
			botPilot.travel(9060.78);
			botPilot.rotate(-90);
			botPilot.travel(9600.12);
			botPilot.rotate(-90);
			botPilot.travel(1860.69);
		}
	}
	
	public void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
		
	}

}

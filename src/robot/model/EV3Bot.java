package robot.model;

import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;


public class EV3Bot
{

	private String botMessage;
	private int xPosition;
	private int yPosition;
	private long waitTime;
	
	public EV3Bot()
	{
		this.botMessage = "Gage codes gageBot";
		this.xPosition = 50;
		this.yPosition = 50;
		this.waitTime = 4000;
	}
	
	public void driveRoom()
	{
		
	}
	
	public void displayMessage()
	{
		LCD.drawString(botMessage, xPosition, yPosition);
		Delay.msDelay(waitTime);
		
	}

}

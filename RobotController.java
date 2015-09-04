package robot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class RobotController implements Runnable{

	private Robot robot;
	private int currX, currY;
	private final int ANIMATION_OFFSET = 1;
	private final long UPDATE_WAIT =  1;
	private boolean mouseClicked,leftClick,rightClick;
	
	public RobotController(){
		try{
			robot = new Robot();
			mouseClicked = false;
			leftClick = false;
			rightClick = false;
		} catch (AWTException e) {
			System.out.println("Error initializing robot");	
			System.exit(1);
		}
	}
	
	//move mouse with animation, moves xOffset horizontally
	//and yOffset vertically
	public void animateMovement(int xOffset, int yOffset){
		PointerInfo currPos = MouseInfo.getPointerInfo();
		Point p = currPos.getLocation();
		currX = (int) p.getX();
		currY = (int) p.getY();
		
		//variable holding the last time it updated mouse movement
		long lastTime = System.currentTimeMillis() - 1000;
		boolean xReached = false, yReached = false;
		
		//animation loop
		while(xReached != true && yReached != true){
			//only move mouse after the update_wait time has elapsed
			if(System.currentTimeMillis() - lastTime > UPDATE_WAIT){
				lastTime = System.currentTimeMillis();
				
				//should mouse move left or right?
				if(xOffset > 0){
					currX += ANIMATION_OFFSET;
					xOffset -= ANIMATION_OFFSET;
				} else if(xOffset < 0) {
					currX -= ANIMATION_OFFSET;
					xOffset += ANIMATION_OFFSET;
				} else {
					xReached = true;
				}
				
				//should mouse move up or down?
				if(yOffset > 0){
					currY += ANIMATION_OFFSET;
					yOffset -= ANIMATION_OFFSET;
				} else if(yOffset < 0){
					currY -= ANIMATION_OFFSET;
					yOffset += ANIMATION_OFFSET;
				} else {
					yReached = true;
				}
				
				robot.mouseMove(currX, currY);
			}
		}
	}
	
	/**
	 * method called when the user wants to click the mouse
	 * @param leftClick
	 * @param rightClick
	 */
	public void click(boolean left, boolean right){
		if(!mouseClicked){
			mouseClicked = true;
			if(left){
				robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
				leftClick = true;
			} else {
				robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
				rightClick = true;
			}
		}
	}
	
	/**
	 * if a button has been clicked it releases it
	 */
	public void unClick(){
		if(mouseClicked){
			if(leftClick){
				leftClick = false;
				robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			} else if(rightClick) {
				rightClick = false;
				robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
			}
		}
	}

	@Override
	public void run() {
		
		
	}
	
}

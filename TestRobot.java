package robot;

public class TestRobot {
	public static void main(String [] args){
		RobotController robot = new RobotController();
		robot.animateMovement(500,-100);
		robot.click(true, false);
		robot.animateMovement(200, 300);
		robot.unClick();
	}
}

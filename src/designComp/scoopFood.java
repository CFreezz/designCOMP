package designComp;

import lejos.nxt.Motor;
import lejos.util.Delay;

public class scoopFood {
	
	public static int BOWL_LOCATION_A = 0;
	public static int BOWL_LOCATION_B = 0;
	public static int BOWL_LOCATION_C = 0;
	public static int SPOON_UP = 0;
	public static void scoop(){
		Motor.A.setSpeed(100);
		Motor.A.setAcceleration(100);
		Motor.B.setSpeed(100);
		Motor.B.setAcceleration(100);
		Motor.C.setSpeed(100);
		Motor.C.setAcceleration(100);
		Motor.A.rotateTo(BOWL_LOCATION_A);
		Motor.C.rotateTo(BOWL_LOCATION_C);
		Motor.B.rotateTo(BOWL_LOCATION_B);
		Delay.msDelay(1000);
		Motor.C.rotateTo(SPOON_UP);
		Motor.B.rotateTo(0);
		Motor.C.rotateTo(0);
		Delay.msDelay(1000);
		
		
		

	}

}

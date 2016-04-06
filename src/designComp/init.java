package designComp;
//set tacho to 0 for each motor
//scan for a face and save that the tacho counts
import lejos.nxt.Motor;

public class init {
	int faceTachoA = -1;
	int faceTachoB = -1;
	int A_MAX_TACHO = 100;
	public void initialize(getFaceTrack tracker){
	    Motor.A.setSpeed(100);
	    Motor.A.setAcceleration(100);
	    Motor.B.setSpeed(100);
	    Motor.B.setAcceleration(100);
		 Motor.A.resetTachoCount();
		 Motor.B.resetTachoCount();	
		int face = tracker.run(false);
		Motor.A.forward();
		while(face != 0 || Motor.A.getTachoCount() < A_MAX_TACHO ){
			face = tracker.run(false);
			
		}
		Motor.A.stop();
		if(face != 0){
			while(face != 7){
				//center the face
			}
		}else{
			//face not found, return to 0
			Motor.A.rotateTo(0);
			
		}
		 
	}

}

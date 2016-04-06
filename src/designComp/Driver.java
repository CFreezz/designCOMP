package designComp;
import lejos.nxt.Motor;
public class Driver {
	//Motor.A.setSpeed(25);
	void main(String[] args){
		Motor.A.setSpeed(25);
		getFaceTrack tracker = new getFaceTrack(); //only instantiate once, only use 'tracker'
		while(!init.initialize(tracker)){
			//continuously tries to initialize until initialize returns true
		}
		
		
	}
}

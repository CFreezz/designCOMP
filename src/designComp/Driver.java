package designComp;
import lejos.nxt.Motor;
public class Driver {
	//Motor.A.setSpeed(25);
	void main(String[] args){
		Motor.A.setSpeed(25);
		getFaceTrack tracker = new getFaceTrack(); //only instantiate once, only use 'tracker'
		int position = tracker.run(false);
		if(position == 0){
			//etc
		}
	}
}

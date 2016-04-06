package designComp;

//set tacho to 0 for each motor
//scan for a face and save that the tacho counts
import lejos.nxt.Motor;

public class init {
	static int faceTachoA = -1;
	static int faceTachoB = -1;
	static int A_MAX_TACHO = 100;
	int B_MAX_TACHO = 100;

	public static boolean initialize(getFaceTrack tracker) {
		// set up our scan speeds
		Motor.A.setSpeed(100);
		Motor.A.setAcceleration(100);
		Motor.B.setSpeed(100);
		Motor.B.setAcceleration(100);
		// reset the initial tacho
		Motor.A.resetTachoCount();
		Motor.B.resetTachoCount();
		Motor.C.resetTachoCount();
		// see if the face is there
		int face = tracker.run(false);
		// scan for a face while moving horizontally w/ motor A, until reaching
		// max tacho
		Motor.A.forward();
		while (face != 0 || Motor.A.getTachoCount() < A_MAX_TACHO) {
			face = tracker.run(false);

		}
		Motor.A.stop();
		// if found a face, center it and get the tacho
		if (face != 0) {
			while (face != 7) {
				// center the face
				face = tracker.RunMotor();

			}
			faceTachoA = Motor.A.getTachoCount();
			faceTachoB = Motor.B.getTachoCount();
		} else {
			// face not found, return to 0 and return false
			Motor.A.rotateTo(0);
			return false;
		}
		// reset the motors to prepare for a scoop
		Motor.A.rotateTo(0);
		Motor.B.rotateTo(0);
		return true;
	}

}

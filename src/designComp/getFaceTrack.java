package designComp;

import javax.bluetooth.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;
import java.io.*;
import java.io.IOException;

//
//getFaceTrack class, must be instantiated ONCE to initialize connectors
//
public class getFaceTrack {
	int check;
	int n;
	int eye;
	DataInputStream dataInputStream;
	DataOutputStream dataOutputStream;
	NXTConnection bluetoothConnection = null;

	// constructor for the class
	getFaceTrack() {
		bluetoothConnection = Bluetooth.waitForConnection();
		bluetoothConnection.setIOMode(NXTConnection.RAW);
		dataOutputStream = bluetoothConnection.openDataOutputStream();
		dataInputStream = bluetoothConnection.openDataInputStream();
		System.out.println("Handshake Success!");
	}

	//
	// call run with boolean eye focus
	// focus on eye -> returns 0 if no face, 1 if eye open, 2 if eye closed
	// no focus -> returns 0 if no face; 7 if face is centered; 1,2,3 and 4
	// based on quadrant
	// returns 5 and 6 if it is close laterally
	// also returns 'distance' encoded within 1,2,3,4,5, and 6
	// +10, +20, and +30 for distance from center
	int run(boolean focusOnEye) {
		try {
			check = dataInputStream.readByte();
			while (check != 100) {
				check = dataInputStream.readByte();
			}
			check = 0;
			dataOutputStream.write(2);
			dataOutputStream.flush();
			n = dataInputStream.readByte();
			eye = dataInputStream.readByte();
		} catch (IOException e) {

		}

		if (focusOnEye == true) {
			if (n == 0) {
				return 0;
			} else if (eye == 1) {
				return 2;
			} else {
				return 1;
			}
		}
		return n;
	}

	//
	//
	// TODO include motor C balance
	public int RunMotor() {
		int n = run(false);
		if (n < 9) {
			Motor.A.setSpeed(25);
			Motor.B.setSpeed(25);
		}
		if (n > 9 && n < 19) {
			Motor.A.setSpeed(50);
			Motor.B.setSpeed(50);
			n = n - 10;
		}
		if (n > 19 && n < 29) {
			Motor.A.setSpeed(100);
			Motor.B.setSpeed(100);
			n = n - 20;
		}
		if (n > 29 && n < 39) {
			Motor.A.setSpeed(200);
			Motor.B.setSpeed(200);
			n = n - 30;
		}

		if (n == 1 || n == 4) {
			Motor.A.forward();
		}
		if (n == 2 || n == 3) {
			Motor.A.backward();
		}
		if (n == 1 || n == 2) {
			Motor.B.backward();
		}
		if (n == 3 || n == 4) {
			Motor.B.forward();
		}
		if (n == 5) {
			Motor.A.forward();
			Motor.B.stop();
		}
		if (n == 6) {
			Motor.A.backward();
			Motor.B.stop();
		}

		if (n == 0 || n == 7) {
			Motor.A.stop();
			Motor.B.stop();
		}
		return n;
	}
}
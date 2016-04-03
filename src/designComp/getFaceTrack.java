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
//constructor for the class
	getFaceTrack() {
		bluetoothConnection = Bluetooth.waitForConnection();
		bluetoothConnection.setIOMode(NXTConnection.RAW);
		dataOutputStream = bluetoothConnection.openDataOutputStream();
		dataInputStream = bluetoothConnection.openDataInputStream();
		System.out.println("Handshake Success!");
	}
//
//call run with boolean eye focus
//focus on eye -> returns 0 if no face, 1 if eye open, 2 if eye closed
//no focus -> returns 0 if no face; 7 if face is centered; 1,2,3 and 4 based on quadrant
//returns 5 and 6 if it is close laterally
//also returns 'distance' encoded within 1,2,3,4,5, and 6
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
			if(n == 0){
				return 0;
			}
			else if(eye == 1){
				return 2;
			}else{
				return 1;
			}
		}
		return n;
	}
}

//
//
//

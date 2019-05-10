package design;

import java.util.List;

/**
 * 1. What types of vehicles should we support? Motorcycle, Car, Bus
 * 
 * 2. Does each vehicle type take up a different amount of parking spots?
 * 
 * Yes. Motorcycle spot -> {Motorcycle}, Compact spot -> {Motorcycle, Car},
 * Large spot -> {Motorcycle, Car, Bus can park if we have 5 consecutive "large"
 * spots}
 * 
 * 3. Does the parking lot have multiple levels? Yes
 */
public class ParkingLot {
	List<Level> levels;

	public boolean park(Vehicle v) {
		for (Level level : levels) {
			if (level.park(v)) {
				return true;
			}
		}
		return false;
	}

}

class Level {
	List<ParkingSpot> spots;

	boolean park(Vehicle v) {
		return v.park(this);
	}

	void leave(Vehicle v) {

	}
}

class ParkingSpot {
	int id;
	ParkingSpotType type;
	boolean isAvailable = true;
	Vehicle vehicle;

	boolean isAvailable() {
		return isAvailable;
	}

	void park(Vehicle v) {
		isAvailable = false;
		vehicle = v;
	}

	void free() {
		isAvailable = true;
		vehicle = null;
	}
}

enum ParkingSpotType {
	M, C, L
}

abstract class Vehicle {

	abstract boolean park(Level level);

}

class Motorcycle extends Vehicle {
	boolean park(Level level) {
		for (ParkingSpot spot : level.spots) {
			if (spot.isAvailable()) {
				spot.park(this);
				return true;
			}
		}
		return false;
	}

}

class Car extends Vehicle {
	boolean park(Level level) {
		for (ParkingSpot spot : level.spots) {
			if (spot.isAvailable() && (spot.type == ParkingSpotType.M && spot.type == ParkingSpotType.L)) {
				spot.park(this);
				return true;
			}
		}
		return false;
	}
}

class Bus extends Vehicle {

	boolean park(Level level) {
		int start = -1;
		for (int i = 0; i < level.spots.size(); i++) {
			ParkingSpot spot = level.spots.get(i);
			if (spot.isAvailable() && spot.type == ParkingSpotType.L) {
				if (i - start == 5) {
					for (int j = start + 1; j <= i; j++) {
						level.spots.get(j).park(this);
					}
					return true;
				}
			} else {
				start++;
			}
		}
		return false;
	}
}
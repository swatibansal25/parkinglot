package com.parking.slot;

import java.util.ArrayList;
import java.util.List;

import com.parking.vehicle.Vehicle;

public class Slot {
	interface CapacityCalculator {
		boolean isCapacityLeft();
	}

	private static final int FREE = 0;
	private static final int FULL = 2;
	private static int idCounter = 0;
	private final int id = ++idCounter;
	private final int capacity;
	private final List<Vehicle> parkingVehicles = new ArrayList<>();

	public Slot(int capacity) {
		this.capacity = capacity;
	}

	public boolean accepts(Vehicle vehicle) {
		return isSlotFree(() -> (capacity >= parkingVehicles.get(0).getSpacesNeeded() + vehicle.getSpacesNeeded()));
	}

	public void addVehicle(Vehicle vehicle) {
		parkingVehicles.add(vehicle);
		System.out.println(String.format("%s parked on slot %d", vehicle, id));
	}

	public boolean isFree() {
		return isSlotFree(() -> (capacity > parkingVehicles.get(0).getSpacesNeeded()));
	}

	private boolean isSlotFree(CapacityCalculator b) {
		switch (parkingVehicles.size()) {
		case FREE:
			return true;
		case FULL:
			return false;
		default:
			return b.isCapacityLeft();
		}
	}

	public void remove(Vehicle vehicle) {
		parkingVehicles.remove(vehicle);
		System.out.println(String.format("%s removed from slot %d", vehicle, id));
	}

	@Override
	public String toString() {
		return "Slot [id: " + id + ", capacity=" + capacity + ", parkingVehicles=" + parkingVehicles + "]";
	}
}

package com.parking.vehicle;

public class Vehicle {
	private final int id;
	private final int spacesNeeded;
	private final double costFactor;
	private String vehicleType;

	public Vehicle(String vehicleType, int id, int spacesNeeded, double costFactor) {
		super();
		this.vehicleType = vehicleType;
		this.id = id;
		this.spacesNeeded = spacesNeeded;
		this.costFactor = costFactor;
	}

	public int getSpacesNeeded() {
		return spacesNeeded;
	}

	public double getCostFactor() {
		return costFactor;
	}

	@Override
	public String toString() {
		return String.format("Vehicle [vehicleType=%s, id=%d, spacesNeeded=%d, costFactor=%.2f]", vehicleType, id,
				spacesNeeded, costFactor);
	}
}
package com.parking.costCalculator;

public class CostCalculator {
	public double getCost(long parkingDuration, double costFactor) {
		return parkingDuration * costFactor;
	}
}

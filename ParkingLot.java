package com.parking.parkingLot;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import com.parking.costCalculator.CostCalculator;
import com.parking.slot.Slot;
import com.parking.ticket.Ticket;
import com.parking.vehicle.Vehicle;

public class ParkingLot {
    private final Collection<Slot> freeParkingSlots = new HashSet<>();
    private final Collection<Slot> allParkingSlots = new HashSet<>();
    private final Map<Vehicle, Slot> parkingVehicles = new HashMap<>();
    private double income = 0.0;
    private static final CostCalculator CALCULATOR = new CostCalculator();

    public ParkingLot(int numberOfSlots) {
        Random random = new Random();
        for (int i = 0; i < numberOfSlots; i++) {
            allParkingSlots.add(new Slot(1 + random.nextInt(3)));
        }
        // all slots initially free
        freeParkingSlots.addAll(allParkingSlots);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Slot targetSlot = freeParkingSlots.stream().filter(p -> p.accepts(vehicle)).findFirst()
                .orElseThrow(() -> new RuntimeException("No free slot for " + vehicle));
        targetSlot.addVehicle(vehicle);
        if (!targetSlot.isFree()) {
            freeParkingSlots.remove(targetSlot);
        }
        parkingVehicles.put(vehicle, targetSlot);
        return new Ticket(vehicle);
    }

    public void unparkVehicle(Ticket ticket) {
        Slot targetSlot = parkingVehicles.remove(ticket.getVehicle());
        targetSlot.remove(ticket.getVehicle());
        freeParkingSlots.add(targetSlot); // set keeps uniqueness
        income += ticket.calculateCost(CALCULATOR);
    }

    @Override
    public String toString() {
        return String.format("ParkingLot [income=%.2f, freeParkingSlots=%d, parkingVehicles=%d]", income, freeParkingSlots.size(),
                parkingVehicles.size());
    }
}

package com.parking.parkingLot;

import com.parking.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.parking.ticket.Ticket;

public class ParkingLotManager {
    private final ParkingLot parkingLot;
    String[] vehicleTypes = { "TwoWheeler", "Car", "Bus" };

    public ParkingLotManager(int numberOfSlots) {
        parkingLot = new ParkingLot(numberOfSlots);
    }

    public static void main(String[] args) {
        int numberOfSlots = Integer.parseInt(args[0]);
        int numberOfIterations = Integer.parseInt(args[1]);

        new ParkingLotManager(numberOfSlots).start(numberOfIterations);
    }

    private void start(int numberOfIterations) {
        List<Ticket> tickets = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfIterations; i++) {
            int nextInt = random.nextInt(1 + tickets.size());
            if (nextInt > tickets.size() * 2 / 3) {
                parkingLot.unparkVehicle(tickets.remove(random.nextInt(tickets.size())));
            } else {
                try {
                    parkNewVehicle(tickets, i);
                } catch (Exception e) {
                    System.out.println("Vehicle not parked: "+e.getMessage());
                }
            }
            System.out.println(parkingLot);
        }
    }

    private void parkNewVehicle(List<Ticket> tickets, int i) {
        String vehicleType = vehicleTypes[new Random().nextInt(vehicleTypes.length)];
        int spacesNeeded = 1 + Arrays.asList(vehicleTypes).indexOf(vehicleType);
        double costFactor = 0.2 + (0.2 * spacesNeeded);
        tickets.add(parkingLot.parkVehicle(new Vehicle(vehicleType, i, spacesNeeded, costFactor)));
    }
}
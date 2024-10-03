package com.project;

public class Coche implements Runnable {
    private final ParkingLot parkingLot;
    private final String nom;

    public Coche(ParkingLot parkingLot, String nom) {
        this.parkingLot = parkingLot;
        this.nom = nom;
    }

    @Override
    public void run() {
        parkingLot.enter(nom);

        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        parkingLot.exit(nom);
    }
}

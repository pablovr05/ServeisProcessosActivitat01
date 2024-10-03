package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int capacity = 3;
        ParkingLot parkingLot = new ParkingLot(capacity);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            String nom = "Cotxe " + i;
            executorService.submit(new Coche(parkingLot, nom));
        }
        executorService.shutdown();
    }
}

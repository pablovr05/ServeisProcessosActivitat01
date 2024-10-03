package com.project;

import java.util.concurrent.Semaphore;

public class ParkingLot {

    private final Semaphore semaphore;

    public ParkingLot(int capacidad) {
        this.semaphore = new Semaphore(capacidad);
    }

    public void enter(String nom) {
        try {
            System.out.println(nom + " Intentando entrar en el parking");
            semaphore.acquire();
            System.out.println("El coche " + nom + " está entrant al parking");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 

    public void exit(String nom) {
        System.out.println("El coche " + nom + " está saliendo del parking");
        semaphore.release();
    }
}
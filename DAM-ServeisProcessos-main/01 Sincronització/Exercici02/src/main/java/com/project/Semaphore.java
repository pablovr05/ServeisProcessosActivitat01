package com.project;

import java.util.concurrent.Semaphore;
import java.io.InterruptedIOException;

public class Semaphore {

    private final Semaphore semaphore;

    public parkingSlot(int capacity) {
        this.semaphore = new Semaphore(capacity);
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
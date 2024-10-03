package com.project;

import java.util.concurrent.Semaphore;

public class WebPage {

    private final Semaphore semaphore;

    public WebPage(int capacidad) {
        this.semaphore = new Semaphore(capacidad);
    }

    public void enter(String nombreUsuario) {
        try {
            System.out.println("El usuario " + nombreUsuario + " está intentando entrar en la web");
            if (semaphore.availablePermits() == 0) {
                System.out.println("El usuario " + nombreUsuario + " está esperando para entrar en la web");
            }
            semaphore.acquire();
            System.out.println("El usuario " + nombreUsuario + " ha entrado en la web");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 

    public void exit(String nombreUsuario) {
        System.out.println("El usuario " + nombreUsuario + " ha salido de la web");
        semaphore.release();
    }
}
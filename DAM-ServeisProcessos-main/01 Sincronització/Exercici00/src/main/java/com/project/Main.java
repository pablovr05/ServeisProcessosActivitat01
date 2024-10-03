package com.project;

import java.util.concurrent.*;

public class Main {

    private static int resultado0 = 1;
    private static int resultado1 = 2;
    private static int resultado2 = 3;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
            System.out.println("Tots els microserveis han acabat. Combinant els resultats... Resultat combinat: " + resultado0 + resultado1 + resultado2);
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable microservice1 = () -> {
            try {
                System.out.println("Microservei 1 processant dades...");
                Thread.sleep(1000);
                System.out.println("Microservei 1 completat.");
                resultado0 = resultado0 * 2;
                barrier.await(); 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice2 = () -> {
            try {
                System.out.println("Microservei 2 processant dades...");
                Thread.sleep(1500);
                System.out.println("Microservei 2 completat.");
                resultado1 = resultado1 * 3;
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice3 = () -> {
            try {
                System.out.println("Microservei 3 processant dades...");
                Thread.sleep(2000);
                System.out.println("Microservei 3 completat.");
                resultado2 = resultado2 * 4;
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Executem les tasques
        executor.submit(microservice1);
        executor.submit(microservice2);
        executor.submit(microservice3);

        // Tanquem l'executor
        executor.shutdown();
    }
}
package com.project;

import java.util.concurrent.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int dada1 = 5;
    private static int dada2 = 10;
    private static int dada3 = 3;
    private static int dada4 = 6;
    private static int dada5 = 9;

    private static int resultado0;
    private static int resultado1;
    private static double resultado2;

    private static List<Integer> llistaDades = new ArrayList<>();

    public static void main(String[] args) {

        llistaDades.add(dada1);
        llistaDades.add(dada2);
        llistaDades.add(dada3);
        llistaDades.add(dada4);
        llistaDades.add(dada5);

        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
            System.out.println("Suma: " + resultado0 + " Mitjana: " + resultado1 + " Desviació: " + resultado2);
            }
        });

        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable microservice1 = () -> {
            try {
                System.out.println("Calculant suma...");
                Thread.sleep(1000);
                System.out.println("Suma completada");
                resultado0 = suma();
                barrier.await(); 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice2 = () -> {
            try {
                System.out.println("Calculant mitjana...");
                Thread.sleep(1500);
                System.out.println("Mitjana completada");
                resultado1 = calcularMitjana();
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        Runnable microservice3 = () -> {
            try {
                System.out.println("Calculant Desviació...");
                Thread.sleep(2000);
                System.out.println("Desviació completada");
                resultado2 = desviacioEstandard();
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

    private static int calcularMitjana() {
        if (llistaDades.isEmpty()) {
            return 0;
        }
        return suma() / llistaDades.size();
    }

    private static int suma() {
        int sumaTotal = 0;
        for (int i : llistaDades) {
            sumaTotal += i;
        }
        return sumaTotal;
    }

    private static double desviacioEstandard() {
        if (llistaDades.isEmpty()) {
            return 0; 
        }
        int media = calcularMitjana();
        double sumaDesviaciones = 0.0;
        for (int i : llistaDades) {
            sumaDesviaciones += Math.pow(i - media, 2);
        }
        double varianza = sumaDesviaciones / llistaDades.size();
        return Math.sqrt(varianza);
    }
}
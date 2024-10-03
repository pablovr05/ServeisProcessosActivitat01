package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        final int capacidad = 3;
        WebPage webPage = new WebPage(capacidad);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            String nombreUsuario = "Usuario " + i;
            executorService.submit(new Usuario(webPage, nombreUsuario));
        }

        executorService.shutdown();
    }
}
package com.project;

public class Usuario implements Runnable {
    private final WebPage webPage;
    private final String nombreUsuario;

    public Usuario(WebPage webPage, String nombreUsuario) {
        this.webPage = webPage;
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public void run() {
        webPage.enter(nombreUsuario);

        try {
            Thread.sleep((long) (Math.random() * 5000)); //Això es podía treure perque el que fá es que no es cumpleixi el first in, first out.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        webPage.exit(nombreUsuario);
    }
}

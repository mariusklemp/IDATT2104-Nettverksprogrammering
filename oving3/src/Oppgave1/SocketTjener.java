package Oppgave1;

import java.io.*;
import java.net.*;

public class SocketTjener {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        ServerSocket tjener = new ServerSocket(PORTNR);
        System.out.println("Logg for tjenersiden. Nå venter vi...");
        while(true){
            Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt
            SocketThread socketThread = new SocketThread(forbindelse);
            socketThread.start();
        }

    }
}

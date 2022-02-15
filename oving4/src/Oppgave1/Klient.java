package Oppgave1;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Klient {

    public static void main(String args[])
            throws IOException
    {
        Scanner scanner = new Scanner(System.in);

        DatagramSocket datagramSocket = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        while (true) {
            System.out.print(
                    "Skriv inn et regnestykke på denne formen:");
            System.out.println(
                    "'1 + 1'");

            String input = scanner.nextLine();

            buf = new byte[65535];
            buf = input.getBytes();

            DatagramPacket DpSend = new DatagramPacket(
                    buf, buf.length, ip, 1234);

            datagramSocket.send(DpSend);

            if (input.equals("bye"))
                break;

            buf = new byte[65535];

            DatagramPacket DpReceive
                    = new DatagramPacket(buf, buf.length);

            datagramSocket.receive(DpReceive);

            String input2 = new String(buf, 0, buf.length);
            input2 = input2.trim();

            System.out.println(
                    "Answer = "
                            + input2);
        }
    }
}

package Oppgave1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.StringTokenizer;


class Tjener {

    public static void main(String[] args)
            throws IOException
    {
        DatagramSocket datagramSocket = new DatagramSocket(1234);

        byte[] buf = null;

        DatagramPacket DpReceive = null;
        DatagramPacket DpSend = null;

        while (true) {
            buf = new byte[65535];

            DpReceive = new DatagramPacket(buf, buf.length);

            datagramSocket.receive(DpReceive);

            String input = new String(buf, 0, buf.length);

            input = input.trim();

            System.out.println("Equation Received: "
                    + input);

            if (input.equals("bye")) {
                System.out.println(
                        "Client sent bye.....EXITING");
                break;
            }

            int result;

            StringTokenizer st = new StringTokenizer(input);

            int oprnd1 = Integer.parseInt(st.nextToken());
            String operation = st.nextToken();
            int oprnd2 = Integer.parseInt(st.nextToken());

            result = switch (operation) {
                case "+" -> oprnd1 + oprnd2;
                case "-" -> oprnd1 - oprnd2;
                case "*" -> oprnd1 * oprnd2;
                default -> oprnd1 / oprnd2;
            };

            System.out.println("Sending the result...");
            String res = Integer.toString(result);

            buf = res.getBytes();

            int port = DpReceive.getPort();

            DpSend = new DatagramPacket(
                    buf, buf.length, InetAddress.getLocalHost(),
                    port);
            datagramSocket.send(DpSend);
        }
    }
}

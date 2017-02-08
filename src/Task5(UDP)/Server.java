package com.company.task5;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) throws Exception {

        final Integer PORT = 8765;
        DatagramSocket serverSocket = new DatagramSocket(PORT);

        while (true) {
            // Create a packet
            DatagramPacket receivePacket = new DatagramPacket(new byte[200], 200);

            //Receive a packet (blocking)
            serverSocket.receive(receivePacket);

            //Each client request handle in separate thread
            //thread action
            Runnable task = () -> {

                String clientUserName = null;

                try {
                    //Read client message (user name) in UTF-8
                    clientUserName = new String(receivePacket.getData(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String serverResponse = "Hello, " + clientUserName;

                byte[] sendData = null;
                try {
                    sendData = serverResponse.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                System.out.println("RECEIVED: " + clientUserName);

                int port = receivePacket.getPort();

                // Create the packet to send to the client
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), port);

                try {
                    //send data to the client
                    serverSocket.send(sendPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }



              };

            //start thread
           new Thread(task).start();

        }

    }

}

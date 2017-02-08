package com.company.task5;

import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String args[]) throws Exception
    {
        final String HOST = "localhost";
        final Integer PORT = 8765;

        //read user name from console stream
        BufferedReader userName =
                new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

        byte[] receiveData = new byte[1024];
        byte[] sendData = userName.readLine().getBytes();

        //create packets
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(HOST), PORT);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        DatagramSocket clientSocket = new DatagramSocket();
        clientSocket.send(sendPacket);
        clientSocket.receive(receivePacket);
        String serverResponse = new String(receivePacket.getData(), "UTF-8");
        System.out.println("FROM SERVER:" + serverResponse);
        clientSocket.close();
    }
}
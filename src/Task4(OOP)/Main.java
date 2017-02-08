package com.company.task4;


public class Main {
    public static void main(String[] args) {

        Tasks.sort();
        System.out.println("------------------------------");

        Tasks.problemB();
        System.out.println("------------------------------");

        Tasks.problemC();
        System.out.println("------------------------------");

        Tasks.writeToFile("temp");
        Tasks.readFromFile("temp");

    }
}

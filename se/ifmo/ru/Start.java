package se.ifmo.ru;

import se.ifmo.ru.client.ConsoleManager;

public class Start {
    public static void main(String[] args){
        System.out.println("Запуск программы");
        ConsoleManager consoleManager=new ConsoleManager();
        consoleManager.interactiveMod();
    }
}

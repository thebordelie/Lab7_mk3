package se.ifmo.ru.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerCommand extends Thread{
    private final BufferedReader keyboard;
    public ServerCommand(){
        keyboard=new BufferedReader(new InputStreamReader(System.in));
        start();
    }
    @Override
    public void run(){
        String command;
        while (true){
            try {
                command=keyboard.readLine();
                if(command.equals("exit")){
                    System.out.println("Завершение работы сервера");
                    System.exit(1);
                }
                if(command.equals("save")){}
                else System.out.println("Неизвестная команда");
            }
            catch (IOException ex){
                System.out.println("Ошибка считывания");
            }

        }
    }
}

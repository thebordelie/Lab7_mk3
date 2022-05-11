package se.ifmo.ru.server;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewClient extends Thread {
    private Socket client;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private LinkedList<Ticket> tickets;
    private Runnable executeCommand;
    private DataBaseHandler dataBaseHandler;
    public NewClient(Socket client, LinkedList<Ticket> tickets,DataBaseHandler dataBaseHandler){
        this.tickets=tickets;
        this.client=client;
        try {
            objectOutputStream=new ObjectOutputStream(client.getOutputStream());
            objectInputStream=new ObjectInputStream(client.getInputStream());
            this.dataBaseHandler=dataBaseHandler;
            start();
        }
        catch (IOException ex){
            System.out.println("Ошибка при инициализации блока ввода-вывода данных");
        }


    }
    @Override
    public void run(){
        Command command;

        try {
            ExecutorService executor = Executors.newCachedThreadPool();
            dataBaseHandler.connectToDataBase(objectInputStream,objectOutputStream);
            dataBaseHandler.setConnect(false);
            while (true){
                command=(Command) objectInputStream.readObject();
                if(command.getNameOfCommand().equals("exit")) break;
                executeCommand=new ExecuteCommand(objectOutputStream,command,tickets,dataBaseHandler);
                executor.submit(executeCommand);
            }
            System.out.println("Пользователь "+client.getInetAddress()+" отключается");
            objectInputStream.close();
            objectOutputStream.close();
            client.close();


        }
        catch (ClassNotFoundException ex){
            System.out.println("Получен неизвестный объект");
        }
        catch (IOException ex){
            System.out.println("Произошёл разрыв соединения с пользователем");
        }
        finally {
            try {
                client.close();
            }
            catch (IOException ex){
                System.out.println("Сокет не закрыт");
            }
        }



    }
}

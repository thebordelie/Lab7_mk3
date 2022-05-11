package se.ifmo.ru.client;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SendCommand {
    private final int PORT=8090;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    protected  Authorization authorization;
    private Message message;
    public SendCommand(){
        newConnection(0);
        message=new Message();

    }
    public void sendingCommand(Command command){
        try {
            objectOutputStream.reset();
            objectOutputStream.writeObject(command);
            objectOutputStream.flush();
            if(command.getNameOfCommand().equals("exit")){
                System.out.println("Отключение программы");
                System.exit(1);}
            message=(Message)objectInputStream.readObject();
            System.out.println(message.getMessage());

        }
        catch (IOException ex){
            System.out.println("Ошибка подсоединения к серверу");
            newConnection(5000);
        }
        catch (NullPointerException exception){
            newConnection(5000);
        }
        catch (ClassNotFoundException ex){
            System.out.println("Получен неизвсетный объект");
        }
    }
    public void newConnection(int sleepTime){
        System.out.println("Попытка подключиться к серверу");
        try {
            Thread.sleep(sleepTime);
            socket=new Socket("localhost",PORT);
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            System.out.println("Подключение произошло успешно");
            authorization=new Authorization(objectOutputStream,objectInputStream);
            authorization.authorizationToDataBase();

        }
        catch (InterruptedException ex){
            System.out.println("Ошибка прерывания");
        }
        catch (UnknownHostException ex){
            System.out.println("Неизвестный хост");
            System.exit(1);
        }
        catch (SocketException ex){
            System.out.println("Сервер не доступен");
            newConnection(5000);
        }
        catch (IOException ex){
            System.out.println("Ошибка при соединении с сервером");
            newConnection(5000);
        }

    }
}

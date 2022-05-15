package se.ifmo.ru.server;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.Message;
import se.ifmo.ru.data.Ticket;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExecuteCommand implements Runnable {
    private final ObjectOutputStream objectOutputStream;
    private final Command command;
    private LinkedList<Ticket> tickets;
    private final WorkingWithDataBase workingWithDataBase;
    private Message message;
    public ExecuteCommand(ObjectOutputStream objectOutputStream, Command command, LinkedList<Ticket> tickets, DataBaseHandler dataBaseHandler) {
        this.objectOutputStream = objectOutputStream;
        this.command = command;
        this.tickets=tickets;
        message=new Message();
        workingWithDataBase=new WorkingWithDataBase(dataBaseHandler.getStatement(), dataBaseHandler.getConnection(), dataBaseHandler.getLogin(),dataBaseHandler.getId());
    }

    @Override
    public void run(){
        Lock lock=new ReentrantLock();
        lock.lock();
        if(workingWithDataBase.processingCommandToDataBase(command,message)){
            message.setMessage(command.executeCommand(tickets));
        }

        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();

        }
        catch (IOException ex){

            System.out.println("Ошибка при отправлении данных");
        }
        finally{
            lock.unlock();
        }
    }

}

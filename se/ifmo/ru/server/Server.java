package se.ifmo.ru.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT=8090;
    public static void main(String[] args) throws ClassNotFoundException{

        Class.forName("org.postgresql.Driver");
        try {
            CollectionManager collectionManager=new CollectionManager();
            DataBaseHandler dataBaseHandler=new DataBaseHandler("jdbc:postgresql://pg:5432/studs","---","---");
            dataBaseHandler.readDataBase(collectionManager.getTickets());
            ServerSocket serverSocket=new ServerSocket(PORT);
            System.out.println("Сервер успешно стартовал");
            new ServerCommand();
            try {
                while (true){
                    Socket client=serverSocket.accept();
                    System.out.println("Подсоединился новый пользователь");
                    System.out.println("Данные нового пользователя "+ client.getInetAddress());
                    new NewClient(client,collectionManager.getTickets(),dataBaseHandler);
                }

            }
            catch (IOException ex){
                System.out.println("Ошибка подсоединения нового пользователя");
            }
            finally {
                serverSocket.close();
            }

        }
        catch (IOException ex){
            System.out.println("Сервер не может быть запущен. Порт занят");
            System.exit(1);
        }

    }
}

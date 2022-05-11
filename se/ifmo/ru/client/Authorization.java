package se.ifmo.ru.client;

import se.ifmo.ru.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Authorization {
    private BufferedReader keyboard;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private long id;
    public Authorization(ObjectOutputStream objectOutputStream, ObjectInputStream objectInputStream) {
        this.keyboard = new BufferedReader(new InputStreamReader(System.in));
        this.objectInputStream=objectInputStream;
        this.objectOutputStream=objectOutputStream;
    }
    public long getId(){return  id;}
    public void authorizationToDataBase(){
        int ent;
        Message ansDataBase;
        System.out.println("Введите цифру от 1 до 3");

        while (true){
            try {
                System.out.println("1: Регистрация нового пользователя\n2: Вход\n3: Выход из приложения ");
                ent=Integer.parseInt(keyboard.readLine());
                if(ent==1||ent==2||ent==3){
                    if (ent==3){
                        System.out.println("Отключение программы");
                        System.exit(0);
                    }
                    Message message=new Message();
                    System.out.print("Логин: ");
                    message.setLogin(keyboard.readLine());
                    System.out.print("Пароль: ");
                    message.setPassword(keyboard.readLine());
                    if (ent==1){
                        message.setMessage("1");
                        objectOutputStream.writeObject(message);
                        objectOutputStream.flush();
                    }
                    if (ent==2){
                        message.setMessage("2");
                        objectOutputStream.writeObject(message);
                        objectOutputStream.flush();
                    }
                    ansDataBase=(Message)objectInputStream.readObject();
                    System.out.println(ansDataBase.getMessage());
                    id=ansDataBase.getId();
                    if(ansDataBase.getMessage().equals("Авторизация прошла успешно"))break;


                }
                else System.out.println("Введите цифру от 1 до 3");
            }
            catch (IOException ex){
                ex.printStackTrace();

            }


            catch (NumberFormatException ex){
                authorizationToDataBase();
            }
            catch (ClassNotFoundException ex){
                System.out.println("Получен неизвестный объект");
            }


        }
    }
}

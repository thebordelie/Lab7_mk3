package se.ifmo.ru.server;

import se.ifmo.ru.Message;
import se.ifmo.ru.data.*;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class DataBaseHandler {
    private static final String SQL_REGISTER_USER="insert into users (login,password) VALUES (?,?)";
    private static final String PEREC="F--LSK=FJ@AIQE";
    private Connection connection;
    private Statement statement;
    private String URL;
    private String username;
    private String password;
    private boolean connect=false;
    private Message message;
    private String login;
    private long id;

    public DataBaseHandler(String URL, String username, String password){
        this.URL=URL;
        this.username=username;
        this.password=password;
        try {
            connection= DriverManager.getConnection(URL, username, password);
            statement=connection.createStatement();
        }
        catch (SQLException ex){
            ex.printStackTrace();

        }
    }

    public void setConnect(boolean connect) {
        this.connect = connect;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void connectToDataBase(ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream){
        try {
            while(!connect){
                message=(Message) objectInputStream.readObject();
                if (message.getLogin().length()>=20){
                    message.setMessage("Слишком длинный пароль");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                    message.setMessage("");
                }
                if(message.getMessage().equals("1")){
                    registerNewUser(message.getLogin(),message.getPassword());
                    message=new Message();
                    message.setMessage("Регистрация прошла успешно");
                    objectOutputStream.writeObject(message);
                    objectOutputStream.flush();
                }
                if(message.getMessage().equals("2")){
                    userAuthorization(message.getLogin(),message.getPassword());
                    login=message.getLogin();
                    message=new Message();
                    if(connect){
                        message.setMessage("Авторизация прошла успешно");
                        message.setId(id);
                        objectOutputStream.writeObject(message);
                        objectOutputStream.flush();
                        }

                    else{
                        message.setMessage("Неправильно введён логин или пароль");
                        objectOutputStream.writeObject(message);
                        objectOutputStream.flush();
                    }
                }


            }
        }
        catch (ClassNotFoundException ex){
            System.out.println("Получен неизвестный класс");
        }
        catch (IOException ex){
            System.out.println("Ошибка при вводе/выводе данных");
        }

    }
    public void userAuthorization(String login,String password){
        try {
            ResultSet resultSet=statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                if(resultSet.getString("login").equals(login)&&resultSet.getString("password").equals(hashPassword(password+PEREC))){
                    connect=true;
                    break;
                }
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public void registerNewUser(String login, String password){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(SQL_REGISTER_USER);
            preparedStatement.setString(1,login);
            preparedStatement.setString(2,hashPassword(password+PEREC));
            preparedStatement.executeUpdate();
            preparedStatement.close();

        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    public String hashPassword(String password){
        try {
            MessageDigest md=MessageDigest.getInstance("SHA-512");
            byte[] digest= md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger bigInteger=new BigInteger(1,digest);
            String hashPass=bigInteger.toString(16);
            while (hashPass.length()<32){
                hashPass="0"+ hashPass;
            }
            return hashPass;
        }
        catch (NoSuchAlgorithmException ex){
            System.out.println("Неверный алгоритм шифрования");
        }
        return null;
    }
    public void readDataBase(LinkedList<Ticket> tickets){
        Ticket ticket;
        try {
            long id=1;
            ResultSet resultSet= statement.executeQuery("select * from ticket");
            while (resultSet.next()){
                id=resultSet.getLong("id");
                ticket=new Ticket(id,resultSet.getString("name"),
                        new Coordinates(Float.parseFloat(resultSet.getString("x_coordinates")),
                                Long.parseLong(resultSet.getString("y_coordinates"))),LocalDate.of(resultSet.getInt("year"),resultSet.getInt("month"),resultSet.getInt("day")),resultSet.getFloat("price"),
                        resultSet.getBoolean("refundable"), TicketType.valueOf(resultSet.getString("tickettype")),
                        new Venue(id,resultSet.getString("nameofvenue"),resultSet.getInt("capacity"),new Address(resultSet.getString("street"),resultSet.getString("zipcode"),new Location(resultSet.getLong("xloc"),resultSet.getFloat("yloc"),resultSet.getLong("zloc"),resultSet.getString("nameloc")))));
                tickets.add(ticket);
            }
            this.id=id;
        }


        catch (SQLException ex){
            System.out.println("Неизвестная таблица");
        }
    }

    public String getLogin() {
        return login;
    }
    public long getId(){return id;}
}

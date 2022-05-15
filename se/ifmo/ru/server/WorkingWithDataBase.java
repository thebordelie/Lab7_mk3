package se.ifmo.ru.server;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.Message;
import se.ifmo.ru.data.*;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

public class WorkingWithDataBase {
    private final Statement statement;
    private LinkedList<Ticket> tickets;
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private static final String SQL_ADD="insert into ticket(name, x_coordinates, y_coordinates, day, month, year, price, refundable, tickettype, nameofvenue, capacity, street, zipcode, xloc, yloc, zloc, creator, nameloc) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_ADD_IF_MIN="select Min(price) from ticket";
    private static final String SQL_CLEAR="DELETE from ticket";
    private static String SQL_REMOVE_BY_ID;
    private static String SQL_REMOVE_HEAD;
    private static final String SQL_MIN_ID="select Min(id) from ticket";
    private static String SQL_UPDATE
    private String login;


    public WorkingWithDataBase(Statement statement,  Connection connection, String login, long id){
        this.statement=statement;
        this.connection=connection;
        this.login=login;
    }
    public boolean processingCommandToDataBase(Command command, Message message){
        boolean commandWork;
        switch (command.getNameOfCommand()){
            case "add":
                commandWork=addTicket(command.getTicket());
                if(!commandWork) message.setMessage("Не удалось добавить элемент");
                return commandWork;
            case "add_if_min":
                commandWork=minPrice(command.getTicket());
                if(!commandWork) message.setMessage("Элемент не наименьший");
                return commandWork;
            case "remove_by_id":
                commandWork=removeTicket(command);
                if(!commandWork) message.setMessage("Не удалось удалить элемент");
                return commandWork;
            case "remove_head":
                commandWork=removeHead();
                if(!commandWork) message.setMessage("Невозможно удалить первый билет");
                return commandWork;
            case "update":
                commandWork=updateTicket(command);
                if(!commandWork)message.setMessage("Не получилось обновить элемент");
                return commandWork;
            default:
                return true;
        }
        return false;
    }
    public boolean updateTicket(Command command){
        try {
            SQL_UPDATE="UPDATE ticket"+"\n SET name="+command.getNameOfCommand()+"\n WHERE id="+command.getArg();
            preparedStatement=connection.prepareStatement(SQL_UPDATE);
            return true;
        }
        catch (SQLException ex){
            System.out.println("Неизветная таблтца");
            return false;
        }

    }
    public boolean removeHead(){
        try {
            ResultSet resultSet=statement.executeQuery(SQL_MIN_ID);
            while (resultSet.next()){
                String minId=resultSet.getString("min");
                String SQL="select * from ticket where id="+minId;
                ResultSet checkLogin=statement.executeQuery(SQL);
                while (checkLogin.next()){
                    String login=checkLogin.getString("creator");
                    if (login.equals(this.login)){
                        SQL_REMOVE_HEAD="DELETE from ticket where id="+minId;
                        statement.executeUpdate(SQL_REMOVE_HEAD);
                        return true;
                }
                }
            }
            return false;

        }
        catch (SQLException ex){
            System.out.println("Попытка удаления из пустой БД");
            return false;
        }
    }
    public boolean removeTicket(Command command){
        try {
            String SQL="select * from ticket WHERE id="+command.getArg();
            ResultSet resultSet=statement.executeQuery(SQL);
            while (resultSet.next()){
                if (login.equals(resultSet.getString("creator"))){
                    SQL_REMOVE_BY_ID="DELETE from ticket WHERE id="+command.getArg();
                    statement.executeUpdate(SQL_REMOVE_BY_ID);
                    return true;
                }
            }
            return false;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.out.println("Неизвестная таблица");
            return false;
        }
    }
    public boolean minPrice(Ticket ticket){
        try {
            ResultSet resultSet=statement.executeQuery(SQL_ADD_IF_MIN);
            while (resultSet.next()){
                float price=resultSet.getFloat("min");
                if(ticket.getPrice()<price){
                    return addTicket(ticket);
                }
            }
            return false;
        }
        catch (SQLException ex){
            System.out.println("Ошибка при добавлении");
            return false;
        }
    }
    public boolean addTicket(Ticket ticket){
        try {
            preparedStatement=connection.prepareStatement(SQL_ADD);
            preparedStatement.setString(1,ticket.getName());
            preparedStatement.setFloat(2,ticket.getCoordinates().getX());
            preparedStatement.setLong(3,ticket.getCoordinates().getY());
            preparedStatement.setInt(4,ticket.getDay());
            preparedStatement.setInt(5,ticket.getMonth());
            preparedStatement.setInt(6,ticket.getYear());
            preparedStatement.setFloat(7,ticket.getPrice());
            preparedStatement.setBoolean(8,ticket.isRefundable());
            preparedStatement.setString(9,String.valueOf(ticket.getType()));
            preparedStatement.setString(10,String.valueOf(ticket.getVenue().getName()));
            preparedStatement.setInt(11,ticket.getVenue().getCapacity());
            preparedStatement.setString(12,String.valueOf(ticket.getVenue().getAddress().getStreet()));
            preparedStatement.setString(13,String.valueOf(ticket.getVenue().getAddress().getZipCode()));
            preparedStatement.setLong(14,ticket.getVenue().getAddress().getTown().getX());
            preparedStatement.setFloat(15,ticket.getVenue().getAddress().getTown().getY());
            preparedStatement.setLong(16,ticket.getVenue().getAddress().getTown().getZ());
            preparedStatement.setString(17,login);
            preparedStatement.setString(18,ticket.getVenue().getAddress().getTown().getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException ex){
            System.out.println("Ошибка при добавлении");
            return false;
        }

        return true;
    }


}

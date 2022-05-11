package se.ifmo.ru.client;

import se.ifmo.ru.Commands.CommandRegister;
import se.ifmo.ru.Commands.CommandsForCollection.*;
import se.ifmo.ru.data.Ticket;
import se.ifmo.ru.data.Venue;
import se.ifmo.ru.utility.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class CommandManager {
    private final SendCommand sendCommand;
    private CommandRegister commandRegister;
    private TicketAsker ticketAsker;
    private BufferedReader keyboard;
    public CommandManager() {
        keyboard=new BufferedReader(new InputStreamReader(System.in));
        ticketAsker=new TicketAsker(new Scanner(System.in));
        this.sendCommand = new SendCommand();
        commandRegister=new CommandRegister();
        commandRegister.registerNewCommand("add",new Add("add"));
        commandRegister.registerNewCommand("add_if_min",new AddIfMin("add_if_min"));
        commandRegister.registerNewCommand("clear",new Clear("clear"));
        commandRegister.registerNewCommand("count_less_than_refundable", new CountLessThanRefundable("count_less_than_refundable"));
        commandRegister.registerNewCommand("execute_script",new ExecuteScript("execute_script"));
        commandRegister.registerNewCommand("group_counting_by_type",new GroupCountingType("group_counting_by_type"));
        commandRegister.registerNewCommand("head",new Head("head"));
        commandRegister.registerNewCommand("help",new Help("help"));
        commandRegister.registerNewCommand("info",new Info("info"));
        commandRegister.registerNewCommand("remove_by_id",new Remove("remove_by_id"));
        commandRegister.registerNewCommand("remove_any_by_venue",new RemoveAnyByVenue("remove_any_by_venue"));
        commandRegister.registerNewCommand("remove_head",new RemoveHead("remove_head"));
        commandRegister.registerNewCommand("show",new Show("show"));
        commandRegister.registerNewCommand("update",new Update("update"));
        commandRegister.registerNewCommand("exit",new Exit("exit"));
    }

    public void processingCommand(String[] finaUserCommand){
        long id;
        switch (finaUserCommand[0]){
            case "add":
                id=sendCommand.authorization.getId();
                System.out.println(id);
                commandRegister.getCommands().get(finaUserCommand[0]).setTicket(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
                break;
            case "add_if_min":
                id=sendCommand.authorization.getId();
                commandRegister.getCommands().get(finaUserCommand[0]).setTicket(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
                break;
            case "remove_by_id":
                try {
                    commandRegister.getCommands().get(finaUserCommand[0]).setArg(Long.parseLong(finaUserCommand[1]));
                }
                catch (NumberFormatException ex){
                    System.out.println("Неверный формат id");
                }
                catch (IndexOutOfBoundsException ex){
                    System.out.println("Отсутствует id");
                }
                break;
            case "help":
                commandRegister.getCommands().get(finaUserCommand[0]).setCommands(commandRegister.getCommands());
                break;
            case "count_less_than_refundable":
                long isRefundable;
                if(Boolean.parseBoolean(finaUserCommand[1])) isRefundable=1;
                else isRefundable=0;
                commandRegister.getCommands().get(finaUserCommand[0]).setArg(isRefundable);
                break;
            case "update":
                try {
                    id=sendCommand.authorization.getId();
                    long user_id=Long.parseLong(finaUserCommand[1]);
                    commandRegister.getCommands().get(finaUserCommand[0]).setTicket(new Ticket(id,ticketAsker.askName(),ticketAsker.askCoordinates(), LocalDate.now(),ticketAsker.askPrice(), ticketAsker.askRefundable(), ticketAsker.askTicketType(),new Venue(id, ticketAsker.askName(), ticketAsker.askCapacity(), ticketAsker.askAddress())));
                    commandRegister.getCommands().get(finaUserCommand[0]).setArg(user_id);
                }
                catch (NumberFormatException ex){
                    System.out.println("Неверное значение id");
                }
                break;
        }
        if(commandRegister.getCommands().containsKey(finaUserCommand[0])){
            sendCommand.sendingCommand(commandRegister.getCommands().get(finaUserCommand[0]));
        }

        else System.out.println("Команда не найдена, напишите 'help' для получения списка возможных команд");
    }
}

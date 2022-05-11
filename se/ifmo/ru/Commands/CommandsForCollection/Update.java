package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Update extends Command {
    public Update(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        for(Ticket ticket:tickets){
            if(ticket.getId()==getArg()){
                tickets.set(tickets.indexOf(ticket),getTicket());
                return "Элемент найден и обновлён";
            }
        }
        return "Элемент не удалось найти в коллекции";
    }
    @Override
    public String infoOfCommand(){
        return "Обновляет элемент по заданному id";
    }

}

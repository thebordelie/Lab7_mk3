package se.ifmo.ru.Commands.CommandsForCollection;


import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Add extends Command {
    public Add(String nameOfCommand) {
        super(nameOfCommand);
    }
    public String executeCommand(LinkedList<Ticket> tickets){
        tickets.add(getTicket());
        return ("Элемент успешно добавлен");
    }
    @Override
    public String infoOfCommand(){
        return "Добавляет в коллекцию новый билет";
    }

}

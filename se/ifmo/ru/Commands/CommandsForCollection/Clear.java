package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Clear extends Command {
    public Clear(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        tickets.clear();
        return "Коллекция успешно очищена";
    }
    @Override
    public String infoOfCommand(){
        return "Очищает коллекцию";
    }
}

package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Head extends Command {
    public Head(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        if(tickets.size()==0){
            return "Коллекция пуста, невозможно вывести первый элемент";
        }
        return "Первый элемент коллекции \n"+tickets.get(0).toString();
    }
    public String infoOfCommand(){
        return "Выводит первый элемент коллекции";
    }
}

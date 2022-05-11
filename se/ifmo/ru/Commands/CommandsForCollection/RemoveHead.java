package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RemoveHead extends Command {
    public RemoveHead(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        try{
            return "Удаленный элемент: "+tickets.remove().getName();

        }
        catch (NoSuchElementException e){
            return "В коллекции отсутствуют элементы, удаление невозможно";
        }
    }

    @Override
    public String infoOfCommand(){
        return "Удаляет первый элемент коллекции";
    }
}

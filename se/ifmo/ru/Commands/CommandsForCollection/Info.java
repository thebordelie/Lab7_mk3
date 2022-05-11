package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Info extends Command {
    public Info(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets) {
        return "Коллекция содержит элементы класса Ticket\n В коллекции сейчас находится " +tickets.size()+" элементов";
    }
    @Override
    public String infoOfCommand(){
        return "Выводит информацию о коллекции";
    }

}

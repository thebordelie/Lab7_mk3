package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class Exit extends Command {
    public Exit(String name){
        super(name);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        return "Завершение программы";
    }
    @Override
    public String infoOfCommand(){
        return "Останавливает программу";
    }
}

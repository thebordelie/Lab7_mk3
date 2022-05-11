package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class CountLessThanRefundable extends Command {
    public CountLessThanRefundable(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> tickets){
        try {
            int count=0;
            for (Ticket ticket:tickets){
                if (ticket.isRefundable()&&getArg()==1){
                    count+=1;
                }
                if(!ticket.isRefundable()&&getArg()==0){
                    count+=1;
                }
            }
            return"Количество элементов="+count;
        }
        catch (NoSuchElementException ex){
            return "Коллекция пуста";
        }
        catch (NumberFormatException ex){
            return "Сравнение невозможно!";
        }
    }
    @Override
    public String infoOfCommand(){
        return "Выводит количество элементов, значение refundable которых меньше заданного";
    }
}

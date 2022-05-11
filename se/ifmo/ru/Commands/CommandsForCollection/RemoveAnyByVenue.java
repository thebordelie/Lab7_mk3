package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;
import se.ifmo.ru.data.Venue;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class RemoveAnyByVenue extends Command {
    private Venue venue;

    public RemoveAnyByVenue(String nameOfCommand) {
        super(nameOfCommand);
    }

    @Override
    public String executeCommand(LinkedList<Ticket> tickets) {
        try {
            for (Ticket ticket : tickets) {
                if (ticket.getVenue().equals(venue)) {
                    tickets.remove(ticket);
                    return "Элемент найден и удалён";
                }
                if (ticket.equals(tickets.getLast())) {
                    return "Элемент не найден";
                }
            }

        } catch (NoSuchElementException ex) {
            return "Коллекция пуста";
        }
        return "";
    }
    @Override
    public String infoOfCommand(){
        return "Удаляет один элемент по заданному venue";
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}

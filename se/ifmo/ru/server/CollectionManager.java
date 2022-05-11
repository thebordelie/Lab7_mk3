package se.ifmo.ru.server;

import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;

public class CollectionManager {
    private LinkedList<Ticket> tickets;

    public CollectionManager() {
        this.tickets =new LinkedList<>();
    }

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }
}

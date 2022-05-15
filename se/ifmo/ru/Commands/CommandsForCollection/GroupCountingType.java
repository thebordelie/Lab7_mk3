package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;
import se.ifmo.ru.data.TicketType;

import java.util.Comparator;
import java.util.LinkedList;

public class GroupCountingType extends Command {
    public GroupCountingType(String nameOfCommand) {
        super(nameOfCommand);
    }

    @Override
    public String executeCommand(LinkedList<Ticket> tickets) {
        String ans = "";
        int countVIP = 0, countUSUAL = 0, countBUDGETARY = 0, countCHEAP = 0;
        TicketType type;
        if (!(tickets.size() == 0)) {
            for (Ticket ticket : tickets) {
                type = ticket.getType();
                switch (type) {
                    case VIP: {
                        countVIP += 1;
                        break;
                    }
                    case USUAL: {
                        countUSUAL += 1;
                        break;
                    }
                    case CHEAP: {
                        countCHEAP += 1;
                        break;
                    }
                    case BUDGETARY: {
                        countBUDGETARY += 1;
                        break;
                    }
                }
            }
            tickets.sort(Comparator.comparing(Ticket::getType));
            return "Коллекция успешно отсортирована\nКоличество VIP=" + countVIP + "\nКоличество USUAL="
                    + countUSUAL + "\nКоличество BUDGETARY=" + countBUDGETARY + "\nКоличество CHEAP=" + countCHEAP;

        } else {
            return "Сортировка невозможна, коллекция пуста";
        }
    }

    @Override
    public String infoOfCommand() {
        return "Сортирует коллекцию по типам, а также выводит количество типов в коллекции";
    }
}

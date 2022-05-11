package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;

public class Save extends Command {
    public Save(String name){
        super(name);
    }
    @Override
    public String infoOfCommand(){
        return getNameOfCommand()+" сохраняет коллекцию в файл";
    }
    /*
    @Override
    public String ExecuteCommand(LinkedList<Ticket> tickets){
        JaxbWorker jaxbWorker=new JaxbWorker();
        TicketList ticketList=new TicketList(tickets);
        String file_name="input.xml";
        jaxbWorker.convertObjectToXml(ticketList,file_name);
        System.out.println("Файл успешно сохранён");
        return "done";
    }
     */

}

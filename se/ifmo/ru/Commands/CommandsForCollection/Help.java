package se.ifmo.ru.Commands.CommandsForCollection;

import se.ifmo.ru.Commands.Command;
import se.ifmo.ru.data.Ticket;

import java.util.LinkedList;
import java.util.Map;

public class Help extends Command {

    public Help(String nameOfCommand) {
        super(nameOfCommand);
    }
    @Override
    public String executeCommand(LinkedList<Ticket> ticket){
        String ans = "";
        for(Map.Entry<String, Command> entry:getCommands().entrySet() ){
            String key=entry.getKey();
            String value=entry.getValue().infoOfCommand();
            ans+=key+": "+value+"\n";
        }
        return ans;
    }

    @Override
    public String infoOfCommand(){
        return "Выводит список команд";
    }


}

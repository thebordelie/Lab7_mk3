package se.ifmo.ru.Commands;

import java.util.HashMap;

public class CommandRegister {
    private HashMap<String, Command> commands;
    public CommandRegister(){
        commands=new HashMap<>();
    }
    public void registerNewCommand(String name, Command command){
        commands.put(name,command);
    }
    public HashMap<String, Command> getCommands(){
        return commands;
    }
}

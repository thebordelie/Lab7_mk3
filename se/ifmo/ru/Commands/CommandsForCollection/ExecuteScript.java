package se.ifmo.ru.Commands.CommandsForCollection;
import se.ifmo.ru.Commands.Command;

public class ExecuteScript extends Command {
    private String fileName;
    public ExecuteScript(String nameOfCommand) {
        super(nameOfCommand);
    }

    @Override
    public String infoOfCommand(){
        return "Считать и выполнить скрипт из указанного файла";
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

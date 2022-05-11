package se.ifmo.ru.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleManager {
    private final BufferedReader keyboard;
    private final CommandManager commandManager;
    public ConsoleManager(){
        keyboard=new BufferedReader(new InputStreamReader(System.in));
        commandManager=new CommandManager();
    }
    public void interactiveMod(){
        String userCommand;
        String[] finalUserCommand;
        try {
            while (true){
                System.out.print("Выполнить команду: ");
                userCommand=keyboard.readLine();
                finalUserCommand=userCommand.split(" ",2);
                if(finalUserCommand[0].equals("clear")){
                    System.out.println("Вам недоступна эта команда");
                    continue;
                }
                if(finalUserCommand[0].equals("execute_script")){
                    executeScript(finalUserCommand);
                }
                else commandManager.processingCommand(finalUserCommand);
            }
        }
        catch (IOException ex){
            System.out.println("Ошибка при считывании");
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Введена пустая строка");
        }

    }
    public void executeScript(String[] finalUserCommand){
        String fileCommand;
        String[] finalFileCommand;
        try {
            BufferedReader inputStreamReader = new BufferedReader(new InputStreamReader(new FileInputStream(finalUserCommand[1])));
            fileCommand = inputStreamReader.readLine();
            while (fileCommand!=null){
                finalFileCommand=fileCommand.trim().split(" ",2);
                System.out.println("Началась обработка комманды"+finalFileCommand[0]);
                if(finalFileCommand[0].equals("execute_script")) System.out.println("Невозможно выполнить комманду");
                commandManager.processingCommand(finalFileCommand);
            }

        }
        catch(IOException ex){
            System.out.println("Проблема при считывании");
        }
        catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("В файле неверная комманда");
        }

    }
}

package se.ifmo.ru.exception;

public class RecursiveCommandException extends Exception{
    public RecursiveCommandException(){
        super("Команда вызывается рекурсивно - выполнение скрипта будет бесконечным");
    }
}

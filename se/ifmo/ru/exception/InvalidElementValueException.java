package se.ifmo.ru.exception;

public class InvalidElementValueException extends Exception{
    public InvalidElementValueException(){
        super("Элемент не удовлетворяет условиям");
    }
}

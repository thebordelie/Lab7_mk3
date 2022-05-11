package se.ifmo.ru.exception;

public class ElementMustNotBeEmptyException extends Exception{
    public ElementMustNotBeEmptyException(){
        super("Элемент не может быть пустым");
    }
}

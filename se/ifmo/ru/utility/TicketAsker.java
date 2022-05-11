package se.ifmo.ru.utility;

import se.ifmo.ru.exception.*;
import se.ifmo.ru.data.Address;
import se.ifmo.ru.data.Coordinates;
import se.ifmo.ru.data.Location;
import se.ifmo.ru.data.TicketType;

import java.util.Scanner;

public class TicketAsker {
    private Scanner scanner;
    public TicketAsker(Scanner scanner){
        this.scanner=scanner;
    }
    public void setScanner(Scanner scanner){
        this.scanner=scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Метод - спрашивает у пользователя название
     * @return Возвращает имя
     */
    public String askName(){
        String name;
        try{
            System.out.print("Введите название:");
            name = scanner.nextLine().trim();
            if (name.equals("")) throw new ElementMustNotBeEmptyException();
            }
        catch (ElementMustNotBeEmptyException e){
            System.out.println(e.getMessage());
            return askName();
            }

        return name;
    }

    /**
     * Метод - Спрашивает у пользователя параметры для Coordinates
     * @return создает и возвращает объект класса Coordinates
     */
    public Coordinates askCoordinates(){
        System.out.println("Введите координаты места прибытия");
        Coordinates coordinates;
        coordinates=new Coordinates(askX(),askY());
        return coordinates;
    }

    /**
     * Метод - спрашивает у пользователя x
     * @return Возвращает координату x
     */
    public float askX(){
        String strX;
        float x;
        try {
            System.out.print("x=");
            strX=scanner.nextLine().trim();
            if (strX.equals("")) throw new ElementMustNotBeEmptyException();
            x=Float.parseFloat(strX);


        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askX();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askX();
        }

        return x;
    }

    /**
     * Метод - спрашивает значение y
     * @return возвращает значение y
     */
    public long askY(){
        String strY;
        long y;
        try {
            System.out.print("y=");
            strY=scanner.nextLine().trim();
            if (strY.equals("")) throw new ElementMustNotBeEmptyException();
            y=Long.parseLong(strY);

        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных!");
            return askY();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askY();
        }

        return y;
    }

    /**
     * Метод - спрашивает цену билета
     * @return возвращает цену билета
     */
    public float askPrice(){
        String strPrice;
        float price;
        try {
            System.out.print("Цена=");
            strPrice=scanner.nextLine().trim();
            if (strPrice.equals("")) throw new ElementMustNotBeEmptyException();

            price=Float.parseFloat(strPrice);
            if(price<=0) throw new InvalidElementValueException();

        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askPrice();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askPrice();
        } catch (InvalidElementValueException e) {
            System.out.println("Цена не может быть не положительной");
            return askPrice();
        }
        return price;
    }

    /**
     * Метод - спрашивает у пользователя возвращаемый ли билет
     * @return возвращает true или false
     */
    public boolean askRefundable(){
        String strRefundable;
        boolean refundable;
        try {
            System.out.print("Возвращаемый(true, false):");
            strRefundable=scanner.nextLine().trim();
            if (strRefundable.equals("")) throw new ElementMustNotBeEmptyException();
            if(!(strRefundable.equals("true")||strRefundable.equals("false"))) throw new NumberFormatException();
            refundable=Boolean.parseBoolean(strRefundable);

        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askRefundable();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askRefundable();
        }
        return refundable;
    }

    /**
     * Метод - спрашивает у пользователя тип Билета
     * @return возвращает тип Билета
     */
    public TicketType askTicketType(){
        String strType;
        TicketType type;
        try {
            System.out.println("Возможные типы билетов\n VIP, CHEAP,BUDGETARY,USUAL.");
            System.out.print("Тип билета:");

            strType=scanner.nextLine().trim();
            if (strType.equals("")) throw new ElementMustNotBeEmptyException();
            type=TicketType.valueOf(strType);
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askTicketType();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askTicketType();
        }
        catch (IllegalArgumentException ex){
            System.out.println("Такого типа не существует");
            return askTicketType();
        }
        return type;
    }

    /**
     * Метод - спрашивает у пользователя координату z
     * @return возвращает z
     */
    public Long askZ(){
        String strZ;
        long z;
        try {
            System.out.print("z=");
            strZ=scanner.nextLine().trim();
            if (strZ.equals("")) throw new ElementMustNotBeEmptyException();
            z=Long.parseLong(strZ);
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askZ();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askZ();
        }
        return z;
    }

    /**
     * Метод - спрашивает у пользователя данные для Location
     * @return возвращает Location
     */
    public Location askLocation(){
        System.out.println("Координаты здания:");
        Location location;
        location=new Location((long)askX(),askY(),askZ(),askName());
        return location;
    }

    /**
     * Метод - спрашивает у пользователя улицу, с которой происходит отправление
     * @return возвращает улицу
     */
    public String askStreet(){
        String strStreet;
        try {
            System.out.print("Улица:");
            strStreet=scanner.nextLine().trim();
            if (strStreet.equals("")) throw new ElementMustNotBeEmptyException();
        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askStreet();
        }


        return strStreet;
    }

    /**
     * Метод - спрашивает у пользователя ZipCode
     * @return возвращает индекс
     */
    public String askZipCode(){
        String strZipCode;
        try {
            System.out.print("Zipcode:");
            strZipCode=scanner.nextLine().trim();
            if (strZipCode.equals("")) throw new ElementMustNotBeEmptyException();
        }

        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askZipCode();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askZipCode();
        }


        return strZipCode;
    }

    /**
     * Метод - спрашивает у пользователя Address
     * @return возвращает объект класса Address
     */
    public Address askAddress(){
        System.out.println("Введите адрес места отбытия");
        Address address;
        address=new Address(askStreet(),askZipCode(),askLocation());
        return address;
    }

    /**
     * Метод - спрашивает у пользователя вместимость
     * @return возвращает вместимость билета
     */
    public int askCapacity(){
        String strCapacity;
        int price;
        try {
            System.out.print("Количество мест=");
            strCapacity=scanner.nextLine().trim();
            if (strCapacity.equals("")) throw new ElementMustNotBeEmptyException();
            price=Integer.parseInt(strCapacity);
            if(price<=0) throw new InvalidElementValueException();
        }
        catch (NumberFormatException ex){
            System.out.println("Неверный формат данных");
            return askCapacity();

        }
        catch (ElementMustNotBeEmptyException ex){
            System.out.println(ex.getMessage());
            return askCapacity();
        }
        catch (InvalidElementValueException ex){
            System.out.println("Вместимость не может быть не положительной");
            return askCapacity();
        }

        return price;
    }
}

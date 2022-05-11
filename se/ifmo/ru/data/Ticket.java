package se.ifmo.ru.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDate;

@XmlRootElement (name="Ticket")
@XmlType(propOrder = { "id","name","coordinates","creationDate","price","refundable","type","venue"})
public class Ticket implements Serializable {
    /** Свойство - Id билета */
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** Свойство - название билета*/
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** Свойство - координаты места прибытия*/
    private Coordinates coordinates; //Поле не может быть null
    /** Свойство - дата создания*/
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /** Свойство - цена билета */
    private float price; //Значение поля должно быть больше 0
    /** Свойство - возвращаемый ли билет*/
    private boolean refundable;
    /** Свойство - тип билета*/
    private TicketType type; //Поле не может быть null
    /** Свойство - место отправления*/
    private Venue venue; //Поле может быть null

    /**
     * Конструктор задаёт основные параметры билета
     * @param id id билета
     * @param name название билета
     * @param coordinates координаты места прибытия
     * @param creationDate дата создания
     * @param price цена билета
     * @param refundable возвратный ли билет
     * @param type тип билета
     * @param venue место отправления
     */
    public Ticket(long id, String name, Coordinates coordinates, LocalDate creationDate, float price, boolean refundable, TicketType type, Venue venue) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
    }
    public Ticket(){}

    /**
     * Возвращает id билета
     * @return long id
     */
    @XmlElement(name="id")
    public long getId() {
        return id;
    }

    /**
     * возвращает название билета
     * @return String name
     */
    @XmlElement(name="name")
    public String getName() {
        return name;
    }

    /**
     * возвращает координаты место прибытия
     * @return Coordinates coordinates
     */
    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату создания билета
     * @return String creationDate
     */
    @XmlElement
    public String getCreationDate() {
        if (creationDate.getMonthValue()<=9){
            return creationDate.getDayOfMonth()+".0"+creationDate.getMonthValue()+"."+creationDate.getYear();
        }
        return creationDate.getDayOfMonth()+"."+creationDate.getMonthValue()+"."+creationDate.getYear();
    }
    public int getDay(){
        return creationDate.getDayOfMonth();
    }
    public int getMonth(){
        return (creationDate.getMonthValue());
    }
    public int getYear(){
        return creationDate.getYear();
    }

    /**
     * возвращает цену билета
     * @return float price
     */
    @XmlElement(name="price")
    public float getPrice() {
        return price;
    }

    /**
     * возвращает возможность вернуть билет
     * @return  boolean refundable
     */
    @XmlElement(name="refundable")
    public boolean isRefundable() {
        return refundable;
    }

    /**
     * возвращает тип билета
     * @return TicketType type
     */
    @XmlElement(name="type")
    public TicketType getType() {
        return type;
    }

    /**
     * возвращает место отправления
     * @return Venue venue
     */
    @XmlElement(name="venue")
    public Venue getVenue() {
        return venue;
    }
    @Override
    public String toString(){
        String info="";
        info+="Билет";
        info+="\n добавлен: "+ creationDate.atStartOfDay().toLocalDate()+" "+creationDate.atStartOfDay().toLocalTime();
        info+="\n Название: "+ name;
        info+="\n Координаты: "+coordinates.getX()+", "+coordinates.getY();
        info+="\n Цена: "+price;
        info+="\n Возвращаемый:"+refundable;
        info+="\n Тип:"+ type;
        info+="\n Отправление с :"+venue.getName();
        info+="\n Находящемся по адресу: "+ venue.getAddress();
        return info;
    }
    @Override
    public int hashCode(){
        return name.hashCode()+coordinates.hashCode()+(int)price+type.hashCode()+venue.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if(this==o){return true; }
        if(o instanceof Ticket){
            Ticket ticket=(Ticket) o;
            return name.equals(ticket.getName())&& coordinates.equals(ticket.getCoordinates())
                    &&(price==ticket.getPrice())&& (refundable==ticket.isRefundable())&&(type==ticket.getType())
                    &&venue.equals(ticket.getVenue());
        }
        return false;
    }



}

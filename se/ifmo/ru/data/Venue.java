package se.ifmo.ru.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name="venue")
@XmlType(propOrder = {"id","name","capacity","address"})
public class Venue implements Serializable {
    //Место отправления
    /** Свойство - id места отправления*/
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /** Свойство - название места отправления*/
    private String name; //Поле не может быть null, Строка не может быть пустой
    /** Свойство - вместимость*/
    private int capacity; //Значение поля должно быть больше 0
    /** Свойство - Адрес*/
    private Address address; //Поле может быть null

    /**
     * Конструктор задаёт место отправления
     * @param id id
     * @param name название
     * @param capacity вместимость
     * @param address адрес
     */
    public Venue(Long id, String name, int capacity,Address address){
        this.id=id;
        this.name=name;
        this.capacity=capacity;
        this.address=address;
    }
    public Venue(){}

    /**
     * Метод getId
     * @return возвращает id Venue
     */
    @XmlElement
    public Long getId() {
        return id;
    }
    /**
     * Метод getName
     * @return возвращает name Venue
     */
    @XmlElement
    public String getName() {
        return name;
    }
    /**
     * Метод getCapacity
     * @return возвращает capacity Venue
     */
    @XmlElement
    public int getCapacity() {
        return capacity;
    }
    /**
     * Метод getAddress
     * @return возвращает Address Venue
     */
    @XmlElement
    public Address getAddress() {
        return address;
    }
    @Override
    public String toString(){
        return "Id="+id+", name="+name+", capacity"+capacity+", adress"+address;
    }
    @Override
    public int hashCode(){
        return (int)(id+name.hashCode()+capacity+address.hashCode());
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Venue){
            Venue venue=(Venue) o;
            return id.equals(venue.getId())&&name.equals(venue.getName())&&(capacity==venue.getCapacity())&& address.equals(venue.getAddress());

        }
        return false;
    }
}

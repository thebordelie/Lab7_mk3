package se.ifmo.ru.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name="address")
@XmlType(propOrder = {"street","zipCode","town"})
public class Address implements Serializable {
    /** Свойство - улица */
    private String street; //Строка не может быть пустой, Поле может быть null
    /** Свойство - индекс */
    private String zipCode; //Поле не может быть null
    /** Свойство - здание */
    private Location town; //Поле может быть null

    /**
     * Конструктор задаёт адрес места отправления
     * @param street Улица
     * @param zipCode Индекс
     * @param town Здание
     */
    public Address(String street, String zipCode, Location town) {
        this.street = street;
        this.zipCode = zipCode;
        this.town = town;
    }
    public Address(){}

    /**
     * Возвращает значение улицы, с которого происходит отправление
     * @return String street
     */
    @XmlElement
    public String getStreet() {
        return street;
    }

    /**
     * Возвращает значение индекса
     * @return String zipCode
     */
    @XmlElement
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Возвращает здание, с которого происходит отправление
     * @return Location town
     */
    @XmlElement
    public Location getTown() {
        return town;
    }

    @Override
    public String toString(){
        return "\nУлица:"+street+", Почтовый индекс:"+zipCode+", \nЗдание:"+town;
    }
    @Override
    public int hashCode(){
        return street.hashCode()+zipCode.hashCode()+town.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Address){
            Address address=(Address) o;
            return street.equals(address.getStreet())&&zipCode.equals(address.getZipCode())&&town.equals(address.getTown());
        }
        return false;
    }
}

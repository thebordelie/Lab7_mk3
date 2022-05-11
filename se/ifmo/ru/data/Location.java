package se.ifmo.ru.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name="town")
@XmlType(propOrder = {"x","y","z","name"})
public class Location implements Serializable {
    /** Свойство - координата по x*/
    private long x;
    /** Свойство - координата по y*/
    private float y;
    /** Свойство - координата по z*/
    private Long z; //Поле не может быть null
    /** Свойство - название здания*/
    private String name; //Длина строки не должна быть больше 272, Поле не может быть null

    /**
     * Конструктор задаёт координаты и имя здания места отправления
     * @param x координата по x
     * @param y координата по y
     * @param z координата по z
     * @param name название здания
     */
    public Location(long x, float y, Long z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }
    public Location(){}
    /**
     * возвращает координату x
     * @return long x
     */
    @XmlElement
    public long getX() {
        return x;
    }
    /**
     * возвращает координату y
     * @return float y
     */
    @XmlElement
    public float getY() {
        return y;
    }
    /**
     * возвращает координату z
     * @return Long z
     */
    @XmlElement
    public Long getZ() {
        return z;
    }

    /**
     * Возвращает название здания
     * @return String name
     */
    @XmlElement
    public String getName() {
        return name;
    }
    @Override
    public String toString(){
        return "\n Название :"+name+",x="+x+", y="+y+", z="+z;

    }
    @Override
    public int hashCode(){
        return (int) (name.hashCode()+x+z+(int) y);
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Location){
            Location coordinates=(Location) o;
            return (x==coordinates.getX())&&(y==coordinates.getY())&&name.equals(coordinates.getName())&&(z.equals(coordinates.getZ()));}
        return false;
    }
}

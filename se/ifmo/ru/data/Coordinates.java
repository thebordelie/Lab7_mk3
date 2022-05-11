package se.ifmo.ru.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name="coordinates")
@XmlType( propOrder={"x","y"})
public class Coordinates implements Serializable {
    /** Свойство - координата x*/
    private float x;
    /** Свойство - координата y*/
    private long y; //Значение поля должно быть больше -132

    /**
     * Конструктор задаёт координаты
     * @param x Координата по x
     * @param y Координата по y
     */
    public Coordinates(float x, long y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}

    /**
     * Возвращает координату x
     * @return int x
     */
    @XmlElement(name="x")
    public float getX() {
        return x;
    }
    /**
     * Возвращает координату y
     * @return int y
     */
    @XmlElement(name="y")
    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return  "x=" + x +", y=" + y ;

    }
    @Override
    public int hashCode(){
        return (int) ((int) x+y);
    }
    @Override
    public boolean equals(Object o){
        if (this==o){return true;}
        if (o instanceof Coordinates){return (x==((Coordinates) o).getX())&&(y==((Coordinates) o).getY());}
        return false;
    }
}

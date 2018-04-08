package ru.kpfu.itis.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kpfu.itis.model.Automobile;

import javax.validation.constraints.Future;
import java.util.Date;

public class TripForm {
    private Automobile auto;
    @NotEmpty(message = "Нужно указать место отправления")
    private String departure;
    @NotEmpty(message = "Нужно указать место прибытия")
    private String destination;
    //TODO: реализовать аннотацию, которая проверяет,
    // что указанная дата не раньше текущего момента времени
    @DateTimeFormat(pattern="dd.MM.yyyy HH:mm")
    @Future(message = "Увы, но вы не можете отправиться в прошлое")
    private Date date;
    private int price;
    private int count; //количество пассажиров
    private String info; //информация и доп. условия

    public TripForm() {

    }

    public Automobile getAuto() {
        return auto;
    }

    public void setAuto(Automobile auto) {
        this.auto = auto;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

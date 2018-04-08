package ru.kpfu.itis.viewobject;

import java.util.Date;

public class TripVO {

    private Long id;

    private DriverVO driver;

    private  AutomobileVO automobile;

    private Date date;

    private int price;

    private int count;

    private String status;

    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverVO getDriver() {
        return driver;
    }

    public void setDriver(DriverVO driver) {
        this.driver = driver;
    }

    public AutomobileVO getAutomobile() {
        return automobile;
    }

    public void setAutomobile(AutomobileVO automobile) {
        this.automobile = automobile;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

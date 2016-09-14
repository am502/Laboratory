package DAO.Model;

public class Book {
    private String name;
    private int price;
    private int id;
    public Book(int id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Book(){}

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public int getPrice(){
        return price;
    }

    public String toString(){
        return id + " " + name + " " + + price + " рублей";
    }

    public void setPrice(int price){
        this.price = price;
    }
}
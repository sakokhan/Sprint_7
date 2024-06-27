package Models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
     private int rentTime;
     private String deliveryDate;
    private String comment;
    private String [] color;

    public Order firstName(String firstName){
        this.firstName = firstName;
        return  this;
    }
    public Order lastName(String lastName){
        this.lastName = lastName;
        return  this;
    }
    public Order address(String address){
        this.address = address;
        return  this;
    }
    public Order metroStation(int metroStation){
        this.metroStation = metroStation;
        return  this;
    }
    public Order phone(String phone){
        this.phone = phone;
        return  this;
    }
    public Order rentTime(int rentTime){
        this.rentTime = rentTime;
        return  this;
    }
    public Order deliveryDate(String deliveryDate){
        this.deliveryDate = deliveryDate;
        return  this;
    }
    public Order comment(String comment){
        this.comment = comment;
        return  this;
    }
    public Order color(String []color){
        this.color = color;
        return  this;
    }
}

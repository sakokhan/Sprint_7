package models;
import static utils.Utils.*;

public class OrderCreator {
    public static Order randomOrder(String [] colour){
        return  new Order()
                .firstName(randomString(20))
                .lastName(randomString(20))
                .address(randomString(25))
                .metroStation(rnd(1, 10))
                .phone("+79109876543")
                .rentTime(rnd(1, 30))
                .deliveryDate(timestamp().toString())
                .comment(randomString(30))
                .color(colour);
    }
}

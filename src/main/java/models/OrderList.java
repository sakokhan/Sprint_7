package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class OrderList {
    private int courierId;
    private String nearestStation;
    private int limit;
    private int page;
    public OrderList(int limit, int page){
        this.limit=limit;
        this.page=page;
    }
    public OrderList(int limit, int page,String nearestStation){
        this.limit=limit;
        this.page=page;
        this.nearestStation=nearestStation;
    }
}

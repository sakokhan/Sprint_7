package Order;

import Models.Order;
import Models.OrderList;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class OrderClient {
    private static final String CREATE_ORDER = "/api/v1/orders";
    public Response createOrder(Order order) {
        return given().header("Content-type", "application/json").and().body(order).when().post(CREATE_ORDER);
    }
    public Response getOrderList(OrderList orderList) {
        return given().header("Content-type", "application/json").and().body(orderList).when().get(CREATE_ORDER);
    }
}
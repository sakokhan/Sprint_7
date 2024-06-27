import Models.OrderList;
import Order.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderListTest {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private OrderClient orderClient;

    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
        orderClient = new OrderClient();
    }
    @Step
    public Response getOrderList(OrderList orderList) {
        Response response =orderClient.getOrderList(orderList);
        return response;
    }
    @Step
    public void checkResponse(Response response){
        response.then().statusCode(SC_OK).body(notNullValue());
    }

    @Test
    @DisplayName("Список заказов")
    @Description("Получение списка заказов без указания courierId")
    public void getOrderList(){
        OrderList orderList = new OrderList();
        checkResponse(getOrderList(orderList));
    }

    @Test
    @DisplayName("Список заказов")
    @Description("10 заказов, доступных курьеру")
    public void getOrderListTenAvailable(){
        OrderList orderList = new OrderList(10,0);
        checkResponse(getOrderList(orderList));
    }
    @Test
    @DisplayName("Список заказов")
    @Description("10 заказов, доступных курьеру возле конкретной станции")
    public void getOrderListTenAvailableMetro(){
        OrderList orderList = new OrderList(10,0, "110");
        checkResponse(getOrderList(orderList));
    }
}

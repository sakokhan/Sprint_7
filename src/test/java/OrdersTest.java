import models.Order;
import order.OrderClient;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static models.OrderCreator.randomOrder;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrdersTest {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private OrderClient orderClient;
    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        orderClient = new OrderClient();
    }

    @Parameterized.Parameter
    public String [] color;

    @Parameterized.Parameters
    public static Object[][] data() {
        return new  String[][][] {
                {{"GREY"}},
                {{"GRAY"}},
                {{"GREY", "GRAY"}},
                {{}}
        };
    }
    @Test
    @DisplayName("Создание заказа")
    @Description("Параметризованный тест создания заказа")
    public void createOrder(){
        Order order = randomOrder(color);
        Response response = orderClient.createOrder(order);
        response.then().statusCode(SC_CREATED).body("track", notNullValue());
    }
}


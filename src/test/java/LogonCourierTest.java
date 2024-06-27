import Courier.CourierClient;
import Models.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static Models.CourierCreator.randomCourierAllAttributes;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class LogonCourierTest {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private CourierClient courierClient;
    int id;
    private Courier courier;
    @Before
    public void setUp(){
        RestAssured.baseURI = BASE_URL;
        courierClient = new CourierClient();
    }
    @Step
    public Courier generateCourier() {
        Courier courier = randomCourierAllAttributes();
        return courier;
    }
    @Step
    public Response makeRegistration(Courier courier) {
        Response response = courierClient.create(courier);
        return response;
    }

    @Test
    @DisplayName("Логин курьера")
    @Description("Курьер может авторизоваться")
    public void loginCourier(){
        courier = generateCourier();
        makeRegistration(courier);
        Response responseLogin = courierClient.login(courier);
        responseLogin.then().statusCode(SC_OK).and().body("id", notNullValue());
        courierClient.getId(courier);
    }
    @Test
    @DisplayName("Ошибка авторизации")
    @Description("если поля пароля нет, запрос возвращает ошибку")
    public void loginCourierNoPass(){
        courier = generateCourier();
        makeRegistration(courier);
        Courier forLogIn = courier.loginFromCourier(courier);
        Response responseLogin = courierClient.login(forLogIn);
        responseLogin.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
        courierClient.getId(courier);
    }
    @Test
    @DisplayName("Ошибка авторизации")
    @Description("если поля логина нет, запрос возвращает ошибку")
    public void loginCourierNoLogin(){
        courier = generateCourier();
        makeRegistration(courier);
        Courier forLogIn = courier.passwordFromCourier(courier);
        Response responseLogin = courierClient.login(forLogIn);
        responseLogin.then().statusCode(SC_BAD_REQUEST).body("message", equalTo("Недостаточно данных для входа"));
        courierClient.getId(courier);
    }
    @Test
    @DisplayName("Ошибка авторизации")
    @Description("Запрос с несуществующей парой логин.пароль")
    public void loginNoExistingCourier(){
        courier = generateCourier();
        Response response = courierClient.login(courier);
        response.then().statusCode(SC_NOT_FOUND).body("message", equalTo("Учетная запись не найдена"));
    }
    @After
    public void tearDown(){courierClient.delete(id);}
}

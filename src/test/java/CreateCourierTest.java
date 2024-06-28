import courier.CourierClient;
import models.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static models.CourierCreator.randomCourierAllAttributes;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTest {
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    private CourierClient courierClient;
    private int id;
    private Courier courier;

    @Before
    public void setUp() {
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
        Response response = courierClient.createCourier(courier);
        return response;
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера со случайными данными")
    public void createCourier() {
        courier = generateCourier();
        makeRegistration(courier).then().statusCode(SC_CREATED).body("ok", equalTo(true));
        id = courierClient.getId(courier);
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера, который уже имеет учетную запись")
    public void createExistingCourier() {
        courier = generateCourier();
        makeRegistration(courier);
        makeRegistration(courier)
                .then().body("message", equalTo("Этот логин уже используется"))
                .and().statusCode(SC_CONFLICT);
        id = courierClient.getId(courier);
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера без указания логина")
    public void createCourierNoLogin() {
        Courier courier1 = generateCourier();
        courier = courier.passwordFromCourier(courier1);
        Response response = makeRegistration(courier);
        response.then().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание курьера")
    @Description("Создание курьера без указания пароля")
    public void createCourierNoPass() {
        Courier courier1 = generateCourier();
        courier = courier.loginFromCourier(courier1);
        Response response = makeRegistration(courier);
        response.then().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and().statusCode(SC_BAD_REQUEST);

    }
    @After
    public void tearDown(){courierClient.deleteCourier(id);}

}

package Courier;

import Models.Courier;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
public class CourierClient {
    private static final String CREATE_ENDPOINT = "api/v1/courier";
    private static final String GET_LOGIN_ENDPOINT = "api/v1/courier/login";
    public Response create(Courier courier) {
        return given().header("Content-type", "application/json").and().body(courier).when().post(CREATE_ENDPOINT);
    }
    public Response login(Courier courier) {
        return given().header("Content-type", "application/json").and().body(courier).when().post(GET_LOGIN_ENDPOINT);
    }
    public Response delete(int id) {
        return given().when().delete(deleteEndpoint(id));
    }
    public int getId(Courier courier) {
        Response responseId = login(courier);
        int id = responseId.path("id");
        return id;
    }
    public String deleteEndpoint(int id){
        String apiUrl = "/api/v1/courier/"+id;
        return apiUrl;
    }
}

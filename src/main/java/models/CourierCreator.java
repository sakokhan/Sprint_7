package models;
import static utils.Utils.randomString;
public class CourierCreator {
    public static Courier randomCourierAllAttributes() {

        return new Courier()
                .withLogin(randomString(20))
                .withPassword(randomString(12))
                .withFirstName(randomString(20));
    }
}

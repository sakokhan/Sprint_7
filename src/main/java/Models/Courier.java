package Models;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
public class Courier {
    private String login;
    private String password;
    private String firstName;
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
   public Courier withLogin(String login){
        this.login = login;
        return this;
    }
    public Courier withPassword(String password){
        this.password = password;
        return this;
    }
    public Courier withFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }
    public static Courier loginFromCourier (Courier courier) {
        return new Courier().withLogin(courier.getLogin());
    }
    public static Courier passwordFromCourier (Courier courier) {return new Courier().withPassword(courier.getPassword());}

}

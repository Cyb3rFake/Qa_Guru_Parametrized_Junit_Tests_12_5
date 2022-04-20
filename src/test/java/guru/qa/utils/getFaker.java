package guru.qa.utils;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;



public class getFaker {
    Faker faker = new Faker();
    public getFaker() {
        System.out.println(faker.name().firstName());
    }
    public String fFirstName() {
        return faker.name().lastName();
    }
    public String fAddress() {
        return faker.address().fullAddress();
    }
    public String fEmail() {
        return faker.internet().emailAddress();
    }

    @Test
    static void getfake() {
        getFaker gf = new getFaker();
        System.out.println(gf.fAddress());
    }
}
package guru.qa;

import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQaWebParametrizedTest {

    @BeforeAll
    static void open(){
        //предусловия
        Selenide.open("https://demoqa.com/text-box");
    }
    @AfterAll
    static void close(){
        closeWebDriver();
    }
    @DisplayName("Проверка текстового поля FullName дата-провайдером @ValueSource")
    @ParameterizedTest(name = "Поле username значение {0}")
    @ValueSource(strings = {
            "Full Name 1",
            " ",
            "%$@#$!@$^&&",
            "ФВФЦываНЕГШЩД"
    })
    void fieldNameValidationTest(String testValues){
        //Шаги:
        $("#userName").setValue(testValues);
        $("#submit").click();
        $("#name").shouldHave(text(testValues));
        Selenide.sleep(500);
    }
    @DisplayName("Проверка текстового поля Email")
    @ParameterizedTest(name = "Поле userEmail значение {0}.Проверочное значение {1}")
    @CsvSource(value = {"Email1@ya.com, Email:Email1@ya.com",
                        "Email2@ya.com, Email:Email2@ya.com"
    })
    void fieldEmailValidationTest(String setMail, String testField){
        //Шаги:
        $("#userEmail").setValue(setMail);
        $("#submit").click();
        $("#email").shouldHave(text(testField));
        Selenide.sleep(500);
    }
    @ParameterizedTest(name = "Проверка поля адреса {0}")
    @MethodSource("addressValues")
    void inputAddressField(String setValue, String outValue) {
        $("#currentAddress").setValue(setValue);
        $("#submit").click();
        $("#output").$("#currentAddress").shouldHave(text(outValue));
        Selenide.sleep(500);
    }
    static Stream<org.junit.jupiter.params.provider.Arguments> addressValues() {
        return Stream.of(
                org.junit.jupiter.params.provider.
                        Arguments.of("LeenaApt. 451 305 Stephan Mill, South Ethel, OR 00694"),
                        Arguments.of("Carlton 6765 Cormier Squares, Gailport, VA 36744-0199"));

    }
}

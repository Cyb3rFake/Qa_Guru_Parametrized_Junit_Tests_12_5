package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    //Дата провайдер или аргумент сорс @ValueSource передает только одну строку
    @ValueSource(strings = {
            "Selenide",
            "JUnit"
    })

    @DisplayName("Проверка яндекса по слову {}")
    @ParameterizedTest(name ="Проверка яндекса по слову {0}")
    void yaSearchTest(String testData) {
        // предусловия :
        Selenide.open("https://ya.ru");
        // Шаги:
        //<input class="input__control input__input mini-suggest__input" tabindex="2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" aria-autocomplete="list" aria-label="Запрос" id="text" maxlength="400" name="text" role="combobox" aria-controls="suggest-list-nbpzg9gpya7">
        $("#text").setValue(testData);
        //<button class="button mini-suggest__button button_theme_search button_size_search-large i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button" type="submit"><span class="button__text">Найти</span></button>
        $("button[type='submit']").click();
        // Ожидаемы результат:
        $$(".serp-item")
                .find(Condition.text(testData))
                .shouldBe(Condition.visible);

    }
    //Дата провайдер или аргумент сорс  @CsvSource() передает две строки
    @CsvSource(value = {
            "Selenide, is an open source library for test",
            "JUnit, Support JUnit"
    },
    delimiter = '|' //ищменяет страндартный разделитьель "," на "|" или любой требуемый
    )
    @ParameterizedTest(name ="Проверка яндекса по слову {0} ожидаем результат [1]")
        void yaComplexSearchTest(String testData,String expectedResult){
        // предусловия :
        Selenide.open("https://ya.ru");
        // Шаги:
        //<input class="input__control input__input mini-suggest__input" tabindex="2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" aria-autocomplete="list" aria-label="Запрос" id="text" maxlength="400" name="text" role="combobox" aria-controls="suggest-list-nbpzg9gpya7">
        $("#text").setValue(testData);
        //<button class="button mini-suggest__button button_theme_search button_size_search-large i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button" type="submit"><span class="button__text">Найти</span></button>
        $("button[type='submit']").click();
        // Ожидаемы результат:
        $$(".serp-item")
                .find(Condition.text(expectedResult))
                .shouldBe(Condition.visible);
    }
}



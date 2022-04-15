package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.conditions.Text;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Selenide.*;

public class WebTest {

    @DisplayName("Проверка яндекса по слову Selenide")
    @Test
    void selenideSearchTest(){
        // предусловия :
        Selenide.open("https://ya.ru");
        // Шаги:
        //<input class="input__control input__input mini-suggest__input" tabindex="2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" aria-autocomplete="list" aria-label="Запрос" id="text" maxlength="400" name="text" role="combobox" aria-controls="suggest-list-nbpzg9gpya7">
        $("#text").setValue("Selenide");
        //<button class="button mini-suggest__button button_theme_search button_size_search-large i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button" type="submit"><span class="button__text">Найти</span></button>
        $("button[type='submit']").click();
        // Ожидаемы результат:
        $$(".serp-item")
                .find(Condition.text("Selenide"))
                .shouldBe(Condition.visible);
    }

    @DisplayName("Проверка яндекса по слову jUnit")
    @Test
    void jUnitSearchTest(){
        // предусловия :
        Selenide.open("https://ya.ru");
        // Шаги:
        //<input class="input__control input__input mini-suggest__input" tabindex="2" autocomplete="off" autocorrect="off" autocapitalize="off" spellcheck="false" aria-autocomplete="list" aria-label="Запрос" id="text" maxlength="400" name="text" role="combobox" aria-controls="suggest-list-nbpzg9gpya7">
        $("#text").setValue("jUnit");
        //<button class="button mini-suggest__button button_theme_search button_size_search-large i-bem button_js_inited" data-bem="{&quot;button&quot;:{}}" tabindex="-1" role="button" type="submit"><span class="button__text">Найти</span></button>
        $("button[type='submit']").click();
        // Ожидаемы результат:
        $$(".serp-item")
                .find(Condition.text("jUnit"))
                .shouldBe(Condition.visible);
    }
}



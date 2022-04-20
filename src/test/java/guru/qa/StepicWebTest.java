package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StepicWebTest {
    @BeforeAll
    static void openUrl(){

        Configuration.browserSize="1920x1080";
        Configuration.baseUrl = "https://stepic.org";
        Configuration.holdBrowserOpen=false;
    }

    @ValueSource(strings = {
            "java",
            "python"
    })
    @DisplayName("Проверка поиска https://stepic.org по слову {}")
    @ParameterizedTest(name ="Проверка поиска stepic по слову {0}")
    void yaSearchTest(String testData) {
        // предусловия :
        Selenide.open("/catalog");
        // Шаги:
        $(".search-form__input").setValue(testData);
        $(".search-form__input").pressEnter();
        // Ожидаемы результат:
        $$(".course-card__title")
                .find(text(testData))
                .shouldBe(Condition.visible);
    }

    @CsvSource(value = {
            "Математика|Объёмная подборка, содержащая курсы подготовки к ЕГЭ по математике и олимпиадам, курсы по теории вероятности, дискретной математике и линейной алгебре.",
            "Языковые курсы|Языковая, культурологическая и филологическая подборка курсов на Stepik, как для начинающих учить языки, так и для тех, кто хочет знать о них больше.",
            "Статистика и анализ данных|В этой подборке живёт цикл курсов по статистике, один из самых популярных на Stepik.",
            "Гуманитарные науки|Вас ждут курсы по философии, логике, риторике, истории и другим гуманитарным направлениям."
    },
            delimiter = '|')
    @ParameterizedTest(name ="Check catalog {0}, result [1]")
    void stepicCatalogTest(String testData) {
        // предусловия :
        Selenide.open("/catalog");
        // Шаги:
        $(byText(testData)).shouldHave(text(testData)).click();
        // Ожидаемы результат:
        $$(".catalog-block")
                .find(text(testData))
                .shouldBe(Condition.visible);
    }

    @Test()
    void yaSearchMenuItemTest() {
        Configuration.baseUrl = "/catalog";

        //Шаги:
        $(".catalog-block").$(byLinkText("/org/compscicenter")).click();
    }

    }

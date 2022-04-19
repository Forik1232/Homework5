package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Параметровизованные тесты")
public class ParametrTest {

    @ValueSource(strings = {
            "Собака",
            "Кошка"
    })

    @ParameterizedTest(name = "Проверка работы поисковика mail.ru {0}")
    void firstTest(String testData) {
        Selenide.open("https://google.com");

        $("input[title='Поиск']").setValue("mail.ru");
        $("input[title='Поиск']").pressEnter();
        $(byText("Mail.ru: почта, поиск в интернете, новости, игры")).click();
        $("#q").click();
        $("#q").setValue(testData);
        $("#q").pressEnter();
        $$("#js-kb-col-center").find(Condition.text(testData)).shouldBe(Condition.visible);

    }

    @CsvSource({
            "Cобака, Соба́ка — домашнее животное",
            "Кошка, Ко́шка — домашнее животное"
    })
    @ParameterizedTest(name = "Проверка работы поисковика mail.ru {0}, ожидаем результат: {1}")
    void firstMailTest(String testData, String expectedResult) {
        Selenide.open("https://mail.ru");

        $("#q").setValue(testData);
        $("#q").pressEnter();

        $$("#js-kb-col-center").find(Condition.text(expectedResult)).shouldBe(Condition.visible);

    }

}

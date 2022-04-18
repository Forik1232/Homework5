package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        $("body > div:nth-child(13) > div:nth-child(1) > div:nth-child(12) > " +
                "div:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > " +
                "div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > " +
                "div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > " +
                "div:nth-child(1) > a:nth-child(1) > h3:nth-child(2)").click();
        $("#q").click();
        $("#q").setValue(testData);
        $("#q").pressEnter();
        $$("#js-kb-col-center").find(Condition.text(testData)).shouldBe(Condition.visible);

    }

}

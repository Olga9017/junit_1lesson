import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.olgapopova.Blocker;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SimpleTest {

    @BeforeEach
    void setup() {
        Selenide.open("http://ya.ru");
    }

    @ValueSource(strings = {
            "Selenide", "Allure"
    })
    @ParameterizedTest(name = "В поисковой выдаче яндекса должно отображаться 10 результатов по запросу {0}")
    @Tags({
            @Tag("BLOCKER"),
            @Tag("WEB")
    })
    void searchResultsShouldBeGreaterThan10(String testData) {

        $("#text").setValue(testData);
        $("button[type='submit']").click();
        $$("li.serp-item").shouldHave(sizeGreaterThanOrEqual(10));

    }
}

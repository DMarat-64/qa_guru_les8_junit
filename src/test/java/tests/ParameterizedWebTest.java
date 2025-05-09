package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedWebTest {
    @BeforeEach
    void setUp() {
        open("https://duckduckgo.com/");
    }

    @ValueSource(strings = {
            "Idea",
            "Selector",
            "Allure"
    })

    @ParameterizedTest(name = "Для поискового запроса {0} не должен выдаваться пустой результат")
    @Tags({
            @Tag("WEB"),
            @Tag("Regress")
    })
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedUrl.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("Regress")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(expectedLink));
    }


}

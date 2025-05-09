package tests;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class JetBrainsAndAllureWebTest {

    static Stream<Arguments> githubRepoHeaderShouldBeCorrect() {
        return Stream.of(
                Arguments.of("JetBrains/ideavim", "ideavim"),
                Arguments.of("allure-framework/allure2", "allure2")
        );
    }

    @MethodSource()
    @ParameterizedTest(name = "Для репозитория {0} должен отображаться заголовок {1}")
    @Tag("WEB")
    void githubRepoHeaderShouldBeCorrect(String repo, String expectedTitle) {
        open("https://github.com/" + repo);
        $("strong.mr-2").shouldHave(text(expectedTitle));
    }
}

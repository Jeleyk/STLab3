package ru.jeleyka.testing.lab3;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;
import static ru.jeleyka.testing.lab3.Util.*;

public class MainTest extends BaseTest {

    @Test
    void testTitle() {
        test(driver -> {
            assertTrue(driver.getTitle().contains("World of Tanks"));
        });
    }

    @Test
    void testLogin() {
        test(driver -> assertDoesNotThrow(() -> login(driver)));
    }

    @Test
    void testCorrectLoginUsername() {
        test(driver -> {
            login(driver);

            WebElement currentNicknameField = element(driver, By.xpath("//*[@id=\"common_menu\"]/div/div[1]/div[1]/a[2]/span[3]"));
            assertEquals(currentNicknameField.getText(), Constant.USERNAME);
        });
    }

    @Test
    void testPremiumShop() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/section[2]/div[2]/a[2]"));
            premiumShopButton.click();

           waitLoad(driver);

           assertTrue(driver.getCurrentUrl().contains("https://eu.wargaming.net/shop/wot/main/"));
        });
    }

    @Test
    void testContentPage() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/div[5]/div[2]/div/div/ul/li[2]/a"));
            premiumShopButton.click();

           waitLoad(driver);

           assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/content/guide/general/common-sandbox-supertests/"));

           WebElement text = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div/div/article/div[3]/div[2]/div[1]/div/div[1]/div[2]/p"));

           assertTrue(text.getText().contains("Это тестирование следующей официальной версии игры, прежде чем она появится на основных серверах. К этому моменту мы уже практически уверены в своих решениях и хотим узнать о ваших впечатлениях, а также выявить критичные ошибки."));


            WebElement otherCategoryButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div/div/article/div[3]/div[1]/div[3]/ul/li[2]"));
            otherCategoryButton.click();


            WebElement text2 = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div[1]/div/div/div/article/div[3]/div[2]/div[2]/div/div[1]/div[2]/p"));

            assertTrue(text2.getText().contains("Это масштабный тест новых или изменённых ключевых механик, которые затрагивают все или большинство аспектов игры. Мы пытаемся тестировать наиболее сложные механики в условиях, максимально приближенных к реальным. Во время тестирования мы проводим важные опросы и собираем данные о поведении игроков. Это помогает определить, какие механики требуют доработки, какие нужно убрать, а в какие следует что-то добавить. На этом этапе мы ещё не уверены, будем ли вводить эти изменения в игру. Мы очень благодарны за любые отзывы и обычно вознаграждаем игроков за участие."));

        });
    }

    @Test
    void testNewsPage1() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/section[3]/div/div[2]/article[3]/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/news/general-news/wot-monthly-june-2024/"));
        });
    }

    @Test
    void testTMNTPage() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/div[2]/div[2]/div[1]/ul/li[4]/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/tmnt-collections/"));
        });
    }

    @Test
    void testMapGuide() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/div[3]/div[2]/div/div/ul/li[4]/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/content/guide/map-guides/"));
        });
    }

    @Test
    void testTwitchDropsGuide() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/div[5]/div[1]/div/div/ul/li[2]/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/content/guide/twitch-drops/"));
        });
    }

    @Test
    void testNews1() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/section[3]/div/div[1]/article/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/news/specials/the-boys-offers-may-2024/"));
        });
    }

    @Test
    void testNews2() {
        test(driver -> {
            WebElement premiumShopButton = element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/section[3]/div/div[2]/article[1]/a"));
            premiumShopButton.click();

            waitLoad(driver);

            assertTrue(driver.getCurrentUrl().contains("https://worldoftanks.eu/ru/news/merchandise/d-day-merch-collection-2024/"));
        });
    }

    private void login(WebDriver driver) {
        WebElement goToEnterPageButton = element(driver, By.xpath("//*[@id=\"common_menu\"]/div/div[1]/div[1]/a[1]"));
        goToEnterPageButton.click();
        waitLoad(driver);
        WebElement loginField = element(driver, By.xpath("//*[@id=\"id_login\"]"));
        WebElement passwordField = element(driver, By.xpath("//*[@id=\"id_password\"]"));

        loginField.sendKeys(Constant.LOGIN);
        passwordField.sendKeys(Constant.PASSWORD);

        passwordField.submit();

        waitLoad(driver);
    }

}

package ru.jeleyka.testing.lab3;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BaseTest {
    private static List<WebDriver> drivers;

    @BeforeAll
    static void before() {
        drivers = Lists.newArrayList(
//                Util.driver("webdriver.chrome.driver", "drivers/chromedriver.exe", ChromeDriver::new)
                Util.driver("webdriver.gecko.driver", "drivers/geckodriver.exe", () -> new FirefoxDriver(
                        new FirefoxOptions() {{
                            setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
                        }}
                ))
        );
    }

    @AfterAll
    static void after() {
        for (WebDriver driver : drivers) {
            driver.quit();
        }
    }

    @BeforeEach
    void beforeEach() {
        for (WebDriver driver : drivers) {
            driver.navigate().to(Constant.URL);
            try {
                Util.element(driver, By.xpath("/html/body/div[1]/div/div[4]/div/section[1]/div[3]/button[2]"))
                        .click();
            } catch (Exception ignored) {
            }
        }
    }


    protected static void test(Consumer<WebDriver> driverTester) {
        drivers.forEach(driverTester);
    }

}

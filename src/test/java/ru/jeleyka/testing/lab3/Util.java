package ru.jeleyka.testing.lab3;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Util {
    protected static WebElement element(WebDriver driver, By by) {
        return wait(driver, Duration.ofSeconds(10),
                ExpectedConditions.elementToBeClickable(by));
    }

    protected static void waitLoad(WebDriver driver) {
        wait(driver, Duration.ofSeconds(5), webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    protected static <V> V wait(WebDriver driver, Duration timeout, Function<? super WebDriver, V> isTrue) {
        return new WebDriverWait(driver, timeout).until(isTrue);
    }

    protected static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected static WebDriver driver(String property, String path, Supplier<WebDriver> getter) {
        System.setProperty(property, path);
        return getter.get();
    }
}

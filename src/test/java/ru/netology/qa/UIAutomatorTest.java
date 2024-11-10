package ru.netology.qa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import ru.netology.qa.screens.MainScreenUIAutomator;

public class UIAutomatorTest {

    private AndroidDriver driver;

    private URL getUrl() {
        try {
            return new URL("http://127.0.0.1:4723");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @BeforeEach
    public void setUp() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "meduimPhone");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);


        driver = new AndroidDriver(getUrl(), desiredCapabilities);
    }

    @Test
    public void testEmptyString() {
        MainScreenUIAutomator main = new MainScreenUIAutomator(driver);

        main.userInput.isDisplayed();
        main.userInput.click();
        String text1 = main.textToBeChanged.getText();
        main.userInput.sendKeys(" ");
        main.buttonChange.isDisplayed();
        main.buttonChange.click();
        String text2 = main.textToBeChanged.getText();
        Assertions.assertEquals(text1, text2);

    }

    @Test
    public void testNewActivity() {
        MainScreenUIAutomator main = new MainScreenUIAutomator(driver);

        main.userInput.isDisplayed();
        main.userInput.click();
        String newText = "TuToK";
        main.userInput.sendKeys((newText));
        main.buttonActivity.isDisplayed();
        main.buttonActivity.click();
        main.activityText.isDisplayed();
        Assertions.assertEquals(main.activityText.getText(), newText);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
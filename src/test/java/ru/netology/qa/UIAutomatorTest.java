package ru.netology.qa;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreenUIAutomator;

import java.net.MalformedURLException;
import java.net.URL;


public class homework {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

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
        Assertions.assertEquals(text1,text2);

    }

    @Test
    public void testNewActivity() {
        MainScreenUIAutomator main = new MainScreenUIAutomator(driver);

        main.userInput.isDisplayed();
        main.userInput.click();
        String newText = "New text";
        main.userInput.sendKeys((newText));
        main.buttonActivity.isDisplayed();
        main.buttonActivity.click();
        main.activityText.isDisplayed();
        Assertions.assertEquals(main.activityText.getText(),newText);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
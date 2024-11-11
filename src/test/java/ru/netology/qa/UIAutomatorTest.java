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
    private MainScreenUIAutomator mainScreenUIAutomator;

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

        mainScreenUIAutomator = new MainScreenUIAutomator(driver);
    }

    @Test
    public void testEmptyString() {

        mainScreenUIAutomator.userInput.isDisplayed();
        mainScreenUIAutomator.userInput.click();
        String text1 = mainScreenUIAutomator.textToBeChanged.getText();
        mainScreenUIAutomator.userInput.sendKeys(" ");
        mainScreenUIAutomator.buttonChange.isDisplayed();
        mainScreenUIAutomator.buttonChange.click();
        String text2 = mainScreenUIAutomator.textToBeChanged.getText();
        Assertions.assertEquals(text1, text2);

    }

    @Test
    public void testNewActivity() {

        mainScreenUIAutomator.userInput.isDisplayed();
        mainScreenUIAutomator.userInput.click();
        String newText = "TuToK";
        mainScreenUIAutomator.userInput.sendKeys((newText));
        mainScreenUIAutomator.buttonActivity.isDisplayed();
        mainScreenUIAutomator.buttonActivity.click();
        mainScreenUIAutomator.activityText.isDisplayed();
        Assertions.assertEquals(mainScreenUIAutomator.activityText.getText(), newText);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
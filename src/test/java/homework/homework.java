package homework;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

@Nested
class homework {
    public String newText = "TuToK";

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
    public void sampleTest() throws InterruptedException {
        var initialText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        MobileElement el2 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.isDisplayed();
        el2.click();
        Thread.sleep(2000);
        MobileElement el4 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        Assertions.assertEquals(el4.getText(), initialText);
    }


    @Test
    public void testNewTab() throws InterruptedException {
        MobileElement el1 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(newText);
        MobileElement el3 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el3.isDisplayed();
        el3.click();
        Thread.sleep(5000);
        MobileElement el4 = (MobileElement) driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        var result = el4.getText();
        Assertions.assertEquals(result, newText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
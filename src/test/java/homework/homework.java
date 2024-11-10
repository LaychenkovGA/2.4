package homework;

import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class homework {
    public String newText = "lyalya";

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "MeduimPhone");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void emptyField() {
        var initialText = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged").getText();
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        var el2 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonChange");
        el2.click();
        var el4 = driver.findElementById("ru.netology.testing.uiautomator:id/textToBeChanged");
        Assertions.assertEquals(el4.getText(), initialText);
    }

    @Test
    public void testNewTab() throws InterruptedException {
        var el1 = driver.findElementById("ru.netology.testing.uiautomator:id/userInput");
        el1.click();
        el1.sendKeys(newText);
        var el3 = driver.findElementById("ru.netology.testing.uiautomator:id/buttonActivity");
        el3.click();
        Thread.sleep(5000);
        var el4 = driver.findElementById("ru.netology.testing.uiautomator:id/text");
        var result = el4.getText();
        Assertions.assertEquals(result, newText);

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
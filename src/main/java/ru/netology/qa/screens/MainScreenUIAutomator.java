package ru.netology.qa.screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;


public class MainScreenUIAutomator {

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public MobileElement userInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public MobileElement buttonChange;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public MobileElement textToBeChanged;

    @AndroidFindBy(id = "buttonActivity")
    public MobileElement buttonActivity;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.TextView")
    public  MobileElement activityText;

    private AppiumDriver driver;

    public MainScreenUIAutomator(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(15)), this);
    }

}
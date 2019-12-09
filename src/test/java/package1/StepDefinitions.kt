package package1;

import io.appium.java_client.AppiumDriver;
//import io.cucumber.java8.En;
import io.cucumber.java.Before;
//import cucumber.api.java.en.Given;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class StepDefinitions {

    AppiumDriver driver;
    AutomationTools autoTools;

    @Before
    public void before() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        URL appiumURL = null;
        try {
            appiumURL = new URL("http://127.0.0.1:" + 5010 + "/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        capabilities.setCapability("appPackage", "com.josh.automationtestapp");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("appActivity", "MainActivity");
        //capabilities.setCapability("platformVersion", 10);
        capabilities.setCapability("platformName", "Android");

        driver = new AppiumDriver(appiumURL, capabilities);
        autoTools = new AutomationTools(driver, AutomationTools.OperatingSystem.ANDROID);

    }

    @Given("there has been a pebkac")
    public void there_has_been_a_pebkac() {
        //driver.findElement(By.id("editTextFieldId")).sendKeys("test");
        autoTools.getElement(new String[]{"editTextFieldId", "IOSIDHERE"}, AutomationTools.IdType.DEFAULT, 15).sendKeys("test");
        System.out.format("test: %n\n");

        //throw new cucumber.api.PendingException();
    }

}

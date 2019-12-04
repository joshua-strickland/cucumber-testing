package package1;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class StepDefinitions implements En {

    AppiumDriver driver;

    public StepDefinitions() {
        Before(() -> {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            URL appiumURL = new URL("http://127.0.0.1:" + 5010 + "/wd/hub");

            capabilities.setCapability("appPackage", "com.josh.automationtestapp");
            capabilities.setCapability("deviceName", "Android");
            capabilities.setCapability("appActivity", "MainActivity");
            //capabilities.setCapability("platformVersion", 10);
            capabilities.setCapability("platformName", "Android");

             driver =  new AppiumDriver(appiumURL, capabilities);

        });

        Given("there has been a pebkac", () -> {
            driver.findElement(By.id("editTextFieldId")).sendKeys("test");
            System.out.format("test: %n\n");

            //throw new cucumber.api.PendingException();
        });

        /*
        Given("there has been a pebkac", () -> {

            driver.findElement(By.id("editTextFieldId")).sendKeys("test");
            System.out.format("test: %n\n");

            //throw new cucumber.api.PendingException();

        });

         */
    }
}
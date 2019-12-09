package package1

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.MalformedURLException
import java.net.URL

//import io.cucumber.java8.En;
//import cucumber.api.java.en.Given;
class StepDefinitions {
    var driver: AppiumDriver<MobileElement>? = null
    var autoTools: AutomationTools? = null
    @Before
    fun before() {
        val capabilities = DesiredCapabilities()
        var appiumURL: URL? = null
        try {
            appiumURL = URL("http://127.0.0.1:" + 5010 + "/wd/hub")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        capabilities.setCapability("appPackage", "com.josh.automationtestapp")
        capabilities.setCapability("deviceName", "Android")
        capabilities.setCapability("appActivity", "MainActivity")
        //capabilities.setCapability("platformVersion", 10);
        capabilities.setCapability("platformName", "Android")
        driver = AppiumDriver(appiumURL, capabilities)
        autoTools = AutomationTools(driver as AppiumDriver, AutomationTools.OperatingSystem.ANDROID)
    }

    @Given("there has been a pebkac")
    fun there_has_been_a_pebkac() { //driver.findElement(By.id("editTextFieldId")).sendKeys("test");
        autoTools!!.getElement(arrayOf("editTextFieldId", "IOSIDHERE"))!!.sendKeys("test")
        System.out.format("test: %n\n")
        //throw new cucumber.api.PendingException();
    }
}
package package1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = "pretty", features = "src/test/resources/package1", glue = "src/test/java/package1")
public class Runner extends AbstractTestNGCucumberTests {




}

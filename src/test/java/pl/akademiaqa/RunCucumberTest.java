package pl.akademiaqa;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html", "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"},
        glue = {"pl.akademiaqa"},
        features = "src/test/resources/pl.akademiaqa",
        snippets = CucumberOptions.SnippetType.UNDERSCORE)
public class RunCucumberTest {
}

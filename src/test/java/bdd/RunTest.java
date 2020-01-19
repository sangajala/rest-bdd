package bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/eurostar-html-report", "json:target/eurostar_report.json"},
        tags = {"@cm"}
)
public class RunTest {

}

package uk.gov.companieshouse.taf.acceptancetests;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        glue = { "io.magentys.cinnamon", "uk.gov.companieshouse.taf" },
        features = "src/test/resources/features",
        plugin = { "pretty", "json:target/cucumber-reports/cucumber.json" },
        tags = { "@single,@compatible"}
)
public class AllTests {}

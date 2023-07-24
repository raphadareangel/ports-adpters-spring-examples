package com.zara.zara5.steps;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = "com.zara.zara5.steps",
    plugin = {"pretty", "html:target/cucumber-reports"}
)
public class IntegrationTest {
}

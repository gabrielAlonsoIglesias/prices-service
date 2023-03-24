package com.inditex.prices.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = { "pretty" },
    features = "src/test/resources/features",
    glue = "com.inditex.prices.steps")
public class PriceRunnerIT {

}

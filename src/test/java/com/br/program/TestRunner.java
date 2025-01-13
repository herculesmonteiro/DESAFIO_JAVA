package com.br.program;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.br.program",
    plugin = {"pretty", "html:target/cucumber-reports"},
    monochrome = true,
    tags = "@web_tables"
)
public class TestRunner {
    // Esta classe permanece vazia e serve apenas como ponto de entrada para os testes Cucumber
}

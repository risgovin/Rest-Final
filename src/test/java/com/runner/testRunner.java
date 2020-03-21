package com.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/com/features/Reqres.feature",
				glue="com/stepdefn",
				plugin={"pretty", 
						"html:target/cucumber-reports",
						"junit:target/cucumber-reports/Cucumber.xml"})
public class testRunner extends AbstractTestNGCucumberTests{
}


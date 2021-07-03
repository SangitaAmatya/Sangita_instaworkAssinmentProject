package com.instawork.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(features = {"src/test/java/com/instawork/features"} , plugin = {"json:target/cucumber.json", "pretty"},
        glue = "com.instawork.hooks")
public class TestRunner extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider
    //@DataProvider (parallel = true) -- For parallel execution support (which is not going to work for our code)
    public Object[][] scenarios() {
        return super.scenarios();
    }

}

package com.instawork.hooks;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.instatwork.base.BaseUtil;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hook extends BaseUtil {

	private BaseUtil base;

	public Hook(BaseUtil base) {
		this.base = base;
	}

	@Before
	public void InitializeTest(Scenario scenario) {
		base.scenarioDef = base.features.createNode(scenario.getName());
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		base.Driver = new ChromeDriver(chromeOptions);
	}

	@After
	public void TearDownTest(Scenario scenario) {
		if (scenario.isFailed()) {
			// Take screenshot logic goes here
			System.out.println(scenario.getName());
		}
		System.out.println("Closing the browser : MOCK");
		base.Driver.quit();
	}

	@BeforeStep
	public void BeforeEveryStep(Scenario scenario) {
		System.out.println("Before every step " + scenario.getId());
	}

	@AfterStep
	public void AfterEveryStep(Scenario scenario)
			throws NoSuchFieldException, IllegalAccessException, InterruptedException {

		// System.out.println("Before every step " + stepTestStep.getId());
	}

}

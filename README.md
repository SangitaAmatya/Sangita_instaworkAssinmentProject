# Sangita_instaworkAssinmentProject
Instawork_Assignment
Language : Java
DesignPatten : Page object model framework
Implement:BDD with Cucumber
How to Run the Scripts: Open the terminal Go to the project path where your project is downlode
 Run This command in command prompt:mvn clean install -Dtestng.dtd.http=true
Pom.xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>groupId</groupId>
    <artifactId>InstaWorkAssignment</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>10</maven.compiler.source>
        <maven.compiler.target>10</maven.compiler.target>
        <webdrivermanager.version>4.3.1</webdrivermanager.version>
    </properties>
    <build>
        <plugins>
            <plugin>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.7.0</version>
                <configuration>
                    <source>10</source>
                    <target>10</target>
                </configuration>
            </plugin>
            <plugin>
               <groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-plugin</artifactId>
				<version>2.22.2</version>
				<configuration>
                    <suiteXmlFiles>
                        <suiteXmlFile>src/testorbitz.xml</suiteXmlFile>
                    </suiteXmlFiles>
				</configuration>
            </plugin>
            <plugin>
                <groupId>net.masterthought</groupId>
                <artifactId>maven-cucumber-reporting</artifactId>
                <version>3.0.0</version>
                <executions>
                    <execution>
                        <id>execution</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>generate</goal>
                        </goals>
                        <configuration>
                            <projectName>ExecuteAutomation</projectName>
                            <outputDirectory>${project.build.directory}/cucumber-report-html</outputDirectory>
                            <cucumberOutput>${project.build.directory}/cucumber.json</cucumberOutput>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>

    <dependencies>


        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-java -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>6.9.1</version>
        </dependency>


        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-testng -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>6.9.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-testng -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>6.9.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-picocontainer -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-picocontainer</artifactId>
            <version>6.9.1</version>
            <scope>test</scope>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.picocontainer/picocontainer -->
        <dependency>
            <groupId>org.picocontainer</groupId>
            <artifactId>picocontainer</artifactId>
            <version>2.15</version>
        </dependency>



        <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-core -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-core</artifactId>
            <version>6.9.1</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>4.0.0-beta-2</version>
        </dependency>



        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver -->
        <dependency>
            <groupId>io.github.bonigarcia</groupId>
            <artifactId>webdrivermanager</artifactId>
            <version>${webdrivermanager.version}</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/net.masterthought/cucumber-reporting -->
        <dependency>
            <groupId>net.masterthought</groupId>
            <artifactId>cucumber-reporting</artifactId>
            <version>5.5.2</version>
        </dependency>


        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.0.7</version>
        </dependency>
    </dependencies>
</project>


Baseutil:
package com.instatwork.base;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;


public class BaseUtil {

    public static WebDriver Driver;

    public ExtentReports extent;

    public static ExtentTest scenarioDef;

    public static ExtentTest features;

    public static String reportLocation = "/Users/Sangita/extent";

}
package com.instatwork.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class ExtentReportUtil extends BaseUtil {

    String fileName = reportLocation + "extentreport.html";


    public void ExtentReport() {
        //First is to create Extent Reports
        extent = new ExtentReports();

        ExtentSparkReporter spark = new ExtentSparkReporter(fileName);

        extent.attachReporter(spark);

    }

    public void ExtentReportScreenshot() throws IOException {

        var scr = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
        Files.copy(scr.toPath(), new File(reportLocation + "screenshot.png").toPath());
        scenarioDef.fail("details").addScreenCaptureFromPath(reportLocation + "screenshot.png");
    }


    public void FlushReport(){
        extent.flush();
    }




}
Feature: FlightFeature

	Scenario: Search Flight between cities
    Given I navigate to the Home page
    And I click on Flight page
    And I enter the  Leaving from  And going to
      | Leavig_from | Going_to      |
      | San Francisco | New York |    
    And I Enter Departing and Returndate
    And I click the search button
    Then I should see the search results
	
	
    Scenario: Book Flight between cities
    Given I navigate to the Home page
    And I click on Flight page
    And I enter the  Leaving from  And going to
      | Leavig_from | Going_to      |
      | SFO | JFK |    
    And I Enter Departig and Returndate
    And I click the search button
    And I click the Non Stop Flight
    And I sort the price from high to low 
    And I select the first flight and click continue
    And I click the Non Stop Flight
    And I sort the price from high to low 
    And I select the first flight and click continue
    Then I should see the proper flights in review page
    
    
    
  package com.instawork.hooks;

import com.aventstack.extentreports.GherkinKeyword;
import com.instatwork.base.BaseUtil;
import com.instawork.pages.Flights;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class Homepage extends BaseUtil {

	private BaseUtil base;

	public Homepage(BaseUtil base) {
		this.base = base;
	}

	@Given("I navigate to the Home page")
	public void i_navigate_to_the_home_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("navigate to the Home page");
		base.Driver.navigate().to("https://www.orbitz.com/");
		Thread.sleep(2000l);

	}

	@And("I click on Flight page")
	public void i_click_on_flight_page() throws Throwable {
		base.scenarioDef.createNode(new GherkinKeyword("And"), "I click on Flight page");
		Flights page = new Flights(base.Driver);
		page.ClickFlight();
		Thread.sleep(2000l);

	}

	@And("I enter the  Leaving from  And going to")
	public void i_enter_the_leaving_from_and_going_to(io.cucumber.datatable.DataTable dataTable) throws Throwable {

		Flights page = new Flights(base.Driver);
		page.enterFromCity("SFO");
		page.enterToCity("JFK");

		Thread.sleep(1000l);
		System.out.println("inside I enter the  Leaving from  And going to ");

	}

	@And("I Enter Departing and Returndate")
	public void i_enter_departing_and_returndate() throws Throwable {
		Flights page = new Flights(base.Driver);
		page.enterDepartDate(base.Driver);
		page.enterReturnDate(base.Driver);
		// Write code here that turns the phrase above into concrete actions
		System.out.println("inside I Enter Departing and Returndate ");

	}

	@And("I click the search button")
	public void i_click_the_search_button() throws Throwable {
		Flights page = new Flights(base.Driver);

		page.clickSearch();
		// Write code here that turns the phrase above into concrete actions
		System.out.println("I click the search button");
	}

	@Then("I should see the search results")
	public void i_should_see_the_search_results() throws Throwable {
		Flights page = new Flights(base.Driver);
		page.verifyResults(base.Driver);
		System.out.println("I should see the search results");
	}

	@And("I click the Non Stop Flight")
	public void i_click_the_Non_Stop_Flight() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.clickNonStopFlights(base.Driver);
		System.out.println("I click the Non Stop Flight");
	}

	@And("I sort the price from high to low")
	public void i_sort_the_price_from_high_to_low() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.sortFromHighToLow(base.Driver);
		System.out.println("I sort the price from high to low");

	}

	@And("I select the first flight and click continue")
	public void i_select_the_first_flight_and_click_continue() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.selectFlight(base.Driver);
		System.out.println("I select the first flight and click continue");

	}

	@Then("I should see the proper flights in review page")
	public void i_should_see_the_proper_flights_in_review_page() throws Throwable {

		Flights page = new Flights(base.Driver);
		page.reviewResults(base.Driver);
		System.out.println("I should see the proper flights in review page");

	}

}
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
package com.instawork.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Flights {

	public Flights(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(how = How.XPATH, using = "//div[@class=\"uitk-text uitk-type-300\" and @data-test-id=\"stops-0-label\"]")
	public WebElement nonStopflights;

	@FindBy(how = How.XPATH, using = "//span[contains(text(), \"Flights\")]")
	public WebElement flights;

	@FindBy(how = How.XPATH, using = "//button[@aria-label=\"Leaving from\"]")
	public WebElement fromCity;

	@FindBy(how = How.XPATH, using = "//button[@aria-label=\"Going to\"]")
	public WebElement toCity;

	@FindBy(how = How.XPATH, using = "//ul[@class=\"uitk-typeahead-results no-bullet\"]")
	public WebElement fromCitySelect;

	@FindBy(how = How.XPATH, using = "//ul[@data-stid=\"location-field-leg1-destination-results\"]")
	public WebElement toCitySelect;

	@FindBy(how = How.XPATH, using = "//button[@data-testid=\"submit-button\"]")
	public WebElement search;

	@FindBy(how = How.XPATH, using = "//button[@data-testid=\"apply-date-picker\"]")
	public WebElement done;

	@FindBy(how = How.XPATH, using = "//input[@id=\"d1\"]")
	public WebElement fromDate;

	@FindBy(how = How.XPATH, using = "//button[@id=\"d1-btn\"]")
	public WebElement fromDateString;

	@FindBy(how = How.XPATH, using = "//button[@id=\"d2-btn\"]")
	public WebElement toDateString;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app-layer-datepicker-departing-returning-start\"]/div[2]/div/div/div[2]/div")
	List<WebElement> elements;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app-layer-datepicker-flights-departure-arrival-start\"]/div[2]/div/div/div[2]/div/div[1]/table")
	List<WebElement> days;

	public void enterFromCity(String from) throws Throwable {
		fromCity.sendKeys(from);
		Thread.sleep(5000l);
		List<WebElement> countriesList = fromCitySelect.findElements(By.tagName("li"));
		for (WebElement li : countriesList) {
			if (li.getText().contains("SFO")) {
				li.click();
			}
		}
	}

	public void enterToCity(String from) throws Throwable {

		toCity.sendKeys("JFK");
		Thread.sleep(5000l);

		System.out.println(toCitySelect.getText());
		List<WebElement> toCitiesList = toCitySelect.findElements(By.tagName("li"));
		for (WebElement li : toCitiesList) {
			if (li.getText().contains("JFK")) {
				li.click();
			}
		}
		Thread.sleep(5000l);

	}

	public void enterDepartDate(WebDriver Driver) throws Throwable {

		DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMMM yyyy");
		LocalDateTime now = LocalDateTime.now();
		String date = now.plusDays(14).format(ft);
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];
		if (day.startsWith("0")) {
			day = day.replace("0", "");
		}
		fromDateString.click();
		selectDate(Driver, month_year, day);
		Thread.sleep(2000);
		WebElement Done = Driver.findElement(By.xpath(
				"//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[2]/div/div/div[1]/div/div[2]/div/div[3]/button"));
		Done.click();

	}

	public void enterReturnDate(WebDriver Driver) throws Throwable {
		Thread.sleep(2000);

		DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMMM yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("enterReturnDate" + now.plusDays(21).format(ft));
		String date = now.plusDays(21).format(ft);
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];
		if (day.startsWith("0")) {
			day = day.replace("0", "");
		}
		toDateString.click();
		selectDate(Driver, month_year, day);
		Thread.sleep(2000);
		WebElement Done = Driver.findElement(By.xpath(
				"//*[@id=\"wizard-flight-tab-roundtrip\"]/div[2]/div[2]/div/div/div[2]/div/div[2]/div/div[3]/button"));
		Done.click();

	}

	public void clickSearch() throws Throwable {
		Thread.sleep(6000l);

		search.click();
		Thread.sleep(6000l);

	}

	public void ClickFlight() {
		flights.click();
	}

	public void selectDate(WebDriver Driver, String month_year, String select_day) throws InterruptedException {
		Thread.sleep(5000);

		List<WebElement> elements1 = Driver
				.findElements(By.xpath("//*[@class=\"uitk-date-picker-menu-months-container\"]/div"));

		for (int i = 0; i < elements1.size(); i++) {

			if (elements1.get(i).getText().split("\n")[0].equals(month_year)) {
				List<WebElement> days1 = Driver
						.findElements(By.xpath("//*[@class=\"uitk-date-picker-weeks\"]/tbody/tr/td/button"));

				for (WebElement d : days1) {
					if (d.getAttribute("aria-label").contains(month_year.substring(0, 3) + " " + select_day)) {
						d.click();
					}
				}

			}

		}

	}

	public void verifyResults(WebDriver driver) throws InterruptedException {
		List<WebElement> srcToDst = driver.findElements(By.xpath(
				"//ul[@class=\"uitk-typelist uitk-typelist-bullet-none uitk-typelist-size-1\"]/li/div/div/div/button"));
		for (WebElement res : srcToDst) {
			res.click();
			Thread.sleep(1000l);

			WebElement srcDstelmt = driver.findElement(By.xpath(
					"//*[@id=\"app-layer-base\"]/div[2]/div[3]/div[1]/section/main/div[6]/section/div[2]/div/div[1]/h2"));
			WebElement departureDate = driver.findElement(By.xpath(
					"//*[@id=\"app-layer-base\"]/div[2]/div[3]/div[1]/section/main/div[6]/section/div[2]/div/div[1]/div/div/div[2]"));
			DateTimeFormatter ft = DateTimeFormatter.ofPattern("MMM dd");
			LocalDateTime now = LocalDateTime.now();
			String date = now.plusDays(14).format(ft);

			Assert.assertEquals("San Francisco to New York", srcDstelmt.getText());
			Assert.assertEquals(date, departureDate.getText().split(",")[1].trim());

			WebElement closeButton = driver.findElement(By.xpath(
					"//button[@class=\"uitk-flex-item uitk-flex-shrink-0 uitk-toolbar-button\" and @data-icon=\"tool-close\"]"));

			closeButton.click();
		}
		Thread.sleep(1000);
		srcToDst.get(0).click();
		Thread.sleep(3000);
		WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), \"Continue\")]"));
		continueButton.click();
		Thread.sleep(5000);
		List<WebElement> dstToSrc = driver.findElements(By.xpath(
				"//ul[@class=\"uitk-typelist uitk-typelist-bullet-none uitk-typelist-size-1\"]/li/div/div/div/button"));
		for (WebElement res : dstToSrc) {
			res.click();
			Thread.sleep(1000l);
			WebElement dstSrcelmt = driver.findElement(By.xpath(
					"//div[@class=\"uitk-card-content-section uitk-card-content-section-padded uitk-spacing uitk-spacing-padding-blockstart-six uitk-spacing-padding-blockend-three uitk-spacing-padding-inline-six\" and @data-test-id=\"flight-review-header\"]/h2"));
			WebElement departureDate = driver.findElement(By.xpath(
					"//div[@class=\"uitk-text uitk-text-default-theme\" and @data-test-id=\"flight-operated\"]"));
			DateTimeFormatter ft = DateTimeFormatter.ofPattern("MMM dd");
			LocalDateTime now = LocalDateTime.now();
			String date = now.plusDays(21).format(ft);
			Assert.assertEquals("New York to San Francisco", dstSrcelmt.getText());
			Assert.assertEquals(date, departureDate.getText().split(",")[1].trim());

			WebElement closeButton = driver.findElement(By.xpath(
					"//button[@class=\"uitk-flex-item uitk-flex-shrink-0 uitk-toolbar-button\" and @data-icon=\"tool-close\"]"));

			closeButton.click();

		}

	}

	public void clickNonStopFlights(WebDriver driver) throws Throwable {
		// TODO Auto-generated method stub
		nonStopflights.click();
		Thread.sleep(3000l);
//		Select se = new Select(driver.findElement(By.xpath("//select[@id='listings-sort']")));
//		se.selectByVisibleText("Price (Highest)");
//		Thread.sleep(15000);

	}

	public void sortFromHighToLow(WebDriver driver) throws Throwable {
		Select se = new Select(driver.findElement(By.xpath("//select[@id='listings-sort']")));
		se.selectByVisibleText("Price (Highest)");
		Thread.sleep(15000);

	}

	public void selectFlight(WebDriver driver) throws Throwable {
		List<WebElement> srcToDst = driver.findElements(By.xpath(
				"//ul[@class=\"uitk-typelist uitk-typelist-bullet-none uitk-typelist-size-1\"]/li/div/div/div/button"));
		srcToDst.get(0).click();
		Thread.sleep(3000);
		WebElement continueButton = driver.findElement(By.xpath("//button[contains(text(), \"Continue\")]"));
		continueButton.click();
		Thread.sleep(15000);

	}

	public void reviewResults(WebDriver driver) {
		List<WebElement> srcToDstList = driver.findElements(By.xpath(
				"//div[@class=\"uitk-card-content-section uitk-card-content-section-padded uitk-spacing uitk-spacing-padding-blockstart-six uitk-spacing-padding-blockend-three uitk-spacing-padding-inline-six\"]/h2"));

		Assert.assertEquals("New York to San Francisco", srcToDstList.get(0).getText());
		List<WebElement> nonStopList = driver.findElements(
				By.xpath("//span[@class=\"uitk-text uitk-type-300 uitk-flex-item uitk-text-emphasis-theme\"]"));
		Assert.assertEquals("Nonstop)", srcToDstList.get(0).getText());

	}

}
package com.instawork.runner;

import com.instatwork.base.ExtentReportUtil;
import com.aventstack.extentreports.gherkin.model.Feature;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.instatwork.base.BaseUtil.features;


public class NGTestListener implements ITestListener {

    ExtentReportUtil extentReportUtil = new ExtentReportUtil();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("On test start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

        System.out.println("On test Sucess");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("On test failure");

//        try{
//            extentReportUtil.ExtentReportScreenshot();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On test skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("On test percentage");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("On start");

        extentReportUtil.ExtentReport();

        //ToDo: Feature - Hard coding the feature name
        features = extentReportUtil.extent.createTest(Feature.class, "LoginFeature");

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("On finish");
        //extentReportUtil.FlushReport();
    }
}
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


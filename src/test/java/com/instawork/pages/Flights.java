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

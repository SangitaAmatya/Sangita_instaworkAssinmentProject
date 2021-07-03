package com.instawork.pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Flights {

	public Flights(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

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

	@FindBy(how = How.XPATH, using = "//input[@id=\"d1\"]")
	public WebElement fromDate;

	@FindBy(how = How.XPATH, using = "//button[@id=\"d1-btn\"]")
	public WebElement fromDateString;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app-layer-datepicker-departing-returning-start\"]/div[2]/div/div/div[2]/div")
	List<WebElement> elements;
	// *[@id="app-layer-datepicker-departing-returning-start"]/div[2]/div/div/div[2]/div
	// *[@id="app-layer-datepicker-departing-returning-start"]/div[2]/div/div/div[2]

	@FindBy(how = How.XPATH, using = "//*[@id=\"app-layer-datepicker-flights-departure-arrival-start\"]/div[2]/div/div/div[2]/div/div[1]/table")
	List<WebElement> days;
	// = driver.findElements(By.xpath("//div[@class='ui-datepicker-inline
	// ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all
	// ui-datepicker-multi ui-datepicker-multi-2']/div[2]/table/tbody/tr/td/a"));
	//// *[@id="app-layer-datepicker-flights-departure-arrival-start"]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[4]

	public void enterFromCity(String from) throws Throwable {
		fromCity.sendKeys(from);
		List<WebElement> countriesList = fromCitySelect.findElements(By.tagName("li"));
		for (WebElement li : countriesList) {
			System.out.println(li.getText());
			if (li.getText().contains("SFO")) {
				li.click();
			}
		}
		Thread.sleep(1000l);
	}

	public void enterToCity(String from) throws Throwable {
		toCity.sendKeys("JFK");
		System.out.println(toCitySelect.getText());
		List<WebElement> toCitiesList = toCitySelect.findElements(By.tagName("li"));
		for (WebElement li : toCitiesList) {
			System.out.println(li.getText());
			if (li.getText().contains("JFK")) {
				li.click();
			}
		}
		Thread.sleep(1000l);

	}

	public void enterDepartDate(WebDriver Driver) throws Throwable {

		DateTimeFormatter ft = DateTimeFormatter.ofPattern("dd-MMMM yyyy");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("testttttt" + now.plusDays(30).format(ft));
		String date = now.plusDays(30).format(ft);
		String splitter[] = date.split("-");
		String month_year = splitter[1];
		String day = splitter[0];
		System.out.println(month_year);
		System.out.println(day);
		selectDate(Driver, month_year, day);
		fromDateString.sendKeys(now.plusDays(14).format(ft));
		Thread.sleep(1000000l);

	}

	public void clickSearch() throws Throwable {
		search.click();
		Thread.sleep(60000l);

	}

	public void ClickFlight() {
		flights.click();
	}

	public void selectDate(WebDriver Driver, String month_year, String select_day) throws InterruptedException {
		fromDateString.click();
		Thread.sleep(10000);

		List<WebElement> elements1 = Driver
				.findElements(By.xpath("//*[@class=\"uitk-date-picker-menu-months-container\"]/div"));
		// #app-layer-datepicker-departing-returning-start >
		// div.uitk-dialog.uitk-dialog-fullscreen.uitk-dialog-fullscreen-bg-default >
		// div > div > div.uitk-calendar.uitk-flex-item.uitk-calendar-scrim
		System.out.println(elements1.isEmpty());
		System.out.println("bshbsdhfg=====" + elements1.size());

		for (int i = 0; i < elements1.size(); i++) {
			System.out.println("test div" + elements1.get(i).getText());
			System.out.println(month_year);

			if (elements1.get(i).getText().equals(month_year)) {
				System.out.println("INside second if....");
				List<WebElement> days1 = Driver
						.findElements(By.xpath("//*[@class=\"uitk-date-picker-weeks\"]/tbody/tr/td"));
				System.out.println("days size=====" + days1.size());

				for (WebElement d : days1) {
					System.out.println("dayss   " + d.getText());
					if (d.getText().equals(select_day)) {
						d.click();
						Thread.sleep(10000);
						return;
					}
				}

			}

		}

	}
}

package com.qa.cnbc.base;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected int locatortimeout = 10;
	protected int timeout = 20;

	public BasePage(WebDriver driver) {

		this.driver = driver;

	}

	// Deleting the content of the input
	public void inputText(By input, String text) {

		String del = Keys.chord(Keys.CONTROL, "a") + Keys.DELETE;
		driver.findElement(input).sendKeys(del + text);
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void selectDropDownByVisible(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int locatortimeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(locatortimeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public Boolean waitForElementToBecomeInvisible(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(locatortimeout));
		wait.until(ExpectedConditions.invisibilityOf(element));
		return true;
	}

	/*
	 * 
	 * wait for element clickable
	 * 
	 * 
	 */
	public WebElement waitForElementClickable(By locator, int locatortimeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(locatortimeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void dropDownValueUsingLocator(By locator, String value) {
		List<WebElement> list = getElements(locator);
		for (WebElement e : list) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void browerScrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll up by a certain number of pixels (negative value to scroll up)
		int scrollPixels = -500; // Adjust as needed
		js.executeScript("window.scrollBy(0, " + scrollPixels + ");");
	}

	public void scrollToElement(By targetElement) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Scroll to the target element
		js.executeScript("arguments[0].scrollIntoView(true);", targetElement);
	}

	public void switchToChildWindow() {
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String childWindowId = it.next();
		System.out.println("child window id is : " + childWindowId);
		waitForPageLoad(timeout);
		// switch work:
		driver.switchTo().window(childWindowId);
		System.out.println("child window title is: " + driver.getTitle());

	}

	public void waitForPageLoad(int timeOut) {

		long endTime = System.currentTimeMillis() + timeOut;

		while (System.currentTimeMillis() < endTime) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			String state = js.executeScript("return document.readyState").toString();
			System.out.println("page is : " + state);
			if (state.equals("complete")) {
				System.out.println("page is fully loaded now....");
				break;
			}

		}
	}

	/**
	 * Accept an alert displayed
	 */
	public void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	public Boolean isElementPresent(By locator) {
		try {

			waitForElementVisible(locator, timeout);
			scrollToElement(locator);
			driver.findElement(locator).isDisplayed();
			return true;
		} catch (Exception ex) {
		}
		return false;
	}
}

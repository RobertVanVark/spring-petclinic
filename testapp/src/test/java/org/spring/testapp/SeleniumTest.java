package org.spring.testapp;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumTest {

	protected static WebDriver driver;

	@BeforeClass
	public static void setUpClass() throws IOException, AWTException {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void cleanUp() throws InterruptedException {
		Thread.sleep(10000L);
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	public SeleniumTest() {
		super();
	}

	protected void makeScreenshot(WebDriver driver, Class clzz, int imageCounter) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String filename = String.format("target/selenium/%s/screenshot%02d.png", clzz.getSimpleName(), imageCounter);
		FileUtils.copyFile(screenshot, new File(filename));
	}

	protected String getHostName() {
		String hostName = System.getProperty("HOSTNAME");
		if (hostName == null) {
			hostName = "52.49.163.172";
		}
		return hostName;
	}

	protected String getBaseUrl() {
		return "http://" + getHostName() + ":9090/petclinic";
	}

}
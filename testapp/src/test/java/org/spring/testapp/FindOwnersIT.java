package org.spring.testapp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FindOwnersIT extends SeleniumTest {

	private int imageCounter = 0;

	@Test
	public void smokeTest() throws InterruptedException, IOException {
		driver.get(getBaseUrl());
		makeScreenShot();
		assertEquals("PetClinic :: a Spring Framework demonstration", driver.getTitle());
		WebElement findOwnersMenu = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[2]/a"));
		findOwnersMenu.click();
		makeScreenShot();
		assertEquals("Find Owners", driver.findElement(By.xpath("/html/body/div/h2")).getText());
		WebElement findButton = driver.findElement(By.xpath("//*[@id=\"search-owner-form\"]/fieldset/div[2]/button"));
		findButton.click();
		makeScreenShot();
		assertEquals("Owners", driver.findElement(By.xpath("/html/body/div/h2")).getText());
		WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"owners_filter\"]/label/input"));
		searchBox.sendKeys("D");
		makeScreenShot();
		searchBox.sendKeys("a");
		makeScreenShot();
		searchBox.sendKeys("v");
		makeScreenShot();
		searchBox.sendKeys("i");
		makeScreenShot();
		searchBox.sendKeys("d");
		makeScreenShot();
		WebElement name = driver.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr/td[1]/a"));
		assertEquals("David Schroeder", name.getText());
		WebElement address = driver.findElement(By.xpath("//*[@id=\"owners\"]/tbody/tr/td[2]"));
		assertEquals("2749 Blackhawk Trail", address.getText());
	}
	
	private  void makeScreenShot() throws IOException {
		makeScreenshot(driver, getClass(), imageCounter);
		imageCounter++;
	}

}

package org.spring.testapp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class SmokeTest extends SeleniumTest {

	private int imageCounter = 0;

	@Test
	public void smokeTest() throws InterruptedException, IOException {
		driver.get(getBaseUrl());
		makeScreenShot();
		assertEquals("PetClinic :: a Spring Framework demonstration", driver.getTitle());
	}

	protected void makeScreenShot() throws IOException {
		makeScreenshot(driver, getClass(), imageCounter);
		imageCounter++;
	}
}

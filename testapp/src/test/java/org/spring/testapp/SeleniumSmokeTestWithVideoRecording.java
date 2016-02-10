package org.spring.testapp;

import static org.junit.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.monte.media.Format;

import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumSmokeTestWithVideoRecording {

	private static WebDriver driver;

	private static ScreenRecorder screenRecorder;

	@BeforeClass
    public static void setUpClass() throws IOException, AWTException {

        //Create a instance of GraphicsConfiguration to get the Graphics configuration
        //of the Screen. This is needed for ScreenRecorder class.
        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        //Create a instance of ScreenRecorder with the required configurations
        screenRecorder = new ScreenRecorder(gc,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,ENCODING_QUICKTIME_JPEG, CompressorNameKey,
                		ENCODING_QUICKTIME_JPEG, DepthKey, (int) 24,FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f,
                		KeyFrameIntervalKey, (int) (15 * 60)),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,"black",
                        FrameRateKey, Rational.valueOf(30)),
                null);

        // create driver
        driver = new FirefoxDriver();

        // maximize screen
        driver.manage().window().maximize();
    }

	@Before
	public void beforeTest() throws IOException {
		screenRecorder.start();
	}

	@After
	public void afterTest() throws IOException {
		screenRecorder.stop();
		List<File> createdMovieFiles = screenRecorder.getCreatedMovieFiles();
		for (File movie : createdMovieFiles) {
			System.out.println("New movie created: " + movie.getAbsolutePath());
		}
	}

	@AfterClass
	public static void cleanUp() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	public void smokeTest() throws InterruptedException, IOException {
		driver.get("http://localhost:8080/petclinic");
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(
				"target/selenium/screenshot.png"));
		assertEquals(
				"PetClinic :: a Spring Framework demonstration for Esnuco",
				driver.getTitle());
	}
}

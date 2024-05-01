package sourcefuse;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class TelescopeHealth {

	UiAutomator2Options options;
	AppiumDriverLocalService service;
	AndroidDriver driver;
	WebDriverWait wait;
	Actions actions;

	@BeforeMethod
	public void SetupAppium() throws MalformedURLException, URISyntaxException {
		//start Appium server
		
		/*
		 * service = new AppiumServiceBuilder() .withAppiumJS( new File(
		 * "C:\\Users\\alida\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"
		 * )) .withIPAddress("127.0.0.1").usingPort(4723).build(); service.start();
		 */
		 
		options = new UiAutomator2Options().setDeviceName("emulator-5554").setPlatformName("Android")
				.setPlatformVersion("13.0").setAutomationName("uiautomator2")
				.setApp(System.getProperty("user.dir")+"\\appReso\\TelescopeHealth-18thMarch.apk");
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
	}

	@AfterMethod
	public void Teardown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
		//service.stop();
	}

	@Test
	public void test1() throws InterruptedException {
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Hemburger Icon\"]")).click();
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"Health Support Request\"]")).click();
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(description(\"Submit\"));"));
		driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.CheckBox[7]")).click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Submit\"]")).click();
		WebElement popup = driver
				.findElement(By.xpath("//android.view.View[@content-desc=\"Please review the provided details.\"]"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(popup));
		Assert.assertEquals(popup.getAttribute("content-desc"), "Please review the provided details.");

	}

	@Test
	public void test2() {
		//in secons test scenario mobile number input field is not accepting any valid mobile so not able to automate

	}

	@Test
	public void test3() throws InterruptedException {
		int AgeOffsetX = -97;
		int WeightOffsetX = 212;
		int HightOffsetX = 140;
		
		//Age 45
		//Weight 120
		//Hight 200
		
		//using offset to select desired value because seekbar has custom configuration so not able to write logic for that.

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.widget.ImageView[@content-desc=\"Hemburger Icon\"]")))
				.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Health Calculators\"]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//android.view.View[contains(@content-desc, 'Calorie Calculator')]"))).click();

		WebElement AgeseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"2\"]")));

		Actions action = new Actions(driver);
		action.moveToElement(AgeseekBar, AgeOffsetX, 0).click().perform();

		WebElement WeightseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"5\"]")));
		action.moveToElement(WeightseekBar, WeightOffsetX, 0).click().perform();

		WebElement HightseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"8\"]")));
		action.moveToElement(HightseekBar, HightOffsetX, 0).click().perform();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@content-desc, 'Sedentary')]")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.view.View[contains(@content-desc, 'Very active')]")))
				.click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(description(\"Maintain Weight\"));")).click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(description(\"Lose Weight\"));")).click();
		
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(description(\"Calculate Calories\"));")).click();
		
		WebElement Calories = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[contains(@content-desc, 'Calories Needed')]")));
		Assert.assertEquals(Calories.getAttribute("content-desc"), "Calories Needed: 3347");
		//3342, 3347
	}

	@Test
	public void test4() {

		int WeightOffsetX = 70; 
		int HightOffsetX = 85;
		int WaistOffsetX = 170;

		//Weight 100
		//Hight 190
		//Waist 120
		
		//using offset to select desired value because seekbar has custom configuration so not able to write logic for that.
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.widget.ImageView[@content-desc=\"Hemburger Icon\"]")))
				.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.view.View[@content-desc=\"Health Calculators\"]")))
				.click();
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(descriptionContains(\"Body Fat Percentage Calculator\"));")).click();
		
		Actions action = new Actions(driver);
		WebElement WeightseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"2\"]")));
		action.moveToElement(WeightseekBar, WeightOffsetX, 0).click().perform();
		
		WebElement HightseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"5\"]")));
		action.moveToElement(HightseekBar, HightOffsetX, 0).click().perform();

		WebElement WaistseekBar = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.SeekBar[@index=\"8\"]")));
		action.moveToElement(WaistseekBar, WaistOffsetX, 0).click().perform();
		
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.widget.Button[contains(@content-desc, 'Male')]")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.view.View[contains(@content-desc, 'Female')]")))
				.click();
		
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(descriptionContains(\"Calculate\"));")).click();

		WebElement Calories = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.view.View[contains(@content-desc, 'Body Fat Percentage:')]")));
	System.out.println(Calories.getAttribute("content-desc"));
	Assert.assertEquals(Calories.getAttribute("content-desc"), "Body Fat Percentage: 17.25%");
		
	}

}

package tutorialsninja.register;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class TC_RF_010 {

	// verify registering an account by providing invalid email to account details
	// like email
	// ashot dependency from mvn repo
	@Test
	public void verifyRegisterAccountWithAllFields1() throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Akhila");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Yandapalli");
		// String timestampedEmail = "akhilayandapallii";
		// driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(timestampedEmail);
		driver.findElement(By.id("input-email")).sendKeys("akhilayandapallii");
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9090909090");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();

		Thread.sleep(2000);
		File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot1, new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
		Assert.assertTrue(
			    compareTwoScreenshots(
			        System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png",
			        System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"
			    ),
			    "Screenshots do not match as expected"
			);

		driver.findElement(By.id("input-email")).clear();

		driver.findElement(By.id("input-email")).sendKeys("akhilayandapallii@");
		//driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(2000);
		File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']"))	.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(srcScreenshot2, new File(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png"));
		Assert.assertTrue(
			    compareTwoScreenshots(
			        System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png",
			        System.getProperty("user.dir") + "\\Screenshots\\sc2Expected.png"
			    ),
			    "Screenshots do not match as expected"
			);
		driver.findElement(By.id("input-email")).clear();

		driver.findElement(By.id("input-email")).sendKeys("akhilayandapallii@gmail");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(2000);
         String expectedWarningMessage="E-Mail Address does not appear to be valid!";
         Thread.sleep(2000);
         Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedWarningMessage);
	
		driver.findElement(By.id("input-email")).clear();

		driver.findElement(By.id("input-email")).sendKeys("akhilayandapallii@gmail.");
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Thread.sleep(3000);
		
		File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']"))
				.getScreenshotAs(OutputType.FILE);
		
		FileHandler.copy(srcScreenshot3, new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png"));
		Thread.sleep(3000);
		
		Assert.assertTrue( compareTwoScreenshots( System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png", System.getProperty("user.dir") + "\\Screenshots\\sc3Expected.png" ),
			    "Screenshots do not match as expected"
			);
	}

	public boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
		BufferedImage actualBImg = ImageIO.read(new File(actualImagePath));
		BufferedImage expectedBImg = ImageIO.read(new File(expectedImagePath));
		// to compareimages we use below step
		ImageDiffer imgdiffer = new ImageDiffer();
		ImageDiff imgDifference = imgdiffer.makeDiff(expectedBImg, actualBImg);

		return imgDifference.hasDiff();
	}
}

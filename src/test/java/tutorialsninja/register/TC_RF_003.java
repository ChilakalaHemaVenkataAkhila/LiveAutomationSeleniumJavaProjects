package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
//VERIFY ACCOUNT registration by providing all details
public class TC_RF_003 {

	@Test
	public void verifyRegisterAccountWithAllFields() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Akhila");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Yandapalli");
		String timestampedEmail = "akhilayandapalli" + System.currentTimeMillis() + "@gmail.com";
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(timestampedEmail);
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9090909090");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("a[href='https://tutorialsninja.com/demo/index.php?route=account/success']")).isDisplayed());
		String expectedProperDetailsOne="Your Account Has Been Created!";
		String expectedProperDetailsTwo="Congratulations! Your new account has been successfully created!";
		String expectedProperDetailsThree="You can now take advantage of member privileges to enhance your online shopping experience with us.";
		String expectedProperDetailsFour="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";		
		String expectedProperDetailsFive="A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please";
		String expectedProperDetailsSix="contact us";
		//to get this info we use content tag
		String actualProperDetails=driver.findElement(By.id("content")).getText();
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
		Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));
		driver.findElement(By.linkText("Continue")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		driver.close();
	}
	

}

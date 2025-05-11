package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
//verify registering an account by providing existing account details like email
public class TC_RF_009 {
    @Test
	public void verifyRegisterAccountWithAllFields1() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Register']")).click();
		driver.findElement(By.xpath("//input[@id='input-firstname']")).sendKeys("Akhila");
		driver.findElement(By.xpath("//input[@id='input-lastname']")).sendKeys("Yandapalli");
		String timestampedEmail = "akhilayandapalli@gmail.com";
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(timestampedEmail);
		driver.findElement(By.xpath("//input[@id='input-telephone']")).sendKeys("9090909090");
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@id='input-confirm']")).sendKeys("pandu");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		String expectedWarningMessage="Warning: E-Mail Address is already registered!";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(), expectedWarningMessage);
				}

}

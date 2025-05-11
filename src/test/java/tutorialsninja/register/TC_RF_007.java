package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_007 {
//verify different ways of navigating to register account page
	@Test
	public void differentWaysofRegisterNavigatingPage() {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath	("//a[normalize-space()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']")).isDisplayed());
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath("//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")).click();
		driver.findElement(By.xpath("//a[@class='list-group-item'][normalize-space()='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[normalize-space()='Register']")).isDisplayed());
		
		
	}

}

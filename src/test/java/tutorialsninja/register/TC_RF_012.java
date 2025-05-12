package tutorialsninja.register;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_RF_012 {
//Verify registering account by using the keyboard keys
	@Test
	public void keyboardKeys() {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");
		driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
		driver.findElement(By.xpath	("//a[normalize-space()='Register']")).click();
		Actions actions=new Actions(driver);
		for(int i=1;i<=23;i++) {
		actions.sendKeys(Keys.TAB).perform();
		}	
	actions.sendKeys("Akhila").pause(Duration.ofSeconds(2)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("Pandu")
.sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("akhilayandapalli" + System.currentTimeMillis() + "@gmail.com")
.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("9898989898").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
.pause(Duration.ofSeconds(1)).sendKeys("12345").pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys("12345")
.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
.pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
.pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();
	
	Assert.assertTrue(driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).isDisplayed());
	Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());
	
	}

}

package tutorialsninja.register;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
//import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TC_RF_002 {
	@Test
public void verifyConfirmationEmail() {
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get("https://www.amazon.in/");
	driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
	driver.findElement(By.xpath("//input[@id='ap_email_login' and contains(@class, 'a-input-tex')]")).sendKeys("akhilachilakala2016@gmail.com");
	driver.findElement(By.xpath("//input[@class='a-button-input']")).click();
	//String appPasscode="llzk hjbd muwc qjzw";
	//String email="akhilachilakala2016@gmail.com";
	driver.findElement(By.partialLinkText("Forgot")).click();
	driver.findElement(By.xpath("//*[@id='continue']")).click();
	
	String host="imap.gmail.com";
	String mailStoreType="imap";
	String port="993";
	String username="akhilachilakala2016@gmail.com";
	String appPassword="llzk hjbd muwc qjzw";
	String recoveryLink=null;
	try {
		Properties properties=new Properties();
		properties.put("mail.store.protocol","imaps");
		properties.put("mail.imap.host", host);
		properties.put("mail.imap.port", port);
		properties.put("mail.imap.ssl.enable", "true");
		
		Session emailSession=Session.getDefaultInstance(properties);
		
		Store store=emailSession.getStore("imaps");
		store.connect(host , username, appPassword);
		Folder inbox=store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		
		Message[] messages=inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
		boolean found=false;
		for(int i=messages.length-1;i>=0;i--) {
			Message message=messages[i];
			if(message.getSubject().contains("amazon.in: Password recovery")) {
				found=true;
				System.out.println("Email Subject: " + message.getSubject());
			//	System.out.println("Subject: " + message.getSubject());
				System.out.println("Email From: " + message.getFrom()[0].toString());
				String emailBody=getTextFromMessage(message);
				System.out.println("Email Body: "+emailBody);				
			//	System.out.println("Email Body: " + getTextFromMessage(message));
				System.out.println("-------------------------------------------------------------------");
			
				String trigger = "If you prefer, copy the following link and paste it into a browser to view\\.";
		        Pattern pattern = Pattern.compile(trigger + "\\s*(https://www\\.amazon\\.in\\S+)");
		        Matcher matcher = pattern.matcher(emailBody);

		        if (matcher.find()) {
		            recoveryLink = matcher.group(1);
		            System.out.println("Extracted Recovery Link: " + recoveryLink);
		        } else {
		            System.out.println("Recovery link not found.");
		        }
				break;
			}
			
			
		}
		if(!found) {
			System.out.println("No confirmation email found.");
		}
		inbox.close(false);
		store.close();		
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	driver.navigate().to(recoveryLink);
	if(driver.findElement(By.xpath("//input[@name='customerResponseDenyButton']")).isDisplayed()) {
		System.out.println("Link in the email has to taken to the proper page");
	}
	else {
		System.out.println("Link in the email has not taken to the proper page");
		
	}
	
	driver.quit();
}
private static String getTextFromMessage(Message message) throws Exception{
	String result="";
	if(message.isMimeType("text/plain")) {
		result=message.getContent().toString();
	}
	else if(message.isMimeType("multipart/*")) {
		MimeMultipart mimeMultipart=(MimeMultipart) message.getContent();
		result = getTextFromMimeMultipart(mimeMultipart);
	}
	return result;
		
	}
private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception{
	//String result="";
StringBuilder result=new StringBuilder();

	int count=mimeMultipart.getCount();
	for(int i=0;i<count;i++) {
		BodyPart bodyPart=mimeMultipart.getBodyPart(i);
		if(bodyPart.isMimeType("text/plain")) {
			result.append(bodyPart.getContent());
			
		}
		else if(bodyPart.isMimeType("text/html")) {
			result.append(bodyPart.getContent());
			
		}
		else if (bodyPart.getContent() instanceof MimeMultipart) {
			result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
			}
	}
	return result.toString();
}

}



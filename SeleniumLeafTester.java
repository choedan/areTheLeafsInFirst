import java.io.File;
import java.io.IOException;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class SeleniumLeafTester {
	ChromeDriver driver;
	int place = 0;
	
	@Before
	public void setUp(){
		driver = new ChromeDriver();
		driver.navigate().to("http://www.nhl.com");
		driver.findElementByLinkText("STANDINGS").click();
		driver.findElementByLinkText("CONFERENCE").click();
		System.out.println("executed setup");
		
	}
	
	@After
	public void tearDown(){
		driver.close();
		System.out.println("executed tear down");
	}
	
	@Test
	public void checkLeafs(){
		
		//Are the leafs in first??
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File("K:\\testScreenShot.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//finds table
		WebElement table = driver.findElement(By.xpath("//*[@id='conference-conference-6']/div/div/div[2]/table/tbody"));
		
		//Abs xpath to first place team
		//WebElement name = driver.findElement(By.xpath("//*[@id='conference-conference-6']/div/div/div[2]/table/tbody/tr[1]/td[1]/span/a/span[3]"));
		//System.out.println("name is " + name.getText());
		
		//creates list of teams, finding elements by css selector class
		List<WebElement> listOfTeams = table.findElements(By.cssSelector(".team--name"));


		for (int i=0;i<listOfTeams.size();i++){
			if(listOfTeams.get(i).getText().equals("Toronto"))
				place = i+1;
					
		}
		
		
		Assert.assertEquals("Leafs are not in first place",1, place);
		
	}
	
}

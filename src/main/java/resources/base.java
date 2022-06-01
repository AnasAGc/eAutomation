package resources;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class base {

	public  WebDriver driver;
	public Properties prop;
	
	public WebDriver  initialDriver() throws IOException {
		
		prop=new Properties();
		
		FileInputStream fil= new FileInputStream("C:\\Users\\aabughalieh\\eclipse-workspace\\Auctions\\src\\test\\java\\Mizan\\Auctions\\data.properties");
		
		prop.load(fil);
		
		
		String browserName= prop.getProperty("browser");
		System.out.println(browserName);
		
		if(browserName.equals("chrome"))
		{
			 System.setProperty("webdriver.chrome.driver", "C:\\Users\\aabughalieh\\eclipse-workspace\\chromedriver.exe");
			driver= new ChromeDriver();
		
			
		}
		else if (browserName.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\aabughalieh\\eclipse-workspace\\geckodriver.exe");
			 driver= new FirefoxDriver();
			
		}
		else if (browserName.equals("IE"))
		{

		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		return driver;
		
		
		
	}
	
	public String getScreenShotPath(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		FileUtils.copyFile(source,new File(destinationFile));
		return destinationFile;


	}

}
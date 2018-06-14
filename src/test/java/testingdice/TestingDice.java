package testingdice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestingDice {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		FileReader reader = new FileReader("JobTitles.txt");
		
		BufferedReader bufReader = new BufferedReader(reader);
		
//		System.out.println(bufReader.readLine());
		
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 1; i <= 20; i++) {
			list.add(bufReader.readLine());
		}
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://dice.com");
		
		String location = "78718";
		ArrayList<String> newList = new ArrayList<>();
		
		for (int i = 0; i < list.size(); i++) {
			driver.findElement(By.id("search-field-keyword")).clear();
			driver.findElement(By.id("search-field-keyword")).sendKeys(list.get(i));
			
			driver.findElement(By.id("search-field-location")).clear();
			driver.findElement(By.id("search-field-location")).sendKeys(location);
			
			driver.findElement(By.id("findTechJobs")).click();
			
			newList.add(list.get(i) + "-"+driver.findElement(By.id("posiCountId")).getText());
			
			driver.navigate().back();
			
		}
		
		driver.close();
		
		for (String each : newList) {
			System.out.println(each.toString()+ " jobs available in " +location +" zip area.");
		}
	}

}

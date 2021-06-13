package dbOperationsOnWebtable;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import object.Employee;

public class Parent {

	protected WebDriver driver = null;
	protected List<Employee> empList = new ArrayList<>();
	protected By rows= By.xpath("//table[@id='example']//tbody//tr");
	
	@BeforeTest
	public void setup() {
		try{
		WebDriverManager.chromedriver().setup();
		
		//Using chrome browser in headless mode
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--headless");
		driver = new ChromeDriver(chromeOptions);
		System.out.println("[Info]:: Trying to launch chrome browser...");
		
		//Implicit timeout of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://datatables.net/extensions/select/examples/initialisation/checkbox.html");
		
		}catch(Exception e) {
			System.out.println("[Error]:: Error while launching the driver binaries");
			e.printStackTrace();
		}
		
		System.out.println("[Info]:: Browser launched successfully (headless mode)");
	}
	
	@BeforeTest(dependsOnMethods = {"setup"},
	description = "Fetching the table data into List of Employee Object")
	public void fetchingTableData() {
		
		//Selecting the highest dropdown value to save myself from pagination
//		By selectLocator = By.cssSelector("select[name='example_length']");
		driver.findElement(By.cssSelector("select[name='example_length']")).click();
		Select select = new Select(driver.findElement(By.cssSelector("select[name='example_length']")));
		select.selectByValue("100");
		System.out.println("[Info]:: Selected option: 100 entries per page");
		
		for(int i=1;i<=driver.findElements(rows).size();i++) {
			empList.add(new Employee(driver.findElement(By.xpath("(//td[@class='sorting_1'])["+i+"]")).getText(),//NameLocator
					driver.findElement(By.xpath("(//td[@class='sorting_1'])["+i+"]//following-sibling::td[1]")).getText(),//PositionLocator
					driver.findElement(By.xpath("(//td[@class='sorting_1'])["+i+"]//following-sibling::td[2]")).getText(),//OfficeLocator
					Integer.parseInt(driver.findElement(By.xpath("(//td[@class='sorting_1'])["+i+"]//following-sibling::td[3]"))//AgeLocator
							.getText()),//Converting string value to integer
					Double.parseDouble(driver.findElement(By.xpath("(//td[@class='sorting_1'])["+i+"]//following-sibling::td[4]"))//SalaryLocator
							.getText().replaceAll("[^0-9]",""))));//Removing  special characters and parsing to double value (eg: $725,000 -> 725000.0)
		}
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
		System.out.println("[Info]:: Session terminated successfully");
	}
}

package selenium_assessment;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test

public class OrangeHrm {

	WebDriver driver=null;
	locators cred = new locators();
	inputs inp = new inputs();
	Logger logger;
   static ExtentReports extent;
   boolean rs ;
   ExtentTest new_test;




	@BeforeSuite

	public void load_application() throws IOException {



		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();


		driver.manage().window().maximize();//method chaining


		FileInputStream fis = new FileInputStream("./config.properties");
		Properties config = new Properties();
		config.load(fis);
        logger = LogManager.getLogger(OrangeHrm.class);

		driver.get(config.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		//starts reports
		ExtentSparkReporter spark = new ExtentSparkReporter("./src/test/resources/Spark.html");



		//create extent reports and attach reporters
		extent = new ExtentReports();
		extent.attachReporter(spark);


	}

	@Test(priority=1)
	public void verify_title() {
new_test=extent.createTest("verify title");

		String ActualTitle = driver.getTitle();
		//driver.manage().window().maximize();
		String ExpectedTitle = "OrangeHRM";
		
		if(ActualTitle.equals(ExpectedTitle)) {
			rs=true;
		}
		else {
			
			rs=false;
		}
     Assert.assertTrue(rs);
     new_test.pass("test passed");

	}
	@Test(priority=2)

	public void login() {
		
		new_test=extent.createTest("verify login");
		//calling web element by name
		WebElement user=driver.findElement(By.name(cred.getUsername()));
		user.sendKeys(inp.username);
 
		////calling web element by id
		WebElement password = driver.findElement(By.id(cred.getPassword()));
		password.sendKeys(inp.password);

		
		////calling web element by name
		WebElement login = driver. findElement(By.name(cred.getLogin()));
		login.click();
		String text = driver.getCurrentUrl();

//checking if the url contain dashbord

		if(text.contains("dashboard")) {

			System.out.println("dashboard keyword is obtained");
		}
		else
			System.out.println("keyword dashbord is not obtained");
		try{
			driver.findElements(By.id("Admin"));

			//Since, no exception, so element is present
			System.out.println("Tab Admin is present");
		}
		catch(NoSuchElementException e){

			//Element is not present
			System.out.println("Tab Admin is not present");
		}

		WebElement gret = driver.findElement(By.id("welcome"));

		if (gret.equals("welcome Admin")) {
			System.out.println("welcome admin is displayed");

		}

		else
			System.out.println("welcome admin is not displayed");
	}
	@Test (priority=3)
	public void validateAdd () { 

		WebElement add = driver.findElement(By.name("btnAdd")); 

		if(add.isDisplayed()) {

			System.out.print("Add button is displayed");
		}

		else
			System.out.print("Add button is not displayed");

	}
	
	@Test (priority=4)
	public void validateDelete() { 

		WebElement delete = driver.findElement(By.id("btnDelete")); 
		if(delete.isDisplayed()) {

			System.out.println("Delete button is displayed");
		}
		else

			System.out.println("Delete button is not displayed");
	}

		@Test (priority=5)
		public void validateSearch() {

			WebElement search = driver.findElement(By.id("searchBtn")); 

			if(search.isDisplayed()) {
				System.out.print("search button is displayed");
			}
			else
				System.out.print("search button is displayed");
		} 

		// validate Reset button
		@Test(priority = 6)
		public void validateReset() { 

			WebElement reset = driver.findElement(By.id("resetBtn")); 

			if(reset.isDisplayed()) {
				System.out.print("rest button is displayed");
			}

			else
				System.out.print("rest button is displayed");


		}
		@Test(priority=7)
		public void modulAdmin() {
			WebElement admin = driver.findElement(By.id(cred.getAdmin()));
			admin.click();

			String url = driver.getCurrentUrl();

			if(url.contains("admin/viewSystemUsers")) {
				System.out.println("Url contain admin/viewSystemusers");
			}
			else
				System.out.println("Url does not contain admin/viewSystemusers"); 
		}
		@Test(priority=8)
		public  void addbtn() {

			// System.out.print("addbtn");

			WebElement add = driver.findElement(By.xpath(cred.getAddbtn()));

			add.click();

			String url = driver.getCurrentUrl();

			if(url.contains("saveSystemUser")) {
				System.out.println("Url contain  saveSystemUser");

			}
			else
				System.out.println("Url does not contain admin/saveSystemUser");

			String expectedheading = "Add User H1" ;

			String heading = driver.findElement(By.xpath(cred.getheading())).getText();
			if(expectedheading.equalsIgnoreCase(heading))
				System.out.println("The expected heading is same as actual heading --- "+heading);
			else
				System.out.println("The expected heading doesn't match the actual heading --- "+heading);

		}


		@Test(priority=9)
		public void add_employee() throws InterruptedException{

			Select status = new Select(driver.findElement(By.id(cred.getStatus())));
			status.selectByValue("2");		
			WebElement empname = driver.findElement(By.id(cred.getEmpname()));
			empname.sendKeys(inp.empname);

			WebElement username = driver.findElement(By.name(cred.getEmpusername()));
			username.sendKeys(inp.addusername);

			WebElement password = driver.findElement(By.name(cred.getEmppass()));
			password.sendKeys(inp.adduserpass);

			WebElement conpassword = driver.findElement(By.name(cred.getConpass()));
			conpassword.sendKeys(inp.adduserpass);
			Thread.sleep(2000);
			WebElement save = driver.findElement(By.name("btnSave"));
			save.click();	


		}
		@Test (priority = 10)
		
			public void click_search() throws InterruptedException {
				Thread.sleep(2000);
				driver.findElement(By.name("searchSystemUser[userName]")).clear();
				driver.findElement(By.name("searchSystemUser[userName]")).sendKeys(inp.empname);

				driver.findElement(By.id("searchBtn")).click();
			}
		

			@Test(priority = 11)

			public void validate_username() {


				WebElement user_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a"));


				if (user_table.getText().equalsIgnoreCase(inp.empname)) {

					System.out.println("results are matching");
				}
				else {
					System.out.println("results are matching");
				}


			}


			@Test(priority = 12)

			public void validate_employeeName() {

				WebElement emp_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[4]"));


				if (emp_table.getText().equalsIgnoreCase("Alice Duval")) {

					System.out.println( "employee names are matching");
				}
				else {

					System.out.println("employee names are NOT matching"); 
				}

			}

			@Test(priority = 13)

			public void validate_status() {



				WebElement status_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[5]"));


				if (status_table.getText().equalsIgnoreCase(inp.status)) {

					System.out.println("is enabled");
				}
				else {

					System.out.println("is not enabled");
				}

			}	
			@Test (priority=14)
			public void delete_record() throws InterruptedException {


				WebElement check_box = driver.findElement(By.xpath("//*[@id=\"ohrmList_chkSelectAll\"]"));
				Thread.sleep(2000);
				check_box.click();

				driver.findElement(By.id("btnDelete")).click();
				Thread.sleep(3000);
				driver.findElement(By.id("dialogDeleteBtn")).click();

			}
			
			@Test (priority=15)
			public void search_deleted() throws InterruptedException {
				Thread.sleep(2000);
				driver.findElement(By.name("searchSystemUser[userName]")).clear();
				driver.findElement(By.name("searchSystemUser[userName]")).sendKeys(inp.empname);
				
				driver.findElement(By.id("searchBtn")).click();


		}
			@Test (priority=16)
			public void logout() throws InterruptedException {

				driver.findElement(By.id("welcome")).click();
				Thread.sleep(2000);
				driver.findElement(By.linkText("Logout")).click();
			}

			@Test(priority = 17)

			public void validate_url_landingPage() {

				

				if (driver.getCurrentUrl().contains("login")){

					System.out.println("url contains login");  
				}
				else {
					System.out.println("url does not contain login"); 
				}
				
			}

			@Test(priority = 18)

			public void validate_url_landingPage2() {

				

				if (driver.getCurrentUrl().contains("OrangeHRM")){

					System.out.println( "url contains OrangeHRM");  
				}
				else {
					System.out.println("url does not contain OrangeHRM"); 
				}
				
			}
			@AfterMethod
			public void evaluate(ITestResult result) {
				if(result.getStatus() == ITestResult.FAILURE) {
					new_test.fail("testfailed");
					logger.info("testfailed");
				}

					
			}
			
			@AfterSuite
			public void clearup() {
				extent.flush();
			}
			
		}











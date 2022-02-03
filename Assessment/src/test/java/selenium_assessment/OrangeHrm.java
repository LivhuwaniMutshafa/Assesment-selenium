package selenium_assessment;

import static org.testng.Assert.assertTrue;

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
	static String output;




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

		//calling web element by id
		WebElement password = driver.findElement(By.id(cred.getPassword()));
		password.sendKeys(inp.password);


		////calling web element by name
		WebElement login = driver. findElement(By.name(cred.getLogin()));
		login.click();
		String text = driver.getCurrentUrl();

		//checking if the url contain dashbord

		if(text.contains("dashboard")) {
			rs = true;
			System.out.println("dashboard keyword is obtained");
		}
		else {
			rs=false;
		}
		Assert.assertTrue(rs);
		new_test.pass("test passed");

		try{
			driver.findElements(By.id("Admin"));
			new_test.pass("Admin tab is displayed");
			logger.info("Test Passed. admin tab is available");

		}
		catch(NoSuchElementException e){

			//Element is not present
			logger.info("Tab Admin is not present");
		}

		WebElement gret = driver.findElement(By.id("welcome"));

		if (gret.equals("welcome Admin")) {
			new_test.pass("welcome Admin is displayed");
			logger.info("Test Passed");


		}

		else
			new_test.pass("welcome Admin is not displayed");
		logger.info("Test failed");
	}
	@Test (priority=3)
	public void Addbtn () throws InterruptedException { 
		Thread.sleep(2000);
		new_test=extent.createTest("verify add button");

		WebElement add = driver.findElement(By.id("btnAdd")); 

		if(add!= null) {
			new_test.pass("add button is displayed");
			//logger.info("Test Passed");



		}


	}

	@Test (priority=4)
	public void Delete() throws InterruptedException { 
		Thread.sleep(2000);
		new_test=extent.createTest("verify delete button");

		WebElement delete = driver.findElement(By.id("btnDelete")); 
		if(delete!= null) {
			new_test.pass("Delete button is displayed");
			logger.info("Test Passed");
		}

	}

	@Test (priority=5)
	public void validateSearch() {
		new_test=extent.createTest("verify search");

		WebElement search = driver.findElement(By.id("searchBtn")); 

		if(search!= null) {
			new_test.pass("Delete button is displayed");
			logger.info("Test Passed");
		}

	} 

	// validate Reset button
	@Test(priority = 6)
	public void validateReset() { 
		new_test=extent.createTest("verify reset");

		WebElement reset = driver.findElement(By.id("resetBtn")); 

		if(reset!= null) {
			new_test.pass("reset button is displayed");
			logger.info("Test Passed");
		}




	}
	@Test(priority=7)
	public void modulAdmin() throws InterruptedException {
		Thread.sleep(2000);
		new_test=extent.createTest("verify admin");
		WebElement admin = driver.findElement(By.id(cred.getAdmin()));
		admin.click();

		String url = driver.getCurrentUrl();

		if(url.contains("admin/viewSystemUsers")) {
			new_test.pass("admin/viewSystemUsers is displayed");
			logger.info("Test passed");
		}

	}
	@Test(priority=8)
	public  void adduser() throws InterruptedException {
		Thread.sleep(2000);
		new_test=extent.createTest("verify add user");
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
		Thread.sleep(2000);

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
		driver.findElement(By.name("searchSystemUser[userName]")).sendKeys(inp.addusername);
		Thread.sleep(2000);
		driver.findElement(By.id("searchBtn")).click();

		WebElement user_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[2]/a"));


		if (user_table.getText().equalsIgnoreCase(inp.addusername)) {

			rs = true;
			System.out.println( "Usernames are matching");
		}
		else {

			rs = false;
			System.out.println( "Usernames are matching");
		}
		assertTrue(rs);
		new_test.pass("usernames are matching");
	}





	@Test(priority = 11)

	public void validate_employeeName() throws InterruptedException {
		Thread.sleep(2000);
		new_test=extent.createTest("validate employee");



		WebElement emp_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[4]"));


		if (emp_table.getText().equalsIgnoreCase("Alice Duval")) {

			System.out.println( "employee names are matching");
		}
		else {

			System.out.println("employee names are NOT matching"); 
		}

	}

	@Test(priority = 12)

	public void validate_status() throws InterruptedException {

		Thread.sleep(2000);
		new_test=extent.createTest("validate status");

		WebElement status_table = driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr/td[5]"));


		if (status_table.getText().equalsIgnoreCase(inp.status)) {

			System.out.println("is enabled");
		}
		else {

			System.out.println("is not enabled");
		}

	}	
	@Test (priority=13)
	public void delete_record() throws InterruptedException {
		Thread.sleep(2000);
		new_test=extent.createTest("delete user");

		WebElement check_box = driver.findElement(By.xpath("//*[@id=\"ohrmList_chkSelectAll\"]"));
		Thread.sleep(2000);
		check_box.click();

		driver.findElement(By.id("btnDelete")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("dialogDeleteBtn")).click();

	}

	@Test (priority=14)
	public void search_deleted() throws InterruptedException {
		Thread.sleep(2000);
		new_test=extent.createTest("search deleted user");
		driver.findElement(By.name("searchSystemUser[userName]")).clear();
		driver.findElement(By.name("searchSystemUser[userName]")).sendKeys(inp.empname);

		driver.findElement(By.id("searchBtn")).click();


	}
	@Test (priority=15)
	public void logout() throws InterruptedException {

		Thread.sleep(2000);
		new_test=extent.createTest("verify log out");

		driver.findElement(By.id("welcome")).click();
		Thread.sleep(2000);
		driver.findElement(By.linkText("Logout")).click();
	}

	@Test(priority = 16)

	public void landingPage() throws InterruptedException {

		Thread.sleep(2000);
		new_test=extent.createTest("url validade");

		if (driver.getCurrentUrl().contains("login")){

			System.out.println("url contains login");  
		}
		else {
			System.out.println("url does not contain login"); 
		}

	}

	@Test(priority = 17)

	public void validate_url_landingPage2() throws InterruptedException {

		Thread.sleep(2000);
		new_test=extent.createTest("validade url");

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











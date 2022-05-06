package BrowserTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
public class TestNGTodo{
    public String username = "indu.devendranmondia";
    public String accesskey = "vtNeuXqUuhlOuJMMTxDrYpNoNVPWlZ62g5HNzsx0KKsGTfoX4B";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;
    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "70.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "LambdaTestSampleApp-Windows-Chrome");
        capabilities.setCapability("name", "LambdaTestJavaSample-Windows-Chrome");
        capabilities.setCapability("visual",true);

//        capabilities.setCapability("build", "MacOS Monterey-Test");
//        capabilities.setCapability("name", "MacOS Monterey-Test");
//        capabilities.setCapability("platform", "MacOS Monterey");
//        capabilities.setCapability("browserName", "Firefox");
//        capabilities.setCapability("version","99.0");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void test1() throws Exception {
        try {//Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            //Let's mark done first two items in the list.
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            // Let's add an item in the list.
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
            // Let's check that the item we added is added in the list.
            String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void Test2() throws Exception {
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.id("txtUsername")).click();
        driver.findElement(By.id("txtUsername")).sendKeys("Admin");
        driver.findElement(By.id("txtPassword")).click();
        driver.findElement(By.id("txtPassword")).sendKeys("admin123");
        driver.findElement(By.id("btnLogin")).click();
        driver.findElement(By.xpath("//a[@id='menu_directory_viewDirectory']/b")).click();
        driver.findElement(By.xpath("//a[@id='menu_pim_viewMyDetails']/b")).click();
        driver.findElement(By.linkText("Contact Details")).click();
        driver.findElement(By.id("welcome")).click();
        driver.findElement(By.linkText("Logout")).click();
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }
}

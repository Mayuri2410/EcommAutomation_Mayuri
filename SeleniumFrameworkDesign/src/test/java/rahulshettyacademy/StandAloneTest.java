package rahulshettyacademy;

import java.time.Duration;

import java.util.List;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(7));
		driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("mayurisamrit1990@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Daman@123");
        driver.findElement(By.id("login")).click();
        driver.manage().window().maximize();
        //Adding second product
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
       // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
  //      wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
       List<WebElement>products = driver.findElements(By.cssSelector(".mb-3"));
    
      WebElement prod= products.stream().filter(product->
      product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
     // prod.findElement(By.cssSelector(".card-body button :last-of-type")).click();
      prod.findElement(By.xpath("//h5[b[text()='ADIDAS ORIGINAL']]/following-sibling::button[contains(text(),'Add To Cart')]")).click();
     
    //Verifying if the product got added in cart or not
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
   List<WebElement> cartProduct= driver.findElements(By.cssSelector(".cartSection h3"));
 //   Boolean match=cartProduct.stream().anyMatch(cartProducts->cartProducts.getText().equalsIgnoreCase(productName));
     // Assert.assertTrue(match);
    
      driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
 driver.findElement(By.cssSelector(".totalRow button")).click();
 Actions a = new Actions(driver);
 a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

 driver.findElement(By.xpath("//span[text()=' India']")).click();
 Thread.sleep(15000);

 JavascriptExecutor js = (JavascriptExecutor) driver;

 js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
  Thread.sleep(10000);
 //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Place Order ')]")));

 driver.findElement(By.xpath("//a[contains(text(),'Place Order ')]")).click();

 String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


 
	}

}

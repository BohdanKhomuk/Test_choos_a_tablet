import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class RozetkaTest {

    public static EventFiringWebDriver eventDriver;

    public void pressOnXpath(String attribute) {
        (new WebDriverWait(eventDriver, 10000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(attribute)));
        eventDriver.findElement(By.xpath(attribute)).click();
    }

    public void pressOnText(String attribute) {
        (new WebDriverWait(eventDriver, 10000)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(attribute)));
        eventDriver.findElement(By.linkText(attribute)).click();
    }

    public void userDelay(int time) {
        try {Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace(); }
    }

    @BeforeClass
    public static void main() {
        System.setProperty("java.net.preferIPv4Stack", "true");
        String browser = new File(RozetkaTest.class.getResource("/chromedriver.exe").getFile()).getPath();
        System.setProperty("webdriver.chrome.driver", browser);
        eventDriver = new EventFiringWebDriver(new ChromeDriver( ));
        EventHandler handler = new EventHandler();
        eventDriver.manage().window().maximize();
        eventDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        eventDriver.register( handler );
        eventDriver.get("https://rozetka.com.ua/");
    }

    @Test
    public void userLogin() throws Exception {
        userDelay(1000);
        pressOnXpath( "//li[@id = '3361']/a[@name='fat_menu_link']" );
        pressOnText( "Планшеты" );
        pressOnText( "Больше 10.1" );
    }

    @AfterClass
    public static void tearDown() {
        eventDriver.quit();
    }
}

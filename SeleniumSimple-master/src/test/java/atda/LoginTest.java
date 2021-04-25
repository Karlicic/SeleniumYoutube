package atda;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import po.LoginPage;
import po.ProductPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }



    //Поради безбедностни причини тука е запишана невалидна лозинка
    //Со соодветната лозинка, тестот поминува
    @Test
    public void shouldLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("marta.karlicic@yahoo.com", "password");
        assertTrue(new ProductPage(driver).isLoaded());
    }

    @Test
    public void canNotLoginWithInvalidCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("user_invalid", "password_invalid");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The email or mobile number you entered isn’t connected to an account. Find your account and log in.");
    }

    @Test
    public void canNotLoginWithEmptyUserName() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "secret_sauce");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "The email or mobile number you entered isn’t connected to an account. Find your account and log in.");

    }



    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Downloads\\SeleniumSimple-master\\SeleniumSimple-master\\src\\main\\resources\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}

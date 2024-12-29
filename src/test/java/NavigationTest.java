import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import models.ResultModel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.ContactUs;
import pageObjects.Meli.DetailsPage;
import pageObjects.Meli.HomePage;
import pageObjects.Meli.ResultPage;
import utils.Driver.DriverFactory;
import utils.Driver.DriverManager;
import utils.TestResultLoggerExtension;

import java.time.Duration;
import java.time.Instant;


import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestResultLoggerExtension.class)
public class NavigationTest {

    private static final String DEFAULT_BROWSER = "chrome";

    @BeforeEach
    public void setup(){
        WebDriver driver = DriverFactory.valueOf(DEFAULT_BROWSER.toUpperCase()).createDriver();
        DriverManager.setDriver(driver);
        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    @Description("Login to facebook")
    public void navigateToFacebook() throws InterruptedException {
        DriverManager.getDriver().get("https://www.facebook.com/");

        DriverManager.getDriver().findElement(By.cssSelector("input#email")).sendKeys("nealachito");
        DriverManager.getDriver().findElement(By.cssSelector("input#pass")).sendKeys("Jesusdavid20*");
        DriverManager.getDriver().findElement(By.cssSelector("button[name='login']")).click();

        Thread.sleep(10000);
    }

    @Test
    @Description("navigate to mercado libre colombia")
    public void navigateToMeli(){
        DriverManager.getDriver().get("https://www.mercadolibre.com/");

        DriverManager.getDriver().findElement(By.xpath("//nav/ul/li[contains(.,\"Colombia\")]")).click();

        String currentUrl = DriverManager.getDriver().getCurrentUrl();

        assertTrue(currentUrl.contains(".com.co"));

    }

    @Test
    @Disabled
    public void workingWithSelects() throws InterruptedException {

        DriverManager.getDriver().get("http://www.automationpractice.pl/index.php?id_category=3&controller=category");

        WebElement mainSelect = DriverManager.getDriver().findElement(By.cssSelector("select#selectProductSort"));

        Select selectWeb = new Select(mainSelect);

        selectWeb.selectByIndex(2);

        Thread.sleep(2000);

        mainSelect = DriverManager.getDriver().findElement(By.cssSelector("select#selectProductSort"));

        selectWeb = new Select(mainSelect);

        selectWeb.selectByIndex(1);

        Thread.sleep(2000);

        mainSelect = DriverManager.getDriver().findElement(By.cssSelector("select#selectProductSort"));

        selectWeb = new Select(mainSelect);

        selectWeb.selectByVisibleText("In stock");

        Thread.sleep(2000);

        mainSelect = DriverManager.getDriver().findElement(By.cssSelector("select#selectProductSort"));

        selectWeb = new Select(mainSelect);

        selectWeb.selectByValue("name:desc");

        Thread.sleep(2000);

    }

    @Test
    @Disabled
    public void implicitWaitExample(){
        DriverManager.getDriver().get("https://www.google.com/");
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(12));

        Instant start = Instant.now();

        try {
            DriverManager.getDriver().findElement(By.id("NoHayNah"));
        }catch (Exception exception){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start,end);
            System.out.println("*********************");
            System.out.println("Time: "+ timeElapsed.getSeconds()+ " seconds");
            System.out.println("*********************");
        }
    }

    @Test
    @Disabled
    public void explicitWaitExample(){
        DriverManager.getDriver().get("https://www.google.com/");
        DriverManager.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Instant start = Instant.now();

        try {
            DriverManager.getDriver().findElement(By.id("NoHayNah"));
        }catch (Exception exception){
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start,end);
            System.out.println("*********************");
            System.out.println("Time: "+ timeElapsed.getSeconds()+ " seconds");
            System.out.println("*********************");
        }

        Instant startExp = Instant.now();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(7));

        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("TampocoExiste")));
        }catch (Exception exception){
            Instant endExp = Instant.now();
            Duration timeElapsed = Duration.between(startExp,endExp);
            System.out.println("*********************");
            System.out.println("Time 2: "+ timeElapsed.getSeconds()+ " seconds");
            System.out.println("*********************");
        }

        Instant startExp2 = Instant.now();
        WebDriverWait wait2 = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(12));

        try {
            wait2.until(ExpectedConditions.presenceOfElementLocated(By.id("hplogo")));
            System.out.println("Estoy aquí");
        }catch (Exception exception){
            Instant endExp2 = Instant.now();
            Duration timeElapsed = Duration.between(startExp2,endExp2);
            System.out.println("*********************");
            System.out.println("Time 3: "+ timeElapsed.getSeconds()+ " seconds");
            System.out.println("*********************");
        }

    }

    @Test
    @Description("fill to form automation practice")
    public void practicedTest(){
        DriverManager.getDriver().get("http://www.automationpractice.pl/index.php?controller=contact");

        ContactUs contactUs = new ContactUs();
        contactUs.writeSubjectHeading("Webmaster");
        contactUs.writeEmail("nealdop14@gmail.com");
        contactUs.writeOrdenEmitida("Orden Emitida");
        contactUs.writeMensaje("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        contactUs.clickSubmitButton();
        contactUs.assertionSuccess("Your message has been successfully sent to our tea");
    }

    @Test
    @Description("searching in meli laptop ryzen 9")
    public void getElementTest(){
        DriverManager.getDriver().get("https://www.mercadolibre.com.do/#from=homecom");


        HomePage homePage = new HomePage();

        homePage.writeSearcher("laptop ryzen 9");
        homePage.search();

        ResultPage resultPage = new ResultPage();

        ResultModel expectedResultModel = resultPage.clickOnRandomItem();

        DetailsPage detailsPage = new DetailsPage();
        ResultModel actualResultModel = detailsPage.getDetailInformation();

        Assertions.assertAll(
                ()->Assertions.assertEquals(expectedResultModel.getPrice(),actualResultModel.getPrice()),
                ()->Assertions.assertEquals(expectedResultModel.getName(),actualResultModel.getName())
        );
    }

    @Test
    @Description("searching in meli iphone 13")
    public void getElementTest2(){
        DriverManager.getDriver().get("https://www.mercadolibre.com.do/#from=homecom");

        HomePage homePage = new HomePage();

        homePage.writeSearcher("Iphone 13");
        homePage.search();

        ResultPage resultPage = new ResultPage();

        ResultModel expectedResultModel = resultPage.clickOnRandomItem();

        DetailsPage detailsPage = new DetailsPage();
        ResultModel actualResultModel = detailsPage.getDetailInformation();

        Assertions.assertAll(
                ()->Assertions.assertEquals(expectedResultModel.getPrice(),actualResultModel.getPrice()),
                ()->Assertions.assertEquals(expectedResultModel.getName(),actualResultModel.getName())
        );
    }
}

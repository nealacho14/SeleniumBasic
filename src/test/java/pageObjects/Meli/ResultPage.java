package pageObjects.Meli;

import models.ResultModel;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObjects.AbstractPageObject;
import utils.Driver.DriverManager;

import java.util.List;
import java.util.Random;

public class ResultPage extends AbstractPageObject {

    @FindBy(css = "ol[class*='ui-search-layout'] li" )
    private List<WebElement> elementos;

    private By price = By.cssSelector("span[class*='andes-money-amount__fraction']");

    private By title = By.cssSelector("h2 a");


    public ResultPage() {

    }

    public WebElement getRandomResults(){
        Random random = new Random();
        WebElement randomElement = elementos.get(random.nextInt(elementos.size()));
        return randomElement;
    }

    public ResultModel clickOnRandomItem(){
        WebElement randomElement = getRandomResults();

        ResultModel expectedResultModel = new ResultModel();
        expectedResultModel.setName(randomElement.findElement(title).getText());
        expectedResultModel.setPrice(randomElement.findElement(price).getText());

        JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
        executor.executeScript("arguments[0].scrollIntoView();", randomElement);
        System.out.println(randomElement.findElement(title).getText());
        System.out.println(randomElement.findElement(price).getText());
        randomElement.findElement(title).click();
        return expectedResultModel;
    }
}

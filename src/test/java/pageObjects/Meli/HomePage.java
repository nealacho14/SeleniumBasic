package pageObjects.Meli;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageObjects.AbstractPageObject;

public class HomePage extends AbstractPageObject {

    @FindBy(id="cb1-edit")
    private WebElement searchbox;

    public HomePage() {
    }

    public void writeSearcher(String busqueda){
        searchbox.sendKeys(busqueda);
    }

    public void search(){
        searchbox.sendKeys(Keys.ENTER);
    }

}

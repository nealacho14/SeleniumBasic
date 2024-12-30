package pageObjects;

import org.openqa.selenium.support.PageFactory;
import utils.Driver.DriverManager;

public class AbstractPageObject {

    protected AbstractPageObject(){
        PageFactory.initElements(DriverManager.getDriver(), this);
    }
}

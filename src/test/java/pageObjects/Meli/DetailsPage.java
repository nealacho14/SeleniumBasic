package pageObjects.Meli;

import models.ResultModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage {

    @FindBy(css = "h1[class*='ui-pdp-title']")
    WebElement titleDetail;

    @FindBy(css = "div[class='ui-pdp-price__second-line'] span[class*='andes-money-amount__fraction']")
    WebElement priceDetail;

    public DetailsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }

    public ResultModel getDetailInformation(){
        ResultModel resultModel = new ResultModel();
        resultModel.setName(titleDetail.getText());
        resultModel.setPrice(priceDetail.getText());
        return resultModel;
    }

}

package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver.DriverManager;
import utils.Photographer;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContactUs extends AbstractPageObject {

    private Wait wait;
    private Photographer photographer;

    @FindBy(css = "#id_contact")
    private WebElement subjectSelectLoc;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailLoc;

    @FindBy(xpath = "//input[@id='id_order']")
    private WebElement ordenEmitidaLoc;

    @FindBy(css = "#message")
    private WebElement mensajeLongLoc;

    @FindBy(xpath = "//button[@id='submitMessage']")
    private WebElement submitButtonLoc;

    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement successMessage;

    public ContactUs() {
        wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        photographer = new Photographer();
    }

    @Step("Seleccionando combo heading")
    public void writeSubjectHeading(String subject){
        photographer.takePhoto();
        Select selectWeb = new Select(subjectSelectLoc);
        selectWeb.selectByVisibleText(subject);
    }

    @Step("Escribiendo email")
    public void writeEmail(String emailIn){
        photographer.takePhoto();
        emailLoc.sendKeys(emailIn);
    }

    @Step("Escribiendo orden emitida")
    public void writeOrdenEmitida(String ordenIn){
        photographer.takePhoto();
        ordenEmitidaLoc.sendKeys(ordenIn);
    }

    @Step("Escribiendo mensaje")
    public void writeMensaje(String mensajeIn){
        photographer.takePhoto();
        mensajeLongLoc.sendKeys(mensajeIn);
    }

    @Step("Enviando información formulario")
    public void clickSubmitButton(){
        photographer.takePhoto();
        submitButtonLoc.click();
    }

    @Step("Mensaje de exito")
    public void assertionSuccess(String message){
        photographer.takePhoto();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='alert alert-success']")));
        assertTrue(successMessage.getText().contains(message));
    }
}

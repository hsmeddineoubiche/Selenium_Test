import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.Select;


class SettingsPageTest extends BasePage {



    private final By displayName = By.xpath("//input[contains(@id,'displayNameInput')]");
    private final By bioTextArea = By.xpath("//textarea[contains(@id,'tagLineInput')]");
    private final By checkBox = By.xpath("//div[contains(@class,'checkbox-group optOutCheckbox')]");
    private final By saveButton = By.xpath("//span[contains(text(),'Save Changes')]");

    public SettingsPageTest(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.allrecipes.com/account/profile#/public-profile");
    }

 
    public void sendFormAsUser(){

        WebDriverWait wait = new WebDriverWait(driver, 10); 
        WebElement displayNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(displayName));
        WebElement bioTextAreaElement = wait.until(ExpectedConditions.visibilityOfElementLocated(bioTextArea));
        WebElement checkBoxElement = wait.until(ExpectedConditions.visibilityOfElementLocated(checkBox));
        displayNameElement.click();
        displayNameElement.clear();
        displayNameElement.sendKeys("Houssameddine");
        bioTextAreaElement.click();
        bioTextAreaElement.clear();
        bioTextAreaElement.sendKeys("Hello i'm doing Selenium Big Task and filling a textarea");
        checkBoxElement.click();
        this.waitAndReturnElement(this.saveButton).click();
    }

}




import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import java.security.SecureRandom;


class LoginPageTest extends BasePage {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

	//from config
	private final String email = ConfigReader.getProperty("email");
	private final String password = ConfigReader.getProperty("password");
	
	//for page load & before fields
	private final By logInWithEmail = By.xpath("//span[contains(text(), 'Log in with Email')]");
	
	//input fields
	private final By usernameField = By.id("username");	
	private final By passwordField = By.id("password");		


	//to click login button
	private final By loginButton = By.id("kc-login");

    private final By joinLocator = By.xpath("//a[contains(text(),'Join now')]");
    private final By signupWithEmail = By.xpath("//span[contains(text(), 'Sign up with Email')]");
    private final By emailAdressLocator = By.xpath("//*[@id='email']");
    private final By emailAdressInputLocator = By.xpath("//input[contains(@class, 'js-username')]");
    private final By passwordInputLocator = By.xpath("//*[@id='password']");
    private final By registerButtonLocator = By.xpath("//input[contains(@class,'js-button-submit')]");
    private final By validationMessageLocator = By.xpath("/html/body/div/div/div/aside/div[1]/div[2]/div/div/ul/li");
    private final By activationLocator = By.xpath("//a[contains(text(),'Activate my account')]");


    public LoginPageTest(WebDriver driver) {
        super(driver);
        this.driver.get(ConfigReader.getProperty("loginPage"));

    }

    public DashboardPageTest loginIn(){
        
        try {
            Thread.sleep(1500);
            this.waitAndReturnElement(logInWithEmail).click();
            Thread.sleep(1500);
            this.waitAndReturnElement(usernameField).click();
            Thread.sleep(1500);
            this.waitAndReturnElement(usernameField).sendKeys(email);
            Thread.sleep(1500);
            this.waitAndReturnElement(passwordField).click();
            Thread.sleep(1500);
            this.waitAndReturnElement(passwordField).sendKeys(password);
            Thread.sleep(1500);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.waitAndReturnElement(loginButton).click();

        return new DashboardPageTest(this.driver);
    }
   

    // E-mail checking (eg. Registration with activation e-mail)

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder(14);
        for (int i = 0; i < 14; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public DashboardPageTest SignUpTest() {

        //it depends on the adress email if the website accept it or no 
        //the credentials are printed to the terminal we can use them to check 

        try{
        
        WebElement joinLocatorElement = this.waitAndReturnElement(this.joinLocator);
        joinLocatorElement.click();
        
        driver.navigate().to("https://temp-mail.io/en");
        Thread.sleep(5500);
        WebElement emailAdressLocatorElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.emailAdressLocator));
        String emailForSignUp = emailAdressLocatorElement.getAttribute("value");
        String passwordForSignUp = generateRandomString();
        System.out.println(emailForSignUp);
        System.out.println(passwordForSignUp);
        driver.navigate().back();
        
        WebElement signupWithEmailElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.signupWithEmail));
        signupWithEmailElement.click();
        
        this.waitAndReturnElement(emailAdressInputLocator).click();
        Thread.sleep(1500);
        this.waitAndReturnElement(emailAdressInputLocator).sendKeys(emailForSignUp);

        this.waitAndReturnElement(passwordInputLocator).click();
        Thread.sleep(1500);
        this.waitAndReturnElement(passwordInputLocator).sendKeys(passwordForSignUp);
        Thread.sleep(1500);
        this.waitAndReturnElement(registerButtonLocator).click();

        Thread.sleep(3000);
        driver.navigate().to("https://temp-mail.io/en");

        WebElement validationMessageLocatorElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.validationMessageLocator));
        validationMessageLocatorElement.click();
        Thread.sleep(1500);
        WebElement activationLocatorElement = wait.until(ExpectedConditions.presenceOfElementLocated(this.activationLocator));
        activationLocatorElement.click();
        Thread.sleep(2000);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new DashboardPageTest(this.driver);

    }
    

}

import static org.junit.Assert.*;
import org.junit.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;


public class Website {
    private WebDriver driver;
    
	private final By logInWithEmail = By.xpath("//span[contains(text(), 'Log in with Email')]");
	    private final By profileDropDown = By.xpath("/html/body/header/div[1]/div[3]/ul/li[3]/button/span");
       private final By personalInfoLocator = By.xpath("//h1[contains(text(), 'Personal Info')]");
       private final By publicProfilesettings = By.xpath("//div[contains(@class,'layoutHeader__titleContainer')]/child::h1");
           private By newsPageTitle = By.xpath("//h1[contains(text(),'Food News and Trends')]");
       
    @Before
    public void setUp() throws MalformedURLException{
        ChromeOptions options = new ChromeOptions();
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();


        int timeoutSeconds = 30; 
        driver.manage().timeouts().implicitlyWait(timeoutSeconds, TimeUnit.SECONDS);

        // WebDriver configuration (modify something in the browser options)
        options.addArguments("--user-agent=H5HIOY");
        options.addArguments("--disable-extensions");
        
    
    }

    @Test
    public void Website() {
        try{


            HomePage homepage = new HomePage(this.driver);
            LoginPageTest loginPage = homepage.clickLoginButton();
            Assert.assertTrue(loginPage.pageLoadCheck(logInWithEmail));        
            Thread.sleep(1500);

            // Login test
            
            DashboardPageTest dashboardPage = loginPage.loginIn();
            Thread.sleep(1500);
            loginPage = homepage.clickLoginButton();
            Thread.sleep(1500);
            
            driver.navigate().back();
            Assert.assertTrue(dashboardPage.pageLoadCheck(profileDropDown));
            Thread.sleep(2000);
            ProfilePageTest profilePage = dashboardPage.clickDashboardToRedirect();
            Thread.sleep(3000);
            Assert.assertTrue(profilePage.pageLoadCheck(personalInfoLocator));
        		
            // Reading page title 
            Assert.assertEquals("Profile | Allrecipes", profilePage.readPageTitle());
            
            SettingsPageTest publicSettingsPage = profilePage.settings();
            Thread.sleep(2000);
            Assert.assertTrue(publicSettingsPage.pageLoadCheck(publicProfilesettings));
            publicSettingsPage.sendFormAsUser();
            Thread.sleep(2000);
            HomePage homepage2 = dashboardPage.logout();
            Thread.sleep(1000);
            StaticPage staticpage = homepage2.clickStaticPageToRedirect();
            Thread.sleep(1000);
            Assert.assertTrue(staticpage.pageLoadCheck(newsPageTitle));
            Thread.sleep(500);


            String[] multiplePages = {
                "https://www.allrecipes.com/recipes/17562/dinner/",
                "https://www.allrecipes.com/recipes/85/holidays-and-events/",
                "https://www.allrecipes.com/kitchen-tips/",
            };

            String[] titles = {
                "Dinner Recipes",
                "Holidays and Events Recipes",
                "Kitchen Tips"
            };
                        
            for (int i = 0; i < multiplePages.length; i++) {
              driver.get(multiplePages[i]);
              Assert.assertEquals(titles[i], driver.getTitle());
              driver.navigate().back();
            }
            Thread.sleep(1000);
            // History test (browser back button)
 
            driver.navigate().to("https://google.com/");
            Thread.sleep(1000);
            driver.navigate().back();
            Thread.sleep(1000);

            // E-mail checking (eg. Registration with activation e-mail)
            loginPage = homepage.clickLoginButton();
            Thread.sleep(4000);
            loginPage.SignUpTest();

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }   

    @After
    public void close() {
        if (this.driver != null) {
            this.driver.quit();
        }
    }   
}

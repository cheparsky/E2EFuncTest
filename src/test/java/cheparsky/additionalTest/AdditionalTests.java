package cheparsky.additionalTest;

import cheparsky.utilities.MyDriver;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;


public class AdditionalTests {
    public WebDriver wd;

    //@Test
    public void TestOfWebDriver(){
        wd = MyDriver.runWDriver("Chrome");
        wd.get("https://www.google.com");
        Assert.assertEquals("Google",wd.getTitle());
        wd.quit();
    }


}

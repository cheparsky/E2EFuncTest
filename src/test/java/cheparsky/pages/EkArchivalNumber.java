package cheparsky.pages;

//connect all necessary standard libraries

import cheparsky.testRunner.RunAutoTest;
import cheparsky.utilities.MyDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkArchivalNumber {

    //add all the necessary variables
    private static WebDriver wd;
    private static JavascriptExecutor jse;

    //add WebElementy
    @FindBy(xpath = "//*[@id='all']/section/div[2]/div/div/p/a")
    private static WebElement naPokazWiecejB;

    @FindBy(xpath = ".//*[@id='all']/section/div[2]/div/div/div/ul/li[21]/p[1]/a[1]/img")
    private static WebElement naPWOkladkaB;

    @FindBy(xpath = ".//*[@id='all']/section/div[1]/div/div/span[2]")
    private static WebElement naWybierzRokB;

    @FindBy(xpath = ".//*[@id='all']/section/div[1]/div/div/div/div/div/ul/li[2]/label")
    private static WebElement naRokB;

    // We define the functions that are responsible for checking archive numbers - process
    public static void runNumeryArchiwalne() {
        String errMesage = "//*******NUMERY ARCHIWALNE*******//";
        wd = MyDriver.wd;
        EkArchivalNumber.setPageObject();
        MyDriver.addErrList(errMesage);
        jse = (JavascriptExecutor) wd;
        wd.get(RunAutoTest.mainPage);
        EkMain.kpProductTO(EkMain.sgRzepaB, 27, "http://www.e-kiosk.pl/numer", "Wydanie(Rzepa)");
        EkProductPage.kpPokazWszystkieB();
        if (MyDriver.ifWWWAreEquals(RunAutoTest.archivalNumberPartPage)) {
            EkArchivalNumber.naPokazWiecejB();
            jse.executeScript("scroll(0,0)");
            EkArchivalNumber.naWybierzRokB();
            EkArchivalNumber.naRokB();
        }
        MyDriver.deleteErrList(errMesage);
    }

    // define actions that are responsible for clicking the button pokaz wiecej
    public static void naPokazWiecejB() {
        MyDriver.buttonElement(naPokazWiecejB,
                naPWOkladkaB,
                "Widok numerów archiwalnych: przycisk 'pokaż więcej' jest klikalny",
                "Widok numerów archiwalnych: przycisk 'pokaż więcej' jest NIE klikalny",
                "Widok numerów archiwalnych nie zawiera przycisku 'pokaż więcej'.");
    }

    // We define actions that are responsible for developing a list with a list of years
    public static void naWybierzRokB() {
        MyDriver.buttonElement(naWybierzRokB,
                naRokB,
                "Widok numerów archiwalnych: wypadająca lista jest klikalna.",
                "Widok numerów archiwalnych: wypadająca lista jest NIE klikalna.",
                "Widok numerów archiwalnych nie zawiera wypadającej listy.");
    }

    // We define the action, which is responsible for selecting the year in the drop-down list
    public static void naRokB() {
        MyDriver.buttonLink(naRokB,
                68,
                "http://www.e-kiosk.pl/index.php?page=titleissues&id_title=4705&year=",
                "Widok numerów archiwalnych: filtrowanie po roku działa.",
                "Widok numerów archiwalnych: rok na wypadającej liście jest NIE klikalny.",
                "Widok numerów archiwalnych nie zawiera roku na wypadającej listy.");
    }

    // define the method that is responsible for adding Page Object Pattern
    public static void setPageObject() {
        PageFactory.initElements(wd, new EkArchivalNumber());
    }
}

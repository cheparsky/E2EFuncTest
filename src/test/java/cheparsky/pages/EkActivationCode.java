package cheparsky.pages;

//connect all necessary standard libraries

import cheparsky.testRunner.RunAutoTest;
import cheparsky.utilities.MyDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//define the name of the class - it must be the same name as the file
public class EkActivationCode {

    //add all the necessary variables
    private static WebDriver wd;

    //add WebElementy
    @FindBy(id = "codeActivate")
    private static WebElement kaKodI;

    @FindBy(xpath = ".//*[@id='all']/section/div/form/fieldset/button")
    private static WebElement kaAktywujB;

    @FindBy(xpath = ".//*[@id='activate-list']/div/form/div/button")
    private static WebElement kaPotwierdzB;

    @FindBy(className = "icon-warning")
    private static WebElement kaIconWarnongL;

    // We define the functions that are responsible for checking activation codes - process
    public static void runKodAktywacyjny() {
        String errMesage = "//*******KOD AKTYWACYJNY*******//";
        wd = MyDriver.wd;
        EkActivationCode.setPageObject();
        MyDriver.addErrList(errMesage);
        wd.get(RunAutoTest.mainPage);
        EkMain.sgKodyAktywacyjneB();
        if (MyDriver.ifWWWAreEquals(RunAutoTest.activationCodePage)) {
            EkActivationCode.kaKodI();
            EkActivationCode.kaAktywujB(57, "https://www.e-kiosk.pl/secure/index.php?page=login&redir=");
            if (MyDriver.ifWWWAreEquals("https://www.e-kiosk.pl/secure/index.php?page=login&redir=")) {
                EkLogIn.slLogowanie(43, "http://www.e-kiosk.pl/index.php?page=coupon");
                if (MyDriver.ifWWWAreEquals("http://www.e-kiosk.pl/index.php?page=coupon&cmd=activate&code=E5FIUUQ3X6")) {
                    EkActivationCode.kaPotwierdzB();
                } else if (MyDriver.ifWWWAreEquals("http://www.e-kiosk.pl/index.php?page=coupon")) {
                    EkActivationCode.kaKodI();
                    EkActivationCode.kaAktywujB(56, "http://www.e-kiosk.pl/index.php?page=coupon&cmd=activate");
                    if (MyDriver.ifWWWAreEquals("http://www.e-kiosk.pl/index.php?page=coupon&cmd=activate")) {
                        EkActivationCode.kaPotwierdzB();
                    }
                }
            }
        }
        MyDriver.deleteErrList(errMesage);
    }

    // We define the action that is responsible for entering kodu aktywacyjnego to inputu kod
    public static void kaKodI() {
        MyDriver.InputData(kaKodI,
                "E5FIUUQ3X6",
                "Strona kodu aktywacyjnego: input 'Kod' nie jest edytowalny.",
                "Strona kodu aktywacyjnego nie zawiera inputu 'Kod'.");

    }

    // define actions that are responsible for clicking the button aktywuj
    public static void kaAktywujB(int urlLenght, String url) {
        MyDriver.buttonLink(kaAktywujB,
                urlLenght,
                url,
                "Strona kodu aktywacyjnego: przycisku 'aktywuj' jest klikalny.",
                "Strona kodu aktywacyjnego: przycisku 'aktywuj' jest NIE klikalny.",
                "Strona kodu aktywacyjnego nie zawiera przycisku 'aktywuj'.");

    }

    // define actions that are responsible for clicking the button potwierdź
    public static void kaPotwierdzB() {
        MyDriver.buttonElement(kaPotwierdzB,
                kaIconWarnongL,
                "Strona kodu aktywacyjnego: przycisku 'potwierdz' jest klikalny.",
                "Strona kodu aktywacyjnego: przycisku 'potwierdz' jest NIE klikalny.",
                "Strona kodu aktywacyjnego nie zawiera przycisku 'potwierdz'.");

    }

    // define the method that is responsible for adding Page Object Pattern
    public static void setPageObject() {
        PageFactory.initElements(wd, new EkActivationCode());
    }

}